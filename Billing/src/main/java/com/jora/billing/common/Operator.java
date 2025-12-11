package com.jora.billing.common;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Operator {
	private int operatorCode;
	private String operatorName;
}
