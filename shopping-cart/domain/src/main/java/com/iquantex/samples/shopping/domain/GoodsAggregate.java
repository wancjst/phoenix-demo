package com.iquantex.samples.shopping.domain;

import com.iquantex.phoenix.core.message.RetCode;
import com.iquantex.phoenix.server.aggregate.ActReturn;
import com.iquantex.phoenix.server.aggregate.EntityAggregateAnnotation;
import com.iquantex.phoenix.server.aggregate.QueryHandler;
import com.iquantex.samples.shopping.goods.GoodsQueryCmd;
import com.iquantex.samples.shopping.goods.GoodsQueryEvent;

import java.io.Serializable;

@EntityAggregateAnnotation(aggregateRootType = "GoodsTcc")
public class GoodsAggregate implements Serializable {

	private static final long serialVersionUID = -6111851668488622895L;

	private long qty;

	private long frozenQty;

	/**
	 * 假定默认100个
	 */
	public GoodsAggregate() {
		this.qty = 100;
		this.frozenQty = 0;
	}

	@QueryHandler(aggregateRootId = "goodsCode")
	public ActReturn act(GoodsQueryCmd cmd) {
		return ActReturn.builder().retCode(RetCode.SUCCESS)
				.event(new GoodsQueryEvent(cmd.getGoodsCode(), qty, frozenQty)).build();
	}
}
