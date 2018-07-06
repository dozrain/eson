package com.rain.utils.framework.error;

import com.rain.config.context.SpringContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ron
 *         2016/12/20.
 */
@Component
@Order(Integer.MAX_VALUE)
public class ErrorInit implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(ErrorDict.class);

    @Override
    public void run(String... args) {
        refreshDict();
    }

    private void refreshDict(){
        try {
            loadClassDict();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void loadClassDict(){
        Map<Integer, String> errorMap =  new HashMap<>();
        final Map<String, Object> errorResources = SpringContext.getBeansWithAnnotation(ErrorResource.class);
        for (final Object errorResource : errorResources.values()) {
            final Class<? extends Object> errorResourceClass = errorResource.getClass();
            Field[] fields = errorResourceClass.getFields();
            for (Field field : fields) {
                int modifiers = field.getModifiers();
                if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)//
                        && Modifier.isFinal(modifiers) && (field.getType() == int.class)) {
                    try {
                        String message = field.getName();
                        if (field.isAnnotationPresent(ErrorMessage.class)) {
                            message = field.getAnnotation(ErrorMessage.class).remark();
                        }
                        errorMap.put(field.getInt(null), message);
                    } catch (Exception e) {
                        logger.error("staticInit()", e);
                    }
                }
            }
        }
        ErrorDict.init(errorMap);
    }
}
