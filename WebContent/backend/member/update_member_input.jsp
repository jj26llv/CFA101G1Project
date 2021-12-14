<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
  MemberVO memVO = (MemberVO) request.getAttribute("memVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<%= memVO==null %>--${memVO.memno}--
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>會員資料修改 - update_member_input.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

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
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>會員資料修改</h3>
		 <h4><a href="<%=request.getContextPath()%>/backend/frontend.jsp">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" name="form1">
<table>
	<tr>
		<td>會員編號:<font color=red><b>*</b></font></td>
		<td><%=memVO.getMemno()%></td>
	</tr>
	<tr>
		<td>會員帳號:</td>
		<td><input type="TEXT" name="memaccount" size="45" 
			 value="<%= (memVO==null)? "abc101" : memVO.getMemaccount()%>" /></td>
	</tr>
	<tr>
		<td>會員密碼:</td>
		<td><input type="TEXT" name="mempassword" size="45"
			 value="<%= (memVO==null)? "123456" : memVO.getMempassword()%>" /></td>
	</tr>
	<tr>
		<td>會員狀態:</td>
		<td><input type="TEXT" name="memstatus" size="45"
			 value=0 /></td>
	</tr>
	<tr>
		<td>驗證狀態:</td>
		<td><input type="TEXT" name="memvrfed" size="45"
			 value=0 /></td>
	</tr>
	<tr>
		<td>會員名稱:</td>
		<td><input type="TEXT" name="memname" size="45"
			 value="<%= (memVO==null)? "Vincent" : memVO.getMemname()%>" /></td>
	</tr>
	<tr>
		<td>會員電話:</td>
		<td><input type="TEXT" name="memmobile" size="45"
			 value="<%= (memVO==null)? "0988123456" : memVO.getMemmobile()%>" /></td>
	</tr>
	<tr>
		<td>城市:</td>
		<td><input type="TEXT" name="memcity" size="45"
			 value="<%= (memVO==null)? "桃園市" : memVO.getMemcity()%>" /></td>
	</tr>
	<tr>
		<td>地區:</td>
		<td><input type="TEXT" name="memdist" size="45"
			 value="<%= (memVO==null)? "平鎮區" : memVO.getMemdist()%>" /></td>
	</tr>
	<tr>
		<td>地址:</td>
		<td><input type="TEXT" name="memadd" size="45"
			 value="<%= (memVO==null)? "我家" : memVO.getMemadd()%>" /></td>
	</tr>
	<tr>
		<td>E-Mail:</td>
		<td><input type="TEXT" name="mememail" size="45"
			 value="<%= (memVO==null)? "jj26llv@gmail.com" : memVO.getMememail()%>" /></td>
	</tr>
	<tr>
		<td>生日:</td>
		<td><input type="TEXT" name="membirth" id="f_date1"></td>
	</tr>
	<tr>
		<td>賣家功能狀態:</td>
		<td><input type="TEXT" name="usderstatus" size="45"
			 value=0 /></td>
	</tr>
	<tr>
		<td>錢包結餘:</td>
		<td><input type="TEXT" name="ecash" size="45"
			 value=0 /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="memno" value="<%=memVO.getMemno()%>">
<input type="submit" value="送出修改"></FORM>
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
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>