<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
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
	<link rel="stylesheet" href="https://cdn.bootcdn.net/ajax/libs/limonte-sweetalert2/11.0.20/sweetalert2.min.css">
	
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>


<title>申請同意書</title>

<style>
.text{
    width: 700px;
    margin: auto;
    padding: 10px;
}
.su{
    padding-left: 40%;
}
.check{
    padding-left: 6%;
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
                    	<input class="btn btn-outline-success btn-sm me-md-4" type="button" value="會員中心" onclick="location.href='<%=request.getContextPath()%>/frontend/member/memberCenter.jsp'">
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

                        <br>
                    </div>
                    <div class="row" style="height:20px"></div>
                    </div>
                </div>
            </div>
        </div>
    </header>
    
<div>
		&emsp;
</div>
<div>
		&emsp;
</div>
<div>
		&emsp;
</div>
    
<body>

    <div>
        <form class="text">
<front>本用戶同意書將自 2021 年  7 月 10 日起對所有用戶生效。</front>
<br>
<div>
		&emsp;
</div>
<front>歡迎使用CountMeIn！</front>
<br>
<div>
		&emsp;
</div>
<front>本用戶同意書是您與新加坡公司CountMeIn Pte. Ltd. 之間所簽訂之契約，您對CountMeIn 帳戶和CountMeIn 服務之使用均受其約束。若您是個人，您必須是CountMeIn 全球服務頁面所列任一國家 / 地區的居民，且須年滿 18 歲或已達到您所在國家 / 地區的法定成年年齡，方可開設CountMeIn 帳戶並使用CountMeIn 服務。若您為公司，您的公司必須在CountMeIn 全球服務頁面所列任一國家 / 地區成立、經營，或位於該國家 / 地區，方可開設CountMeIn 帳戶並使用CountMeIn 服務。</front>
<br>
<div>
		&emsp;
</div>
<front>開設並使用CountMeIn 帳戶，即表示您同意遵守本用戶同意書中的所有條款和細則。您還同意遵守下列其他政策，以及「法律同意書」頁面上所有適用於您的其他同意書，包括：</front>
<br>
<div>
		&emsp;
</div>
<front>合理使用政策</front>
<br>
<front>備用付款方式條款</front>
<br>
<div>
		&emsp;
</div>
<front>請仔細閱讀本用戶同意書的所有條款和細則、這些政策的條款和所有適用於您的其他同意書。</front>
<br>
<div>
		&emsp;
</div>
<front>我們可隨時修訂本用戶同意書及上述任何政策。除非另有說明，否則修訂版本將自發佈於我們的網站時即時生效。如果我們的變更會縮減您的權利或增加您的責任，我們將於網站的「政策更新」頁面發佈通知，並至少提前 14 天通知您。您註冊CountMeIn 服務時，「法律同意書」頁面中已公佈的「政策更新」頁面所列的全部未來變更，皆已併入本用戶同意書以作為參考文件，並於「政策更新」中的規定時間生效。</front>
<br>
<div>
		&emsp;
</div>
<front>如果您不同意修訂後的條款和細則，您必須停止使用CountMeIn 服務、關閉帳戶，並終止您與我們的關係（無需支付任何費用或罰金）。本用戶同意書將繼續適用於您先前使用我們的服務的行為。</front>
<br>
<div>
		&emsp;
</div>
<front>關於我們的主要業務</front>
<br>
<div>
		&emsp;
</div>
<front>CountMeIn 為付款服務供應商，透過網路為您建立、代管、維護和提供CountMeIn 服務。我們的服務允許您向任何擁有CountMeIn 帳戶的用戶付款或收款（如適用）。我們所提供的服務內容會因國家 / 地區而異。登入您的CountMeIn 帳戶，即可了解您居住的國家 / 地區可使用哪些服務。</front>
<br>
<div>
		&emsp;
</div>
<front>CountMeIn 服務由CountMeIn Pte. Ltd. 提供。</front>
<br>
<div>
		&emsp;
</div>
<front>本用戶同意書並非CountMeIn 服務之要約，且CountMeIn 並未透過本用戶同意書限定任何國家 / 地區或市場。</front>


        </form>
    </div>
	
<div>
		&emsp;
</div>
	
	
	<div class="su">
		<div class="check">
		  <label for="myCheckbox"><input id="myCheckbox" type="checkbox"/>確認內容</lable>
		</div>
		  <p>
		  <form action="../../member/MemberServlet" id="agree" > 
		  <input type="hidden" name="mememail" value="${user.mememail}">
		  <input type="hidden" value="approveUsderStatus" name="action">
		  </form>
		  
		  <button id="myButton" disabled class="mybutton">同意</button>
	</div>		


<div>
		&emsp;
</div>
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

</body>


<!-- =========================================以下為 checkbox 之相關設定========================================== -->
	<script>
		$(document).ready(function () {
		  $('#myCheckbox').click(function () {
		    $('#myButton').prop("disabled", !$("#myCheckbox").prop("checked")); 
		  })
		});
	</script>
<!-- =========================================以下為 checkbox 之相關設定========================================== -->
	
<!-- =========================================以下為 SweetAlert 之相關設定========================================== -->
	<script>
	$('#myButton').click(function(){
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
			 			      '請至您的信箱查收驗證信.',
			 			      'success'
			 			).then((result) => {
			 					console.log(result);
			 				 		if (result.isConfirmed) {
			 				 			$('#agree').submit()
			 				 		 }
			 					})		
			 		 }
				})
	})
	</script>
<!-- =========================================以上為 SweetAlert 之相關設定========================================== -->
	
	
	
</html>