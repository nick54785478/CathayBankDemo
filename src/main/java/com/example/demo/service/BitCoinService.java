package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.BitcoinQueriedResponseData;
import com.example.demo.dto.request.CreateCoinRequest;
import com.example.demo.dto.request.UpdateCoinRequest;

/**
 * BitCoinService 抽象介面
 **/
public interface BitCoinService {

	/**
	 * 新增一筆幣別資料
	 */
	void addNewData(CreateCoinRequest request);

	/**
	 * 新增多筆幣別資料
	 */
	void addAllData(List<CreateCoinRequest> coinList);

	/**
	 * 刪除一筆幣別資料
	 */
	void delData(String code);

	/**
	 * 修改一筆幣別資料
	 */
	void updData(UpdateCoinRequest request);

	/**
	 * 查一筆幣別資料
	 */
	BitcoinQueriedResponseData getByCode(String code);

	/**
	 * 查詢全部幣別資料
	 */
	List<BitcoinQueriedResponseData> getAllCoinData();
}
