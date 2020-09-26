package com.swpu.hth.service.impl;

import com.swpu.hth.dao.UserDao;
import com.swpu.hth.entity.UserDO;
import com.swpu.hth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public String registerUser(String username, String pwd, String license, String sex, String tel, int drive_age, int cartype) throws Exception{
        UserDO user = new UserDO().setUsername(username).setPwd(pwd).setLicense(license).setSex(sex).setTel(tel).setDrive_age(drive_age).setCartype(cartype);
        if(userDao.isContainsUser(username) == null){
            System.out.println(license);
            userDao.register(user);
            return "success";
        }
        return "failed";
    }

    /**
     *
     * @param username 用户名
     * @param pwd
     * @return
     */
    @Override
    public String login(String username,String pwd) throws Exception{
        UserDO userDOL = userDao.login(username);
        if(userDOL==null){
            return "用户名不存在";
        }
        if(userDOL.getPwd().equals(pwd)){
            return "successed";
        }
        return "failed";
    }

    @Override
    public List<UserDO> queryAll() throws Exception{
        return userDao.queryAll();
    }

    @Override
    public List<UserDO> queryByName(String username) throws Exception{
        return userDao.queryByName(username);
    }
}