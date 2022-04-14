package com.xj.tribune.service;

import com.xj.tribune.vo.User;
import java.util.List;

public interface IUserService {

    List<User> findAll();

    User login(String username, String password);

    long register(User user);
}
