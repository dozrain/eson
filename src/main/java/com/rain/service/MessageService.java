package com.rain.service;

import com.alibaba.fastjson.JSONObject;
import com.rain.mapper.MessageMapper;
import com.rain.model.dao.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tpeng on 2016/12/22.
 */
@Service
public class MessageService extends BaseService<Message>{

    private Logger logger = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public Mapper getMapper() {
        return messageMapper;
    }
}
