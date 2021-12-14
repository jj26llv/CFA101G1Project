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
	<link rel="stylesheet" href="<%=request.getContextPath()%>/frontend/member/css/header.css">
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	
<title>會員資料修改</title>

<style>
  table {
	width: 510px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
    font-size: 14px
  }
  th, td {
    padding: 1px;
  }
  .err{
  	padding-left: 30%;
  }
  .form{
  	padding-left: 37%;
  }
  .su{
  	padding-left: 12%;
  }
  .mybutton{
	width: 200px;
  display: block;
  padding: 15px 25px;
  font-size: 24px;
  cursor: pointer;
  text-align: center;   
  text-decoration: none;
  outline: none;
  border: none;
  border-radius: 15px;
  box-shadow: 0 9px #999;
  }
  .mybutton:active {
  background-color: rgb(37, 68, 96);
  box-shadow: 0 5px rgb(221, 177, 32);
  transform: translateY(4px);
  }
</style>

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

                    </ul>
                    <form>
                    	<input class="btn btn-outline-success btn-sm me-md-4" type="button" value="會員中心" id="clean" onclick="location.href='<%=request.getContextPath()%>/frontend/member/memberCenter.jsp'">
                    </form>
                    <form class="d-flex" action="../../member/LoginServlet">
                        <button class="btn btn-outline-success btn-sm me-md-4" type="submit" value="logout" name="action">登出</button>
                    </form>
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
                                <h6 class="btn btn-outline-warning btn-md" style="font-size:1cm">會員資料更新</h6>
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

<body bgcolor='white'>


<%-- 錯誤表列 --%>
<div class="err">
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
</div>


<div>
		&emsp;
</div>

<div class="form">
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" name="form1" id="update">
<table>
	<tr>
		<td>會員編號:<font color=red><b>*</b></font></td>
		<td><%=memVO.getMemno()%></td>
	</tr>
	<tr>
		<td>會員帳號:</td>
		<td><input type="TEXT" name="memaccount" size="45" id="memaccount"
			 value="<%= memVO.getMemaccount()%>" readonly="readonly"/> <h3 id="u-prompt"></h3> </td>
	</tr>
	<tr>
		<td>會員密碼:</td>
		<td><input type="TEXT" name="mempassword" size="45" id="mempassword"
			 value="<%= memVO.getMempassword()%>" /><h3 id="p-prompt"></h3></td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>會員狀態:</td> -->
		<td><input type="hidden" name="memstatus" size="45"
			 value="<%=memVO.getMemstatus()%>" readonly="readonly" /></td>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>驗證狀態:</td> -->
		<td><input type="hidden" name="memvrfed" size="45"
			 value="<%=memVO.getMemvrfed()%>" readonly="readonly" /></td>
<!-- 	</tr> -->
	<tr>
		<td>會員名稱:</td>
		<td><input type="TEXT" name="memname" size="45" id="memname"
			 value="<%=memVO.getMemname()%>" /><h3 id="n-prompt"></h3></td>
	</tr>
	<tr>
		<td>會員電話:</td>
		<td><input type="TEXT" name="memmobile" size="45" id="memmobile"
			 value="<%=memVO.getMemmobile()%>" /> <h3 id="m-prompt"></h3></td>
	</tr>
	<tr>
		<td>城市:</td>
		<td><input type="TEXT" name="memcity" size="45" id="memcity"
			 value="<%=memVO.getMemcity()%>" /><h3 id="c-prompt"></h3></td> 
	</tr>
	<tr>
		<td>地區:</td>
		<td><input type="TEXT" name="memdist" size="45" id="memdist"
			 value="<%=memVO.getMemdist()%>" /><h3 id="d-prompt"></h3></td>
	</tr>
	<tr>
		<td>地址:</td>
		<td><input type="TEXT" name="memadd" size="45" id="memadd"
			 value="<%=memVO.getMemadd()%>" /><h3 id="a-prompt"></h3></td>
	</tr>
	<tr>
		<td>E-Mail:</td>
		<td><input type="TEXT" name="mememail" size="45" id="mememail"
			 value="<%=memVO.getMememail()%>" readonly="readonly" /> <h3 id="e-prompt"></h3></td>
	</tr>
	<tr>
		<td>生日:</td>
		<td><input type="TEXT" name="membirth" id="f_date1" size="45"></td>
	</tr>
	<tr>
<!-- 		<td>賣家功能狀態:</td> -->
		<td><input type="hidden" name="usderstatus" size="45"
			 value="<%=memVO.getUsderstatus()%>" readonly="readonly" /></td>
	</tr>
	<tr>
<!-- 		<td>錢包結餘:</td> -->
		<td><input type="hidden" name="ecash" size="45"
			 value="<%=memVO.getEcash()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="memberUpdate">
<input type="hidden" name="memno" value="<%=memVO.getMemno()%>">
</FORM>
<div class="su">
<input type="submit" id="submit" value="送出修改" class="mybutton">
</div>
</div>

<div>
	<tr>
		&emsp;
	</tr>
</div>
<div>
	<tr>
		&emsp;
	</tr>
