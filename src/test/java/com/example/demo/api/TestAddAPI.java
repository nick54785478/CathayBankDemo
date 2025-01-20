package com.example.demo.api;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.client.BitcoinFeignClient;
import com.example.demo.dto.BitcoinQueriedResponseData;
import com.example.demo.dto.request.CreateCoinRequest;
import com.example.demo.dto.response.CurrentPrceResponse;
import com.example.demo.dto.response.CurrentPrceResponse.BpiResource;
import com.example.demo.service.impl.BitCoinServiceImpl;
import com.example.demo.util.BaseDataTransformer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class TestAddAPI {

	@Autowired
	private BitCoinServiceImpl bitCoinService;
	@Autowired
	private BitcoinFeignClient client;

	@Test
	public void testAddAllData() {
		CurrentPrceResponse resource = client.getCurrentPrice();

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
}
