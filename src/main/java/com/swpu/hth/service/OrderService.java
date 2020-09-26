package com.swpu.hth.service;

import com.swpu.hth.entity.OrderReDO;

import java.util.ArrayList;
import java.util.Date;

/**
 * 订单
 */
public interface OrderService {
    /**
     * 创建订单
     *
     * @param username 用户名
     * @param license 车牌号
     * @param time_re 预约时间（订单创建时间）
     * @throws Exception 创建订单失败，抛出异常
     */
    void createOrder(String username, String license, Date time_re, float r1, float tr, double lat, double lng) throws Exception;


    void updateOrder(double r1, double tr, int i) throws Exception;

    void updateOrders(int tr, int i) throws Exception;

//
//    /**
//     * 保存历史订单
//     *
//     * @param username 用户名
//     * @param license 车牌号
//     * @param time_re 预约时间（订单创建时间）
//     * @param time_in in
//     * @param time_out out
//     * @throws Exception 创建订单失败，抛出异常
//     */
//    void saveOrderHis(String username, String license, String spot_id, Date time_re, Date time_in, Date time_out) throws Exception;
//
    /**
     * 查询预约订单信息
     * @return 订单数据
     */
    ArrayList<OrderReDO> queryReOrder() throws Exception;
}
