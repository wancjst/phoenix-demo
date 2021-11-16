package com.iquantex.samples.shopping.domain;

import com.iquantex.phoenix.core.message.RetCode;
import com.iquantex.phoenix.server.aggregate.ActReturn;
import com.iquantex.phoenix.server.aggregate.CommandHandler;
import com.iquantex.phoenix.server.aggregate.EntityAggregateAnnotation;
import com.iquantex.phoenix.server.aggregate.QueryHandler;
import com.iquantex.samples.shopping.account.AccountQueryCmd;
import com.iquantex.samples.shopping.account.AccountQueryEvent;
import com.iquantex.samples.shopping.buyGoods.BuyGoodsCmd;
import com.iquantex.samples.shopping.buyGoods.BuyGoodsEvent;

import java.io.Serializable;

@EntityAggregateAnnotation(aggregateRootType = "AccountAggregate")
public class AccountAggregate implements Serializable {

	private static final long serialVersionUID = 1989248847513267951L;

	private double amt;

	private double frozenAmt;

	/**
	 * 假定默认1W元
	 */
	public AccountAggregate() {
		this.amt = 10000;
		this.frozenAmt = 0;
	}

	@QueryHandler(aggregateRootId = "accountCode")
	public ActReturn act(AccountQueryCmd cmd) {
		return ActReturn.builder().retCode(RetCode.SUCCESS)
				.event(new AccountQueryEvent(cmd.getAccountCode(), amt, frozenAmt)).build();
	}

	@CommandHandler(aggregateRootId = "accountCode")
	public ActReturn act(BuyGoodsCmd cmd){
		return ActReturn.builder().retCode(RetCode.SUCCESS)
				.event(new BuyGoodsEvent("ok")).build();
	}
}
