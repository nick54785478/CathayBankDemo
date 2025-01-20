package com.example.demo.enums;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum BitcoinCode {

	USD("USD", "美元"), EUR("EUR", "歐元"), GBP("GBP", "英鎊"), JPY("JPY", "日圓");

	@Getter
	private final String code;

	@Getter
	private final String codeZh;

	// enum 轉 Map
	private static final Map<String, BitcoinCode> labelToTypeMap = new HashMap<>();

	static {
		for (BitcoinCode type : BitcoinCode.values()) {
			labelToTypeMap.put(type.code, type);
		}
	}

	public static Map<String, BitcoinCode> getMap() {
		return labelToTypeMap;
	}
}
