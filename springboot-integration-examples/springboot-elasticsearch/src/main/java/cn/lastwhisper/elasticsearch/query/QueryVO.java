package cn.lastwhisper.elasticsearch.query;

/**
 * 
 * @author lastwhisper
 * @date 2020/5/23
 */
public class QueryVO {

    private String keyword;

    private Integer page = 1;

    private Integer size = 10;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
