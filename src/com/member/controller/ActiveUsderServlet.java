package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.model.MemberService;

import redis.clients.jedis.Jedis;

@WebServlet("/member/activeusderServlet")
public class ActiveUsderServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String code = request.getParameter("code");
		String mememail = request.getParameter("mememail");
		Jedis jedis = new Jedis("localhost",6379);
		String checkCode = jedis.hget(mememail, "email");
		
		if(code.equals(checkCode)) {
			MemberService service = new MemberService();
			try {
				service.activeusder(mememail);
				request.setAttribute("msg", "賣家功能啟用成功，請重新登入");
				request.setAttribute("status", true);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "賣家功能啟用失敗，請重新申請驗證");
				request.setAttribute("status", false);
			}
			
		}else {
			request.setAttribute("msg", "賣家功能啟用失敗，請重新申請驗證");
			request.setAttribute("status", false);
		}
		//response.sendRedirect(request.getContextPath()+"/frontend/member/activeUsder.jsp");
		request.getRequestDispatcher("/frontend/member/activeUsder.jsp").forward(request, response);
	}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
