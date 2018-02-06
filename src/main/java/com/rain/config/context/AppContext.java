package com.rain.config.context;

import com.alibaba.fastjson.JSONObject;
import com.rain.dao.entity.LoginUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Conventions;
import org.springframework.util.Assert;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ron on 2016/5/16.
 */
public class AppContext extends LinkedHashMap<String, Object> {

    private static Integer page = Integer.valueOf(1);
    private static Integer rows = Integer.valueOf(10);

    public AppContext() {
    }

    public AppContext(String attributeName, Object attributeValue) {
        this.addAttribute(attributeName, attributeValue);
    }

    public AppContext(Object attributeValue) {
        this.addAttribute(attributeValue);
    }

    public AppContext addAttribute(String attributeName, Object attributeValue) {
        Assert.notNull(attributeName, "Context attribute name must not be null");
        this.put(attributeName, attributeValue);
        return this;
    }

    public AppContext addAttribute(Object attributeValue) {
        Assert.notNull(attributeValue, "Context object must not be null");
        return attributeValue instanceof Collection && ((Collection) attributeValue).isEmpty() ? this : this.addAttribute(Conventions.getVariableName(attributeValue), attributeValue);
    }

    public AppContext addAllAttributes(Collection<?> attributeValues) {
        if (attributeValues != null) {
            Iterator var2 = attributeValues.iterator();

            while (var2.hasNext()) {
                Object attributeValue = var2.next();
                this.addAttribute(attributeValue);
            }
        }
        return this;
    }

    public AppContext addAllAttributes(Map<String, ?> attributes) {
        if (attributes != null) {
            this.putAll(attributes);
        }
        return this;
    }

    public AppContext mergeAttributes(Map<String, ?> attributes) {
        if (attributes != null) {
            Iterator var2 = attributes.entrySet().iterator();
            while (var2.hasNext()) {
                Map.Entry entry = (Map.Entry) var2.next();
                String key = (String) entry.getKey();
                if (!this.containsKey(key)) {
                    this.put(key, entry.getValue());
                }
            }
        }
        return this;
    }

    public Map<String, String> getRequestParamMap(){
        return (Map<String, String>) get(ContextConstants.KEY_REQUEST_PARAM_MAP);
    }

    public Object getParam(String key){
        return getRequestParamMap().get(key);
    }
    public String[] getParams(String key){
        return getRequestParamMap().get(key).split(BaseParam.STRING_SPLIT);
    }

    public String getSafeStringParam(String key){
        Object obj = getRequestParamMap().get(key);
        if(obj == null || StringUtils.isBlank(obj.toString())){
            return "";
        }
        return obj.toString();
    }



    public <T> T getRequestParamClass(Class<T> c){
        return getParamMapClass(c, getRequestParamMap());
    }
    public <T> T getParamMapClass(Class<T> c, Map paramMap){
        String json = JSONObject.toJSON(paramMap).toString();
        try{
            return (T)JSONObject.parseObject(json, c);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public Integer getAttrPage(){
        String str = getRequestParamMap().get(ParamConstants.KEY_PAGE);
        Integer result = page;
        try {
            result = (StringUtils.isNotBlank(str) ? Integer.parseInt(str) : page);
        }catch (Exception e){}
        return result;
    }

    public Integer getAttrRows(){
        String str = getRequestParamMap().get(ParamConstants.KEY_ROWS);
        Integer result = rows;
        try {
            result = (StringUtils.isNotBlank(str) ? Integer.parseInt(str) : rows);
        }catch (Exception e){}
        return result;
    }

    public String getSessionKey(){
        return (String) get(ContextConstants.KEY_SESSIONKEY_PARAM);
    }

    public LoginUser getLoginUser(){
        return (LoginUser) get(ContextConstants.KEY_USER);
    }

    public HttpServletRequest getHttpServletRequest(){
        return (HttpServletRequest)get(ContextConstants.KEY_REQUEST);
    }

    public HttpServletResponse getHttpServletResponse(){
        return (HttpServletResponse)get(ContextConstants.KEY_RESPONSE);
    }

    public HttpSession getHttpServletSession(){
        HttpServletRequest request = ((HttpServletRequest)get(ContextConstants.KEY_REQUEST));
        if(request != null){
            return request.getSession(true);
        }else{
            return null;
        }
    }

    public ServletContext getHttpServletContext(){
        HttpServletRequest request = ((HttpServletRequest)get(ContextConstants.KEY_REQUEST));
        if(request != null){
            return request.getSession(true).getServletContext();
        }else{
            return null;
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
