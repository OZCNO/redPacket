package cn.lynnjy.demo.controller;


import cn.lynnjy.demo.model.LocalMoney;
import cn.lynnjy.demo.util.CacheManage;
import cn.lynnjy.demo.pojo.LuckyMoney;
import cn.lynnjy.demo.service.RedisManage;
import cn.lynnjy.demo.util.ResponseMessage;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//用于发红包的控制器
public class SendController {

    @Autowired
    private RedisManage redisManage;

    @PostMapping(value = "/send")
    public JSONObject sendLuckyMoney(@RequestBody LuckyMoney luckyMoney){

        //获取LuckMoney属性
        int size = luckyMoney.getSize();
        String sender = luckyMoney.getSender();
        String wishes = luckyMoney.getWishes();

//        System.out.println(luckyMoney.getSize()+" "+luckyMoney.getMoney());
        //建立红包本地缓存
        LocalMoney localMoney = CacheManage.getLocalPack(size,sender,wishes);
        //存入redis key = (int)本地缓存对象的packid  value = luckyMoney对象
        redisManage.sendLuckyMoney(localMoney.getPackID(),luckyMoney);
        //反馈
        return ResponseMessage.ok();
    }

}
