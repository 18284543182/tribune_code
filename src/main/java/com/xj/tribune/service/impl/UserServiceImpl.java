package com.xj.tribune.service.impl;

import com.xj.tribune.mapper.UserMapper;
import com.xj.tribune.service.IUserService;
import com.xj.tribune.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        try{
            List<User> users = userMapper.findAll();
            return users;
        }catch (Exception e){
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public User login(String username, String password) {
        return userMapper.login(username, password);
    }
}
