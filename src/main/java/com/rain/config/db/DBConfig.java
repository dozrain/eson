package com.rain.config.db;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by tpeng on 2016/04/22
 */
//@Configuration
//@ConfigurationProperties(prefix = "primarydb")
public class DBConfig {

    String username;
    String password;
    String url;

    String sapdata_username;
    String sapdata_password;
    String sapdata_url;

    String driverClassName;

    @Value("${validationQuery:''}")
    String validationQuery;
    //<!-- 连接池启动时的初始值 -->
    @Value("${initialSize:3}")
    int initialSize;
    //<!-- 最大活动连接的最大值 -->
    @Value("${maxActive:5}")
    int maxActive;
    //<!-- 最大空闲值.当经过一个高峰时间后，连接池可以慢慢将已经用不到的连接慢慢释放一部分，一直减少到maxIdle为止 -->
    @Value("${maxIdle:8}")
    int maxIdle;
    //<!-- 最小空闲值.当空闲的连接数少于阀值时，连接池就会预申请去一些连接，以免洪峰来时来不及申请 -->
    @Value("${minIdle:1}")
    int minIdle;
    //最大等待时间:当没有可用连接时,连接池等待连接被归还的最大时间(以毫秒计数),超过时间则抛出异常,如果设置为-1表示无限等待
    @Value("${maxWait:1000}")
    int maxWait;
    //标记是否删除泄露的连接,如果他们超过了removeAbandonedTimout的限制.
    @Value("${removeAbandoned:true}")
    boolean removeAbandoned;

    @Value("${removeAbandonedTimeout:1800}")
    int removeAbandonedTimeout;
    //标记当Statement或连接被泄露时是否打印程序的stack traces日志。
    @Value("${logAbandoned:true}")
    boolean logAbandoned;

    @Value("${defaultAutoCommit:true}")
    private boolean defaultAutoCommit;

    @Value("${validationInterval:600000}")
    private long validationInterval;

    @Value("${testWhileIdle:true}")
    private boolean testWhileIdle;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public int getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public boolean isRemoveAbandoned() {
        return removeAbandoned;
    }

    public void setRemoveAbandoned(boolean removeAbandoned) {
        this.removeAbandoned = removeAbandoned;
    }

    public int getRemoveAbandonedTimeout() {
        return removeAbandonedTimeout;
    }

    public void setRemoveAbandonedTimeout(int removeAbandonedTimeout) {
        this.removeAbandonedTimeout = removeAbandonedTimeout;
    }

    public boolean isLogAbandoned() {
        return logAbandoned;
    }

    public void setLogAbandoned(boolean logAbandoned) {
        this.logAbandoned = logAbandoned;
    }

    public String getSapdata_username() {
        return sapdata_username;
    }

    public void setSapdata_username(String sapdata_username) {
        this.sapdata_username = sapdata_username;
    }

    public String getSapdata_password() {
        return sapdata_password;
    }

    public void setSapdata_password(String sapdata_password) {
        this.sapdata_password = sapdata_password;
    }

    public String getSapdata_url() {
        return sapdata_url;
    }

    public void setSapdata_url(String sapdata_url) {
        this.sapdata_url = sapdata_url;
    }
}
