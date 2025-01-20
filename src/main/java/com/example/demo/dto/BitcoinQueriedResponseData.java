package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BitcoinQueriedResponseData {

	private String code;

	private String codeZh;

	private String symbol;

	private String rate;

	private String description;

	private Double rateFloat;
	
	private String updateTime;
}
