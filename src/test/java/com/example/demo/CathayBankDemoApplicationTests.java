package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.client.IntegrationTestFeignClient;
import com.example.demo.dto.BitcoinQueriedResponseData;
import com.example.demo.dto.request.CreateCoinRequest;
import com.example.demo.dto.request.UpdateCoinRequest;
import com.example.demo.dto.response.BaseResponse;
import com.example.demo.dto.response.BitcoinListQueriedResponse;
import com.example.demo.dto.response.BitcoinQueriedResponse;
import com.example.demo.dto.response.CurrentPriceResponse.BitcoinResource;
import com.example.demo.service.outbound.CoinDeskSerivce;
import com.example.demo.util.BaseDataTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * 整合測試 (Integration Test)
 */
@Slf4j
@SpringBootTest
class CathayBankDemoApplicationTests {

	@Autowired
	private IntegrationTestFeignClient client;
	@Autowired
	private CoinDeskSerivce coinDeskSerivce;
	
	@Test
	void contextLoads() {
		testAddAllData();
		testGetAllCoinData();
		testAddNewData();
		testGetByCode();
		testUpdByCode();
		testDelByCode();
	}

	/**
	 * 呼叫外部 API 取得Bitcoin 時價資料，並呼叫 API 進行新增多筆 Coin 資料 預期
	 */
//	@Test
	void testAddAllData() {
		// 呼叫外部 API 取得 Code Disk 幣別價格資料
		List<BitcoinResource> currentPriceList = coinDeskSerivce.getTransformedDataCurrentPriceList();
		log.info("呼叫外部 API 取得 Code Disk 幣別價格資料:{}", currentPriceList);
		// 幣別資料轉換
		List<CreateCoinRequest> request = BaseDataTransformer.transformData(currentPriceList, CreateCoinRequest.class);
		ResponseEntity<BaseResponse> response = client.addAllData(request);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	/**
	 * 呼叫 API 查詢剛剛新增的資料
	 * 預期: 
	 * 	- 資料不為空
	 * 	- Status Code 為 200
	 * */
//	@Test
	void testGetAllCoinData() {
		ResponseEntity<BitcoinListQueriedResponse> response = client.getAllCoinData();
		List<BitcoinQueriedResponseData> data = response.getBody().getData();
		log.info("呼叫 API 查詢剛剛新增的資料，ResponseData:{}", data);
		assertTrue(!data.isEmpty());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	/**
	 * 呼叫 API 進行新增單筆 Coin 資料(日圓)
	 * 預期: 
	 * 	Status Code 為 200
	 */
//	@Test
	void testAddNewData() {
		CreateCoinRequest request = new CreateCoinRequest("JPY", "&#165;", "4,041,059.52", "Japanese yen");
		ResponseEntity<BaseResponse> response = client.add(request);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
	
	/**
	 * 呼叫 API 查詢剛剛新增的日圓資料
	 * 預期: 
	 * 	- 資料不為 NULL
	 * 	- Status Code 為 200
	 * */
//	@Test
	void testGetByCode() {
		ResponseEntity<BitcoinQueriedResponse> response = client.getByCode("JPY");
		BitcoinQueriedResponseData data = response.getBody().getData();
		log.info("呼叫 API 查詢剛剛新增的日圓資料，ResponseData:{}", data);
		assertNotNull(data);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	/**
	 * 呼叫 API 進行更新單筆 Coin 資料(日圓)
	 * 預期: 
	 * 	- 資料更新是否相符
	 * 	- Status Code 為 200
	 * */
//	@Test
	void testUpdByCode() {
		UpdateCoinRequest request = new UpdateCoinRequest("JPY", "&#165;", "4,050,059.52", "Japanese yen");
		ResponseEntity<BaseResponse> response = client.updByCode("JPY", request);

		// 查詢日圓資料
		ResponseEntity<BitcoinQueriedResponse> query = client.getByCode("JPY");
		BitcoinQueriedResponseData data = query.getBody().getData();
		log.info("呼叫 API 查詢剛剛更新的日圓資料，ResponseData:{}", data);
		assertEquals(data.getRate(), "4,050,059.52");
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	/**
	 * 呼叫 API 查詢剛剛刪除的日圓資料
	 * 預期: 
	 * 	- Status Code 為 200
	 * */
//	@Test
	void testDelByCode() {
		ResponseEntity<BaseResponse> response = client.delByCode("JPY");
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

}
