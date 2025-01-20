package com.example.demo.dto.response;

import com.example.demo.dto.BitcoinQueriedResponseData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BitcoinQueriedResponse extends BaseResponse{

	private BitcoinQueriedResponseData data;

}
