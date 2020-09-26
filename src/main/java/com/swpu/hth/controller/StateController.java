package com.swpu.hth.controller;

import com.swpu.hth.entity.StateDO;
import com.swpu.hth.service.StateService;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/state")
public class StateController{

    @Autowired
    @Qualifier("stateServiceImpl")
    private StateService stateService;

    @PostMapping("/query")
    public StateDO query(@RequestBody HashMap<String, Integer> hashMap) throws Exception {
        int spot_id = hashMap.get("spot_id");
        System.out.println(spot_id);
        return stateService.querystate(spot_id);
    }

    @PostMapping("update")
    public void update(@RequestParam("spot_id") int spot_id,
                       @RequestParam("state")int state)throws Exception{
        StateDO stateDO = new StateDO().setSpot_id(spot_id).setState(state);
        stateService.updateState(stateDO);
    }
}
