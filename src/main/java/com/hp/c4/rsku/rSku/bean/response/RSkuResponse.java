package com.hp.c4.rsku.rSku.bean.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//@JsonPropertyOrder(alphabetic=true)
@JsonPropertyOrder({ "costDate", "countryCode", "countryDescription", "deliveryMethod", "mot", "outputCurrency",
		"fileName", "baseSkuCostDetails", "rapidSkuCostDetails", "accessDeniedProducts" })
public class RSkuResponse extends Response {

	private String fileName;
	private ProductCost rapidSkuCostDetails;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public ProductCost getRapidSkuCostDetails() {
		return rapidSkuCostDetails;
	}

	public void setRapidSkuCostDetails(ProductCost rapidSkuCostDetails) {
		this.rapidSkuCostDetails = rapidSkuCostDetails;
	}

	@Override
	public String toString() {
		return "RSkuResponse [fileName=" + fileName + ", rapidSkuCostDetails=" + rapidSkuCostDetails + "]";
	}

}
