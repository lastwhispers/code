package cn.cunchang.v2.web.req;

import lombok.Data;

import java.util.List;

/**
 * Created by qiumu on 2020/10/11.
 */
@Data
public class RequestConfigParam {


    /**
     * 白名单userID
     */
    private List<Integer> grayUserIdList;


    /**
     * 配置列表
     */
    private List<RequestConfig> requestConfigList;

}
