package com.rain.config.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by tpeng on 2016/5/26.
 */
@Configuration
@ConfigurationProperties(prefix = "sqlPluginConfig")
public class SqlPluginsConfig {

    @Value("${pageHelperPlugin:false}")
    private boolean pageHelperPlugin;

    public boolean isPageHelperPlugin() {
        return pageHelperPlugin;
    }

    public void setPageHelperPlugin(boolean pageHelperPlugin) {
        this.pageHelperPlugin = pageHelperPlugin;
    }

}
