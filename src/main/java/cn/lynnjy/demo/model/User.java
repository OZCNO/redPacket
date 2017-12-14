package cn.lynnjy.demo.model;

import com.alibaba.fastjson.JSON;


//抢红包用户user类
public class User {

    private String id;//packID
    private String nickname;//微信昵称
    private String imgUrl;//微信头像
    private double grabMoney;//抢到的钱
    private String picUrl;//照片
//    private int happy;//笑容
//    private int beauty;//颜值
//
//    public int getHappy() {
//        return happy;
//    }
//
//    public void setHappy(int happy) {
//        this.happy = happy;
//    }
//
//    public int getBeauty() {
//        return beauty;
//    }
//
//    public void setBeauty(int beauty) {
//        this.beauty = beauty;
//    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }




    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public double getGrabMoney() {
        return grabMoney;
    }

    public void setGrabMoney(double grabMoney) {
        this.grabMoney = grabMoney;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }


}
