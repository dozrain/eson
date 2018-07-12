package com.rain.rabbit.mail;

/**
 * Created by tpeng on 2016/5/13.
 */
public interface ConfigConstants {
    /**
     * config charset
     * */
    String CHARSET = "utf-8";
    /**
     * config file path
     * */
    String PRIMARY_CONFIG_PATH = "classpath:config/aog-primary.yml";
    String WEIXIN_CONFIG_PATH = "classpath:config/aog-weixin.yml";
    String AMQP_CONFIG_PATH = "classpath:config/aog-amqp.yml";
    String MAIL_CONFIG_PATH = "classpath:config/aog-mail.yml";
    String SMS_CONFIG_PATH = "classpath:config/aog-sms.yml";
    String VERIFY_CONFIG_PATH = "classpath:config/aog-verify.yml";
    String FILTER_CONFIG_PATH = "classpath:config/aog-webfilter.yml";
    String REDIS_CONFIG_PATH = "classpath:config/aog-redis.yml";
    String FTP_CONFIG_PATH = "classpath:config/aog-ftp.yml";
    String OSS_CONFIG_PATH = "classpath:config/aog-oss.yml";

    /**
     * mapper  scan  path
     * */
    String SCAN_PACKAGE_PATH = "com.bireturn";


    /**
     * datasource  service name
     * */
    String PRIMARY_SESSION_FACTORY = "primarySessionFactory";
    String PRIMARY_DATA_SOURCE = "primaryDataSource";
    String PRIMARY_TRANSACTION_MANAGER = "primaryTransactionManager";
    String PRIMARY_JDBC_TEMPLATE = "primaryJdbcTemplate";

    /**
     * redis  bean name
     * */
    String BN_REDIS_TEMPLATE_SERVICE = "redisTemplateService";
    String REDIS_CONNECTION_FACTORY = "redisConnectionFactory";
    String REDIS_TEMPLATE = "redisTemplate";
    String REDIS_STRING_TEMPLATE = "stringRedisTemplate";

    /**
     * json  bean name
     * */
    String JSON_MESSAGE_CONVERTER = "messgaeConverter";
    String JSON_HTTP_MESSAGE_CONVERTER = "httpConverter";

    /**
     * mq  bean name
     * */
    String RABBIT_MESSAGE_HANDLER_FACTORY = "meesageHandlerMethodFactory";

    /**
     * output
     * */
    String OUTPUT_TYPE_JSON = "JSON";
    String OUTPUT_TYPE_XML = "XML";
    String OUTPUT_TYPE_TEXT = "TEXT";
    String OUTPUT_TYPE_DEFAULT = OUTPUT_TYPE_JSON;

    String CONTENT_TYPE_JSON = "application/json;charset=" + CHARSET;
    String CONTENT_TYPE_XML = "text/xml;charset=" + CHARSET;
    String CONTENT_TYPE_TEXT = "text/plain;charset=" + CHARSET;
    String CONTENT_TYPE_JAVASCRIPT = "application/javascript;charset=" + CHARSET;

    /**
     * device
     * */
    String DEVICE_TYPE_PC = "PC";
    String DEVCIE_TYPE_ANDROID = "ANDROID";
    String DEVICE_TYPE_IOS = "IOS";
    String DEVICE_TYPE_UNKNOW = "UNKNOW";

    /**
     * storage
     * */
    String STORAGE_LOCAL = "local";
    String STORAGE_FTP = "ftp";
}
