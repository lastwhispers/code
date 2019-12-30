package book.chapter06.pubsub;

import java.io.Serializable;

/**
 *
 * @author lastwhisper
 * @date 12/29/2019
 */
public class DbcpConfig implements Serializable {

    private String driverClass;
    private String url;
    private String username;
    private String password;
    private String maxActive;
    private String maxIdle;
    private String maxWait;

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
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

    public String getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(String maxActive) {
        this.maxActive = maxActive;
    }

    public String getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(String maxIdle) {
        this.maxIdle = maxIdle;
    }

    public String getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(String maxWait) {
        this.maxWait = maxWait;
    }

    public DbcpConfig() {
    }

    public DbcpConfig(String driverClass, String url, String username, String password, String maxActive, String maxIdle, String maxWait) {
        this.driverClass = driverClass;
        this.url = url;
        this.username = username;
        this.password = password;
        this.maxActive = maxActive;
        this.maxIdle = maxIdle;
        this.maxWait = maxWait;
    }

    @Override
    public String toString() {
        return "DbcpConfig{" +
                "driverClass='" + driverClass + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", maxActive='" + maxActive + '\'' +
                ", maxIdle='" + maxIdle + '\'' +
                ", maxWait='" + maxWait + '\'' +
                '}';
    }
}
