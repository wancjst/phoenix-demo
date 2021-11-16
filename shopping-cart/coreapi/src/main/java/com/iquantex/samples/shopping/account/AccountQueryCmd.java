package com.iquantex.samples.shopping.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@Getter
public class AccountQueryCmd implements Serializable {

	private static final long serialVersionUID = -4177381326154554282L;

	/** 转入账户 */
	private String accountCode;

}
