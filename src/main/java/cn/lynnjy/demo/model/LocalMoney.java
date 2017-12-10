package cn.lynnjy.demo.model;

import java.util.*;

//本地红包类操作
public class LocalMoney {

    private String packID;//红包id
    private String sender;//发红包老哥微信昵称
    private List<String> nickNameList;//暂时存储用户nickname
    private List<User> list;//存user对象的list 用于拆红包时存入相应金额
    private int size;//红包个数
    private String wishes;//红包祝福语

    public String getSender() {
        return sender;
    }

    public List<User> getList() {
        return list;
    }

    /**
     * 初始化构造方法 接收数据
     * @param size
     * @param sender
     */
    public LocalMoney(int size,String sender,String wishes) {
        //        userGrabs = new HashMap<>();
        this.size = size;
        nickNameList = new LinkedList<>();
        list = new LinkedList<>();
        this.sender = sender;
        this.wishes = wishes;
        packID = UUID.randomUUID().toString();
        System.out.println(packID);
    }




    //
    /**
     * 以给nickNameList存nickname形式暂时存放用户
     * @param nickname
     * @return
     */
    public boolean addUser(String nickname){
        if(size > nickNameList.size()){
            System.out.println("添加抢红包用户: "+nickname);
//            User user = new User();
//            user.setNickname(nickname);
            nickNameList.add(nickname);
            return true;
        }else
            return false;
    }


    /**
     * redis处理后取钱 实例化User并存入list
     * @param nickname
     * @param money
     * @return
     */
    public boolean addMoney(String nickname,Double money,String imgUrl){

        if (checkUser(nickname)){
            User user = new User();
            user.setNickname(nickname);
            user.setGrabMoney(money);
            user.setImgUrl(imgUrl);
            //插入
            list.add(user);
            System.out.println("用户: "+nickname+" 拆开红包获得: "+money+"元");
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



    //Getters
    /**
     * 获取红包id
     * @return String packID
     */
    public String getPackID() {
        return packID;
    }

    public String getWishes(){
        return wishes;
    }

    public int getSize() {
        return size;
    }
    /**
     * 返回已抢红包人数
     * @return
     */
    public int getGrabbedSize(){
        return nickNameList.size();
    }





}
