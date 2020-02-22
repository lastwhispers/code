
package com.bfxy.springboot.config.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="spring.datasource") 
@PropertySource("classpath:druid.properties")
public class DruidDataSourceSettings {

	private String driverClassName;
	private String url;
	private String username;
	private String password;
	
	@Value("${druid.initialSize}")
	private int initialSize;
	
	@Value("${druid.minIdle}")
	private int minIdle;
	
	@Value("${druid.maxActive}")
	private int maxActive;
	
	@Value("${druid.timeBetweenEvictionRunsMillis}")
	private long timeBetweenEvictionRunsMillis;
	
	@Value("${druid.minEvictableIdleTimeMillis}")
	private long minEvictableIdleTimeMillis;
	
	@Value("${druid.validationQuery}")
	private String validationQuery;
	
	@Value("${druid.testWhileIdle}")
	private boolean testWhileIdle;
	
	@Value("${druid.testOnBorrow}")
	private boolean testOnBorrow;
	
	@Value("${druid.testOnReturn}")
	private boolean testOnReturn;
	
	@Value("${druid.poolPreparedStatements}")
	private boolean poolPreparedStatements;
	
	@Value("${druid.maxPoolPreparedStatementPerConnectionSize}")
	private int maxPoolPreparedStatementPerConnectionSize;
	
	@Value("${druid.filters}")
	private String filters;
	
	@Value("${druid.connectionProperties}")
	private String connectionProperties;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer properdtyConfigure(){
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	public String getDriverClassName() {
		return driverClassName;
	}
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public int getInitialSize() {
		return initialSize;
	}
	public void setInitialSize(int initialSize) {
		this.initialSize = initialSize;
	}
	public int getMinIdle() {
		return minIdle;
	}
	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}
	public int getMaxActive() {
		return maxActive;
	}
	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}
	public long getTimeBetweenEvictionRunsMillis() {
		return timeBetweenEvictionRunsMillis;
	}
	public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}
	public long getMinEvictableIdleTimeMillis() {
		return minEvictableIdleTimeMillis;
	}
	public void setMinEvictableIdleTimeMillis(long minEvictableIdleTimeMillis) {
		this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
	}
	public String getValidationQuery() {
		return validationQuery;
	}
	public void setValidationQuery(String validationQuery) {
		this.validationQuery = validationQuery;
	}
	public boolean isTestWhileIdle() {
		return testWhileIdle;
	}
	public void setTestWhileIdle(boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}
	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}
	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}
	public boolean isTestOnReturn() {
		return testOnReturn;
	}
	public void setTestOnReturn(boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}
	public boolean isPoolPreparedStatements() {
		return poolPreparedStatements;
	}
	public void setPoolPreparedStatements(boolean poolPreparedStatements) {
		this.poolPreparedStatements = poolPreparedStatements;
	}
	public int getMaxPoolPreparedStatementPerConnectionSize() {
		return maxPoolPreparedStatementPerConnectionSize;
	}
	public void setMaxPoolPreparedStatementPerConnectionSize(
			int maxPoolPreparedStatementPerConnectionSize) {
		this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
	}
	public String getFilters() {
		return filters;
	}
	public void setFilters(String filters) {
		this.filters = filters;
	}
	public String getConnectionProperties() {
		return connectionProperties;
	}
	public void setConnectionProperties(String connectionProperties) {
		this.connectionProperties = connectionProperties;
	}
	
}
