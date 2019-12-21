# API

### 商品列表

```
GET /product/list
```

参数

```
无
```

返回

```
{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "name": "热榜",
            "type": 1,
            "foods": [
                {
                    "id": "123456",
                    "name": "皮蛋粥",
                    "price": 1.2,
                    "description": "好吃的皮蛋粥",
                    "icon": "http://xxx.com",
                }
            ]
        },
        {
            "name": "好吃的",
            "type": 2,
            "foods": [
                {
                    "id": "123457",
                    "name": "慕斯蛋糕",
                    "price": 10.9,
                    "description": "美味爽口",
                    "icon": "http://xxx.com",
                }
            ]
        }
    ]
}
```


### 创建订单

```
POST /order/create
```

参数

```
name: "张三"
phone: "18868822111"
address: "慕课网总部"
openid: "ew3euwhd7sjw9diwkq" //用户的微信openid
items: [{
    productId: "157875196366160022",
    productQuantity: 2 //购买数量
}]

```

返回

```
{
  "code": 0,
  "msg": "成功",
  "data": {
      "orderId": "147283992738221" 
  }
}

```


### 买家登录

```
GET /login/buyer
```

参数

```
openid: abc
```

返回

`cookie里设置openid=abc`

```
{
    code: 0,
    msg: "成功",
    data: null
}
```

### 卖家登录

```
GET /login/seller
```

参数

```
openid: xyz
```

返回

`cookie里设置token=UUID, redis设置key=UUID, value=xyz`

```
{
    code: 0,
    msg: "成功",
    data: null
}
```