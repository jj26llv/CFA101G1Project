package com.member.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class MemberService {

	private MemberDAO_interface dao;

	public MemberService() {
		dao = new MemberDAO();
	}

	//登入
	public MemberVO login(String memaccount, String mempassword) {
		return dao.confirmAccountAndPassword(memaccount, mempassword);
	}
	
	//驗證帳號是否被使用
	public MemberVO checkaccount(String memaccount) {
		return dao.checkaccount(memaccount);
	}
	//驗證信箱是否被使用
	public MemberVO checkemail(String mememail) {
		return dao.checkemail(mememail);
	}
	
	//啟用信箱驗證
	public void active(String mememail) {
		dao.updateEmailStatus(mememail);
	}
	
	//啟用賣家功能
	public void activeusder(String mememail) {
		dao.approveUsderStatus(mememail);
	}
	
	//新會員註冊
	public int register(String memaccount, String mempassword, Integer memstatus, Integer memvrfed,
			String memname,String memmobile, String memcity, String memdist, String memadd, 
			String mememail, Date membirth, Timestamp memjointime, Integer usderstatus, Integer ecash) {

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
		//dao.insert2(memVO);
		
		return dao.registerInsert(memVO);
		
		//return memVO;
	}
	
	
	//更新錢包餘額
	public MemberVO updateEcash(Integer memno, Integer ecash) {

        MemberVO memberVO = new MemberVO();

          memberVO.setMemno(memno);
          memberVO.setEcash(ecash);
          dao.updateEcash(memberVO);
          return memberVO;
}	
	
	
	public MemberVO addMem(String memaccount, String mempassword, Integer memstatus, Integer memvrfed,
			String memname,String memmobile, String memcity, String memdist, String memadd, 
			String mememail, Date membirth, Timestamp memjointime, Integer usderstatus, Integer ecash) {

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
		dao.insert(memVO);

		return memVO;
	}

	public MemberVO memberUpdate(Integer memno, String memaccount, String mempassword, Integer memstatus, Integer memvrfed,
			String memname,String memmobile, String memcity, String memdist, String memadd, 
			String mememail, Date membirth, Integer usderstatus, Integer ecash) {

		MemberVO memVO = new MemberVO();

		memVO.setMemno(memno);
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
		dao.memberUpdate(memVO);

		return memVO;
	}

	public MemberVO updateMem(Integer memno, String memaccount, String mempassword, Integer memstatus, Integer memvrfed,
			String memname,String memmobile, String memcity, String memdist, String memadd, 
			String mememail, Date membirth, Integer usderstatus, Integer ecash) {

		MemberVO memVO = new MemberVO();

		memVO.setMemno(memno);
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
		dao.update(memVO);

		return memVO;
	}

	public void deleteMem(Integer memno) {
		dao.delete(memno);
	}

	public MemberVO getOneMem(Integer memno) {
		return dao.findByPrimaryKey(memno);
	}

	public List<MemberVO> getAll() {
		return dao.getAll();
	}
}