</div>
<div>
	<tr>
		&emsp;
	</tr>
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
</body>






<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=memVO.getMembirth()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
</script>

<!-- =========================================以上為 datetimepicker 之相關設定========================================== -->


<script>
$('#submit').click(function(){
	Swal.fire({
		  title: '確認修改',
		  text: " ",
		  icon: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: '同意',
		  cancelButtonText: '取消'
	}).then((result) => {
		console.log(result);
 		if (result.isConfirmed) {			 			
 			Swal.fire(
 			      '成功!',
 			      '修改送出.',
 			      'success'
 			).then((result) => {
 					console.log(result);
 				 		if (result.isConfirmed) {
 				 			$('#update').submit()
 				 		 }
 					})		
 		 }
	})
})	
</script>




<!-- =========================================以下為 驗證及錯誤處理 之相關設定========================================== -->
<%-- 點回到會員中心清除暫存, 回來後恢復原先資料 --%>
<script>
	$('#clean').click(function(){
		localStorage.removeItem("mempassword")
		localStorage.removeItem("memname")
		localStorage.removeItem("memcity")
		localStorage.removeItem("memdist")
		localStorage.removeItem("memadd")
		localStorage.removeItem("membirth")
	});
</script>

<%-- 送出修改如有錯誤,保留送出前的資料 --%>
<script>
	//會員密碼 
	var LMP= localStorage.getItem("mempassword");
	if(LMP){
		$('input[name=mempassword]').val(LMP);
	}	
	var mempassword = $('input[name=mempassword]').change(function(){
		var mempassword = $('input[name=mempassword]').val()
			localStorage.setItem("mempassword", mempassword);
	});
	
	//會員名稱
	var LMN= localStorage.getItem("memname");
	if(LMN){
		$('input[name=memname]').val(LMN);
	}	
	var memname = $('input[name=memname]').change(function(){
		var memname = $('input[name=memname]').val()
			localStorage.setItem("memname", memname);
	});
	//會員城市
	var LMC= localStorage.getItem("memcity");
	if(LMC){
		$('input[name=memcity]').val(LMC);
	}	
	var memcity = $('input[name=memcity]').change(function(){
		var memcity = $('input[name=memcity]').val()
			localStorage.setItem("memcity", memcity);
	});
	//會員地區
	var LMD= localStorage.getItem("memdist");
	if(LMD){
		$('input[name=memdist]').val(LMD);
	}	
	var memdist = $('input[name=memdist]').change(function(){
		var memdist = $('input[name=memdist]').val()
			localStorage.setItem("memdist", memdist);
	});
	//會員地址
	var LMA= localStorage.getItem("memadd");
	if(LMA){
		$('input[name=memadd]').val(LMA);
	}	
	var memadd = $('input[name=memadd]').change(function(){
		var memadd = $('input[name=memadd]').val()
			localStorage.setItem("memadd", memadd);
	});
	//會員生日
	var LMB= localStorage.getItem("membirth");
	if(LMB){
		$('input[name=membirth]').val(LMB);
	}	
	var membirth = $('input[name=membirth]').change(function(){
		var membirth = $('input[name=membirth]').val()
			localStorage.setItem("membirth", membirth);
	});
</script>
<!-- =========================================以上為 驗證及錯誤處理 之相關設定========================================== -->

<!-- =========================================以下為 前台格式驗證 之相關設定========================================== -->
<script>

//驗證帳號格式
$('#memaccount').on('input', function() {
    $('#u-prompt').text("");
    if (validateUsername()) {
        $('#memaccount').css('border', '2px solid #27da80')
    } else {
        $('#u-prompt').text("請確認帳號格式");
        $('#u-prompt').css('color', 'red');
        $('#u-prompt').css('font-size', '10px');
        $('#memaccount').css('border', '2px solid red')
    }
});
//驗證密碼格式
$('#mempassword').on('input', function() {
    $('#p-prompt').text("");
    if (validatePassword()) {
        $('#mempassword').css('border', '2px solid #27da80')
    } else {
        $('#p-prompt').text("請確認密碼格式");
        $('#p-prompt').css('color', 'red');
        $('#p-prompt').css('font-size', '10px');
        $('#mempassword').css('border', '2px solid red')
    }
});
//驗證名稱格式
$('#memname').on('input', function() {
    $('#n-prompt').text("");
    if (validateName()) {
        $('#memname').css('border', '2px solid #27da80')
    } else {
        $('#n-prompt').text("請確認名稱格式");
        $('#n-prompt').css('color', 'red');
        $('#n-prompt').css('font-size', '10px');
        $('#memname').css('border', '2px solid red')
    }
});
//驗證電話格式
$('#memmobile').on('input', function() {
    $('#m-prompt').text("");
    if (validateMobile()) {
        $('#memmobile').css('border', '2px solid #27da80')
    } else {
        $('#m-prompt').text("請確認電話格式");
        $('#m-prompt').css('color', 'red');
        $('#m-prompt').css('font-size', '10px');
        $('#memmobile').css('border', '2px solid red')
    }
});
//驗證城市格式
$('#memcity').on('input', function() {
    $('#c-prompt').text("");
    if (validateCity()) {
        $('#memcity').css('border', '2px solid #27da80')
    } else {
        $('#c-prompt').text("請確認輸入格式");
        $('#c-prompt').css('color', 'red');
        $('#c-prompt').css('font-size', '10px');
        $('#memcity').css('border', '2px solid red')
    }
});
//驗證地區格式
$('#memdist').on('input', function() {
    $('#d-prompt').text("");
    if (validateDist()) {
        $('#memdist').css('border', '2px solid #27da80')
    } else {
        $('#d-prompt').text("請確認輸入格式");
        $('#d-prompt').css('color', 'red');
        $('#d-prompt').css('font-size', '10px');
        $('#memdist').css('border', '2px solid red')
    }
});
//驗證地址格式
$('#memadd').on('input', function() {
    $('#a-prompt').text("");
    if (validateAdd()) {
        $('#memadd').css('border', '2px solid #27da80')
    } else {
        $('#a-prompt').text("請確認輸入格式");
        $('#a-prompt').css('color', 'red');
        $('#a-prompt').css('font-size', '10px');
        $('#memadd').css('border', '2px solid red')
    }
});
//驗證信箱格式
$('#mememail').on('input', function() {
    $('#e-prompt').text("");
    if (validateEmail()) {
        $('#mememail').css('border', '2px solid #27da80')
    } else {
        $('#e-prompt').text("請確認信箱格式");
        $('#e-prompt').css('color', 'red');
        $('#e-prompt').css('font-size', '10px');
        $('#mememail').css('border', '2px solid red')
    }
});

