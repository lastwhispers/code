package cn.lastwhisper.v3.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AppMapper {
    List<Map<String,Object>> executeQuery(@Param("sql") String sql);

    List<Map<String,Object>> executeQuery2(@Param("sql") String sql);
}
