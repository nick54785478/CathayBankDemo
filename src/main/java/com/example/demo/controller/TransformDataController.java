package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.response.CurrentPriceResponse;
import com.example.demo.dto.response.CurrentPriceResponse.BitcoinResource;
import com.example.demo.service.outbound.CoinDeskSerivce;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/coindesk")
public class TransformDataController {

	private CoinDeskSerivce coinDeskSerivce;

	/**
	 * 取得 Coin 時價幣別資料
	 * 
	 * @param requestData
	 * @return CurrentPriceResponse
	 */
	@PostMapping("/currentPrice")
	public ResponseEntity<CurrentPriceResponse> getCurrentPrice() {
		return new ResponseEntity<>(coinDeskSerivce.getCurrentPrice(), HttpStatus.OK);
	}

	/**
	 * 取得 Coin 時價幣別轉換後的資料
	 * 
	 * @param requestData
	 * @return CurrentPriceResponse
	 */
	@GetMapping("/transformedData")
	public ResponseEntity<List<BitcoinResource>> getTransformedData() {
		return new ResponseEntity<>(coinDeskSerivce.getTransformedDataCurrentPriceList(), HttpStatus.OK);
	}
}
