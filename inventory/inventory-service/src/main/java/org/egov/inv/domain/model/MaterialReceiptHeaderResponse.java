package org.egov.inv.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Hold the Material Receipt Note Response information
 */
@ApiModel(description = "Hold the Material Receipt Note Response information")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-10-27T06:37:03.617Z")

public class MaterialReceiptHeaderResponse {
    @JsonProperty("ResposneInfo")
    private ResponseInfo resposneInfo = null;

    @JsonProperty("MaterialReceipt")
    private List<MaterialReceiptHeader> materialReceipt = null;

    public MaterialReceiptHeaderResponse resposneInfo(ResponseInfo resposneInfo) {
        this.resposneInfo = resposneInfo;
        return this;
    }

    /**
     * Get resposneInfo
     *
     * @return resposneInfo
     **/
    @ApiModelProperty(value = "")

    @Valid

    public ResponseInfo getResposneInfo() {
        return resposneInfo;
    }

    public void setResposneInfo(ResponseInfo resposneInfo) {
        this.resposneInfo = resposneInfo;
    }

    public MaterialReceiptHeaderResponse materialReceipt(List<MaterialReceiptHeader> materialReceipt) {
        this.materialReceipt = materialReceipt;
        return this;
    }

    public MaterialReceiptHeaderResponse addMaterialReceiptItem(MaterialReceiptHeader materialReceiptItem) {
        if (this.materialReceipt == null) {
            this.materialReceipt = new ArrayList<MaterialReceiptHeader>();
        }
        this.materialReceipt.add(materialReceiptItem);
        return this;
    }

    /**
     * Get materialReceipt
     *
     * @return materialReceipt
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<MaterialReceiptHeader> getMaterialReceipt() {
        return materialReceipt;
    }

    public void setMaterialReceipt(List<MaterialReceiptHeader> materialReceipt) {
        this.materialReceipt = materialReceipt;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MaterialReceiptHeaderResponse materialReceiptHeaderResponse = (MaterialReceiptHeaderResponse) o;
        return Objects.equals(this.resposneInfo, materialReceiptHeaderResponse.resposneInfo) &&
                Objects.equals(this.materialReceipt, materialReceiptHeaderResponse.materialReceipt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resposneInfo, materialReceipt);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class MaterialReceiptHeaderResponse {\n");

        sb.append("    resposneInfo: ").append(toIndentedString(resposneInfo)).append("\n");
        sb.append("    materialReceipt: ").append(toIndentedString(materialReceipt)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

