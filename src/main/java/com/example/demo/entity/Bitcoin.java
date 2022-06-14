package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name = "bitcoin")
@EntityListeners(AuditingEntityListener.class)
public class Bitcoin {

	@Id @Column(name = "code")
	@Type(type = "string")
	private String code;
	
	@Column(name = "codezh")
	private String codeZh;
	
	private String symbol;
	
	private String rate;
	
	private String description;
	
	@Column(name = "rate_float")
	private Double rateFloat;
	
	@LastModifiedDate
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", shape = JsonFormat.Shape.STRING, timezone = "GMT+8")
	private Date updateTime;
	
	
	public Bitcoin() {
	}
	
	
	

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

	

	public Bitcoin(String code, String codeZh, String symbol, String rate, String description, Double rateFloat,
			Date updateTime) {
		this.code = code;
		this.codeZh = codeZh;
		this.symbol = symbol;
		this.rate = rate;
		this.description = description;
		this.rateFloat = rateFloat;
		this.updateTime = updateTime;
	}




	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getCodeZh() {
		return codeZh;
	}

	public void setCodeZh(String codeZh) {
		this.codeZh = codeZh;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getRateFloat() {
		return rateFloat;
	}

	public void setRateFloat(Double rateFloat) {
		this.rateFloat = rateFloat;
	}

	
	public Date getUpdateTime() {
		return updateTime;
	}



	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}




	@Override
	public String toString() {
		return "Bitcoin [code=" + code + ", codeZh=" + codeZh + ", symbol=" + symbol + ", rate=" + rate
				+ ", description=" + description + ", rateFloat=" + rateFloat + ", updateTime=" + updateTime + "]";
	}



	
	
	
}
