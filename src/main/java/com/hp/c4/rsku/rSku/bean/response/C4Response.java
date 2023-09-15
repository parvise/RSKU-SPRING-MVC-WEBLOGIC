package com.hp.c4.rsku.rSku.bean.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "costDate", "countryCode", "countryDescription", "deliveryMethod", "mot", "outputCurrency",
		"baseSkuCostDetails", "accessDeniedProducts" })
public class C4Response extends Response {

}
