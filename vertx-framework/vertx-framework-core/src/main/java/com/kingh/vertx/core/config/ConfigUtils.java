package com.kingh.vertx.core.config;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author <a href="https://blog.csdn.net/king_kgh>Kingh</a>
 * @version 1.0
 * @date 2019/4/11 10:36
 */
public class ConfigUtils {

    private static Properties properties;

    static {
        InputStream ins = ConfigUtils.class.getClassLoader().getResourceAsStream("application.properties");
        try {
            properties = new Properties();
            properties.load(ins);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Object getValue(String key, Class type) {
        if (key == null || "".equals(key)) {
            return null;
        }

        if (type == null) {
            type = String.class;
        }

        String value = properties.getProperty(key);
        if(value == null || "".equals(value)) {
            return null;
        }

        if (type == String.class) {
            return value;
        } else if (type == Integer.class) {
            return Integer.parseInt(value);
        } else if (type == Long.class) {
            return Long.parseLong(value);
        } else if (type == Boolean.class) {
            return Boolean.parseBoolean(value);
        }
        throw new RuntimeException("暂不支持的类型");
    }

}
