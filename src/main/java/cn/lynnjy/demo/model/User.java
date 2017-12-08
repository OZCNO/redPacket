package cn.lynnjy.demo.model;

import com.alibaba.fastjson.JSON;

public class User {


    private String nickname;
    private double grabMoney;

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

    public String toJson(){

        return JSON.toJSONString(this);
    }

}
