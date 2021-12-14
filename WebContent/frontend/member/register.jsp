<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
  MemberVO memVO = (MemberVO) request.getAttribute("memVO");
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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>加入會員</title>

<style>
  table {
	width: 510px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
    font-size: 14px;
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
  	padding-left: 43%;
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
                    	<input class="btn btn-outline-success btn-sm me-md-4" type="button" value="回到登入頁面" id="clean" onclick="location.href='<%=request.getContextPath()%>/frontend/member/login.html'">
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
                                <h6 class="btn btn-outline-warning btn-md" style="font-size:1cm">加入會員</h6>
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



<body>

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
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" name="form1" id="register">
<table >
	<tr>
		<td>會員帳號:</td>
		<tr>
		<td><input type="TEXT" name="memaccount" size="45" id="memaccount"
			 value="<%= (memVO==null)? "" : memVO.getMemaccount()%>" /> <h3 id="u-prompt"></h3> </td>		 
	</tr>
	<tr>
		<td>會員密碼:</td>
		<tr>
		<td><input type="TEXT" name="mempassword" size="45" id="mempassword"
			 value="<%= (memVO==null)? "" : memVO.getMempassword()%>" /> <h3 id="p-prompt"></h3> </td>
	</tr>

	<tr>
		<td>會員名稱:</td>
		<tr>
		<td><input type="TEXT" name="memname" size="45" id="memname"
			 value="<%= (memVO==null)? "" : memVO.getMemname()%>" /> <h3 id="n-prompt"></h3></td>
	</tr>
	<tr>
		<td>會員電話:</td>
		<tr>
		<td><input type="TEXT" name="memmobile" size="45" id="memmobile"
			 value="<%= (memVO==null)? "" : memVO.getMemmobile()%>" /> <h3 id="m-prompt"></h3> </td>
	</tr>
	<tr>
		<td>城市:</td>
		<tr>
		<td><input type="TEXT" name="memcity" size="45" id="memcity"
			 value="<%= (memVO==null)? "" : memVO.getMemcity()%>" /><h3 id="c-prompt"></h3></td>
	</tr>
	<tr>
		<td>地區:</td>
		<tr>
		<td><input type="TEXT" name="memdist" size="45" id="memdist"
			 value="<%= (memVO==null)? "" : memVO.getMemdist()%>" /><h3 id="d-prompt"></h3></td>
	</tr>
	<tr>
		<td>地址:</td>
		<tr>
		<td><input type="TEXT" name="memadd" size="45" id="memadd"
			 value="<%= (memVO==null)? "" : memVO.getMemadd()%>" /><h3 id="a-prompt"></h3></td>
	</tr>
	<tr>
		<td>E-Mail:</td>
		<tr>
		<td><input type="TEXT" name="mememail" size="45" id="mememail"
			 value="<%= (memVO==null)? "" : memVO.getMememail()%>" /><h3 id="e-prompt"></h3></td>
	</tr>
	<tr>
		<td>生日:</td>
		<tr>
		<td><input type="text" name="membirth" id="f_date1" size="45"></td>
	</tr>
</table>


<br>
<input type="hidden" name="action" value="registerInsert">
</FORM>
</div>

<div class="su">
	<input class="mybutton" id="submit" type="submit" value="送出新增" disabled >
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

<% 
  java.sql.Date membirth = null;
  try {
	    membirth = memVO.getMembirth();
   } catch (Exception e) {
	    membirth = new java.sql.Date(System.currentTimeMillis());
   }
%>
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
		   value: '<%=membirth%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });       
</script>

<!-- =========================================以上為 datetimepicker 之相關設定========================================== -->

<!-- =========================================以下為 SweetAlert 之相關設定========================================== -->	
	<script>
	$('#submit').click(function(){
		Swal.fire({
			  title: '確認送出',
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
	 			      '歡迎加入會員.',
	 			      'success'
	 			).then((result) => {
	 					console.log(result);
	 				 		if (result.isConfirmed) {
	 				 			$('#register').submit()
	 				 		 }
	 					})		
	 		 }
		})
})			

	</script>
<!-- =========================================以上為 SweetAlert 之相關設定========================================== -->

