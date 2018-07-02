package com.rain.config.framework.error;

/**
 * Created by Administrator on 2017\11\17 0017.
 */
@ErrorResource
public class ErrorData {

    @ErrorMessage(value = "RES.ERROR.SUSSESS",remark = "请求成功")
    public static final int SUCCESS_CODE = 200;

    @ErrorMessage(value = "RES.ERROR.FORBIDDEN_ERROR_CODE",remark = "未登陆或登陆超时，请重新登陆")
    public static final int FORBIDDEN_ERROR_CODE = 100;

    @ErrorMessage(value = "MOBILE_NOT_LOGIN",remark = "登录超时，请重新登录")
    public static final int MOBILE_NOT_LOGIN = 101;

    @ErrorMessage(value = "ERR_USER_LOGIN_FAIL",remark = "用户名不存在")
    public static final int ERR_USERNAME_LOGIN = 103;

    @ErrorMessage(value = "ERR_USER_LOGIN_FAIL",remark = "密码错误")
    public static final int ERR_PASSWORD_LOGIN = 105;

    @ErrorMessage(value = "ERR_USER_REPEAT_CLICK",remark = "请勿多次重复点击")
    public static final int ERR_USER_REPEAT_CLICK = 104;

    @ErrorMessage(value = "RES.ERROR.NOT_FOUND_ERROR",remark = "找不到指定的内容")
    public static final int NOT_FOUND_ERROR_CODE = 404;

    @ErrorMessage(value = "RES.ERROR.REQUEST_PARAM_ERROR",remark = "服务器内部错误")
    public static final int SERVER_ERROR = 500;

    @ErrorMessage(value = "RES.ERROR.REQUEST_PARAM_ERROR",remark = "请求参数有误")
    public static final int REQUEST_PARAM_ERROR = 502;

    @ErrorMessage(value = "RES.ERROR.ENUM_CONVERT_ERROR",remark = "该枚举类型不支持该转换")
    public static final int ENUM_CONVERT_ERROR = 503;

    @ErrorMessage(value = "ORIGINAL_PWD_NOT_RIGHT",remark = "您输入的原密码不正确!")
    public static final int ORIGINAL_PWD_NOT_RIGHT = 506;


}
