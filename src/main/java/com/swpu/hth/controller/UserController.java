package com.swpu.hth.controller;

import com.swpu.hth.entity.UserDO;
import com.swpu.hth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestParam("username") String username,
                                @RequestParam("pwd") String pwd,
                                @RequestParam("license") String license,
                                @RequestParam("sex") String sex,
                                @RequestParam("tel") String tel,
                                @RequestParam("drive_age") int drive_age,
                                @RequestParam("cartype") int cartype)throws Exception{
        return userService.registerUser(username,pwd,license,sex,tel,drive_age,cartype);
    }

    //登录
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("pwd") String pwd) throws Exception{
        return userService.login(username,pwd);
    }

    @GetMapping("/queryAll")
    public List<UserDO> queryAl() throws Exception{
        return userService.queryAll();
    }

    @PostMapping("/byname")
    public List<UserDO> querybyname(@RequestBody HashMap<String,String> hashmap) throws Exception{
        String username = hashmap.get("username");
        return userService.queryByName(username);
    }
}
