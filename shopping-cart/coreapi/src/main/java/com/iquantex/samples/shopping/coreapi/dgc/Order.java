package com.iquantex.samples.shopping.coreapi.dgc;

import com.iquantex.phoenix.dgc.annotation.Compute;
import com.iquantex.phoenix.dgc.annotation.Model;
import com.iquantex.phoenix.dgc.annotation.Observable;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Model
@Getter
@Setter
public class Order {
    /** 物品名称 */
    private String itemName;
    /** 物品数量 */
    @Observable
    private long qty;
    /** 物品价格 */
    @Observable private double price;

    /** 订单金额 (物品数量 * 物品价格) */
    @Compute
    public double getOrderAmount() {
        double amount = getQty() * getPrice();
        log.info("* get order itemName<{}> orderAmount<{}>", getItemName(), amount);
        return amount;
    }
}
