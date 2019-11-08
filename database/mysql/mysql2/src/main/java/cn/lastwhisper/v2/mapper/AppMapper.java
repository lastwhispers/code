package cn.lastwhisper.v2.mapper;

import cn.lastwhisper.v2.annotation.DataSource;
import cn.lastwhisper.v2.db.DbContextHolder;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AppMapper {

    @DataSource(value = DbContextHolder.MASTER)
    List<Map<String, Object>> executeQuery(@Param("sql") String sql);

    @DataSource(value = DbContextHolder.SLAVE)
    List<Map<String, Object>> executeQuery2(@Param("sql") String sql);
}
