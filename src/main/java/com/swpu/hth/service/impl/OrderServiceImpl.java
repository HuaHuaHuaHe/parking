package com.swpu.hth.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.swpu.hth.dao.OrderDao;
import com.swpu.hth.entity.OrderReDO;
import com.swpu.hth.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    @DS(value = "all-ds")
    @GlobalTransactional
    public int createOrder(String username, String license, Date time_re, float r1, float tr, double lat, double lng) throws Exception {
        OrderReDO orderre = new OrderReDO().setUsername(username).setLicense(license).setTime_re(time_re).setR1(r1).setTr(tr).setLat(lat).setLng(lng);
        return orderDao.saveOrderRe(orderre);
    }

    @Override
    public void updateOrder(double lat, double lng, int i) throws Exception {
        orderDao.updateOrder(lat,lng,i);
    }

    @Override
    public void updateOrders(int tr, int i) throws Exception {
        orderDao.updateOrders(tr,i);
    }

    @Override
    public OrderReDO queryOrder(String username, String license) throws Exception {
        return orderDao.qureyOrder(username, license);
    }

    @Override
    public ArrayList<OrderReDO> queryReOrder() throws Exception {
        return orderDao.qureyOrders();
    }


}
