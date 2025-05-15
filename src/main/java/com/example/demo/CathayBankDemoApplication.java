package com.example.demo;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.demo.dto.request.CreateCoinRequest;
import com.example.demo.service.impl.BitCoinServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableJpaAuditing
@EnableFeignClients
@SpringBootApplication
public class CathayBankDemoApplication {

	@Autowired
	private BitCoinServiceImpl bitCoinService;

	public static void main(String[] args) {
		SpringApplication.run(CathayBankDemoApplication.class, args);
	}

	@PostConstruct()
	public void init() {
		// 新增
		CreateCoinRequest eur = new CreateCoinRequest("EUR", "&euro;", "77,602.70", "Euro");
		CreateCoinRequest usd = new CreateCoinRequest("USD", "&#36;", "84,025.16", "US Dollar");
		CreateCoinRequest gbp = new CreateCoinRequest("GBP", "&pound;", "64,951.03", "Great British Pound");

		bitCoinService.addNewData(eur);
		bitCoinService.addNewData(usd);
		bitCoinService.addNewData(gbp);
		
		log.info("進行資料初始化，插入比特幣資料進資料庫。");
		log.info("歐元價格: {}", eur);
		log.info("美元價格: {}", usd);
		log.info("英鎊價格: {}", gbp);
		
		System.out.println("init: 新增比特幣資料.....");

	}
}
