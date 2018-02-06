package com.rain.config.exception;




/**
 * 依赖ErrorCode
 * Created by zhangxl on 2015/8/20.
 */
public class SystemException extends RuntimeException{

    private int errorCode = -1;

    public SystemException(){
        super(ErrorDict.asString(ErrorData.SERVER_ERROR));
        this.errorCode = ErrorData.SUCCESS_CODE;
    }


    public SystemException(int errorCode){
        super(ErrorDict.asString(errorCode));
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public static void throwCustomException(int errorCode){
        throw new SystemException(errorCode);
    }

    public static void throwCommonCustomException(){
        throw new SystemException();
    }


    public static boolean hasError(int errorCode){
        if(errorCode != ErrorData.SUCCESS_CODE){
            return true;
        }
        return false;
    }
    public static void doErrorHandle(int errorCode){
        if(hasError(errorCode)){
            throwCustomException(errorCode);
        }
    }


}
