package com.swpu.hth.controller;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.swpu.hth.dao.FingerDao;
import com.swpu.hth.entity.FingerDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locate")
public class WifiFingerController {

    //指纹数据库值存放在 fingerDOList中
    private List<FingerDO> fingerDOList;

    @Autowired
    private FingerDao fingerDao;

    /**
     *
     * @param mac AP唯一地址
     * @param rssi 信号强度值
     * @return 返回定位位置
     * @throws Exception
     */
    @DS(value = "all-ds")
    @PostMapping("/wifi")
    public float wifiFingerLocate(@RequestParam("mac") String mac,
                                     @RequestParam("rssi") int rssi)throws Exception{
        //查询指纹数据库中所有的值
        fingerDOList = fingerDao.queryAll();
        System.out.println(fingerDOList.get(1).getMac());
        //根据输入的 mac地址和 rssi来确定位置

        return 0.0f;
    }

}
