package com.iquantex.samples.shopping.goods;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

/**
 * 商品查询命令
 */
@Builder
@AllArgsConstructor
@Getter
public class GoodsQueryCmd implements Serializable {

	private String goodsCode;

}
