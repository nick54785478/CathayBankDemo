package com.example.demo.client;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.request.CreateCoinRequest;
import com.example.demo.dto.response.CurrentPriceResponse;
import com.example.demo.dto.response.CurrentPriceResponse.BpiResource;
import com.example.demo.util.BaseDataTransformer;

@SpringBootTest
class BitcoinFeignClientTest {

	@Autowired
	private BitcoinFeignClient client;

	@Test
	void testGetCurrentPrice() {
		CurrentPriceResponse currentPrice = client.getCurrentPrice();
		System.out.println("currentPrice:" + currentPrice);
		BpiResource bpi = currentPrice.getBpi();
		// 美金轉換
		CreateCoinRequest usd = BaseDataTransformer.transformData(bpi.getUsd(), CreateCoinRequest.class);
		// 歐元轉換
		CreateCoinRequest eur = BaseDataTransformer.transformData(bpi.getEur(), CreateCoinRequest.class);
		// 英鎊轉換
		CreateCoinRequest gbp = BaseDataTransformer.transformData(bpi.getGbp(), CreateCoinRequest.class);

		List<CreateCoinRequest> request = List.of(usd, eur, gbp);
		System.out.println(request);
	}

}
