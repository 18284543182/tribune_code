package com.xj.tribune.controller;


import com.xj.tribune.service.IUserService;
import com.xj.tribune.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    @Autowired
    private IUserService service;

    @GetMapping("/findAll")
    public String findAll(Model model) {
        List<User> users = service.findAll();
        if (!CollectionUtils.isEmpty(users)) {
            model.addAttribute("users", users);
        }
        return "index";
    }

    @GetMapping("/index")
    public String index(){
        return "login";
    }

    @PostMapping("/login")
    public String login(String username, String password, HttpSession session) {
        User login = service.login(username, password);
        if (login != null) {
            session.setAttribute("user", login);
            log.info("用户名["+username+"]登录成功");
            // 跳转到查询所有
            return "redirect:/api/user/findAll";
        } else {
            // 跳转回到登录
            return "redirect:/index";
        }
    }
}
