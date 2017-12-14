package cn.lynnjy.demo.controller;

import cn.lynnjy.demo.model.User;
import cn.lynnjy.demo.service.RedisManage;
import cn.lynnjy.demo.util.CacheManage;
import cn.lynnjy.demo.util.ResponseMessage;
import cn.lynnjy.demo.util.Utils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
     * @param
     * @return jsonobj
     */
    @PostMapping(value = "/receive/get")
    public JSONObject grab(@RequestBody User user){
        //红包id 根据获取到的红包id 取出本地LocalMoney对象并插入ip地址
        if(CacheManage.addUser(user.getId(), user.getNickname())){
            return ResponseMessage.ok();
        }
        if(CacheManage.checkUser(id," "+user.getNickname())){
            return ResponseMessage.error("已抢过该红包");
        }
        else
            return ResponseMessage.error("你来晚了，红包已被抢光！");
    }


    /**
     * 拆红包
     * @param
     * @return jsonobj
     */
    @PostMapping(value = "/receive/open")
    public JSONObject open(@RequestBody User user){
        String id = user.getId();
        String nickname = user.getNickname();
        String imgUrl = user.getImgUrl();
        String picUrl = user.getPicUrl();

        //判断该用户是否在list内
        if(!CacheManage.checkUser(id,nickname)){
            return ResponseMessage.error("你来晚了，红包已被抢光！");
        }

        else {
//            double money = redisManage.modifyLuckyMoney(id);
            Map resultMap = redisManage.modifyLuckyMoney(id,picUrl);

            //人脸识别失败 返回500
            if((int)resultMap.get("code")!=0)
                return ResponseMessage.error("人脸识别失败！");

            double wmoney = (double)resultMap.get("wmoney");
            if (wmoney == 0.0)
//                CacheManage.remove(id);
                return ResponseMessage.error("红包已被抢完！");


            CacheManage.addMoney(id,nickname,wmoney,imgUrl);
            return ResponseMessage.okMap(Utils.getStr(resultMap));

        }
    }
}

