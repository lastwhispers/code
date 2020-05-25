gateway单独使用
https://blog.csdn.net/jiongyan6966/article/details/84633632

service-product 8081
http://localhost:8081/method

service-order-load1 8082
service-order-load2 8083
http://localhost:8082/method
http://localhost:8083/method

zuul-gateway 9000
http://localhost:9000/shop-api/product/method
http://localhost:9000/shop-api/order/method