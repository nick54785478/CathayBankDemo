package com.example.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentPriceResponse {
	
	private TimeResource time;
	
	private String disclaimer;
	
	private String chartName;
	
	private BpiResource bpi;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class BpiResource {
		
		@JsonProperty("USD")
		private BitcoinResource usd;
		
		@JsonProperty("GBP")
		private BitcoinResource gbp;
		
		@JsonProperty("EUR")
		private BitcoinResource eur;
		
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class BitcoinResource {
		
		private String code;

		private String symbol;

		private String rate;

		private String description;

		@JsonProperty("rate_float")
		private Double rateFloat;
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class TimeResource {
		
		private String updated;
		
		private String updatedISO;
		
		private String updateduk;
	}

}

