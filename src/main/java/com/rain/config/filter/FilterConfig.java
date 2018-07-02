package com.rain.config.filter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
/**
 * @author ron
 *         2016/5/25.
 */

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
@ConfigurationProperties(prefix = "webfilter")
public class FilterConfig {

    private String securityUrlPattern;

    private String amroFilterUrlPattern;

    private String wwwDomain;

    public String getWwwDomain() {
        return wwwDomain;
    }

    public void setWwwDomain(String wwwDomain) {
        this.wwwDomain = wwwDomain;
    }

    public String getSecurityUrlPattern() {
        return securityUrlPattern;
    }

    public void setSecurityUrlPattern(String securityUrlPattern) {
        this.securityUrlPattern = securityUrlPattern;
    }

    public String getAmroFilterUrlPattern() {
        return amroFilterUrlPattern;
    }

    public void setAmroFilterUrlPattern(String amroFilterUrlPattern) {
        this.amroFilterUrlPattern = amroFilterUrlPattern;
    }
}
