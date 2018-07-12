package com.rain.mapper;

import com.rain.model.dao.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;
import java.util.Map;

public interface MessageMapper extends Mapper<Message> {

     List getMessageByParams(Map map);

     @Insert("insert into MESSAGE  (cmpcode,sitecode,sitename,w_partno,t_partno,num) " +
             "values(#{cmpcode},#{sitecode},#{sitename},#{wPartno},#{tPartno},#{num})")
     int insertLunziStockInfoByPkid(@Param("id") String id, @Param("sitecode") String sitecode,
                                    @Param("sitename") String sitename, @Param("wPartno") String wPartno,
                                    @Param("tPartno") String tpartno, @Param("num") Integer num);
}