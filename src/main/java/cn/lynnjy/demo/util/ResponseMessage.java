package cn.lynnjy.demo.util;

import cn.lynnjy.demo.model.LocalMoney;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Array;
import java.util.Iterator;

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

    public static JSONObject error(String message) {
        JSONObject res = new JSONObject();
        res.put("errcode", 500);
        res.put("errmsg", message);
        return res;
    }

    public static JSONObject list(LocalMoney localMoney){
        JSONObject res = new JSONObject();
        int size = localMoney.getSize();
        res.put("size",size);

        //        Iterator iter = localMoney.getList().iterator();
//
//        while (iter.hasNext()){
//
//            res.put("userGrabs",iter.next());
//        }
        String GrabsObjects = JSON.toJSONString(localMoney.getList());
        res.put("grabsList",GrabsObjects);
        res.put("errcode", 200);
        res.put("errmsg", "OK");
        return res;

    }
}
