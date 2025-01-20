package com.example.demo.service.outbound;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.client.BitcoinFeignClient;
import com.example.demo.dto.response.CurrentPrceResponse;
import com.example.demo.dto.response.CurrentPrceResponse.BitcoinResource;

@Service
public class CoinDeskSerivce {

	@Autowired
	private BitcoinFeignClient client;

	static void init() {

	}

	/**
	 * 透過 Feign Client 取得幣別價格資料
	 */
	public CurrentPrceResponse getCurrentPrice() {
		return client.getCurrentPrice();
	}

	/**
	 * 透過 Feign Client 取得幣別價格資料清單
	 */
	public List<BitcoinResource> getCurrentPriceList() {
		CurrentPrceResponse resource = client.getCurrentPrice();
		BitcoinResource usd = resource.getBpi().getUsd();// 美金轉換
		BitcoinResource eur = resource.getBpi().getEur(); // 歐元轉換
		BitcoinResource gbp = resource.getBpi().getGbp();// 英鎊轉換
		return List.of(usd, eur, gbp);

	}

}
