<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/frontend/member/css/active.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <title>會員驗證頁面</title>
</head>
<body>
    <div class="active-wrap">
        <div class="active-container">
            <a href="#"><img src="${pageContext.request.contextPath}/frontend/member/images/logo.png" alt=""></a>
            <h2 id="msg">${msg}</h2>
            <p id="page">將在5秒後頁面自動跳轉</p>
            <a class="login" href="${pageContext.request.contextPath}/frontend/member/login.html">立即登入</a>
        </div>
        
    </div>
    <script>
        if(${status}){
            $('#msg').css("color","green");
        }else{
            $('#msg').css("color","red");
        }
        let num = 4;
        let id = window.setInterval(() => {
         $('#page').html("將在"+num+"秒後頁面自動跳轉")
            if(num == 0){
                window.clearInterval(id);
                window.location.href = "${pageContext.request.contextPath}/frontend/member/login.html";
            }
            num--;
        }, 1000);               
    </script>
</body>
</html>