package com.iquantex.samples.shopping.coreapi.dgc;

import com.iquantex.phoenix.dgc.DgcObjectManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author quail
 */
@Slf4j
public class ShoppingTest {
    @Test
    public void test() {
        // 1. 构造购物车实例，通过 DgcHelper.newProxy 生成 ShoppingCar 的代理类
        DgcObjectManager dgcObjectManager = new DgcObjectManager();
        ShoppingCar car = dgcObjectManager.getDgcObject(ShoppingCar.class);
        car.setDiscount(1);

        // 2. 构造订单实例(模拟购买1000种水果)，通过 DgcHelper.newProxy 生成 Order 的代理类
        for (int i = 0; i < 1000; i++) {
            Order order = dgcObjectManager.getDgcObject(Order.class);
            order.setItemName("fruit-" + i);
            order.setQty(i);
            order.setPrice(i * 0.1d);
            car.getOrderList().add(order);
        }

        // 3. 计算总价，这时由于第一次计算所以涉及到的每个方法都会计算一遍。这次计算完成之后Dgc会对计算结果进行缓存
        log.info("first call car getTotalPayAmount");
        car.getTotalPayAmount();

        // 4. 修改折扣后，再次计算总价。
        // 修改折扣(setDiscount)会导致 getDiscount() 和 getTotalPayAmount() 这两个方法对应的缓存数据失效，由于订单数据并没有被修改，所以 getTotalAmount() 可以直接取内存值，不需要在次进行计算。
        log.info("change discount then call car getTotalPayAmount");
        car.setDiscount(0.8);
        car.getTotalPayAmount();
    }
}
