package com.swpu.hth.controller;

import com.swpu.hth.entity.ChargeDO;
import com.swpu.hth.service.ChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/charge")
public class ChargeController {

    @Autowired
    ChargeService chargeService;

    @GetMapping("/history")
    public List<ChargeDO> queryAllCharge() throws Exception {
        return chargeService.queryAll();
    }

    @PostMapping("/byname")
    public List<ChargeDO> query(@RequestBody HashMap<String,String> hashMap) throws Exception {
        return chargeService.queryByName(hashMap.get("username"));
    }

}
