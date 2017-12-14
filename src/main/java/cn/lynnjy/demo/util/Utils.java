package cn.lynnjy.demo.util;



import cn.lynnjy.demo.model.LocalMoney;
import cn.lynnjy.demo.pojo.LuckyMoney;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qcloud.image.ImageClient;
import com.qcloud.image.request.FaceDetectRequest;

import java.util.*;

/**
 * 工具类
 */
public class Utils {

    static int APP_ID = 1255478844;
    static String SECRET_ID = "AKIDSs691jWuIVaUCpmft0wgFdw77wa3rQL6";
    static String SECRET_KEY = "0rX2MURDoX7XmrlV4Xu7D9uTr46xjR8y";
    static String bucketName = "picture-1255478844.cosgz.myqcloud.com";

    public static Map getPictureMap(String pictureUrl){

        ImageClient imageClient = new ImageClient(APP_ID, SECRET_ID, SECRET_KEY);
        // 图片内容方式
//        String faceDetectName  = "";
//        String faceDetectImage = "";
//        try {
//            String[] ss = pictureUrl.split("\\\\");
//            faceDetectName = ss[ss.length-1];
//            faceDetectImage = CommonFileUtils.getFileContent(pictureUrl);
//        } catch (Exception ex) {
//            System.out.println(ex);
//        }
//        FaceDetectRequest faceDetectReq = new FaceDetectRequest(bucketName, faceDetectName, faceDetectImage, 1);
        // 1. url方式
        String faceDetectUrl = pictureUrl;
        FaceDetectRequest faceDetectReq = new FaceDetectRequest(bucketName, faceDetectUrl, 1);

        //结算结果
        String ret = imageClient.faceDetect(faceDetectReq);
        imageClient.shutdown();
        JSONObject result = JSON.parseObject(ret);
        Map resultMap = new HashMap();
        //判断是否识别成功
        if(result.getInteger("code")==0){
            resultMap.put("code",0);
        }else{
            resultMap.put("code",-1101);
            return resultMap;
        }

        JSONObject face = result.getJSONObject("data").getJSONArray("face").getJSONObject(0);
        //放入map

        resultMap.put("beauty",face.getInteger("beauty"));
        resultMap.put("age",face.getInteger("age"));
        resultMap.put("happy",face.getInteger("expression"));
        resultMap.put("sex",face.getInteger("gender") > 50 ? "male":"female");

        Double weight = 1.0 + ((double)face.getInteger("expression")-35)/100;
        resultMap.put("weight",weight);

        return resultMap;
    }

    /**
     * 获取随机红包金额
     */
    public static Map getRandomMoney(LuckyMoney luckyMoney, String picUrl) {
        double money;
        double wmoney;
        Map resultMap = getPictureMap(picUrl);
        if((Integer)resultMap.get("code")!=0){
            return resultMap;
        }

        Double weight = (Double) resultMap.get("weight");
        // remainSize 剩余的红包数量
        // remainMoney 剩余的钱
        int remainSize = luckyMoney.getSize();
        if (remainSize == 1) {
            luckyMoney.setSize( remainSize - 1);
            wmoney = (double) Math.round(luckyMoney.getMoney() * 100) / 100;
            money = wmoney;
            resultMap.put("money",money);
            resultMap.put("wmoney",wmoney);

            return resultMap;
        }

        if(remainSize == 0){
            resultMap.put("money",0.0);
            resultMap.put("wmoney",0.0);
            return resultMap;
        }

        double remainMoney = luckyMoney.getMoney();
        Random r = new Random();
        double min   = 0.01;
        double max   = remainMoney / remainSize * 2;
        money = r.nextDouble() * max;
        //乘权重
        wmoney = money * weight;

        wmoney = wmoney >= remainMoney ? 0.99*remainMoney : wmoney ;

        money = money <= min ? 0.01: money;
        money = Math.floor(money * 100) / 100;
        wmoney = wmoney <= min ? 0.01: wmoney;
        wmoney = Math.floor(wmoney * 100) / 100;


        luckyMoney.setSize( remainSize - 1);
        luckyMoney.setMoney(remainMoney - wmoney);

        resultMap.put("money",money);
        resultMap.put("wmoney",wmoney);
        return resultMap;
    }


