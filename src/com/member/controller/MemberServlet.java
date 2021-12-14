package com.member.controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.google.gson.Gson;
import com.member.model.*;

@WebServlet("/member/MemberServlet")
public class MemberServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		res.setCharacterEncoding("UTF-8");

		
// 來自select_page.jsp的請求		
		if ("getOne_For_Display".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("memno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/member/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer memno = null;
				try {
					memno = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/member/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MemberService memSvc = new MemberService();
				MemberVO memVO = memSvc.getOneMem(memno);
				if (memVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/member/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // 資料庫取出的empVO物件,存入req
				String url = "/backend/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/member/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		
// 來自listAllEmp.jsp的請求		
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer memno = new Integer(req.getParameter("memno"));
				
				/***************************2.開始查詢資料****************************************/
				MemberService memSvc = new MemberService();
				MemberVO memVO = memSvc.getOneMem(memno);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("memVO", memVO);         // 資料庫取出的empVO物件,存入req
				String url = "/backend/member/update_member_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				e.printStackTrace(); //訊息
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/member/listAllEmp.jsp");
				failureView.forward(req, res);
			}
		}
		
// 來自update_emp_input.jsp的請求		
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer memno = new Integer(req.getParameter("memno").trim());
				
				String memaccount = req.getParameter("memaccount");
				String memaccountReg = "^[(a-zA-Z0-9)]{5,12}$";
				if (memaccount == null || memaccount.trim().length() == 0) {
					errorMsgs.add("會員帳號: 請勿空白");
				} else if(!memaccount.trim().matches(memaccountReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員帳號: 只能是英文字母與數字 , 且長度必需在5到12之間");
	            }
				
				String mempassword = req.getParameter("mempassword");
				String mempasswordReg = "^[(a-zA-Z0-9)]{6,12}$";
				if (mempassword == null || mempassword.trim().length() == 0) {
					errorMsgs.add("會員密碼: 請勿空白");
				} else if(!mempassword.trim().matches(mempasswordReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員密碼: 只能是英文字母與數字 , 且長度必需在6到12之間");
	            }
				
				Integer memstatus = null;
				try {
					memstatus = new Integer(req.getParameter("memstatus").trim());
				} catch (NumberFormatException e) {
					memstatus = 0;
					errorMsgs.add("帳號狀態請填數字.");
				} if (memstatus == null) {
					errorMsgs.add("帳號狀態: 請勿空白");
				} else if(memstatus > 2) {
					errorMsgs.add("帳號狀態: 只能介於數字0到2之間");
	            }
				Integer memvrfed = null;
				try {
					memvrfed = new Integer(req.getParameter("memvrfed").trim());
				} catch (NumberFormatException e) {
					memvrfed = 0;
					errorMsgs.add("驗證狀態請填數字.");
				} if (memvrfed == null) {
					errorMsgs.add("驗證狀態: 請勿空白");
				} else if(memvrfed > 1) {
					errorMsgs.add("驗證狀態: 只能介於數字0到1之間");
				}
				
				String memname = req.getParameter("memname");
				String memnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,10}$";
				if (memname == null || memname.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!memname.trim().matches(memnameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母 , 且長度必需在2到10之間");
	            }
				
				String memmobile = req.getParameter("memmobile");
				String memmobileReg = "^09[0-9]{8}$";
				if (memmobile == null || memmobile.trim().length() == 0) {
					errorMsgs.add("會員電話: 請勿空白");
				} else if(!memmobile.trim().matches(memmobileReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員電話: 只能是數字 , 且長度必須為10碼");
	            }
				
				String memcity = req.getParameter("memcity");
				String memcityReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,15}$";
				if (memcity == null || memcity.trim().length() == 0) {
					errorMsgs.add("城市: 請勿空白");
				} else if(!memcity.trim().matches(memcityReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("城市不能使用數字及特殊符號");
	            }
				
				String memdist = req.getParameter("memdist");
				String memdistReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,15}$";
				if (memdist == null || memdist.trim().length() == 0) {
					errorMsgs.add("地區: 請勿空白");
				} else if(!memdist.trim().matches(memdistReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("地區不能使用數字及特殊符號");
	            }
				
				String memadd = req.getParameter("memadd");
				String memaddReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,15}$";
				if (memadd == null || memadd.trim().length() == 0) {
					errorMsgs.add("地址: 請勿空白");
				} else if(!memadd.trim().matches(memaddReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("地址不能使用特殊符號");
	            }
				
				String mememail = req.getParameter("mememail");
				String mememailReg = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
				if (mememail == null || mememail.trim().length() == 0) {
					errorMsgs.add("Emial: 請勿空白");
				} else if(!mememail.trim().matches(mememailReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("Email格式不正確");
	            }
				
				java.sql.Date membirth = null;
				try {
					membirth = java.sql.Date.valueOf(req.getParameter("membirth").trim());
				} catch (IllegalArgumentException e) {
					membirth=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Integer usderstatus = null;
				try {
					usderstatus = new Integer(req.getParameter("usderstatus").trim());
				} catch (NumberFormatException e) {
					usderstatus = 0;
					errorMsgs.add("賣家功能狀態請填數字.");
				} if (usderstatus == null) {
					errorMsgs.add("賣家功能狀態: 請勿空白");
				} else if(usderstatus > 1) {
					errorMsgs.add("賣家功能狀態: 只能介於數字0到1之間");
				}
				
				Integer ecash = null;
				try {
					ecash = new Integer(req.getParameter("ecash").trim());
				} catch (NumberFormatException e) {
					ecash = 0;
					errorMsgs.add("錢包餘額請填數字.");
				} if (ecash == null) {
					errorMsgs.add("錢包餘額: 請勿空白");
				}

				MemberVO memVO = new MemberVO();
				
				memVO.setMemaccount(memaccount);
				memVO.setMempassword(mempassword);
				memVO.setMemstatus(memstatus);
				memVO.setMemvrfed(memvrfed);
				memVO.setMemname(memname);
				memVO.setMemmobile(memmobile);
				memVO.setMemcity(memcity);
				memVO.setMemdist(memdist);
				memVO.setMemadd(memadd);
				memVO.setMememail(mememail);
				memVO.setMembirth(membirth);
				memVO.setUsderstatus(usderstatus);
				memVO.setEcash(ecash);
				memVO.setMemno(memno);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/member/update_member_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MemberService memSvc = new MemberService();
				memVO = memSvc.updateMem(memno, memaccount, mempassword, memstatus, memvrfed, memname, memmobile, memcity, memdist, memadd, mememail, membirth, usderstatus, ecash);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/backend/member/listOneMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				e.printStackTrace(); //訊息
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/member/update_member_input.jsp");
				failureView.forward(req, res);
			}
		}

		
// 來自memberUpdate.jsp的請求		
			if ("memberUpdate".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer memno = new Integer(req.getParameter("memno").trim());
				
				String memaccount = req.getParameter("memaccount");
				String memaccountReg = "^[(a-zA-Z0-9)]{5,12}$";
				if (memaccount == null || memaccount.trim().length() == 0) {
					errorMsgs.add("會員帳號: 請勿空白");
				} else if(!memaccount.trim().matches(memaccountReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員帳號: 只能是英文字母與數字 , 且長度必需在5到12之間");
	            }
				
				String mempassword = req.getParameter("mempassword");
				String mempasswordReg = "^[(a-zA-Z0-9)]{6,12}$";
				if (mempassword == null || mempassword.trim().length() == 0) {
					errorMsgs.add("會員密碼: 請勿空白");
				} else if(!mempassword.trim().matches(mempasswordReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員密碼: 只能是英文字母與數字 , 且長度必需在6到12之間");
	            }
				
				Integer memstatus = new Integer(req.getParameter("memstatus").trim());
				
				Integer memvrfed = new Integer(req.getParameter("memvrfed").trim());
				
				String memname = req.getParameter("memname");
				String memnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,10}$";
				if (memname == null || memname.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!memname.trim().matches(memnameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母 , 且長度必需在2到10之間");
	            }
				
				String memmobile = req.getParameter("memmobile");
				String memmobileReg = "^09[0-9]{8}$";
				if (memmobile == null || memmobile.trim().length() == 0) {
					errorMsgs.add("會員電話: 請勿空白");
				} else if(!memmobile.trim().matches(memmobileReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員電話: 只能是數字 , 且長度必須為10碼");
	            }
				
				String memcity = req.getParameter("memcity");
				String memcityReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,15}$";
				if (memcity == null || memcity.trim().length() == 0) {
					errorMsgs.add("城市: 請勿空白");
				} else if(!memcity.trim().matches(memcityReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("城市不能使用數字及特殊符號");
	            }
				
				String memdist = req.getParameter("memdist");
				String memdistReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,15}$";
				if (memdist == null || memdist.trim().length() == 0) {
					errorMsgs.add("地區: 請勿空白");
				} else if(!memdist.trim().matches(memdistReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("地區不能使用數字及特殊符號");
	            }
				
				String memadd = req.getParameter("memadd");
				String memaddReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,15}$";
				if (memadd == null || memadd.trim().length() == 0) {
					errorMsgs.add("地址: 請勿空白");
				} else if(!memadd.trim().matches(memaddReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("地址不能使用特殊符號");
	            }
				
				String mememail = req.getParameter("mememail");
				String mememailReg = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
				if (mememail == null || mememail.trim().length() == 0) {
					errorMsgs.add("Emial: 請勿空白");
				} else if(!mememail.trim().matches(mememailReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("Email格式不正確");
	            }
				
				java.sql.Date membirth = null;
				try {
					membirth = java.sql.Date.valueOf(req.getParameter("membirth").trim());
				} catch (IllegalArgumentException e) {
					membirth=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Integer usderstatus = new Integer(req.getParameter("usderstatus").trim());
				
				Integer ecash = new Integer(req.getParameter("ecash").trim());


				MemberVO memVO = new MemberVO();
				
				memVO.setMemaccount(memaccount);
				memVO.setMempassword(mempassword);
				memVO.setMemstatus(memstatus);
				memVO.setMemvrfed(memvrfed);
				memVO.setMemname(memname);
				memVO.setMemmobile(memmobile);
				memVO.setMemcity(memcity);
				memVO.setMemdist(memdist);
				memVO.setMemadd(memadd);
				memVO.setMememail(mememail);
				memVO.setMembirth(membirth);
				memVO.setUsderstatus(usderstatus);
				memVO.setEcash(ecash);
				memVO.setMemno(memno);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/member/memberUpdate.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MemberService memSvc = new MemberService();
				memVO = memSvc.memberUpdate(memno, memaccount, mempassword, memstatus, memvrfed, memname, memmobile, memcity, memdist, memadd, mememail, membirth, usderstatus, ecash);

				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				HttpSession session = req.getSession();
				session.setAttribute("user", memVO); //更新
				
				String url = "/frontend/member/memberCenter.jsp";   //forword 為自己拿東西找別人做事
				res.sendRedirect(req.getContextPath()+url);	//重導向回來

//				String url = "/backend/member/listAllMember.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);	
				
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				e.printStackTrace(); //訊息			
				
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/member/memberUpdate.jsp");
				failureView.forward(req, res);

			}
		}
		
		
		
// 來自addMember.jsp的請求  		
        if ("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				String memaccount = req.getParameter("memaccount");
				
				String memaccountReg = "^[(a-zA-Z0-9)]{5,12}$";
				if (memaccount == null || memaccount.trim().length() == 0) {
					errorMsgs.add("會員帳號: 請勿空白");
				} else if(!memaccount.trim().matches(memaccountReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員帳號: 只能是英文字母與數字 , 且長度必需在5到12之間");
	            }
				
				String mempassword = req.getParameter("mempassword");
				String mempasswordReg = "^[(a-zA-Z0-9)]{6,12}$";
				if (mempassword == null || mempassword.trim().length() == 0) {
					errorMsgs.add("會員密碼: 請勿空白");
				} else if(!mempassword.trim().matches(mempasswordReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員密碼: 只能是英文字母與數字 , 且長度必需在6到12之間");
	            }
				
				Integer memstatus = null;
				try {
					memstatus = new Integer(req.getParameter("memstatus").trim());
				} catch (NumberFormatException e) {
					memstatus = 0;
					errorMsgs.add("帳號狀態請填數字.");
				} if (memstatus == null) {
					errorMsgs.add("帳號狀態: 請勿空白");
				} else if(memstatus > 2) {
					errorMsgs.add("帳號狀態: 只能介於數字0到2之間");
	            }
				
				Integer memvrfed = null;
				try {
					memvrfed = new Integer(req.getParameter("memvrfed").trim());
				} catch (NumberFormatException e) {
					memvrfed = 0;
					errorMsgs.add("驗證狀態請填數字.");
				} if (memvrfed == null) {
					errorMsgs.add("驗證狀態: 請勿空白");
				} else if(memvrfed > 2) {
					errorMsgs.add("驗證狀態: 只能介於數字0到2之間");
				}
				
				String memname = req.getParameter("memname");
				String memnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,10}$";
				if (memname == null || memname.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!memname.trim().matches(memnameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母 , 且長度必需在2到10之間");
	            }
				
				String memmobile = req.getParameter("memmobile");
				String memmobileReg = "^09[0-9]{8}$";
				if (memmobile == null || memmobile.trim().length() == 0) {
					errorMsgs.add("會員電話: 請勿空白");
				} else if(!memmobile.trim().matches(memmobileReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員電話: 只能是數字 , 且長度必須為10碼");
				}
				
				String memcity = req.getParameter("memcity");
				String memcityReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,15}$";
				if (memcity == null || memcity.trim().length() == 0) {
					errorMsgs.add("城市: 請勿空白");
				} else if(!memcity.trim().matches(memcityReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("城市不能使用數字及特殊符號");
	            }
				
				String memdist = req.getParameter("memdist");
				String memdistReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,15}$";
				if (memdist == null || memdist.trim().length() == 0) {
					errorMsgs.add("地區: 請勿空白");
				} else if(!memdist.trim().matches(memdistReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("地區不能使用數字及特殊符號");
	            }
				
				String memadd = req.getParameter("memadd");
				String memaddReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,15}$";
				if (memadd == null || memadd.trim().length() == 0) {
					errorMsgs.add("地址: 請勿空白");
				} else if(!memadd.trim().matches(memaddReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("地址不能使用特殊符號");
	            }
				
				String mememail = req.getParameter("mememail");
				String mememailReg = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
				if (mememail == null || mememail.trim().length() == 0) {
					errorMsgs.add("Emial: 請勿空白");
				} else if(!mememail.trim().matches(mememailReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("Email格式不正確");
	            }
				
				java.sql.Date membirth = null;
				try {
					membirth = java.sql.Date.valueOf(req.getParameter("membirth").trim());
				} catch (IllegalArgumentException e) {
					membirth=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				
				java.sql.Timestamp memjointime = null;			
				memjointime = new java.sql.Timestamp(System.currentTimeMillis());

				
				Integer usderstatus = null;
				try {
					usderstatus = new Integer(req.getParameter("usderstatus").trim());
				} catch (NumberFormatException e) {
					usderstatus = 0;
					errorMsgs.add("賣家功能狀態請填數字.");
				} if (usderstatus == null) {
					errorMsgs.add("賣家功能狀態: 請勿空白");
				} else if(usderstatus > 1) {
					errorMsgs.add("賣家功能狀態: 只能介於數字0到1之間");
				}
				
				Integer ecash = null;
				try {
					ecash = new Integer(req.getParameter("ecash").trim());
				} catch (NumberFormatException e) {
					ecash = 0;
					errorMsgs.add("錢包餘額請填數字.");
				} if (ecash == null) {
					errorMsgs.add("錢包餘額: 請勿空白");
				}


				MemberVO memVO = new MemberVO();
				
				memVO.setMemaccount(memaccount);
				memVO.setMempassword(mempassword);
				memVO.setMemstatus(memstatus);
				memVO.setMemvrfed(memvrfed);
				memVO.setMemname(memname);
				memVO.setMemmobile(memmobile);
				memVO.setMemcity(memcity);
				memVO.setMemdist(memdist);
				memVO.setMemadd(memadd);
				memVO.setMememail(mememail);
				memVO.setMembirth(membirth);
				memVO.setMemjointime(memjointime);
				memVO.setUsderstatus(usderstatus);
				memVO.setEcash(ecash);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/member/addMember.jsp");
					failureView.forward(req, res);
					return;
				}				
				
				/***************************2.開始新增資料***************************************/
				MemberService memSvc = new MemberService();
				memVO = memSvc.addMem(memaccount, mempassword, memstatus, memvrfed, memname, memmobile, memcity, memdist, memadd, mememail, membirth, memjointime, usderstatus, ecash);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/backend/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				e.printStackTrace(); //訊息
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/member/addMember.jsp");
				failureView.forward(req, res);
			}
		}
		
// 來自register.jsp的請求  		
        if ("registerInsert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				
				String memaccount = req.getParameter("memaccount");
				
				String memaccountReg = "^[(a-zA-Z0-9)]{5,12}$";
				if (memaccount == null || memaccount.trim().length() == 0) {
					errorMsgs.add("會員帳號: 請勿空白");
				} else if(!memaccount.trim().matches(memaccountReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員帳號: 只能是英文字母與數字 , 且長度必需在5到12之間");
	            }
				
				String mempassword = req.getParameter("mempassword");
				String mempasswordReg = "^[(a-zA-Z0-9)]{6,12}$";
				if (mempassword == null || mempassword.trim().length() == 0) {
					errorMsgs.add("會員密碼: 請勿空白");
				} else if(!mempassword.trim().matches(mempasswordReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員密碼: 只能是英文字母與數字 , 且長度必需在6到12之間");
	            }
				
				Integer memstatus = 0;
				
				Integer memvrfed = 0;
				
				String memname = req.getParameter("memname");
				String memnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,10}$";
				if (memname == null || memname.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!memname.trim().matches(memnameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母 , 且長度必需在2到10之間");
	            }
				
				String memmobile = req.getParameter("memmobile");
				String memmobileReg = "^09[0-9]{8}$";
				if (memmobile == null || memmobile.trim().length() == 0) {
					errorMsgs.add("會員電話: 請勿空白");
				} else if(!memmobile.trim().matches(memmobileReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員電話: 只能是數字 , 且長度必須為10碼");
				}
				
				String memcity = req.getParameter("memcity");
				String memcityReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,15}$";
				if (memcity == null || memcity.trim().length() == 0) {
					errorMsgs.add("城市: 請勿空白");
				} else if(!memcity.trim().matches(memcityReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("城市不能使用數字及特殊符號");
	            }
				
				String memdist = req.getParameter("memdist");
				String memdistReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,15}$";
				if (memdist == null || memdist.trim().length() == 0) {
					errorMsgs.add("地區: 請勿空白");
				} else if(!memdist.trim().matches(memdistReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("地區不能使用數字及特殊符號");
	            }
				
				String memadd = req.getParameter("memadd");
				String memaddReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,15}$";
				if (memadd == null || memadd.trim().length() == 0) {
					errorMsgs.add("地址: 請勿空白");
				} else if(!memadd.trim().matches(memaddReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("地址不能使用特殊符號");
	            }
				
				String mememail = req.getParameter("mememail");
				String mememailReg = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
				if (mememail == null || mememail.trim().length() == 0) {
					errorMsgs.add("Emial: 請勿空白");
				} else if(!mememail.trim().matches(mememailReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("Email格式不正確");
	            }
				
				java.sql.Date membirth = null;
				try {
					membirth = java.sql.Date.valueOf(req.getParameter("membirth").trim());
				} catch (IllegalArgumentException e) {
					membirth=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				
				java.sql.Timestamp memjointime = null;			
				memjointime = new java.sql.Timestamp(System.currentTimeMillis());

				
				Integer usderstatus = 0;
				
				Integer ecash = 0;

				MemberVO memVO = new MemberVO();
				
				memVO.setMemaccount(memaccount);
				memVO.setMempassword(mempassword);
				memVO.setMemstatus(memstatus);
				memVO.setMemvrfed(memvrfed);
				memVO.setMemname(memname);
				memVO.setMemmobile(memmobile);
				memVO.setMemcity(memcity);
				memVO.setMemdist(memdist);
				memVO.setMemadd(memadd);
				memVO.setMememail(mememail);
				memVO.setMembirth(membirth);
				memVO.setMemjointime(memjointime);
				memVO.setUsderstatus(usderstatus);
				memVO.setEcash(ecash);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/member/register.jsp");
					failureView.forward(req, res);
					return;
				}				
				
				/***************************2.開始新增資料***************************************/
				MemberService memSvc = new MemberService();
				int count = memSvc.register(memaccount, mempassword, memstatus, memvrfed, memname, memmobile, memcity, memdist, memadd, mememail, membirth, memjointime, usderstatus, ecash);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				if(count == 1) {
					System.out.println("成功");
					SendEmail se = new SendEmail(mememail);
					se.start();
					se = null;

					String url = "/frontend/member/login.html";   //forword 為自己拿東西找別人做事
					res.sendRedirect(req.getContextPath()+url);	//重導向回來

				}else {
					System.out.println("失敗");
					res.getWriter().print("0");
				}

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				e.printStackTrace(); //訊息
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/member/register.jsp");
				failureView.forward(req, res);
			}
		}

        
// 來自listAllEmp.jsp		
		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer memno = new Integer(req.getParameter("memno"));
				
				/***************************2.開始刪除資料***************************************/
				MemberService memSvc = new MemberService();
				memSvc.deleteMem(memno);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/backend/member/listAllMember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/member/listAllMember.jsp");
				failureView.forward(req, res);
			}
		}
		
	
// 來自agreeForm.jsp		
		if ("approveUsderStatus".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數***************************************/
				String mememail = req.getParameter("mememail");
				System.out.println(mememail);
				
				
				/***************************2.發送開通信***************************************/
				MemberService memSvc = new MemberService();
				memSvc.activeusder(mememail);
				SendEmailUsder se = new SendEmailUsder(mememail);
				se.start();
				se = null;

				
				/***************************3.申請完成,準備轉交(Send the Success view)***********/		
				String url = "/frontend/member/memberCenter.jsp";   //forword 為自己拿東西找別人做事
				res.sendRedirect(req.getContextPath()+url);	//重導向回來

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/frontend/member/memberCenter.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Gson gson = new Gson();
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("memno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					String json = gson.toJson(errorMsgs);
					res.getWriter().print(json);
					return;//程式中斷
				}
				
				Integer memno = null;
				try {
					memno = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					String json = gson.toJson(errorMsgs);
					res.getWriter().print(json);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MemberService memSvc = new MemberService();
				MemberVO memVO = memSvc.getOneMem(memno);
				if (memVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					String json = gson.toJson(errorMsgs);
					res.getWriter().print(json);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memVO", memVO); // 資料庫取出的empVO物件,存入req
				String json = gson.toJson(memVO);
				res.getWriter().print(json);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				String json = gson.toJson(errorMsgs);
				res.getWriter().print(json);
			}
		}
		
		
		
		
		
	}
}
