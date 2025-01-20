package com.example.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.config.BitcoinFeignConfiguration;
import com.example.demo.dto.response.CurrentPrceResponse;

@FeignClient(value = "BitcoinFeignClient", url = "https://api.coindesk.com/v1/bpi/currentprice.json", configuration = BitcoinFeignConfiguration.class)
public interface BitcoinFeignClient {

	/**
	 * 呼叫 Code Desk 資料取得幣別資料
	 * 
	 * @return CurrentPrceResponse
	 */
	@GetMapping("")
	public CurrentPrceResponse getCurrentPrice();

}
