package com.rain.utils.framework.session;

import com.rain.utils.framework.context.AppContext;
import com.rain.utils.framework.context.ContextUtil;
import javax.servlet.ServletContext;
import java.util.Set;

/**
 * @author ron
 *         2016/12/31.
 */

public class ContainerSession implements ISession {

    @Override
    public void set(String key, String value) {
        ServletContext session = ContextUtil.getAppContext().getHttpServletContext();
        session.setAttribute(key, value);
    }

    @Override
    public void set(String key, String value, long time) {
        ServletContext session = ContextUtil.getAppContext().getHttpServletContext();
        session.setAttribute(key, value);
    }

    @Override
    public Object get(String key) {
        AppContext appContext = ContextUtil.getAppContext();
        if(appContext != null){
            ServletContext session = appContext.getHttpServletContext();
            return session.getAttribute(key);
        }else{
            return null;
        }

    }

    @Override
    public void delete(String key) {
        AppContext appContext = ContextUtil.getAppContext();
        if(appContext != null){
            ServletContext session = appContext.getHttpServletContext();
            session.removeAttribute(key);
        }
    }

    @Override
    public Set keys(String patternKey) {
        return null;
    }
}
