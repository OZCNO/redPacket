package cn.lynnjy.demo.model;

import cn.lynnjy.demo.pojo.LuckyMoney;
import cn.lynnjy.demo.util.Utils;
import com.alibaba.fastjson.JSON;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;

import java.util.List;

public class OpenPack implements SessionCallback<List<String>>{
    private double money;
    private String packID;

    /**
     * 传值
     * @param id
     */
    public void setPackID(String id) {
        this.packID = id;
    }

    @Override
    public List<String> execute(RedisOperations redisOperations) throws DataAccessException {

        //事务监控
        redisOperations.watch(packID);
        //根据key:packID取出String 并将string转换为对象
        String savedId = (String)redisOperations.opsForValue().get(packID);
        LuckyMoney lm = JSON.parseObject(savedId,LuckyMoney.class);
        //获取该次随机money
        money  = Utils.getRandomMoney(lm);
        //修改redis数据
        redisOperations.multi();
        redisOperations.opsForValue().set(packID,lm.toJson());
        //执行
        return redisOperations.exec();

//        return null;
    }


    public double getMoney(){
        return money;
    }


}
