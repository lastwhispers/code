package cn.lastwhisper.mybatis.datasource.domain;

import java.io.Serializable;

/**
 * @author Administrator
 */
public class QueryVo implements Serializable {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}