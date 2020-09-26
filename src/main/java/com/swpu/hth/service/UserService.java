package com.swpu.hth.service;


import com.swpu.hth.entity.UserDO;

import java.util.List;

/**
 * 用户
 */

public interface UserService {

    /**
     *
     * @param username 用户名
     * @param pwd 密码
     * @param license 车牌号
     * @param sex 性别
     * @param tel 电话
     * @param drive_age 驾龄
     * @param cartype 车辆类型（大小）
     */
    String registerUser(String username,String pwd,String license,String sex,String tel,int drive_age,int cartype ) throws Exception;

    /**
     *
     * @param username 用户名
     * @return successed or failed
     */
    String login(String username,String pwd) throws Exception;

    /**
     *  查询所有用户数据
     * @return
     */
    List<UserDO> queryAll() throws Exception;

    /**
     *  根据用户名查询对应用户数据
     * @return
     */
    List<UserDO> queryByName(String username) throws Exception;
}
