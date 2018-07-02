package com.rain.config.filter;

import com.rain.config.constans.ConfigConstants;
import com.rain.config.constans.ContextConstants;
import com.rain.config.constans.ParamConstants;
import com.rain.config.context.AppContext;
import com.rain.config.context.*;
import com.rain.config.framework.cookie.CookieUtil;
import com.rain.utils.encrypt.Identities;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Rain on 2018\1\10 0010.
 */
@Component
public class MyFilter implements Filter,InitializingBean {

    Logger logger = LoggerFactory.getLogger(MyFilter.class);

    private List<String> loginList = new ArrayList();



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        try {

            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            //创建context对象
            AppContext context = ContextUtil.getAppContext();
            //加载 context参数
            loadParam(request, response);
            request.setAttribute(ContextConstants.REQUEST_CONTEXT_NAME, context);
            boolean interSwitch = true;
            String uri = (String) context.get(ContextConstants.KEY_URI_PARAM);
            //除忽略列表以外，所有接口请求均必须授权，其他WEB请求不需要拦截
            if (loginList.contains(uri) || uri == null || uri.indexOf(".html")>0) {
                interSwitch = false;
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            //清理context;
            ContextUtil.cleanContext();
        }
    }
    // 加载参数
    private void loadParam(HttpServletRequest request, HttpServletResponse response) {

        String uri = convertUri(request.getRequestURI());
        String device = getDevice(request);
        String sessionKey = getSessionKey(request);
        String udid = getUdid(request);
        String version = getVersion(request);
        String ua = getUserAgent(request);
        try {
            request.setCharacterEncoding("utf8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        AppContext context = ContextUtil.getAppContext();
        if (ConfigConstants.DEVICE_TYPE_PC.equals(device) && StringUtils.isBlank(udid)) {
            udid = Identities.uuid2();
            CookieUtil.addCookie(response, ParamConstants.KEY_UDID_PARAM, udid, Integer.MAX_VALUE);
        }
        context.put(ContextConstants.KEY_AGENT_PARAM, ua);
        context.put(ContextConstants.KEY_URI_PARAM, uri);
        context.put(ContextConstants.KEY_UDID_PARAM, udid);
        context.put(ContextConstants.KEY_SESSIONKEY_PARAM, sessionKey);
        context.put(ContextConstants.KEY_DEVICE_PARAM, device);
        context.put(ContextConstants.KEY_VERSION_PARAM, version);
        context.put(ContextConstants.KEY_REQUEST, request);
        context.put(ContextConstants.KEY_RESPONSE, response);
        context.put(ContextConstants.KEY_REQUESTID, RequestId.getRequestId());
        context.put(ContextConstants.KEY_JSONP_CALLBACK_PARAM, request.getParameter(ParamConstants.KEY_JSONP_CALLBACK));
        context.put(ContextConstants.KEY_OUTPUT_TYPE_PARAM, request.getParameter(ParamConstants.KEY_OUTPUT_TYPE));
        Map<String, String> map = BaseParam.initParamMap(request);
        map.remove("_");
        context.put(ContextConstants.KEY_REQUEST_PARAM_MAP, map);
    }
    //获取设备
    private String getDevice(HttpServletRequest request) {
        String val = getVal(request, ParamConstants.KEY_DEVICE_PARAM);
        if (StringUtils.isNotBlank(val)) {
            return val;
        }
        String ua = getUserAgent(request);
        if (ua.indexOf("android") > 0) {
            val = ConfigConstants.DEVCIE_TYPE_ANDROID;
        } else if (ua.indexOf("ipad") > 0 || ua.indexOf("iphone") > 0 || ua.indexOf("ipod") > 0) {
            val = ConfigConstants.DEVICE_TYPE_IOS;
        } else {
            val = ConfigConstants.DEVICE_TYPE_PC;
        }
        return val;
    }
    private String getVal(HttpServletRequest request, String key) {
        String val = request.getHeader(key);
        if (StringUtils.isNotBlank(val)) {
            return val;
        }
        val = CookieUtil.getCookieValue(request, key);
        if (StringUtils.isNotBlank(val)) {
            return val;
        }
        val = request.getParameter(key);
        return val;
    }
    //获取版本
    private String getVersion(HttpServletRequest request) {
        String val = getVal(request, ParamConstants.KEY_VERSION_PARAM);
        if (StringUtils.isBlank(val)) {
            val = "1.0";
        }
        return val;
    }
    //获取session
    private String getSessionKey(HttpServletRequest request) {
        String val = getVal(request, ParamConstants.KEY_SESSIONKEY_PARAM);
        return val;
    }
    //获取设备识别码
    private String getUdid(HttpServletRequest request) {
        String val = getVal(request, ParamConstants.KEY_UDID_PARAM);
        return val;
    }
    //获取用户代理
    private String getUserAgent(HttpServletRequest request) {
        String val = getVal(request, ParamConstants.KEY_USER_AGENT);
        return val;
    }
    //获取访问URL
    private static String convertUri(String uri) {
        if (uri.indexOf("//") > 0) {
            return convertUri(uri.replace("//", "/"));
        }
        return uri;
    }
    //获取地址IP
    public static String getAddressIP(HttpServletRequest request) {
        String ipAddress = null;
        ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1")) {
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }

        }
        if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //微信授权 页面
        loginList.add("/api/v1/weixin/auth");
        //普通登录 页面
        loginList.add("/api/v1/loginGo");
        loginList.add("/api/v1/loginOut");
    }
}
