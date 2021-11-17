package com.iquantex.samples.shopping.goods;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

/**
 * 商品查询命令
 */
@Builder
@Getter
public class GoodsQueryCmd implements Serializable {

	private static final long serialVersionUID = -6227045788681848075L;
	private String goodsCode;

}
