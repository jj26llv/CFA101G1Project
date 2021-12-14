<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
 MemberVO memVO = (MemberVO) session.getAttribute("user"); 
%>

<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css2?family=Noto+Serif+TC&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
	<link rel="stylesheet" href="css/header.css">
	<link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.css" rel="stylesheet">  


    <title>會員中心</title>

</head>

    <header>
        <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
            <div class="container-fluid">
                <a class="navbar-brand" href="<%=request.getContextPath()%>/frontend/Frontpage.jsp">CountMEIn</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
                    aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <c:if test="${not empty user}">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                                data-bs-toggle="dropdown" aria-expanded="false">
							查看訂單
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/frontend/listAllOrderByMemNo.jsp">商城訂單頁面</a></li>
                                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/frontend/used/listAllProdByBuyer.jsp">二手商城買家訂單頁面</a></li>
                                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/frontend/used/listAllProdBySeller.jsp">二手商城賣家訂單頁面</a></li>
                                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/auctOrder/auctOrder.do?action=orderRec">拍賣商城訂單頁面</a></li>
                                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/frontend/bid/order_Index1.jsp">競標商城訂單頁面</a></li>
                                <li>
                                    <hr class="dropdown-divider">
                                </li>
                                <li><a class="dropdown-item" href="<%=request.getContextPath()%>/frontend/listAllCollectionByMemNo.jsp">查看收藏商品</a></li>
                            </ul>
                        </li>
 						<li class="nav-item">
                            <a class="nav-link" href="<%=request.getContextPath()%>/frontend/member/memberCenter.jsp">會員中心</a>
                        </li>
                       	<li class="nav-item">
                            <a class="nav-link" href="<%=request.getContextPath()%>/chat/insta.jsp">聊天室</a>
                        </li>
                    </c:if>
                    </ul>
                    
                    
                    <c:if test="${empty user}">
	                     	<a href="<%=request.getContextPath()%>/frontend/member/register.jsp">
	                        <button class="btn btn-outline-success btn-sm me-md-4" type="submit">註冊</button>
	                        </a>
	                        <a href="<%=request.getContextPath()%>/frontend/member/login.html">
	                        <button class="btn btn-outline-success btn-sm">登入會員</button>
	                        </a>
	                </c:if>	
                    <c:if test="${not empty user}">	                        
	                    	<a href="<%=request.getContextPath()%>/transRec/transrec.do?action=deposit">
	                     	 <button class="btn btn-outline-success btn-sm me-md-4" type="button">我的錢包</button>
	                     	 <input type="hidden" name="action" value="deposit">
	                     	</a>
	                </c:if> 
                    <c:if test="${not empty user}">                    
                    <form class="d-flex" action="<%=request.getContextPath()%>/member/LoginServlet">
                        <button class="btn btn-outline-success btn-sm me-md-4" type="submit" value="logout" name="action">登出</button>
                    </form>
                    </c:if>
                    
                </div>
            </div>
        </nav>

        <div class="MallTop">
            <div style="margin-top:56px">
                <div class="container">
                    <div class="row">
                        <div class="col" style="height:50px;">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-3">
                            <a href="<%=request.getContextPath()%>/frontend/member/memberCenter.jsp">
                                <img src="<%=request.getContextPath()%>/frontend/member/images/logo.png" alt="logo" style="height:100px;" margin-bottom=10px;>
                            </a>
                        </div>
                        <div class="col-6">
                            <div class="row align-items-end" style="height:80px;">
                                <div class="col">
                                  <a href="<%=request.getContextPath()%>/frontend/EShop.jsp">	
                                    <button type="button" class="btn btn-outline-warning btn-md">主要商城</button>
                                  </a>  
                                </div> 
                                <div class="col">
                                 <a href="<%=request.getContextPath()%>/frontend/used/listAllProd.jsp">
                                    <button type="button" class="btn btn-outline-warning btn-md">二手商城</button>
                                 </a>
                                </div>
                                <div class="col">
                                  <a href="<%=request.getContextPath()%>/frontend/auctAct/auctActIndex.jsp">	
                                   <button type="button" class="btn btn-outline-warning btn-md">拍賣商城</button>
                                   </a>
                                </div>
                                 <div class="col">
                                 	<a href="<%=request.getContextPath()%>/frontend/bid/listAllBid.html">
                                    <button type="button" class="btn btn-outline-warning btn-md">競標商城</button>
                                	</a>
                                </div>
                                <div class="col">
                                 <a href="<%=request.getContextPath()%>/forum/index.html">	
                                    <button type="button" class="btn btn-outline-warning btn-md">討論交流</button>
                                  </a>  
                                </div>
                            </div>
                        </div>
                        <br>
                    </div>
                    <div class="row" style="height:20px"></div>
                    </div>
                </div>
            </div>
        </div>
    </header>
    
    <session>
        <div class="container">
            <div class="prod" style="width:100%;">
                <div class="row">
                    <div class="col-3">
                        <div class="d-flex flex-column flex-shrink-0 p-3 bg-light" style="width: 220px;">
                            <a href=""
                                class="d-flex align-items-center mb-5 mb-md-0 me-md-0 cornflowerblue text-decoration-none">
                                <i class="bi bi-person-circle fs-2 mb-3"></i> &nbsp; <h4> &nbsp;會員中心</h4>
                            </a>
                            <hr>
                            <ul class="nav nav-pills flex-column mb-auto ">
                                <li>
                                    <a href="<%=request.getContextPath()%>/frontend/member/memberUpdate.jsp" class="nav-link link-dark">
                                        <i > &nbsp;</i>會員資料管理</i><br>
                                    </a>
                                </li>
                                <li>
                                    <a href="<%=request.getContextPath()%>/frontend/member/agreeForm.jsp" class="nav-link link-dark">
                                        <i> &nbsp;</i>申請賣家功能</i>
                                    </a>
                                </li>
                                <li>
                                    <a href="<%=request.getContextPath()%>/frontend/RepairForm/select_page.jsp" class="nav-link link-dark">
                                        <i> &nbsp;</i>維修服務</i>
                                    </a>
                                </li>
                            </ul>
                            <hr>
                            <div class="dropdown">
                                <a href="#" class="d-flex align-items-center link-dark">
                                </a>
                                </ul>
                            </div>
                        </div>
                    </div>
                    	<div class="col-9">
