package com.xj.tribune.controller;


import com.xj.tribune.service.IUserService;
import com.xj.tribune.util.ValidateImageCodeUtils;
import com.xj.tribune.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    @Autowired
    private IUserService service;

    @GetMapping("/index")
    public String findAll(Model model) {
        List<User> users = service.findAll();
        if (!CollectionUtils.isEmpty(users)) {
            model.addAttribute("users", users);
        }
        return "view/index";
    }

    @PostMapping("/register")
    public String register(User user, String code, HttpSession session) {
        // 生成的验证码
        String sessionCode = (String)session.getAttribute("code");
        // 忽略大小写, 比较用户输入的验证码与生成的验证码
        if (sessionCode.equalsIgnoreCase(code)) {
            // 注册
            long registerId = service.register(user);
            log.info("用户编号["+registerId+"]注册成功");
            // 注册成功跳转到登录界面
            return "redirect:/api/view/login";
        } else {
            // 输入错误
            // 注册失败跳转到注册界面
            return "redirect:/api/view/toRegister";
        }
    }

    @PostMapping("/login")
    public String login(String username, String password, HttpSession session) {
        User login = service.login(username, password);
        if (login != null) {
            session.setAttribute("user", login);
            log.info("用户名["+username+"]登录成功");
            // 跳转到查询所有
            return "redirect:/api/user/index";
        } else {
            // 跳转回到登录
            return "redirect:/api/view/login";
        }
    }

    @GetMapping("/code")
    public void getImage(HttpSession session, HttpServletResponse response) throws IOException {
        // 生成验证码
        String securityCode = ValidateImageCodeUtils.getSecurityCode();
        BufferedImage image = ValidateImageCodeUtils.createImage(securityCode);
        // 存入session作用域中
        session.setAttribute("code", securityCode);
        // 响应图片
        ServletOutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }
}
