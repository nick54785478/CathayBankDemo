package com.example.demo.util;

public enum Code2Symbol {

	USD("USD", "$", "美金"),
	GBP("GBP", "£", "英鎊"),
	JPY("JPY", "¥", "日幣"),
	EUR("EUR", "€", "歐元");
	
	
	
	private final String code;
	private final String symbol;
	private final String codeZh;
	
	
	private Code2Symbol(String code, String symbol, String codeZh) {
		this.code = code;
		this.symbol = symbol;
		this.codeZh = codeZh;
	}
	public String getCode() {
		return code;
	}
	public String getSymbol() {
		return symbol;
	}
	public String getCodeZh() {
		return codeZh;
	}
	
	
	
	

	
}

