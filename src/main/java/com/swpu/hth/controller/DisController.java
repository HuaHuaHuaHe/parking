package com.swpu.hth.controller;

import com.alibaba.fastjson.JSONObject;
import com.swpu.hth.entity.DistributedDO;
import com.swpu.hth.service.DisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dis")
public class DisController {

    @Autowired
    DisService disService;

    @GetMapping("/query")
    public String queryDis(@RequestParam String username,
                                  @RequestParam String license) throws Exception{
        DistributedDO sids = disService.queryByName(username,license);
        if(null == sids) return "failed";
        double lat = sids.getLat();
        double lng = sids.getLng();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("lat",""+lat);
        jsonObject.put("lng",""+lng);
        return jsonObject.toJSONString();
    }
}
