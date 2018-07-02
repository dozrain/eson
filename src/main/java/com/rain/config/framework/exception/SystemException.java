package com.rain.config.framework.exception;


import com.rain.config.framework.error.ErrorData;
import com.rain.config.framework.error.ErrorDict;

/**
 * 依赖ErrorCode
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
