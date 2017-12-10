package cn.lynnjy.demo.util;

import cn.lynnjy.demo.model.LocalMoney;

import java.util.concurrent.ConcurrentHashMap;


//本地缓存管理类
public class CacheManage {
    private static ConcurrentHashMap<String, LocalMoney> datas = new ConcurrentHashMap<>();//用静态hashmap来存放本地缓存对象

    public static ConcurrentHashMap<String, LocalMoney> getDatas() {
        return datas;
    }

    /**
     * 该方法用于获取本地localMoney 最终用于罗列各自抢到的红包
     * @param packID
     * @return几
     */
    public static LocalMoney getFromDatas(String packID){
        LocalMoney localMoney  = datas.get(packID);
        return localMoney;
    }





    /**
     * 该方法用于把本地对象localMoney 存放到ConcurrentHashMap
     * @param size
     * @param sender
     * @return localMoney本地缓存对象
     */
    public static LocalMoney getLocalPack(int size,String sender,String wishes){
        LocalMoney localMoney = new LocalMoney(size,sender,wishes);
//        System.out.println(size);

        try{
            datas.put(localMoney.getPackID(), localMoney);
        }catch (Exception e){
            e.printStackTrace();


        }finally {
            return localMoney;
        }

    }

    /**
     * 该方法用于List插入拆红包用户的ip(或微信id)
     * @return  boolean
     */
    public static boolean addUser(String packID,String nickname){

        //从hashmap中取出localMoney对象
        LocalMoney localMoney = datas.get(packID);
        return localMoney.addUser(nickname);

    }

    /**
     * 该方法用于ip对比 同一个ip只能领一次
     * @return  boolean
     */
    public static boolean checkUser(String packID,String nickname){


        LocalMoney localMoney = datas.get(packID);

        return localMoney.checkUser(nickname);
    }

    public static void delUser(String packID,String nickname){
        LocalMoney localMoney = datas.get(packID);

        localMoney.delUser(nickname);
    }

    public static void addMoney(String packID,String nickname,double money,String imgUrl){
        //从hashmap中取出localMoney对象
        LocalMoney localMoney = datas.get(packID);
        localMoney.addMoney(nickname,money,imgUrl);
    }


    /**
     * 红包领完 remove该红包
     */
    public static void remove(String packID){
        if(datas.contains(packID)){
            datas.remove(packID);
        }
    }





}
