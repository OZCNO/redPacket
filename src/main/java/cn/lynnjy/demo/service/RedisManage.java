package cn.lynnjy.demo.service;


import cn.lynnjy.demo.model.OpenPack;
import cn.lynnjy.demo.pojo.LuckyMoney;
import cn.lynnjy.demo.util.CacheManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.concurrent.*;

@Service
public class RedisManage {
    @Autowired
    private StringRedisTemplate StringRedisTemplate;
    private ExecutorService pool;

    private static long timeOut = 1;//timeout 为 1天


    /**
     *  容器初始化bean之前
     */
    @PostConstruct
    public void init(){

        StringRedisTemplate.setEnableTransactionSupport(true);

        //newCachedThreadPool 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
        pool = Executors.newCachedThreadPool();

    }


    /**
     * destroy之前执行
     */
    @PreDestroy
    public void shutDown(){
        pool.shutdown();
    }


    /**
     * send
     * 该方法传入redis数据库 k为String v为String类型的json格式字符串 取出时应当转换为json对象
     */
    public void sendLuckyMoney(String packID , LuckyMoney lm){
        //操作字符串
        StringRedisTemplate.opsForValue().set(packID,lm.toJson(),TimeUnit.DAYS.toDays(timeOut));
    }

    /**
     * receive
     */
    public Map modifyLuckyMoney(String packID, String picUrl){
        OpenPack op = new OpenPack();
        op.setPicUrl(picUrl);
        op.setPackID(packID);


        //因频繁读取数据 此处采用乐观锁来进行开红包处理
        boolean success = false;
        while (!success){
            /*Future 表示异步计算的结果。
            它提供了检查计算是否完成的方法，以等待计算的完成，并获取计算的结果。
            计算完成后只能使用 get 方法来获取结果，
            如有必要，计算完成前可以阻塞此方法。*/
            Future<Object> future = pool.submit(()->StringRedisTemplate.execute(op));
            try {
                if (future.get() == null){
                    continue;
                }
            }catch (InterruptedException | ExecutionException e){
                throw new RuntimeException("领取红包出错");
            }

            success = true;


        }


//        CacheManage.delUser(packID,nickname);
//        return op.getMoney();
//        return op.getWmoney();
        return op.getResultMap();
    }










}
