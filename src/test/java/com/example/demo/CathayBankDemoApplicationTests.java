package com.example.demo;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.Bitcoin;
import com.example.demo.service.impl.BitCoinServiceImpl;
import com.example.demo.util.Code2Symbol;

@RunWith(SpringRunner.class)
@SpringBootTest
class CathayBankDemoApplicationTests {

	@Autowired
	private BitCoinServiceImpl bcService;
	
	
	@Test
	void contextLoads() {
		// 新增
		Bitcoin bitcoin = new Bitcoin("JPY", "¥", "4,041,059.52", "Japanese yen", 4041059.52);
		bcService.addNewData(bitcoin);
		System.out.println("新增成功");
		
		// 查詢
		Bitcoin bean = bcService.getByCode("JPY");
		System.out.println("BitCoin = "+bean.toString());
		
		// 修改
		bitcoin.setDescription("Japanese Yen");
		bcService.updData(bitcoin);
		System.out.println("===========修改後===========");
		System.out.println("BitCoin = "+bcService.getByCode("JPY").toString());
		
		// 符號轉換
		System.out.println(Code2Symbol.valueOf(bitcoin.getCode()));
		System.out.println(bitcoin.getCode()
				+" ---> "+
				Code2Symbol.valueOf(bitcoin.getCode()).getSymbol());
		
		Date date = new Date();
		System.out.println(date);
		System.out.println(date.getTime());
		
		// 刪除
		bcService.delData(bitcoin);
		System.out.println("刪除成功");
		
		try {
			System.out.println("BitCoin = "+bcService.getByCode("JPY").toString());
			
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("BitCoin不存在資料庫");
		}

	}

}



