```json

GET /item/docs/_search

# 全文搜索
GET /item/docs/_search
{
  "query": {
    "match": {
      "title": "坚果R1"
    }
  }
}

# 词条（全词）匹配
GET /item/docs/_search
{
  "query": {
    "term": {
      "brand": "小米"
    }
  }
}


# 模糊匹配
GET /item/docs/_search
{
  "query": {
    "fuzzy": {
      "title": "小米"
    }
  }
}

# 布尔查询
GET /item/docs/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "term": {
            "category": "手机"
          }
        },
        {
          "term": {
            "brand": "小米"
          }
        }
      ]
    }
  }
}

# 范围匹配
GET /item/docs/_search
{
  "query": {
    "range": {
      "price": {
        "gte": 3000,
        "lte": 4000
      }
    }
  }
}

# 词条分组
GET /item/docs/_search
{
  "query": {
    "term": {
      "category": "手机"
    }
  },
  "sort": [
    {
      "price": {
        "order": "asc"
      }
    }
  ]
}


```