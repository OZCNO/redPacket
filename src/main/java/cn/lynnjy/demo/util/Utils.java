package cn.lynnjy.demo.util;



import cn.lynnjy.demo.pojo.LuckyMoney;

import java.util.Random;

/**
 * 工具类
 */
public class Utils {

    /**
     * 获取随机红包金额
     */
    public static double getRandomMoney(LuckyMoney luckyMoney) {
        // remainSize 剩余的红包数量
        // remainMoney 剩余的钱
        int remainSize = luckyMoney.getSize();
        if (remainSize == 1) {
            luckyMoney.setSize( remainSize - 1);
            return (double) Math.round(luckyMoney.getMoney() * 100) / 100;
        }

        if(remainSize == 0){
            return 0;
        }

        double remainMoney = luckyMoney.getMoney();
        Random r = new Random();
        double min   = 0.01;
        double max   = remainMoney / remainSize * 2;
        double money = r.nextDouble() * max;
        money = money <= min ? 0.01: money;
        money = Math.floor(money * 100) / 100;
        luckyMoney.setSize( remainSize - 1);
        luckyMoney.setMoney(remainMoney - money);
        return money;
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