// //驗證註冊帳號是否可用
// $('#memaccount').on('blur', function() {
//     if (!validateUsername()) {
//         return;
//     }
//     $('#u-prompt').text("");
//     let memaccount = $('#memaccount').val();
//     $.ajax({
//         type: "post",
//         url: "../../member/MemberCheckServlet",
//         data: {
//             "memaccount": memaccount,
//         },
//         success: function(result) {
//         	console.log(result);
//             if (result == "1") {
//                 $('#memaccount').css('border', '2px solid #27da80')
//             } else {
//                 $('#u-prompt').text("帳號已被註冊");
//                 $('#u-prompt').css('color', 'red');
//                 $('#u-prompt').css('font-size', '10px');
//                 $('#memaccount').css('border', '2px solid red')
//             }
//         }
//     })
// });

// //驗證註冊信箱是否可用
// $('#mememail').on('blur', function() {
//     if (!validateEmail()) {
//         return;
//     }
//     $('#e-prompt').text("");
//     let mememail = $('#mememail').val();
//     $.ajax({
//         type: "post",
//         url: "../../member/MailCheckServlet",
//         data: {
//             "mememail": mememail,
//         },
//         success: function(result) {
//         	console.log(result);
//             if (result == "1") {
//                 $('#mememail').css('border', '2px solid #27da80')
//             } else {
//                 $('#e-prompt').text("信箱已被註冊");
//                 $('#e-prompt').css('color', 'red');
//                 $('#e-prompt').css('font-size', '10px');
//                 $('#mememail').css('border', '2px solid red')
//             }
//         }
//     })
// });
</script>

<!-- =========================================以上為 前台格式驗證 之相關設定========================================== -->


<!-- =========================================以下為 正則表達 之相關設定========================================== -->
<script>
//帳號正則表達式驗證
function validateUsername() {
    let memaccount = $('#memaccount').val();
    const re = /^[0-9A-Za-z]{5,12}$/ ;
    return re.test(memaccount);
}
//密碼正則表達式驗證
function validatePassword() {
    let mempassword = $('#mempassword').val();
    const re = /^[0-9A-Za-z]{5,12}$/ ;
    return re.test(mempassword);
}
//名稱正則表達式驗證
function validateName() {
    let memname = $('#memname').val();
    const re = /^[(\u4e00-\u9fa5)(a-zA-Z)]{2,10}$/ ;
    return re.test(memname);
}
//電話正則表達式驗證
function validateMobile() {
    let memmobile = $('#memmobile').val();
    const re = /^09[0-9]{8}$/ ;
    return re.test(memmobile);
}
//城市正則表達式驗證
function validateCity() {
    let memcity = $('#memcity').val();
    const re = /^[(\u4e00-\u9fa5)(a-zA-Z)]{2,15}$/ ;
    return re.test(memcity);
}
//地區正則表達式驗證
function validateDist() {
    let memdist = $('#memdist').val();
    const re = /^[(\u4e00-\u9fa5)(a-zA-Z)]{2,15}$/ ;
    return re.test(memdist);
}
//地址正則表達式驗證
function validateAdd() {
    let memadd = $('#memadd').val();
    const re = /^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,15}$/ ;
    return re.test(memadd);
}
//信箱正則表達式驗證
function validateEmail() {
    let mememail = $('#mememail').val();
    const re = /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    return re.test(mememail);
}
</script>
<!-- =========================================以上為 正則表達 之相關設定========================================== -->



</html>