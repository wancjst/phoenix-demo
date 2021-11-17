package com.iquantex.samples.shopping.coreapi.dgc;

import com.iquantex.phoenix.dgc.annotation.Compute;
import com.iquantex.phoenix.dgc.annotation.Model;
import com.iquantex.phoenix.dgc.annotation.Observable;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Model
@Getter
@Setter
public class ShoppingCar {
    /** 所有订单 */
    @Observable
    private List<Order> orderList = new ArrayList<>();
    /** 折扣 */
    @Observable private double discount;

    public double getDiscount() {
        log.info("* get discount <{}>", discount);
        return discount;
    }

    /** 总支付金额 (折扣 * 所有订单总价格) */
    @Compute
    public double getTotalPayAmount() {
        double amount = getDiscount() * getTotalAmount();
        log.info("* calc total pay amount<{}>", amount);
        return amount;
    }

    /** 所有订单总价 */
    @Compute
    public double getTotalAmount() {
        return getOrderList().stream().map(Order::getOrderAmount).reduce(0.0, Double::sum);
    }
}
