package org.egov.asset.web.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.egov.asset.config.ApplicationProperties;
import org.egov.asset.contract.AssetRequest;
import org.egov.asset.contract.DepreciationRequest;
import org.egov.asset.contract.DisposalRequest;
import org.egov.asset.contract.RevaluationRequest;
import org.egov.asset.model.Asset;
import org.egov.asset.model.AssetCategory;
import org.egov.asset.model.AssetCriteria;
import org.egov.asset.model.AssetCurrentValue;
import org.egov.asset.model.AssetStatus;
import org.egov.asset.model.DepreciationCriteria;
import org.egov.asset.model.Disposal;
import org.egov.asset.model.DisposalCriteria;
import org.egov.asset.model.Revaluation;
import org.egov.asset.model.RevaluationCriteria;
import org.egov.asset.model.YearWiseDepreciation;
import org.egov.asset.model.enums.AssetCategoryType;
import org.egov.asset.model.enums.AssetConfigurationKeys;
import org.egov.asset.model.enums.AssetStatusObjectName;
import org.egov.asset.model.enums.Status;
import org.egov.asset.model.enums.TransactionType;
import org.egov.asset.model.enums.TypeOfChangeEnum;
import org.egov.asset.service.AssetCommonService;
import org.egov.asset.service.AssetConfigurationService;
import org.egov.asset.service.AssetMasterService;
import org.egov.asset.service.AssetService;
import org.egov.asset.service.CurrentValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AssetValidator {

    @Autowired
    private AssetCategoryValidator assetCategoryValidator;

    @Autowired
    private AssetService assetService;

    @Autowired
    private AssetMasterService assetMasterService;

    @Autowired
    private CurrentValueService currentValueService;

    @Autowired
    private AssetConfigurationService assetConfigurationService;

    @Autowired
    private AssetCommonService assetCommonService;

    @Autowired
    private ApplicationProperties applicationProperties;

    public void validateAsset(final AssetRequest assetRequest) {
        final AssetCategory assetCategory = findAssetCategory(assetRequest);
        final Asset asset = assetRequest.getAsset();
        if (asset.getEnableYearWiseDepreciation() != null
                && validateAssetCategoryForLand(assetCategory.getAssetCategoryType()))
            asset.setDepreciationRate(null);
        else
            validateYearWiseDepreciationRate(asset);
        if (Status.CAPITALIZED.toString().equals(asset.getStatus())) {
            final BigDecimal grossValue = asset.getGrossValue();
            if (grossValue == null)
                throw new RuntimeException("Asset Gross Value can not be null.");
            else if (grossValue.compareTo(BigDecimal.ONE) < 0)
                throw new RuntimeException("Asset Gross Value can not be less than 1.");
        }
    }

    public void validateYearWiseDepreciationRate(final Asset asset) {
        if (asset.getEnableYearWiseDepreciation() != null && asset.getEnableYearWiseDepreciation()) {
            final List<String> finacialYears = new ArrayList<String>();
            for (final YearWiseDepreciation ywd : asset.getYearWiseDepreciation()) {
                finacialYears.add(ywd.getFinancialYear());
                final Double depreciationRate = ywd.getDepreciationRate();
                assetCommonService.validateDepreciationRateValue(depreciationRate);
                ywd.setDepreciationRate(assetCommonService.getDepreciationRate(depreciationRate));
            }
            checkDuplicateFinancialYear(finacialYears);
        } else if (asset.getEnableYearWiseDepreciation() != null && !asset.getEnableYearWiseDepreciation()) {
            final Double depreciationRate = asset.getDepreciationRate();
            assetCommonService.validateDepreciationRateValue(asset.getDepreciationRate());
            asset.setDepreciationRate(assetCommonService.getDepreciationRate(depreciationRate));
        }

    }

    private void checkDuplicateFinancialYear(final List<String> finacialYears) {
        if (!finacialYears.isEmpty()) {
            final Iterator<String> itr = finacialYears.iterator();
            if (itr.hasNext()) {
                final String finYear = itr.next();
                while (itr.hasNext()) {
                    final String current = itr.next();
                    if (finYear.equalsIgnoreCase(current))
                        throw new RuntimeException("Can not contain duplicate financial years");
                }
            }
        }
    }

    public AssetCategory findAssetCategory(final AssetRequest assetRequest) {
        final AssetCategory assetCategory = assetRequest.getAsset().getAssetCategory();
        final List<AssetCategory> assetCategories = assetCategoryValidator.findByIdAndCode(assetCategory.getId(),
                assetCategory.getCode(), assetRequest.getAsset().getTenantId());

        if (assetCategories.isEmpty())
            throw new RuntimeException("Invalid asset category");
        else
            return assetCategories.get(0);
    }

    public void validateRevaluationCriteria(final RevaluationCriteria revaluationCriteria) {
        if (revaluationCriteria.getFromDate() == null && revaluationCriteria.getToDate() != null)
            throw new RuntimeException("Invalid Search! from date required");
        if (revaluationCriteria.getFromDate() != null && revaluationCriteria.getToDate() == null)
            throw new RuntimeException("Invalid Search! to date required");
        if (revaluationCriteria.getFromDate() != null && revaluationCriteria.getToDate() != null
                && revaluationCriteria.getToDate().compareTo(revaluationCriteria.getFromDate()) == -1)
            throw new RuntimeException("Invalid Search! to date should not be less than from date");
        if (revaluationCriteria.getFromDate() != null && revaluationCriteria.getToDate() != null
                && revaluationCriteria.getToDate().compareTo(revaluationCriteria.getFromDate()) == 0)
            throw new RuntimeException("Invalid Search! to date should not be equal to from date");
    }

    public void validateDisposalCriteria(final DisposalCriteria disposalCriteria) {
        if (disposalCriteria.getFromDate() == null && disposalCriteria.getToDate() != null)
            throw new RuntimeException("Invalid Search! from date required");
        if (disposalCriteria.getFromDate() != null && disposalCriteria.getToDate() == null)
            throw new RuntimeException("Invalid Search! to date required");

        if (disposalCriteria.getFromDate() != null && disposalCriteria.getToDate() != null
                && disposalCriteria.getToDate().compareTo(disposalCriteria.getFromDate()) == -1)
            throw new RuntimeException("Invalid Search! to date should not be less than from date");

        if (disposalCriteria.getFromDate() != null && disposalCriteria.getToDate() != null
                && disposalCriteria.getToDate().compareTo(disposalCriteria.getFromDate()) == 0)
            throw new RuntimeException("Invalid Search! to date should not be equal to from date");
    }

    public void validateDisposal(final DisposalRequest disposalRequest) {
        final Disposal disposal = disposalRequest.getDisposal();
        final String tenantId = disposal.getTenantId();
        final Asset asset = assetService.getAsset(tenantId, disposal.getAssetId(), disposalRequest.getRequestInfo());
        log.debug("Asset For Disposal :: " + asset);
        validateAssetForCapitalizedStatus(asset);
        if (StringUtils.isEmpty(disposal.getBuyerName()))
            throw new RuntimeException("Buyer Name should be present for disposing asset : " + asset.getName());

        if (StringUtils.isEmpty(disposal.getBuyerAddress()))
            throw new RuntimeException("Buyer Address should be present for disposing asset : " + asset.getName());

        if (StringUtils.isEmpty(disposal.getDisposalReason()))
            throw new RuntimeException("Disposal Reason should be present for disposing asset : " + asset.getName());

        if (disposal.getSaleValue() == null)
            throw new RuntimeException("Sale Value should be present for disposing asset : " + asset.getName());

        verifyPanCardAndAdhaarCardForAssetSale(disposal);
        if (getEnableYearWiseDepreciation(tenantId)) {
            validateAssetCategoryForVoucherGeneration(asset);

            if (asset.getAssetCategory() != null && asset.getAssetCategory().getAssetAccount() == null)
                throw new RuntimeException("Asset account should be present for disposing asset : " + asset.getName());

            if (disposal.getAssetSaleAccount() == null)
                throw new RuntimeException(
                        "Asset sale account should be present for asset disposal voucher generation");
        }
    }

    private void verifyPanCardAndAdhaarCardForAssetSale(final Disposal disposal) {
        final String adhaarcardNumber = disposal.getAadharCardNumber();
        final String pancardNumber = disposal.getPanCardNumber();
        final Pattern pattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");
        final Matcher matcher = pattern.matcher(pancardNumber);

        final boolean transactionTypeSaleCheck = TransactionType.SALE.compareTo(disposal.getTransactionType()) == 0;

        if (transactionTypeSaleCheck && StringUtils.isEmpty(adhaarcardNumber))
            throw new RuntimeException("Aadhar Card Number is necessary for asset sale");

        if (transactionTypeSaleCheck && !StringUtils.isEmpty(adhaarcardNumber) && adhaarcardNumber.length() < 12
                && !adhaarcardNumber.matches("\\d+"))
            throw new RuntimeException("Aadhar Card Number should be numeric and should have length 12");

        if (transactionTypeSaleCheck && StringUtils.isEmpty(pancardNumber))
            throw new RuntimeException("PAN Card Number is necessary for asset sale");

        if (transactionTypeSaleCheck && !StringUtils.isEmpty(pancardNumber) && !matcher.matches())
            throw new RuntimeException("PAN Card Number Should be in Format : ABCDE1234F");
    }

    private void validateAssetForCapitalizedStatus(final Asset asset) {
        final List<AssetStatus> assetStatus = assetMasterService.getStatuses(AssetStatusObjectName.ASSETMASTER,
                Status.CAPITALIZED, asset.getTenantId());
        log.debug("asset status ::" + assetStatus);
        if (!assetStatus.isEmpty()) {
            final String status = assetStatus.get(0).getStatusValues().get(0).getCode();
            if (!status.equals(asset.getStatus()))
                throw new RuntimeException("Status of Asset " + asset.getName()
                        + " Should be CAPITALIZED for Revaluation, Depreciation and Disposal/sale");
        } else
            throw new RuntimeException(
                    "Status of asset :" + asset.getName() + "doesn't exists for tenant id : " + asset.getTenantId());
    }

    public void validateRevaluation(final RevaluationRequest revaluationRequest) {
        final Revaluation revaluation = revaluationRequest.getRevaluation();
        final String tenantId = revaluation.getTenantId();
        final Asset asset = assetService.getAsset(tenantId, revaluation.getAssetId(),
                revaluationRequest.getRequestInfo());
        log.debug("Asset For Revaluation :: " + asset);
        validateAssetForCapitalizedStatus(asset);
        final boolean enableVoucherGeneration = getEnableYearWiseDepreciation(tenantId);
        if (enableVoucherGeneration) {
            validateAssetCategoryForVoucherGeneration(asset);
            validateFund(revaluation.getFund());
        }

        final TypeOfChangeEnum typeOfChange = validateRevaluationForTypeOfChange(revaluation, asset,
                enableVoucherGeneration);

        if (revaluation.getRevaluationAmount() == null)
            throw new RuntimeException(
                    "The amount by which the value is increased/decreased is necessary for revaluation");

        if (revaluation.getFunction() == null)
            throw new RuntimeException("Function from financials is necessary for asset revaluation");

        BigDecimal assetCurrentAmount = null;
        final Set<Long> ids = new HashSet<>();
        ids.add(revaluation.getAssetId());
        final List<AssetCurrentValue> assetCurrentValues = currentValueService
                .getCurrentValues(ids, tenantId, revaluationRequest.getRequestInfo()).getAssetCurrentValues();
        if (!assetCurrentValues.isEmpty())
            assetCurrentAmount = assetCurrentValues.get(0).getCurrentAmount();
        else if (asset.getAccumulatedDepreciation() != null)
            assetCurrentAmount = asset.getGrossValue().subtract(asset.getAccumulatedDepreciation());
        else
            assetCurrentAmount = asset.getGrossValue();

        log.debug("Asset Current Value :: " + assetCurrentAmount);

        revaluation.setCurrentCapitalizedValue(assetCurrentAmount);

        if (typeOfChange != null && TypeOfChangeEnum.DECREASED.compareTo(typeOfChange) == 0
                && (revaluation.getValueAfterRevaluation().compareTo(assetCurrentAmount) == 0
                        || revaluation.getValueAfterRevaluation().compareTo(assetCurrentAmount) == -1))
            throw new RuntimeException(
                    "Decrease in amount should not be equal or greater than the gross value of the asset. current gross value of asset is "
                            + assetCurrentAmount + " and value after revaluation is "
                            + revaluation.getValueAfterRevaluation());

        validateRevaluationDate(revaluation);

        // Setting Default Revaluation Status as APPROVED
        revaluation.setStatus(Status.APPROVED.toString());

    }

    private void validateRevaluationDate(final Revaluation revaluation) {
        final Long revaluationDate = revaluation.getRevaluationDate();
        if (revaluationDate == null)
            throw new RuntimeException("Revaluation Date is Required");

        if (revaluationDate > new Date().getTime())
            throw new RuntimeException("Revaluation Date should not be greater than current date.");
    }

    private void validateFund(final Long fundId) {
        if (fundId == null)
            throw new RuntimeException(
                    "Fund from financials is necessary for Asset Revaluation,Asset Depreciation and Asset Sale/Disposal");
    }

    private TypeOfChangeEnum validateRevaluationForTypeOfChange(final Revaluation revaluation, final Asset asset,
            final boolean enableVoucherGeneration) {
        final TypeOfChangeEnum typeOfChange = revaluation.getTypeOfChange();
        if (typeOfChange == null)
            throw new RuntimeException("Type Of Change is necessary for asset revaluation");

        if (getEnableYearWiseDepreciation(revaluation.getTenantId())) {
            if (typeOfChange != null && TypeOfChangeEnum.DECREASED.compareTo(typeOfChange) == 0
                    && revaluation.getFixedAssetsWrittenOffAccount() == null)
                throw new RuntimeException("Fixed Asset Written Off Account is necessary for asset " + asset.getName()
                        + " voucher generation for revaluation");

            final AssetCategory assetCategory = asset.getAssetCategory();

            if (typeOfChange != null
                    && (TypeOfChangeEnum.DECREASED.compareTo(typeOfChange) == 0
                            || TypeOfChangeEnum.INCREASED.compareTo(typeOfChange) == 0)
                    && assetCategory != null && assetCategory.getAssetAccount() == null)
                throw new RuntimeException("Asset Account is necessary for asset " + asset.getName()
                        + " voucher generation for revaluation");

            if (typeOfChange != null && TypeOfChangeEnum.INCREASED.compareTo(typeOfChange) == 0 && assetCategory != null
                    && assetCategory.getRevaluationReserveAccount() == null)
                throw new RuntimeException("Revaluation Reserve Account is necessary for asset " + asset.getName()
                        + " for voucher generation.");
        }
        return typeOfChange;
    }

    private boolean getEnableYearWiseDepreciation(final String tenantId) {
        return assetConfigurationService.getEnabledVoucherGeneration(AssetConfigurationKeys.ENABLEVOUCHERGENERATION,
                tenantId);
    }

    private void validateAssetCategoryForVoucherGeneration(final Asset asset) {
        if (asset.getAssetCategory() == null)
            throw new RuntimeException(
                    "Asset Category should be present for asset " + asset.getName() + " for voucher generation");
    }

    public void validateAssetForUpdate(final AssetRequest assetRequest) {
        final Asset assetFromReq = assetRequest.getAsset();
        final Asset asset = assetService.getAsset(assetFromReq.getTenantId(), assetFromReq.getId(),
                assetRequest.getRequestInfo());
        if (!assetFromReq.getCode().equalsIgnoreCase(asset.getCode()))
            throw new RuntimeException("Invalid Asset Code for Asset :: " + asset.getName());
        else
            validateYearWiseDepreciationRate(assetRequest.getAsset());
    }

    public void validateDepreciation(final DepreciationRequest depreciationRequest) {
        final Double depreciationMinimumValue = Double.valueOf(applicationProperties.getDepreciaitionMinimumValue());
        log.debug("Depreciation Minimum value :: " + depreciationMinimumValue);
        final DepreciationCriteria depreciationCriteria = depreciationRequest.getDepreciationCriteria();
        final String tenantId = depreciationCriteria.getTenantId();
        final Long fromDate = depreciationCriteria.getFromDate();
        log.debug("Depreciation Criteria From date :: " + fromDate);
        final Long toDate = depreciationCriteria.getToDate();
        log.debug("Depreciation Criteria To date :: " + toDate);
        final Set<Long> assetIds = depreciationCriteria.getAssetIds();
        log.debug("Asset IDs for Depreciation :: " + assetIds);
        final String status = Status.CAPITALIZED.toString();
        final String finacialYear = depreciationCriteria.getFinancialYear();
        log.debug("Depreciation Criteria Financial Year :: " + finacialYear);
        AssetCriteria assetCriteria = null;

        if (finacialYear == null && fromDate == null && toDate == null)
            throw new RuntimeException(
                    "financialyear and (time period)fromdate,todate both cannot be null please provide atleast one value.");
        if (finacialYear == null && fromDate != null && toDate == null)
            throw new RuntimeException("If From Date is selected then To date is mandatory.");
        if (finacialYear == null && fromDate != null && toDate != null && assetIds != null && !assetIds.isEmpty())
            assetCriteria = AssetCriteria.builder().id(new ArrayList<Long>(assetIds)).status(status)
                    .fromCapitalizedValue(depreciationMinimumValue).tenantId(tenantId).fromDate(fromDate).toDate(toDate)
                    .build();
        else
            throw new RuntimeException("Asset IDs are mandatory for custom time period.");

        log.debug("Asset Criteria for Asset Search for Depreciation :: " + assetCriteria);

        final List<Asset> assets = assetService.getAssets(assetCriteria, depreciationRequest.getRequestInfo())
                .getAssets();
        log.debug("Assets For Depreciation :: " + assets);

        if (getEnableYearWiseDepreciation(tenantId) && assets != null && !assets.isEmpty())
            for (final Asset asset : assets) {
                final String assetName = asset.getName();
                final AssetCategory assetCategory = asset.getAssetCategory();
                final boolean assetCategoryForLand = validateAssetCategoryForLand(assetCategory.getAssetCategoryType());
                if (assetCategoryForLand)
                    throw new RuntimeException("Asset category type is LAND for asset :: " + assetName);
                if (assetCategory.getAccumulatedDepreciationAccount() == null)
                    throw new RuntimeException(
                            "Accumulated Depreciation Account should be present for voucher generation for asset :: "
                                    + assetName + " for asset Category :: " + assetCategory.getName());
                if (assetCategory.getDepreciationExpenseAccount() == null)
                    throw new RuntimeException(
                            "Depreciation Expense Account should be present for voucher generation for asset :: "
                                    + assetName + " for asset Category :: " + assetCategory.getName());
            }

    }

    private boolean validateAssetCategoryForLand(final AssetCategoryType assetCategoryType) {
        if (assetCategoryType != null && AssetCategoryType.LAND.compareTo(assetCategoryType) == 0)
            return true;
        else
            return false;
    }

}
