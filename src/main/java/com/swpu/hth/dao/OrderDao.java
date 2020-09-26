package com.swpu.hth.dao;

import com.swpu.hth.entity.OrderReDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface OrderDao {

    /**
     * 插入订单记录
     *
     * @param order 订单
     */
    @Insert("INSERT INTO orders (username, license, time_re, r1, tr, lat, lng) VALUES (#{username}, #{license}, #{time_re}, #{r1}, #{tr}, #{lat}, #{lng})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void saveOrderRe(OrderReDO order);

    @Update("update orders set lat = #{lat},lng = #{lng} where id = #{id}")
    void updateOrder(double lat,double lng, int id);

    @Update("update orders set tr = #{tr} where id = #{id}")
    void updateOrders(int tr, int id);

    @Select("Select * from orders")
    ArrayList<OrderReDO> qureyOrders();

}
