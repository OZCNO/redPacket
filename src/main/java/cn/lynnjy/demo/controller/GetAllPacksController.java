package cn.lynnjy.demo.controller;


import cn.lynnjy.demo.model.LocalMoney;
import cn.lynnjy.demo.util.ResponseMessage;
import cn.lynnjy.demo.util.Utils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//获取所有可抢红包列表控制器
@RestController
public class GetAllPacksController {

    /**
     *
     * @return JSONObject
     */
    @GetMapping("/packList")
    public JSONObject packList(){
        List<LocalMoney> packList = Utils.getPackList();
        if (!packList.isEmpty()){
            return ResponseMessage.listAllPackets(packList);
        }
        else
            return ResponseMessage.error("当前没有红包，快去发一个吧!");

    }
}
