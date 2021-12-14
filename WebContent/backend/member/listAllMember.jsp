<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    MemberService memSvc = new MemberService();
    List<MemberVO> list = memSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有會員資料 - listAllMember.jsp</title>

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
	width: 1500px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
    font-size: 14px
  }
  th, td {
    padding: 7px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有會員資料</h3>
		 <h4><a href="<%=request.getContextPath()%>/backend/frontend.jsp">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>會員<br>編號</th>
		<th>會員<br>帳號</th>
		<th>會員<br>密碼</th>
		<th>帳號<br>狀態</th>
		<th>驗證<br>狀態</th>
		<th>會員驗證完成時間</th>
		<th>會員<br>姓名</th>
		<th>會員<br>電話</th>
		<th>縣市</th>
		<th>區域</th>
		<th>地址</th>
		<th>E-Mail</th>
		<th>生日</th>
		<th>加入時間</th>
		<th>賣家功能<br>狀態</th>
		<th>錢包結餘</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${memberVO.memno}</td>
			<td>${memberVO.memaccount}</td>
			<td>${memberVO.mempassword}</td>
			<td>${memberVO.memstatus}</td>
			<td>${memberVO.memvrfed}</td>
			<td>${memberVO.memnovrftime}</td>
			<td>${memberVO.memname}</td>
			<td>${memberVO.memmobile}</td>
			<td>${memberVO.memcity}</td>
			<td>${memberVO.memdist}</td>
			<td>${memberVO.memadd}</td>
			<td>${memberVO.mememail}</td>
			<td>${memberVO.membirth}</td>
			<td>${memberVO.memjointime}</td>
			<td>${memberVO.usderstatus}</td>
			<td>${memberVO.ecash}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="memno"  value="${memberVO.memno}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="memno"  value="${memberVO.memno}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>