package com.iquantex.samples.shopping.goods;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 商品查询事件
 */
@AllArgsConstructor
@Getter
public class GoodsQueryEvent implements Serializable {

	private static final long serialVersionUID = -1530998230636426641L;
	private String goodsCode;

	private long qty;

	private long frozenQty;

}
