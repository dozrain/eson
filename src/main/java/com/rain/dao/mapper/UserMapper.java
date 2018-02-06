package com.rain.dao.mapper;

import com.rain.dao.entity.User;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@Component
public interface UserMapper extends Mapper<User>{

    User selectAccountByUsername(String username);
}