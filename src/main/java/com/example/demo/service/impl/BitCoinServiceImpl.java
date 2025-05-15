package com.example.demo.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.BitcoinQueriedResponseData;
import com.example.demo.dto.request.CreateCoinRequest;
import com.example.demo.dto.request.UpdateCoinRequest;
import com.example.demo.entity.Bitcoin;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.BitCoinRepository;
import com.example.demo.service.BitCoinService;
import com.example.demo.util.BaseDataTransformer;

import lombok.extern.slf4j.Slf4j;

/**
 * BitCoinService 實作
 */
@Slf4j
@Service
public class BitCoinServiceImpl implements BitCoinService {

	@Autowired
	private BitCoinRepository bcRepository;

	/**
	 * 新增一筆比特幣幣別交易資料
	 * 
	 * @param requestData 比特幣交易幣別資料
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
	@Override
	public void addNewData(CreateCoinRequest requestData) {
		bcRepository.findById(requestData.getCode()).ifPresent(e -> {
			log.error("資料庫中已有資料");
			throw new ValidationException("VALIDATE_FAILED", "資料庫中已有資料");
		});
		Bitcoin bitcoin = new Bitcoin();
		bitcoin.create(requestData);
		bcRepository.save(bitcoin);

	}

	/**
	 * 新增多筆比特幣幣別交易資料
	 * 
	 * @param coinList 比特幣幣別交易資料
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
	@Override
	public void addAllData(List<CreateCoinRequest> coinList) {
		List<Bitcoin> bitcoins = coinList.stream().map(req -> {
			Bitcoin bitcoin = new Bitcoin();
			bitcoin.create(req);
			return bitcoin;
		}).collect(Collectors.toList());
		bcRepository.saveAll(bitcoins);
	}

	/**
	 * 刪除特定比特幣交易幣別資料
	 * 
	 * @param code
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
	@Override
	public void delData(String code) {
		bcRepository.findById(code).ifPresentOrElse(e -> {
			bcRepository.delete(e);
		}, () -> {
			log.error("刪除失敗，查無該筆資料， Code:" + code);
			throw new ValidationException("VALIDATE_FAILED", "刪除失敗，查無該筆資料");
		});
	}

	/**
	 * 更新特定比特幣交易幣別資料
	 * 
	 * @param requestData
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
	@Override
	public void updData(UpdateCoinRequest requestData) {
		bcRepository.findById(requestData.getCode()).ifPresentOrElse(coin -> {
			coin.update(requestData);
			bcRepository.save(coin);
		}, () -> {
			log.error("查無此資料, Code:{}", requestData.getCode());
			throw new ValidationException("VALIDATE_FAILED", "查無此資料，Code:" + requestData.getCode());
		});
	}

	/**
	 * 透過 Code 取得幣別資料
	 * 
	 * @param code
	 */
	@Override
	public BitcoinQueriedResponseData getByCode(String code) {
		Bitcoin coin = bcRepository.findByCode(code);
		if (Objects.isNull(coin)) {
			return null;
		}
		return BaseDataTransformer.transformData(coin, BitcoinQueriedResponseData.class);
	}

	/**
	 * 取得所有幣別資料
	 */
	@Override
	public List<BitcoinQueriedResponseData> getAllCoinData() {
		List<Bitcoin> coinList = bcRepository.findAll();
		System.out.println(coinList);
		return BaseDataTransformer.transformData(coinList, BitcoinQueriedResponseData.class);
	}

}
