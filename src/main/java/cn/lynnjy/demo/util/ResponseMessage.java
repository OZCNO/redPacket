package cn.lynnjy.demo.util;

import cn.lynnjy.demo.model.LocalMoney;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Map;

/**
 * 响应消息。controller中处理后，返回此对象，响应请求结果给客户端。
 *
 */
public class ResponseMessage {

    public static JSONObject ok() {
        return ok("OK");
    }

    public static JSONObject ok(Object data) {
        JSONObject res = new JSONObject();
        res.put("errcode", 200);
        res.put("errmsg", "OK");
        res.put("result", data.toString());
        return res;
    }

    public static JSONObject okMap(Map map){
        JSONObject res = new JSONObject();
        res.put("errcode", 200);
        res.put("errmsg", "OK");
        res.put("result", map);
        return res;
    }

    public static JSONObject listAllPackets(Object data){
        JSONObject res = new JSONObject(true);
        res.put("errcode", 200);
        res.put("errmsg", "OK");

        res.put("result", data);
        return res;
    }

    public static JSONObject error(String message) {
        JSONObject res = new JSONObject();
        res.put("errcode", 500);
        res.put("errmsg", message);
        return res;
    }



    public static JSONObject list(LocalMoney localMoney){
        JSONObject res = new JSONObject(true);

        //获取总发红包个数并返回
        int size = localMoney.getSize();
        res.put("size",size);
        //获取已拆红包个数并返回
        int grabbedSize = localMoney.getGrabbedSize();
        res.put("grabbedSize",grabbedSize);
        //获取抢到红包微信用户列表并返回
//        String GrabsObjects = JSON.toJSONString(localMoney.getList());
        res.put("grabsList",localMoney.getList());
        //返回发送红包用户
        res.put("sender",localMoney.getSender());
        //返回祝福语
        res.put("wishes",localMoney.getWishes());
        //返回发送时间
        res.put("sendTime",localMoney.getSendTime());

        res.put("errcode", 200);
        res.put("errmsg", "OK");
        return res;

    }


//    public static JSONObject ok(Object data , String str){
//        JSONObject res = new JSONObject();
//        res.put("errcode", 200);
//        res.put("errmsg", "OK");
//        res.put("result", data);
//        res.put("str",str);
//        return res;
//    }
}
