# SpringBoot Devtool


引入 **spring-boot-devtools** 依赖

1. 访问`http://127.0.0.1:8080/`，响应 "Hello"  
2. 修改`@GetMapping("/")`为`@GetMapping("/devtools")`，`ctrl+f9` 编译代码，console 输出 "LiveReload server is running"
3. 访问`http://127.0.0.1:8080/`，响应 "404"，访问 `http://127.0.0.1:8080/devtools`，响应 "Hello Devtools"  