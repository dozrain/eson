package com.rain.dao.mapper;

import com.rain.dao.entity.User;
import tk.mybatis.mapper.common.Mapper;

public interface loginMapper extends Mapper{

    int  insertUserCata(User priceList);

//    @Select({
//            "SELECT * FROM USER u WHERE u.name = name"
//    })
//   User findByUserName(@Param("name") String name);

}