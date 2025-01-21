package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.dto.request.CreateCoinRequest;
import com.example.demo.dto.request.UpdateCoinRequest;
import com.example.demo.enums.BitcoinCode;
import com.example.demo.enums.Code2Symbol;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bitcoin")
@EntityListeners(AuditingEntityListener.class)
public class Bitcoin {

	@Id
	@Column(name = "code")
	@Type(type = "string")
	private String code;

	@Column(name = "codezh")
	private String codeZh;

	private String symbol;

	private String rate;

	private String description;

	@Column(name = "rate_float")
	private Double rateFloat;

	// 會在資料變更時即時更新時間
	@LastModifiedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "GMT+8")
	private Date updateTime;

	public Bitcoin(String code, String symbol, String rate, String description, Double rateFloat) {
		this.code = code;
		this.symbol = symbol;
		this.rate = rate;
		this.description = description;
		this.rateFloat = rateFloat;
	}

	public Bitcoin(String code, String symbol, String rate, String description, Double rateFloat, Date updateTime) {
		this.code = code;
		this.symbol = symbol;
		this.rate = rate;
		this.description = description;
		this.rateFloat = rateFloat;
		this.updateTime = updateTime;
	}

	/**
	 * 新增 BitCoin 資料
	 * 
	 * @param requestData
	 */
	public void create(CreateCoinRequest requestData) {
		this.code = requestData.getCode();
		this.codeZh = (StringUtils.isNotBlank(requestData.getCode()))
				? BitcoinCode.valueOf(requestData.getCode()).getCodeZh()
				: null;
		this.symbol = (StringUtils.isNotBlank(requestData.getCode()))
				? Code2Symbol.valueOf(requestData.getCode()).getLabel()
				: null;
		this.rate = requestData.getRate();
		this.description = requestData.getDescription();
		this.rateFloat = Double.valueOf(requestData.getRate().replaceAll(",", ""));
	}

	/**
	 * 更新 BitCoin 資料
	 * 
	 * @param requestData
	 */
	public void update(UpdateCoinRequest requestData) {
		this.codeZh = (StringUtils.isNotBlank(requestData.getCode()))
				? BitcoinCode.valueOf(requestData.getCode()).getCodeZh()
				: null;
		this.symbol = (StringUtils.isNotBlank(requestData.getCode()))
				? Code2Symbol.valueOf(requestData.getCode()).getLabel()
				: null;
		this.rate = requestData.getRate();
		this.description = requestData.getDescription();
		this.rateFloat = Double.valueOf(requestData.getRate().replaceAll(",", ""));
		this.updateTime = new Date();
	}

}
