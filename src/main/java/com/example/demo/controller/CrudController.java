package com.example.demo.controller;

import java.util.Date;
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

import com.example.demo.entity.Bitcoin;
import com.example.demo.service.impl.BitCoinServiceImpl;
import com.example.demo.util.Code2Symbol;
import com.google.gson.Gson;

@RestController
public class CrudController {

	@Autowired
	BitCoinServiceImpl bcService;

	// 新增一筆
	@PostMapping("/add")
	public ResponseEntity<String> add(@RequestBody Bitcoin bitcoin){
		
		Bitcoin b = bcService.getByCode(bitcoin.getCode());
		if (b!=null) {
			return new ResponseEntity<>("資料庫中已有資料", HttpStatus.FORBIDDEN);
		}
		
		// set符號
		String symbol = Code2Symbol.valueOf(bitcoin.getCode()).getSymbol();
		bitcoin.setSymbol(symbol);
		bitcoin.setCodeZh(Code2Symbol.valueOf(bitcoin.getCode()).getCodeZh());
		
		// 新增更新時間
		bitcoin.setUpdateTime(new Date());
		
		bcService.addNewData(bitcoin);
		System.out.println("新增成功!!!");
		System.out.println("BitCoin = "+bitcoin.toString());
		return new ResponseEntity<>("成功新增一筆資料!", HttpStatus.OK);
	}
	 
	// 新增多筆
	@PostMapping("/addAll")
	public ResponseEntity<String> addAll(@RequestBody List<Bitcoin> coinList){
		for (Bitcoin bitcoin : coinList) {
			String symbol = Code2Symbol.valueOf(bitcoin.getCode()).getSymbol();
			// set符號
			bitcoin.setSymbol(symbol);
			// set 幣別中文
			bitcoin.setCodeZh(Code2Symbol.valueOf(bitcoin.getCode()).getCodeZh());

			
			// 新增更新時間
			bitcoin.setUpdateTime(new Date());
			System.out.println("bitCoin = "+bitcoin.toString());
		}
		bcService.addAll(coinList);
		System.out.println("新增成功!!!");
		return new ResponseEntity<>("成功新增一筆資料!", HttpStatus.OK);
	}
	
	// 查詢多筆
	@GetMapping("/getAll")
	public ResponseEntity<String> getAll(){
		List<Bitcoin> coinList = bcService.getAll();
		
		for (Bitcoin bitcoin : coinList) {
			String symbol = Code2Symbol.valueOf(bitcoin.getCode()).getSymbol();
			// set符號
			bitcoin.setSymbol(symbol);
			System.out.println(bitcoin.toString());
			
			System.out.println("bitCoin = "+bitcoin.toString());
		}
		
		
		Gson gson = new Gson();
		String jsonData = gson.toJson(coinList);
		
		return new ResponseEntity<>(jsonData, HttpStatus.OK);

	}

	
	// 查詢單筆
	@GetMapping("getByCode/{code}")
	public ResponseEntity<Bitcoin> getByCode(@PathVariable("code") String code){

		Bitcoin coin = bcService.getByCode(code);
		if (coin!=null) {
			coin.setSymbol(Code2Symbol.valueOf(coin.getCode()).getSymbol());

		}
			
		return new ResponseEntity<>(coin, HttpStatus.OK);

	}
	
	// 修改
	@PutMapping("updByCode/{code}")
	public ResponseEntity<Object> updByCode(@RequestBody Bitcoin coin){

		System.out.println("coin: "+coin.toString());
		coin.setUpdateTime(new Date());
		bcService.updData(coin);
			
		return new ResponseEntity<Object>("修改成功", HttpStatus.OK);

	}
	
	// 刪除
	@DeleteMapping("delByCode/{code}")
	public ResponseEntity<Object> delByCode(@PathVariable("code") String code){
		Bitcoin coin = bcService.getByCode(code);
		System.out.println("coin: "+coin.toString());
		bcService.delData(coin);
			
		return new ResponseEntity<Object>("刪除成功", HttpStatus.OK);

	}
	
	
	
}

