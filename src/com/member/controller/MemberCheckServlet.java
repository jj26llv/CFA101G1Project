package com.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.*;


@WebServlet("/member/MemberCheckServlet")
public class MemberCheckServlet extends HttpServlet {
       
	//驗證帳號是否被使用
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		MemberService memSvc = new MemberService();
		String memaccount = request.getParameter("memaccount");
		MemberVO user = memSvc.checkaccount(memaccount);
		
		if(user == null) {
			response.getWriter().print("1");
		}else {
			response.getWriter().print("0");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}