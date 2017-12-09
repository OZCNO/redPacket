package cn.lynnjy.demo.controller;

import cn.lynnjy.demo.pojo.LuckyMoney;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hi")
public class DemoController {


    @Autowired
    private LuckyMoney lm;

    @RequestMapping(value = "/demo",method = RequestMethod.GET)
    public String say(){
        return "demo";
    }

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public String get(){
        return ""+lm.getMoney();
    }


    @GetMapping(value = "/{name}")
    public String test(@PathVariable("name") String name){
        return name;
    }



}
