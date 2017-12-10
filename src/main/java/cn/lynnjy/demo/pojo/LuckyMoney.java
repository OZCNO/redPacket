package cn.lynnjy.demo.pojo;


import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;


@Component
//@ConfigurationProperties(prefix = "luckyMoney")
// pojo 红包类
public class LuckyMoney {


    private String sender;//发红包的老哥
    private int size;//总人数
    private double money;//总money
    private String wishes;//红包祝福语
    private String imgUrl;//头像url

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getWishes() {
        return wishes;
    }

    public void setWishes(String wishes) {
        this.wishes = wishes;
    }



    public String getSender() {
        return sender;
    }


//    public LuckyMoney(){
//        this.money = 10;
//        this.size = 10;
//    }

    /**
     * 同步方法
     * @param size
     */
    public synchronized void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

//    public void setSize(int size) {
//        this.size = size;
//    }


    public double getMoney() {
        return money;
    }


    /**
     * 同步方法
     * @param money
     */
    public synchronized void setMoney(double money) {
        this.money = money;
    }

    /**
     * @param sender
     */
    public  void setSender(String sender) {
        this.sender = sender;
    }

    public String toJson(){
        return JSON.toJSONString(this);
    }
}
