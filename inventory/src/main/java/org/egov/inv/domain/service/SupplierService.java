package org.egov.inv.domain.service;

import java.util.List;

import org.egov.inv.domain.exception.CustomBindException;
import org.egov.inv.domain.exception.ErrorCode;
import org.egov.inv.domain.exception.InvalidDataException;
import org.egov.inv.persistence.entity.SupplierEntity;
import org.egov.inv.persistence.repository.SupplierJdbcRepository;
import org.egov.tracer.kafka.LogAwareKafkaTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import io.swagger.model.Store;
import io.swagger.model.StoreRequest;
import io.swagger.model.Supplier;
import io.swagger.model.SupplierRequest;
@Service
public class SupplierService {
	
	@Autowired
	private InventoryUtilityService inventoryUtilityService;

	@Autowired
	private LogAwareKafkaTemplate<String, Object> kafkaTemplate;
	
	@Autowired
	private SupplierJdbcRepository supplierJdbcRepository;
	
	@Value("${inv.supplier.save.topic}")
	private String createTopic;
	
	@Value("${inv.supplier.update.topic}")
	private String updateTopic;

	

	public List<Supplier> create(SupplierRequest supplierRequest, String tenantId, BindingResult errors) {
		try {

			for (Supplier supplier : supplierRequest.getSuppliers()) {
				supplier.setAuditDetails(inventoryUtilityService.mapAuditDetails(supplierRequest.getRequestInfo(), tenantId));
				if (!supplierJdbcRepository.uniqueCheck("code", new SupplierEntity().toEntity(supplier))) {
					errors.addError(new FieldError("supplier", "code", supplier.getCode(), false,
							new String[] { ErrorCode.NON_UNIQUE_VALUE.getCode() }, null,
							ErrorCode.NON_UNIQUE_VALUE.getMessage() + " . " + ErrorCode.NON_UNIQUE_VALUE.getDescription()));
				}
				if (errors.hasErrors()) {
					throw new CustomBindException(errors.getFieldError().getCode() + " : " + (errors.getFieldError().getDefaultMessage().replace("{0}", errors.getFieldError().getField())).replace("{1}", errors.getFieldError().getRejectedValue().toString()));
				}
				supplier.setId(supplierJdbcRepository.getSequence(supplier));
			}
		} catch (CustomBindException e) {
			throw e;
		}
			
				
		return push(supplierRequest);
	}
	

	public List<Supplier> push(SupplierRequest supplierRequest) {
		kafkaTemplate.send(createTopic, supplierRequest);
		return supplierRequest.getSuppliers();
	}


	public List<Supplier> update(SupplierRequest supplierRequest, String tenantId, BindingResult errors) {
		try {

			for (Supplier supplier : supplierRequest.getSuppliers()) {
				 if (supplier.getId() == null) {
                     throw new InvalidDataException("id", ErrorCode.MANDATORY_VALUE_MISSING.getCode(), supplier.getId());
                 }
				supplier.setAuditDetails(inventoryUtilityService.mapAuditDetailsForUpdate(supplierRequest.getRequestInfo(), tenantId));
				if (!supplierJdbcRepository.uniqueCheck("code", new SupplierEntity().toEntity(supplier))) {
					errors.addError(new FieldError("supplier", "code", supplier.getCode(), false,
							new String[] { ErrorCode.NON_UNIQUE_VALUE.getCode() }, null,
							ErrorCode.NON_UNIQUE_VALUE.getMessage() + " . " + ErrorCode.NON_UNIQUE_VALUE.getDescription()));
				}
				if (errors.hasErrors()) {
					throw new CustomBindException(errors.getFieldError().getCode() + " : " + (errors.getFieldError().getDefaultMessage().replace("{0}", errors.getFieldError().getField())).replace("{1}", errors.getFieldError().getRejectedValue().toString()));
				}
			}
		} catch (CustomBindException e) {
			throw e;
		}
			
				
		return pushForUpdate(supplierRequest);
	}
	public List<Supplier> pushForUpdate(SupplierRequest supplierRequest) {
		kafkaTemplate.send(updateTopic, supplierRequest);
		return supplierRequest.getSuppliers();
	}	


}