<div>
	&emsp;
</div>
                        	<h4 >會員訊息</h4>
                        
						<table>
							<form>
								<tr>
									<td>會員編號:</td>
									<td><input type="text" name="memno" size="45" 
										value="${user.memno}" readonly="readonly" ></td>
									</tr>
								<tr>
									<td>會員帳號:</td>
									<td><input type="TEXT" name="memaccount" size="45" 
										 value="${user.memaccount}" readonly="readonly"></td>
								</tr>
								<tr>
									<td>會員密碼:</td>
									<td>
										<input type="password" name="mempassword" size="45" id="pwd"
										 value="${user.mempassword}" readonly="readonly">
										 <i class="fa fa-eye-slash" onclick="showhide()" id="eye"></i> 
									</td>
								</tr>
								<tr>
									<td>會員狀態:</td>
									<td><input type="TEXT" name="memstatus" size="45" id="memstatus"
										 value="${user.memstatus}" readonly="readonly"></td>									
								</tr>
								<tr>
									<td>驗證狀態:</td>
									<td><input type="TEXT" name="memvrfed" size="45" id="memvrfed"
										 value="${user.memvrfed}" readonly="readonly">

									</td>
								</tr>
								<tr>
									<td>會員名稱:</td>
									<td><input type="TEXT" name="memname" size="45"
										 value="${user.memname}" readonly="readonly"></td>
								</tr>
								<tr>
									<td>會員電話:</td>
									<td><input type="TEXT" name="memmobile" size="45"
										 value="${user.memmobile}" readonly="readonly"></td>
								</tr>
								<tr>
									<td>城市:</td>
									<td><input type="TEXT" name="memcity" size="45"
										 value="${user.memcity}" readonly="readonly"></td>
								</tr>
								<tr>
									<td>地區:</td>
									<td><input type="TEXT" name="memdist" size="45"
										 value="${user.memdist}" readonly="readonly"></td>
								</tr>
								<tr>
									<td>地址:</td>
									<td><input type="TEXT" name="memadd" size="45"
										 value="${user.memadd}" readonly="readonly"></td>
								</tr>
								<tr>
									<td>E-Mail:</td>
									<td><input type="TEXT" name="mememail" size="45"
										 value="${user.mememail}" readonly="readonly"></td>
								</tr>
								<tr>
									<td>生日:</td>
									<td><input type="TEXT" name="membirth" size="45"
										value="${user.membirth}" readonly="readonly"></td>
								</tr>
								<tr>
									<td>賣家功能狀態:</td>
									<td><input type="TEXT" name="usderstatus" size="45" id="usderstatus"
										 value="${user.usderstatus}" readonly="readonly"></td>
								</tr>
								<tr>
<!-- 									<td>錢包結餘:</td> -->
									<td><input type="hidden" name="ecash" size="45"
										 value="${user.ecash}" readonly="readonly"></td>
								</tr>

							</form>
						</table>
                        
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </session>
    
<div>
	&emsp;
</div>
    
    <footer>
        <div class="foot">
            <div class="container">
                <div class="row" style="height:200px;">
                    <div class="col-3"></div>
                    <div class="col-9"></div>
                </div>
            </div>
        </div>
    </footer>
    

<body>
</body>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <script>
    $('#logout').load('../../member/loginServlet?action=logout');
    </script>
    

<script>
 var value = document.getElementById('memstatus').value;
 var text;
	switch (parseInt(value)) {
		case 0:
			text = '未驗證';
			break;
		case 1:
			text = '已驗證';
			break;
	}
	document.getElementById('memstatus').value = text;
	
 var value = document.getElementById('memvrfed').value;
 var text;
	switch (parseInt(value)) {
		case 0:
			text = '未驗證';
			break;
		case 1:
			text = '已驗證';
			break;
	}
	document.getElementById('memvrfed').value = text;
	
 var value = document.getElementById('usderstatus').value;
 var text;
	switch (parseInt(value)) {
		case 0:
			text = '未驗證';
			break;
		case 1:
			text = '已驗證';
			break;
	}
	document.getElementById('usderstatus').value = text;
</script>


<script type="text/javascript">
            var eye = document.getElementById("eye");
            var pwd = document.getElementById("pwd");

            function showhide(){

                    if (pwd.type == "password") {
                        pwd.type = "text";
                            eye.className='fa fa-eye'
                    }else {
                        pwd.type = "password";
                        eye.className='fa fa-eye-slash'
                    }
            }
</script>



</html>