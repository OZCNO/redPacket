package cn.lynnjy.demo.model;

import java.util.*;

//本地红包类操作
public class LocalMoney {

    private String packID;//红包id
    private String sender;//发红包的老哥
    private List<String> nickNameList;
    private List<User> list;//存user对象的list 用于拆红包时存入相应金额
    private int size;//红包个数


    public List<User> getList() {
        return list;
    }

    /**
     * 初始化构造方法 接收数据
     * @param size
     * @param sender
     */
    public LocalMoney(int size,String sender) {
        //        userGrabs = new HashMap<>();
        this.size = size;
        nickNameList = new LinkedList<>();
        list = new LinkedList<>();
        this.sender = sender;
        packID = UUID.randomUUID().toString();
        System.out.println(packID);
    }


    //Getters
    /**
     * 获取红包id
     * @return String packID
     */
    public String getPackID() {
        return packID;
    }

    public int getSize() {
        return size;
    }

//    public Map<String, String> getUserGrabs() {
//        return userGrabs;
//    }

    //
    /**
     * 以给nickNameList存nickname形式暂时存放用户
     * @param nickname
     * @return
     */
    public boolean addUser(String nickname){
        if(size > list.size()){
            System.out.println(nickname);
//            User user = new User();
//            user.setNickname(nickname);
            nickNameList.add(nickname);
            return true;
        }else
            return false;
    }




    public boolean addMoney(String nickname,Double money){

        if (checkUser(nickname)){
            User user = new User();
            user.setNickname(nickname);
            user.setGrabMoney(money);
            //插入
            list.add(user);
            return true;
        }
        else
            return false;
    }

    public boolean checkUser(String nickname){
        return nickNameList.contains(nickname);
    }

    public void delUser(String nickname){
        nickNameList.remove(nickname);
    }




}
