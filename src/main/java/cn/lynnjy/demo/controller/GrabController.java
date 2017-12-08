package cn.lynnjy.demo.controller;

import cn.lynnjy.demo.service.RedisManage;
import cn.lynnjy.demo.util.CacheManage;
import cn.lynnjy.demo.util.ResponseMessage;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
//用于抢红包的控制器
public class GrabController {

    @Autowired
    private RedisManage redisManage;


//    /**
//     * 抢红包
//     * @param id
//     * @return jsonobj
//     */
//    @GetMapping(value = "/receive/get/{id}")
//    public JSONObject grab(@PathVariable("id") String id){
//        //红包id 根据获取到的红包id 取出本地LocalMoney对象并插入ip地址
//        if(CacheManage.addUser(id)){
//            return ResponseMessage.ok();
//        }else
//            return ResponseMessage.error("你来晚了，红包已被抢光！");
//    }

    /**
     * 抢红包
     * @param id,nickname
     * @return jsonobj
     */
    @GetMapping(value = "/receive/get")
    public JSONObject grab(@RequestParam("id") String id, @RequestParam("nickname") String nickname){
        //红包id 根据获取到的红包id 取出本地LocalMoney对象并插入ip地址
        if(CacheManage.addUser(id,nickname)){
            return ResponseMessage.ok();
        }else
            return ResponseMessage.error("你来晚了，红包已被抢光！");
    }


    /**
     * 拆红包
     * @param id
     * @return jsonobj
     */
    @GetMapping(value = "/receive/open")
    public JSONObject open(@RequestParam("id") String id, @RequestParam("nickname") String nickname){
        //判断该ip是否在list内
        if(!CacheManage.checkUser(id,nickname)){
            return ResponseMessage.error("你来晚了，红包已被抢光！");
        }
        else {
            double money = redisManage.modifyLuckyMoney(id);
            if (money == 0.0){
//                CacheManage.remove(id);
                return ResponseMessage.error("红包已被抢完！");
            }
            CacheManage.addMoney(id,nickname,money);
            return ResponseMessage.ok(money);

        }
    }
}

