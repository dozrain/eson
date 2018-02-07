package com.rain.config.database;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.github.pagehelper.PageHelper;
import com.rain.config.context.ConfigConstants;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/**
 * Created by tpeng on 2016/04/22
 */
@Configuration
@ConfigurationProperties(prefix = "primarydb")
@EnableTransactionManagement
public class DataBaseConfig {

    String username;
    String password;
    String url;
    String driverClassName;

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
    @Value("${validationQuery:''}")
    String validationQuery;
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

    @Resource
    private SqlPluginsConfig sqlPluginConfig;


    @Bean
    public ServletRegistrationBean druidServlet() {
        return new ServletRegistrationBean(new StatViewServlet(), "/druid");
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid");
        return filterRegistrationBean;
    }

    @Bean(name = ConfigConstants.PRIMARY_DATA_SOURCE, destroyMethod = "close")
    @Primary
    public javax.sql.DataSource getBasicDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(this.url);
        druidDataSource.setUsername(this.username);
        druidDataSource.setPassword(this.password);
        druidDataSource.setMaxActive(this.maxActive);
        druidDataSource.setInitialSize(this.initialSize);
        druidDataSource.setMaxWait(this.maxWait);
        druidDataSource.setMinIdle(this.minIdle);
        druidDataSource.setTimeBetweenEvictionRunsMillis(3000L);
        druidDataSource.setMinEvictableIdleTimeMillis(300000L);
        druidDataSource.setValidationQuery(this.validationQuery);
        druidDataSource.setTestWhileIdle(this.testWhileIdle);
        druidDataSource.setTestOnBorrow(false);
        druidDataSource.setTestOnReturn(false);
        druidDataSource.setRemoveAbandoned(removeAbandoned);
        druidDataSource.setRemoveAbandonedTimeout(removeAbandonedTimeout);
        druidDataSource.setPoolPreparedStatements(false);
        List<String> inits = new ArrayList<>();
        inits.add("select 'x'");
        druidDataSource.setConnectionInitSqls(inits);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        return druidDataSource;
    }


    @Bean(name = ConfigConstants.PRIMARY_JDBC_TEMPLATE)
    public JdbcTemplate getJdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(getBasicDataSource());
        return jdbcTemplate;
    }

    @Bean(name = ConfigConstants.PRIMARY_TRANSACTION_MANAGER)
    public DataSourceTransactionManager getDataSourceTransactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(getBasicDataSource());
        return transactionManager;
    }

    @Bean(name = ConfigConstants.PRIMARY_SESSION_FACTORY)
    public SqlSessionFactory getSqlSessionFactoryBean() throws Exception{
        List<Interceptor> list = new ArrayList<Interceptor>();
        if(sqlPluginConfig.isPageHelperPlugin()){
//            list.add(pageHelper());
        }
        Interceptor[] plugins = new Interceptor[list.size()];
        for (int i = 0; i< list.size(); i++){
            plugins[i] = list.get(i);
        }
        SqlSessionFactoryBean ssf = new SqlSessionFactoryBean();
        ssf.setDataSource(getBasicDataSource());
        ssf.setPlugins(plugins);
        SqlSessionFactory sf = ssf.getObject();
        sf.getConfiguration().setMapUnderscoreToCamelCase(true);
        sf.getConfiguration().setJdbcTypeForNull(JdbcType.NULL);
        return sf;
    }

    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("dialect", "mysql");
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("pageSizeZero", "true");
        p.setProperty("reasonable", "true");
        p.setProperty("params", "pageNum=start;pageSize=limit;");
        pageHelper.setProperties(p);
        return pageHelper;
    }


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

}
