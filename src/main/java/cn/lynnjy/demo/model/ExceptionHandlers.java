package cn.lynnjy.demo.model;

import cn.lynnjy.demo.util.ResponseMessage;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;


//全局异常处理
@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public JSONObject exceptionHandler(RuntimeException re, HttpServletResponse httpServletResponse) {
        return ResponseMessage.error(re.getMessage());
    }
}
