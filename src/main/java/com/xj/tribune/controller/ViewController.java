package com.xj.tribune.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 直接访问templates下的静态页面是无法获取static中的样式的
 * 用该控制器进行去访问, 该控制器没有其他作用, 只是为了访问界面
 * @author xj
 * @date 2022-04-14
 */
@Controller
@RequestMapping("/api/view")
public class ViewController {

    @GetMapping("/login")
    public String login() {
        return "view/login";
    }

    @GetMapping("/toRegister")
    public String toRgsiter() {
        return "view/regist";
    }

    @GetMapping("/index")
    public String toIndex() {
        return "view/index";
    }

    @GetMapping("/rabbitmq")
    public String rabbit(){
        return "view/rabbitmq";
    }


}
