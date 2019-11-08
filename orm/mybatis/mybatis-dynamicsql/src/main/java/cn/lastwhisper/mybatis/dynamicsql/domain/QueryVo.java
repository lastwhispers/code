package cn.lastwhisper.mybatis.dynamicsql.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 */
public class QueryVo implements Serializable {
    private List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}