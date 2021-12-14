package com.member.model;

import java.util.*;

public interface MemberDAO_interface {
	//取得所有會員
	public List<MemberVO> getAll();
	//取得指定單一會員
	public MemberVO findByPrimaryKey(Integer MemNo);
	//驗證帳號密碼是否存在(Login)
	public MemberVO confirmAccountAndPassword(String memaccount, String mempassword);
	//驗證帳號是否被使用
	public MemberVO checkaccount(String memaccount);
	//驗證信箱是否被使用
	public MemberVO checkemail(String mememail);
	//新增會員資料
	public void insert(MemberVO MemberVO);
	//新增會員資料(前台)
    public int registerInsert(MemberVO MemberVO);
    //更新會員資料
    public void update(MemberVO MemberVO);
    //前端更新會員資料
    public void memberUpdate(MemberVO MemberVO);
    //刪除會員資料
    public void delete(Integer MemNo);
	//信箱更新會員驗證狀態
	public void updateEmailStatus(String mememail);
	//開通會員賣家狀態
	public void approveUsderStatus(String mememail);
	//更新錢包餘額
	public void updateEcash(MemberVO  memberVO);
    
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
