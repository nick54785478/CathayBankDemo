package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Bitcoin;
import com.example.demo.repository.BitCoinRepository;
import com.example.demo.service.BitCoinService;

@Service
public class BitCoinServiceImpl implements BitCoinService {

	@Autowired
	BitCoinRepository bcRepository;
	
	@Override
	public void addNewData(Bitcoin coin) {
		bcRepository.save(coin);
		
	}

	@Override
	public void addAll(List<Bitcoin> coinList) {
		bcRepository.saveAll(coinList);
		
	}

	@Override
	public void delData(Bitcoin coin) {
		bcRepository.delete(coin);
		
	}

	@Override
	public void updData(Bitcoin coin) {
		bcRepository.save(coin);
	}

	@Override
	public Bitcoin getByCode(String code) {
		return bcRepository.findByCode(code);
	}

	@Override
	public List<Bitcoin> getAll() {
		return bcRepository.findAll();
	}


}