<!-- =========================================以下為 前台格式驗證 之相關設定========================================== -->
<script>
$(function() {
    //設置驗證旗幟
    let u_flag = false;
    let p_flag = false;
    let n_flag = false;
    let m_flag = false;
    let c_flag = false;
    let d_flag = false;
    let a_flag = false;
    let e_flag = false;

    //確認旗幟均為true按鈕才能按
    function checkFlag() {
        if (u_flag && p_flag && n_flag && m_flag && c_flag && d_flag && a_flag && e_flag) {
            $("#submit").removeAttr("disabled")
        } else {
            $("#submit").attr("disabled", "disabled")
        }
    }


//驗證帳號格式
$('#memaccount').on('input', function() {
    $('#u-prompt').text("");
    if (validateUsername()) {
        $('#memaccount').css('border', '2px solid #27da80')
        u_flag = true;
    } else {
        $('#u-prompt').text("會員帳號請使用英文字母與數字混合 , 且長度必需在5到12之間並不能空白");
        $('#u-prompt').css('color', 'red');
        $('#u-prompt').css('font-size', '10px');
        $('#memaccount').css('border', '2px solid red')
        u_flag = false;
    }
    checkFlag();
});
//驗證密碼格式
$('#mempassword').on('input', function() {
    $('#p-prompt').text("");
    if (validatePassword()) {
        $('#mempassword').css('border', '2px solid #27da80')
        p_flag = true;
    } else {
        $('#p-prompt').text("會員密碼請使用英文字母與數字混合 , 且長度必需在6到12之間並不能空白");
        $('#p-prompt').css('color', 'red');
        $('#p-prompt').css('font-size', '10px');
        $('#mempassword').css('border', '2px solid red')
        p_flag = false;
    }
    checkFlag();
});
//驗證名稱格式
$('#memname').on('input', function() {
    $('#n-prompt').text("");
    if (validateName()) {
        $('#memname').css('border', '2px solid #27da80')
        n_flag = true;
    } else {
        $('#n-prompt').text("會員姓名請使用中、英文字母 , 且長度必需在2到10之間並不能空白");
        $('#n-prompt').css('color', 'red');
        $('#n-prompt').css('font-size', '10px');
        $('#memname').css('border', '2px solid red')
        n_flag = false;
    }
    checkFlag();
});
//驗證電話格式
$('#memmobile').on('input', function() {
    $('#m-prompt').text("");
    if (validateMobile()) {
        $('#memmobile').css('border', '2px solid #27da80')
        m_flag = true;
    } else {
        $('#m-prompt').text("請確認會員電話格式, 且長度必須為10碼並不能空白");
        $('#m-prompt').css('color', 'red');
        $('#m-prompt').css('font-size', '10px');
        $('#memmobile').css('border', '2px solid red')
        m_flag = false;
    }
    checkFlag();
});
//驗證城市格式
$('#memcity').on('input', function() {
    $('#c-prompt').text("");
    if (validateCity()) {
        $('#memcity').css('border', '2px solid #27da80')
        c_flag = true;
    } else {
        $('#c-prompt').text("城市不能使用數字及特殊符號且不能空白");
        $('#c-prompt').css('color', 'red');
        $('#c-prompt').css('font-size', '10px');
        $('#memcity').css('border', '2px solid red')
        c_flag = false;
    }
    checkFlag();
});
//驗證地區格式
$('#memdist').on('input', function() {
    $('#d-prompt').text("");
    if (validateDist()) {
        $('#memdist').css('border', '2px solid #27da80')
        d_flag = true;
    } else {
        $('#d-prompt').text("地區不能使用數字及特殊符號且不能空白");
        $('#d-prompt').css('color', 'red');
        $('#d-prompt').css('font-size', '10px');
        $('#memdist').css('border', '2px solid red')
        d_flag = false;
    }
    checkFlag();
});
//驗證地址格式
$('#memadd').on('input', function() {
    $('#a-prompt').text("");
    if (validateAdd()) {
        $('#memadd').css('border', '2px solid #27da80')
        a_flag = true;
    } else {
        $('#a-prompt').text("地址不能使用特殊符號且不能空白");
        $('#a-prompt').css('color', 'red');
        $('#a-prompt').css('font-size', '10px');
        $('#memadd').css('border', '2px solid red')
        a_flag = false;
    }
    checkFlag();
});
//驗證信箱格式
$('#mememail').on('input', function() {
    $('#e-prompt').text("");
    if (validateEmail()) {
        $('#mememail').css('border', '2px solid #27da80')
        e_flag = true;
    } else {
        $('#e-prompt').text("請檢查Email格式是否正確並不能空白");
        $('#e-prompt').css('color', 'red');
        $('#e-prompt').css('font-size', '10px');
        $('#mememail').css('border', '2px solid red')
        e_flag = false;
    }
    checkFlag();
});


//驗證註冊帳號是否可用
$('#memaccount').on('blur', function() {
    if (!validateUsername()) {
        return;
    }
    $('#u-prompt').text("");
    let memaccount = $('#memaccount').val();
    $.ajax({
        type: "post",
        url: "../../member/MemberCheckServlet",
        data: {
            "memaccount": memaccount,
        },
        success: function(result) {
        	console.log(result);
            if (result == "1") {
                $('#memaccount').css('border', '2px solid #27da80')
            } else {
                $('#u-prompt').text("帳號已被註冊");
                $('#u-prompt').css('color', 'red');
                $('#u-prompt').css('font-size', '10px');
                $('#memaccount').css('border', '2px solid red')
            }
        }
    })
});

//驗證註冊信箱是否可用
$('#mememail').on('blur', function() {
    if (!validateEmail()) {
        return;
    }
    $('#e-prompt').text("");
    let mememail = $('#mememail').val();
    $.ajax({
        type: "post",
        url: "../../member/MailCheckServlet",
        data: {
            "mememail": mememail,
        },
        success: function(result) {
        	console.log(result);
            if (result == "1") {
                $('#mememail').css('border', '2px solid #27da80')
            } else {
                $('#e-prompt').text("信箱已被註冊");
                $('#e-prompt').css('color', 'red');
                $('#e-prompt').css('font-size', '10px');
                $('#mememail').css('border', '2px solid red')
            }
        }
    })
})
});
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
    const re = /^[0-9A-Za-z]{6,12}$/ ;
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
    const re = /^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{8,15}$/ ;
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