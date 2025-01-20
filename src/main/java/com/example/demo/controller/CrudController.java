package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BitcoinQueriedResponseData;
import com.example.demo.dto.request.CreateCoinRequest;
import com.example.demo.dto.request.UpdateCoinRequest;
import com.example.demo.dto.response.BaseResponse;
import com.example.demo.dto.response.BitcoinListQueriedResponse;
import com.example.demo.dto.response.BitcoinQueriedResponse;
import com.example.demo.service.impl.BitCoinServiceImpl;

@RestController
public class CrudController {

	@Autowired
	private BitCoinServiceImpl bcService;

	/**
	 * 新增一筆幣別資料
	 * 
	 * @param requestData
	 * @return
	 */
	@PostMapping("/add")
	public ResponseEntity<BaseResponse> add(@RequestBody CreateCoinRequest requestData) {
		bcService.addNewData(requestData);
		return new ResponseEntity<>(new BaseResponse("201", "成功新增一筆資料!"), HttpStatus.CREATED);
	}

	/**
	 * 新增多筆幣別資料
	 * 
	 * @param requestData
	 * @return
	 */
	@PostMapping("/addAll")
	public ResponseEntity<BaseResponse> addAll(@RequestBody List<CreateCoinRequest> requestData) {
		bcService.addAllData(requestData);
		return new ResponseEntity<>(new BaseResponse("201", "成功新增多筆資料!"), HttpStatus.CREATED);
	}

	/**
	 * 查詢所有幣別資料
	 */
	@GetMapping("/getAll")
	public ResponseEntity<BitcoinListQueriedResponse> getAllCoinData() {
		List<BitcoinQueriedResponseData> coinList = bcService.getAllCoinData();
		BitcoinListQueriedResponse response = new BitcoinListQueriedResponse();
		response.setCode("200");
		response.setMessage("查詢成功");
		response.setData(coinList);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	/**
	 * 透過幣別查詢該筆幣別資料
	 */
	@GetMapping("getByCode/{code}")
	public ResponseEntity<BitcoinQueriedResponse> getByCode(@PathVariable("code") String code) {
		BitcoinQueriedResponseData responseData = bcService.getByCode(code);
		BitcoinQueriedResponse response = new BitcoinQueriedResponse();
		response.setCode("200");
		response.setMessage("查詢成功");
		response.setData(responseData);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/**
	 * 修改 (情境: 前端 Form 表單資料修改)
	 * 
	 */
	@PutMapping("updByCode/{code}")
	public ResponseEntity<BaseResponse> updByCode(@PathVariable String code, @RequestBody UpdateCoinRequest request) {
		request.setCode(code);
		bcService.updData(request);
		return new ResponseEntity<BaseResponse>(new BaseResponse("200", "修改成功"), HttpStatus.OK);
	}

	/**
	 * 刪除該筆幣別資料
	 */
	@DeleteMapping("delByCode/{code}")
	public ResponseEntity<BaseResponse> delByCode(@PathVariable("code") String code) {
		bcService.delData(code);
		return new ResponseEntity<BaseResponse>(new BaseResponse("200", "刪除成功"), HttpStatus.OK);

	}

}
