package com.rain.dao.mapper;

import com.rain.dao.entity.User;

public interface UserMapper {

    int  insertUserCata(User priceList);

//    @Select({
//            "SELECT * FROM USER u WHERE u.name = name"
//    })
//   User findByUserName(@Param("name") String name);

}