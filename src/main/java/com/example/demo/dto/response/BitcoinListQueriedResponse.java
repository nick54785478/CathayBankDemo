package com.example.demo.dto.response;

import java.util.List;

import com.example.demo.dto.BitcoinQueriedResponseData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BitcoinListQueriedResponse extends BaseResponse {

	private List<BitcoinQueriedResponseData> data;

	
	
}
