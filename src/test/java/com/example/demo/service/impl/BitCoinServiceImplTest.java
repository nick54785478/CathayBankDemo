package com.example.demo.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.client.BitcoinFeignClient;
import com.example.demo.dto.BitcoinQueriedResponseData;
import com.example.demo.dto.request.CreateCoinRequest;
import com.example.demo.dto.request.UpdateCoinRequest;
import com.example.demo.dto.response.CurrentPriceResponse;
import com.example.demo.dto.response.CurrentPriceResponse.BpiResource;
import com.example.demo.util.BaseDataTransformer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class BitCoinServiceImplTest {

	@Autowired
	private BitCoinServiceImpl bitCoinService;
	@Autowired
	private BitcoinFeignClient client;
	
	/**
	 * 新增多筆資料
	 * */
	@Test
	public void testAddAllData() {
		CurrentPriceResponse resource = client.getCurrentPrice();

		// DTO 轉換 Request Data
		BpiResource bpi = resource.getBpi();
		log.info("BPI: {}", bpi);

		// 美金轉換
		CreateCoinRequest usd = BaseDataTransformer.transformData(bpi.getUsd(), CreateCoinRequest.class);
		// 歐元轉換
		CreateCoinRequest eur = BaseDataTransformer.transformData(bpi.getEur(), CreateCoinRequest.class);
		// 英鎊轉換
		CreateCoinRequest gbp = BaseDataTransformer.transformData(bpi.getGbp(), CreateCoinRequest.class);

		List<CreateCoinRequest> request = List.of(usd, eur, gbp);

		// 新增
		bitCoinService.addAllData(request);

		// 查詢出來比較
		List<BitcoinQueriedResponseData> coins = bitCoinService.getAllCoinData();
		log.info("bitcoin list: {}", coins);
		assertTrue(!coins.isEmpty());
	}

	@Test
	void testAddNewData() {
		// 新增
		CreateCoinRequest request = new CreateCoinRequest("JPY", "&#165;", "4,041,059.52", "Japanese yen");
		bitCoinService.addNewData(request);
		// 查詢出來比較
		BitcoinQueriedResponseData coin = bitCoinService.getByCode("JPY");
		assertNotNull(coin);
	}

	/**
	 * 預期: 有查詢到 USD 資料
	 */
	@Test
	void testGetByCode() {
		// 查詢
		BitcoinQueriedResponseData coin = bitCoinService.getByCode("JPY");
		System.out.println("coin:" + coin);
		assertNotNull(coin);
	}
	
	@Test
	void testDelData() {

	}

	@Test
	void testUpdData() {
		// 新增
		UpdateCoinRequest request = new UpdateCoinRequest("JPY", "&#165;", "4,045,059.52", "Japanese yen");
		bitCoinService.updData(request);
	}

	/**
	 * 預期: 有查詢到幣別資料
	 */
	@Test
	void testGetAll() {
		List<BitcoinQueriedResponseData> coinList = bitCoinService.getAllCoinData();
		assertTrue(!coinList.isEmpty());// 判斷是否查無資料
	}

}
