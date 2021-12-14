$(function login() {
	  //登入
	  $('#login').on('click', function () {
	    let account = $('#account').val();
	    let password = $('#password').val();
	    $.ajax({
	      type: "POST",
	      url: "../../member/LoginServlet",
	      data: {
	        "memaccount": account,
	        "mempassword": password,	
	        "action": "login"
	      },
	      success: function (result) {
	        if (result == "1") {
	        //	window.location.href="memberCenter.jsp";
	        	
	        //登入成功後將action submit 	
	       $('#login-form').submit();

	        } else {
	          $('#u-prompt').text("帳號或密碼錯誤");
	          $('#u-prompt').css('color', 'red');
	          $('#u-prompt').css('font-size', '12px');


	          $('#account').css('font-size', '10px');
	          $('#account').css('border', '2px solid red')

	          $('#password').css('font-size', '10px');
	          $('#password').css('border', '2px solid red')
	        }
	      }
	    });
	  });
	});