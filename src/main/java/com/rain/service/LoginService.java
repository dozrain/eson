package com.rain.service;

import com.rain.config.context.AppContext;
import com.rain.config.context.ContextUtil;
import com.rain.config.context.ParamConstants;
import com.rain.config.exception.ErrorData;
import com.rain.config.exception.SystemException;
import com.rain.config.framework.Result;
import com.rain.dao.entity.User;
import com.rain.dao.mapper.UserMapper;
import com.rain.utils.cookie.CookieUtil;
import com.rain.utils.encrypt.SecurityService;
import com.rain.utils.sesstion.SesstionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * Created by rain on 2017\12\12 0010.
 */
@Service
public class LoginService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    SecurityService securityService;

    //用户登录
    public Result loginGo(){
        AppContext appContext = ContextUtil.getAppContext();
        String username = appContext.getSafeStringParam("logname");
        String password = appContext.getSafeStringParam("logpass");
        User user = userMapper.selectAccountByUsername(username);
        if(password == null){
            SystemException.throwCustomException(ErrorData.ERR_USERNAME_LOGIN);
        }
        password = SecurityService.hashLoginPwd(password, user.getSalt());
        Result result = Result.buildSuccess();
        if(user.getPassword().equals(password)){
            String sessionKey = securityService.generateSessionKey();
            CookieUtil.addCookie(appContext.getHttpServletResponse(),ParamConstants.KEY_SESSIONKEY_PARAM,
                                 sessionKey,ParamConstants.USER_COOKIE_EXPIRE_TIME);
        }else {
            SystemException.throwCustomException(ErrorData.ERR_PASSWORD_LOGIN);
        }
        return result;
    }
    //用户退出
    public Result loginOut(){
        AppContext appContext = ContextUtil.getAppContext();
        String sessionKey = appContext.getSessionKey();
        if(StringUtils.isNotBlank(sessionKey)){
            CookieUtil.deleteCookie(appContext.getHttpServletRequest(),appContext.getHttpServletResponse(),
                                    ParamConstants.KEY_SESSIONKEY_PARAM);
        }
        return Result.buildSuccess();
    }
}
