package com.example.demo.service.outbound;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.client.BitcoinFeignClient;
import com.example.demo.dto.response.CurrentPriceResponse;
import com.example.demo.dto.response.CurrentPriceResponse.BitcoinResource;

/**
 * 呼叫外部 API 取得 Bitcoin 資料的 Service
 * */
@Service
public class CoinDeskSerivce {

	@Autowired
	private BitcoinFeignClient client;

	/**
	 * 透過 Feign Client 取得幣別價格資料
	 * 
	 * @return CurrentPriceResponse
	 */
	public CurrentPriceResponse getCurrentPrice() {
		return client.getCurrentPrice();
	}

	/**
	 * 透過 Feign Client 取得幣別價格資料清單
	 * 
	 * @return List<BitcoinResource>
	 */
	public List<BitcoinResource> getTransformedDataCurrentPriceList() {
		CurrentPriceResponse resource = client.getCurrentPrice();
		if (Objects.isNull(resource)) {
			return new ArrayList<>();
		}

		BitcoinResource usd = resource.getBpi().getUsd();// 美金轉換
		BitcoinResource eur = resource.getBpi().getEur(); // 歐元轉換
		BitcoinResource gbp = resource.getBpi().getGbp();// 英鎊轉換
		return List.of(usd, eur, gbp);

	}

}
