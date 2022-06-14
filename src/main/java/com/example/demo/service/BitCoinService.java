package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Bitcoin;

public interface BitCoinService {

	// 新
	void addNewData(Bitcoin coin);
	
	// 新增多筆
	void addAll(List<Bitcoin> coinList);
	
	// 刪
	void delData(Bitcoin coin);
	
	// 改
	void updData(Bitcoin coin);
	
	// 查
	Bitcoin getByCode(String code);
	
	// 查詢全部資料
	List<Bitcoin> getAll();
}