    /**
     * 获取未完成的红包列表
     * @return
     */
    public static List<LocalMoney> getPackList(){
        List<LocalMoney> list = new LinkedList<>();
        for (LocalMoney localMoneys : CacheManage.getDatas().values()){
            if (localMoneys.getGrabbedSize()<localMoneys.getSize()){
                list.add(localMoneys);
            }
            else
                continue;
        }
        return list;
    }



    public static Map getStr(Map resultMap){

        String str1 = "";
        String str2 = "";
        String str3 = "";

        String age_rank = "";
        String sex_rank = "";
        String happy_rank = "";
        String beauty_rank = "";
        int age,sex,happy,beauty;
        String[] rank = {"a","b","c","d","e"};
        age = Integer.parseInt(resultMap.get("age").toString());
        sex_rank = resultMap.get("sex").toString();
        happy = Integer.parseInt(resultMap.get("happy").toString());
        beauty = Integer.parseInt(resultMap.get("beauty").toString());

//        if(sex > 50)
//            sex_rank = "male";
//        else
//            sex_rank = "female";

        if(age < 18)
            age_rank = rank[0];
        else if (age < 30)
            age_rank = rank[1];
        else if (age < 50)
            age_rank = rank[2];
        else if (age < 75)
            age_rank = rank[3];
        else
            age_rank = rank[4];


        for(int i=0;i<4;i++){
            if(happy < i*25) {
                happy_rank = rank[i];
                break;
            }
        }


        if(beauty < 25)
            beauty_rank = rank[0];
        else if (beauty < 50)
            beauty_rank = rank[1];
        else if (beauty < 70)
            beauty_rank = rank[2];
        else
            beauty_rank = rank[3];

        switch (sex_rank+age_rank){
            case "malea":str1="小仙男说的就是你啦！";break;
            case "maleb":str1="年轻人，你且站在这里不要动，我去给你买几个橘子。";break;
            case "malec":str1="你在男人最黄金的年龄段。";break;
            case "maled":str1="大伯，年龄不是问题！";break;
            case "malee":str1="老爷爷，笑一笑十年少！";break;
            case "femalea":str1="正处青春年华，如春花初放，似莲之清纯。";break;
            case "femaleb":str1="正处成熟的时期，如玉兰般优雅，风姿绰约。";break;
            case "femalec":str1="正处知性稳重的时期，如流水潺潺，清净而不张狂。";break;
            case "femaled":str1="正处花甲之年，如水仙般恬静，处世自如。";break;
            case "femalee":str1="正处喜寿之年，但愿得，河清长寿。";break;
        }

        switch (happy_rank){
            case "a":str2="再不笑你就面瘫啦！";break;
            case "b":str2="害羞啥呢？";break;
            case "c":str2="如果坏心情是一种病，那你的笑就是药。";break;
            case "d":str2="你笑得像个200斤的孩子。";break;
        }

        switch (beauty_rank){
            case "a":str3="人见人哀，花见花败，车见车爆胎。";break;
            case "b":if (sex_rank.equals("female"))str3="你很美，只是美的不够具体。";else str3="只是在人群中多看了你一眼，仅此而已";break;
            case "c":str3="就算没有沉鱼落雁之容，中人之姿总是有的";break;
            case "d":if (sex_rank.equals("female"))str3="人见人爱，花见花开，阳光为你绽放。";else str3="三百六十度无死角的仰慕，简直帅绝人寰";break;
        }

        resultMap.put("sexAndAge_rank",str1);
        resultMap.put("happy_rank",str2);
        resultMap.put("beauty_rank",str3);


        return resultMap;
    }


//    public static void main(String[] args){
//
//        LuckyMoney luckyMoney = new LuckyMoney();
//        for(int i = 0; i < 10; i++){
//            System.out.println(getRandomMoney(luckyMoney));
//            System.out.println(luckyMoney.getMoney());
//            System.out.println(luckyMoney.getSize());
//            System.out.println("-----------------------------------");
//        }
//    }
}
