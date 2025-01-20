package com.example.demo.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.config.IntegrationTestFeignConfiguration;
import com.example.demo.dto.request.CreateCoinRequest;
import com.example.demo.dto.request.UpdateCoinRequest;
import com.example.demo.dto.response.BaseResponse;
import com.example.demo.dto.response.BitcoinListQueriedResponse;
import com.example.demo.dto.response.BitcoinQueriedResponse;

@FeignClient(value = "IntegrationTestFeignClient", url = "http://localhost:8080/CathayBankDemo", configuration = IntegrationTestFeignConfiguration.class)
public interface IntegrationTestFeignClient {

	/**
	 * 新增一筆幣別資料
	 * 
	 * @param requestData
	 * @return
	 */
	@PostMapping("/add")
	public ResponseEntity<BaseResponse> add(@RequestBody CreateCoinRequest requestData);

	/**
	 * 新增多筆幣別資料
	 * 
	 * @param requestData
	 * @return
	 */
	@PostMapping("/addAll")
	public ResponseEntity<BaseResponse> addAllData(@RequestBody List<CreateCoinRequest> requestData);

	/**
	 * 查詢所有幣別資料
	 */
	@GetMapping("/getAll")
	public ResponseEntity<BitcoinListQueriedResponse> getAllCoinData();

	/**
	 * 透過幣別查詢該筆幣別資料
	 */
	@GetMapping("getByCode/{code}")
	public ResponseEntity<BitcoinQueriedResponse> getByCode(@PathVariable("code") String code);

	/**
	 * 修改 (情境: 前端 Form 表單資料修改)
	 * 
	 */
	@PutMapping("updByCode/{code}")
	public ResponseEntity<BaseResponse> updByCode(@PathVariable String code, @RequestBody UpdateCoinRequest request);

	/**
	 * 刪除該筆幣別資料
	 */
	@DeleteMapping("delByCode/{code}")
	public ResponseEntity<BaseResponse> delByCode(@PathVariable("code") String code);

}
