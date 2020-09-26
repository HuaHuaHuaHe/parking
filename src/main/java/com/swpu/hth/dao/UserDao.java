package com.swpu.hth.dao;

import com.swpu.hth.entity.UserDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {

    /**
     *
     * 查询用户信息是否存在
     *
     * @param username 用户名
     */
    @Select("SELECT cartype FROM user where username = #{username}")
    Integer isContainsUser(String username);

    /**
     *
     * 插入用户信息（用于注册）
     *
     * @param user 用户
     */
    @Insert("INSERT INTO user (username, pwd, license, sex, tel, drive_age, cartype) VALUES (#{username}, #{pwd}, #{license}, #{sex}, #{tel}, #{drive_age}, #{cartype})")
    void register(UserDO user);


    @Select("select * from user where username=#{username}")
    UserDO login(String username);

    /**
     *  byName查询
     * @param username
     * @return
     */
    @Select("SELECT *FROM user where username = #{username}")
    List<UserDO> queryByName(String username);

    /**
     *  byName查询
     * @param
     * @return
     */
    @Select("SELECT *FROM user")
    List<UserDO> queryAll();

}

