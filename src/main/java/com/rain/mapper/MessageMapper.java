package com.rain.mapper;

import com.rain.model.dao.Message;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;
import java.util.Map;

public interface MessageMapper extends Mapper<Message> {

     List getMessageByParams(Map map);
}