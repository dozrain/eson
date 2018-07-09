package com.rain.service.user;

import com.rain.utils.framework.context.AppContext;
import com.rain.utils.framework.context.ContextUtil;
import com.rain.utils.framework.result.Result;
import com.rain.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017\11\10 0010.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public Result registration(){
        AppContext appContext = ContextUtil.getAppContext();
        return new Result();
    }

    public Result cancel(){
        return new Result();
    }
}
