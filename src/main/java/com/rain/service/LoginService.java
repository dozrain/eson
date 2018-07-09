package com.rain.service;


import com.rain.config.constans.ParamConstants;
import com.rain.utils.framework.context.AppContext;
import com.rain.utils.framework.context.ContextUtil;
import com.rain.utils.framework.error.ErrorData;
import com.rain.utils.framework.exception.SystemException;
import com.rain.utils.framework.result.Result;
import com.rain.mapper.UserMapper;
import com.rain.model.dao.User;
import com.rain.utils.framework.cookie.CookieUtil;
import com.rain.utils.encrypt.Security;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;


/**
 * Created by mail on 2017\12\12 0010.
 */
@Service
public class LoginService {

    @Resource
    UserMapper userMapper;
    @Resource
    Security security;

    //用户登录/
    public Result loginGo(){
        AppContext appContext = ContextUtil.getAppContext();
        String username = appContext.getSafeStringParam("logname");
        String password = appContext.getSafeStringParam("logpass");
        User user = userMapper.selectAccountByUsername(username);
        if(user == null||username.isEmpty()){
            SystemException.throwCustomException(ErrorData.ERR_USERNAME_LOGIN);
        }
        password = security.hashLoginPwd(password, user.getSalt());
        Result result = Result.buildSuccess();
        if(user.getPassword().equals(password)){
            String sessionKey = security.generateSessionKey();

//            iSession.set(sessionKey, JSONObject.toJSONString(user), ParamConstants.USER_REDIS_EXPIRE_TIME);
            CookieUtil.addCookie(appContext.getHttpServletResponse(), ParamConstants.KEY_SESSIONKEY_PARAM,
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
//            iSession.delete(sessionKey);
            CookieUtil.deleteCookie(appContext.getHttpServletRequest(),appContext.getHttpServletResponse(),
                                    ParamConstants.KEY_SESSIONKEY_PARAM);
        }
        return Result.buildSuccess();
    }
}
