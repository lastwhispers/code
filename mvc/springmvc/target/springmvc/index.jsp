<%@page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>主页面</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript">
        $(function () {
            // $("#btn").click(
            //     function () {
            //         $.ajax({
            //              type:"get",
            //              url:"springmvc/testajax",
            //              data:null,
            //              dataType:"json",
            //              success:function(data){
            //                   // alert(data);
            //                  $("#mydiv").html("Json对象："+data);
            //              }
            //         });
            //     }
            // );

            $("#testAjaxParam").click(function () {
                $.ajax({
                    type: "POST",
                    url: "testParam3",
                    data: '{"id":"1","username":"泰斯特","age":22}',
                    dataType: "text",
                    contentType: "application/json",//发生数据的格式
                    success: function (data) {
                        alert(data);
                    }
                });
            });
        });
    </script>
</head>
<body>
<button id="btn">异步请求</button>
<hr/>
<div id="mydiv"></div>
<hr/>
<h2>请求参数封装的连接</h2>
<a href="testParam1?username=test&age=18">请求参数封装原理分析</a>
<br/>
<a href="testParam2?username=test&age=18">RequestParam分析</a>
<br/>
<a id="testAjaxParam" href="#">RequestBody分析</a>
<br/>
<a href="testParam4/aaa/23">PathVariable分析</a>
<hr/>
<h2>文件下载分析</h2>
<a href="download">文件下载分析</a>
<br/>
</body>
</html>
