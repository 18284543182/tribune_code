package com.xj.tribune.mapper;

import com.xj.tribune.vo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户相关持久化
 * @author xj
 */
@Mapper
public interface UserMapper {
    List<User> findAll();

    void save(User user);

    User login(@Param("username") String name, @Param("password") String password);

}
