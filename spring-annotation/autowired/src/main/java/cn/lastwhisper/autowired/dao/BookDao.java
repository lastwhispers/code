package cn.lastwhisper.autowired.dao;

import org.springframework.stereotype.Repository;

// 默认bean的id为类名首字母小写
@Repository
public class BookDao {

    private int nowBookDaoLable = 1;

    public int getNowBookDaoLable() {
        return nowBookDaoLable;
    }

    public void setNowBookDaoLable(int nowBookDaoLable) {
        this.nowBookDaoLable = nowBookDaoLable;
    }

    @Override
    public String toString() {
        return "BookDao{" +
                "nowBookDaoLable=" + nowBookDaoLable +
                '}';
    }
}
