package com.example.demo.service.outbound;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.response.CurrentPriceResponse;
import com.example.demo.dto.response.CurrentPriceResponse.BitcoinResource;

@SpringBootTest
class CoinDeskSerivceTest {

	@Autowired
	private CoinDeskSerivce coinDeskSerivce;

	@Test
	void testGetCurrentPrice() {
		CurrentPriceResponse resource = coinDeskSerivce.getCurrentPrice();
		assertNotNull(resource);
	}

	@Test
	void testGetTransformedDataCurrentPriceList() {
		List<BitcoinResource> resource = coinDeskSerivce.getTransformedDataCurrentPriceList();
		assertTrue(!resource.isEmpty());

	}

}
