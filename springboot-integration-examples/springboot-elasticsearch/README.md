# SpringData Elasticsearch

SpringData 提供了 ElasticsearchRepository 和 ElasticsearchTemplate 操作 Elasticsearch

# ElasticsearchRepository

- 文档操作
    * CRUD
    * 排序
    * 分页
- 复合查询
    *  matchQuery：词条匹配，先分词然后在调用termQuery进行匹配
    *  TermQuery：词条匹配，不分词
    *  wildcardQuery：通配符匹配
    *  fuzzyQuery：模糊匹配
    *  rangeQuery：范围匹配
    *  booleanQuery：布尔查询

ElasticsearchRepository 不支持扩展性查询，因为 DefaultResultMapper 不支持高亮结果集映射
需要使用 ElasticsearchTemplate 结合 AbstractResultMapper 自定义结果集映射

# ElasticsearchTemplate


- 高亮查询
- 聚合查询
- 建议查询

ElasticsearchTemplate 实现了 ElasticsearchOperations  的 Transport Client.
ElasticsearchRestTemplate 实现了 ElasticsearchOperations  的 High Level REST Client.



# 参考
https://blog.csdn.net/weixin_43814195/article/details/85281287
https://www.cnblogs.com/vcmq/p/9966693.html
https://blog.csdn.net/weter_drop/article/details/104584163