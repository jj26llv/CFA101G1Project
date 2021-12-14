<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Member: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li>
  	<div class="accordion" id="accordionExample">
	  <div class="card">
	    <div class="card-header" id="headingOne">
	      <h2 class="mb-0">
	        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapse1" aria-expanded="true" aria-controls="collapseOne">
	          	<b>顯示所有會員</b>
	        </button>
	      </h2>
	    </div>
	
	    <div id="collapse1" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
	      <div class="card-body">
	        <b><a href='<%=request.getContextPath()%>/backend/member/listAllMember.jsp'>列出所有會員</a></b><br>	      
	      </div>
	    </div>
	  </div>
	</div>
  </li>
  
  
  <li>
  	 <div class="accordion" id="accordionExample">
	  <div class="card">
	    <div class="card-header" id="headingTwo">
	      <h2 class="mb-0">
	        <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapse2" aria-expanded="false" aria-controls="collapseTwo">
	          	<b>單一會員查詢</b>
	        </button>
	      </h2>
	    </div>
	    <div id="collapse2" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
	      <div class="card-body">
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" >
		        <b>輸入會員編號 (如11001):</b>
		        <input type="text" name="memno">
		        <input type="hidden" name="action" value="getOne_For_Display">
		        <input type="submit" value="送出">
		    </FORM>

		  </div> 
	      <div class="card-body" >
	        <jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService" />
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" >
		       <b>選擇會員編號:</b>
		       <select size="1" name="memno">
		         <c:forEach var="memVO" items="${memSvc.all}" > 
		          <option value="${memVO.memno}">${memVO.memno}
		         </c:forEach>   
		       </select>
		       <input type="hidden" name="action" value="getOne_For_Display">
		       <input type="submit" value="送出">
		    </FORM> 	      	
	      </div>
	      <div class="card-body">
		     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" >
		       <b>選擇會員姓名:</b>
		       <select size="1" name="memno">
		         <c:forEach var="memVO" items="${memSvc.all}" > 
		          <option value="${memVO.memno}">${memVO.memname}
		         </c:forEach>   
		       </select>
		       <input type="hidden" name="action" value="getOne_For_Display">
		       <input type="submit" value="送出">
		     </FORM>	      	
	      </div>
	    </div>
	  </div>
	</div>
  </li>
  
  <li>
  	<div class="accordion" id="accordionExample">
	  <div class="card">
	    <div class="card-header" id="headingOne">
	      <h2 class="mb-0">
	        <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapse3" aria-expanded="true" aria-controls="collapseOne">
	          	<b>新增會員</b>
	        </button>
	      </h2>
	    </div>
	
	    <div id="collapse3" class="collapse" aria-labelledby="headingOne" data-parent="#accordionExample">
	      <div class="card-body">
	        <b><a href='<%=request.getContextPath()%>/backend/member/addMember.jsp'>新增會員</a></b><br>	      
	      </div>
	    </div>
	  </div>
	</div>
  </li>
 </ul>
</body>
</html>