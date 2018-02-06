package com.rain.dao.mapper;

import java.util.List;

public interface ExcelDataMapper{

    int insertPriceCata(List list);

//    @Select({
//            "SELECT * FROM aog_price_catalugue_2007 WHERE PART_NUMBER = #{PART_NUMBER}"
//    })
//    List<User> selectPriceCata(@Param("PART_NUMBER") String returnNo);

}