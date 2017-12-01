package com.rain.config.context;


import org.springframework.core.Conventions;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;


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

}

