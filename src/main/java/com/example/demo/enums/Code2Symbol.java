package com.example.demo.enums;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Code2Symbol {

	USD("&#36;", "$"), GBP("&pound;", "£"), JPY("&#165;", "¥"), EUR("&euro;", "€");

	@Getter
	private final String code;
	@Getter
	private final String label;

	// enum 轉 Map
	private static final Map<String, Code2Symbol> labelToTypeMap = new HashMap<>();

	static {
		for (Code2Symbol type : Code2Symbol.values()) {
			labelToTypeMap.put(type.label, type);
		}
	}

	public static Code2Symbol fromLabel(String label) {
		return labelToTypeMap.get(label);
	}

	public static Map<String, Code2Symbol> getMap() {
		return labelToTypeMap;
	}

	public static Boolean checkTrainKind(String label) {
		Code2Symbol kind = Code2Symbol.fromLabel(label);
		if (kind == null) {
			return false;
		}
		return true;
	}

}
