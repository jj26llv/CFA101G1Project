package com.member.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.member.model.*;


@WebServlet("/member/LoginServlet")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String action = request.getParameter("action");
		//response.getWriter().print(action);
		
		
		//登入&驗證
		if ("login".equals(action)) {
			MemberService memSvc = new MemberService();
			String memaccount = request.getParameter("memaccount");
			String mempassword = request.getParameter("mempassword");
			MemberVO user = memSvc.login(memaccount,mempassword);
			//response.getWriter().print(user);
			HttpSession session = request.getSession();
			if (user != null) {
				session.setAttribute("user", user);
				response.getWriter().print("1");
				
				// 設定寫入cookie時的編碼
//				memaccount = URLEncoder.encode(memaccount, "utf-8");
//                mempassword = URLEncoder.encode(mempassword, "utf-8");
//                Cookie c = new Cookie("autoLogin", memaccount + "," + mempassword);
//                c.setPath(request.getContextPath());
//
//                // 新增cookie
//                response.addCookie(c);
//                System.out.println(c);
				
				
			} else {
				response.getWriter().print("0");
			}
		}
		//跳轉頁面
		if ("success".equals(action)) {
			response.sendRedirect(request.getContextPath()+"/frontend/member/memberCenter.jsp");
		}
		
		//登出
		if ("logout".equals(action)) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect(request.getContextPath()+"/frontend/member/login.html");
		}
		//商品頁面登出
		if ("PDlogout".equals(action)) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect(request.getContextPath()+"/frontend/auctAct/auctActIndex.jsp");
		}
		//����
		if ("Mlogout".equals(action)) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect(request.getContextPath()+"/transRec/transrec.do?action=deposit");
		}
		
		if ("check".equals(action)) {
			MemberVO user = (MemberVO) request.getSession().getAttribute("user");
			Gson gson =new Gson();
			if(user != null) {
				request.setAttribute("user", user);
				String json = gson.toJson(user);
				response.getWriter().print(json);
//				String url = "/forum/navigation.jsp";
//				RequestDispatcher successView = request.getRequestDispatcher(url); // ����漱 listOneItem.jsp
//				successView.forward(request, response);
			}else {
				response.getWriter().print("-1");
//				RequestDispatcher failureView = request.getRequestDispatcher("/forum/navigation.jsp");
//				failureView.forward(request, response);
//				return;
			}
		}
		
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}

}
