package com.rain.mapper;

import com.rain.model.dao.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

public interface UserMapper extends Mapper{

    User selectAccountByUsername(String username);

    List<User> selectAll();
}