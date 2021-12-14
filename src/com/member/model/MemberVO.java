package com.member.model;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class MemberVO implements java.io.Serializable{
	
	private Integer memno;
	private String memaccount;
	private String mempassword;
	private Integer memstatus;
	private Integer memvrfed;
	private Timestamp memnovrftime;
	private String memname;
	private String memmobile;
	private String memcity;
	private String memdist;
	private String memadd;
	private String mememail;
	private Date membirth;
	private Timestamp memjointime;
	private Integer usderstatus;
	private Integer ecash;
	
	public Integer getMemno() {
		return memno;
	}
	public void setMemno(Integer memno) {
		this.memno = memno;
	}
	public String getMemaccount() {
		return memaccount;
	}
	public void setMemaccount(String memaccount) {
		this.memaccount = memaccount;
	}
	public String getMempassword() {
		return mempassword;
	}
	public void setMempassword(String mempassword) {
		this.mempassword = mempassword;
	}
	public Integer getMemstatus() {
		return memstatus;
	}
	public void setMemstatus(Integer memstatus) {
		this.memstatus = memstatus;
	}
	public Integer getMemvrfed() {
		return memvrfed;
	}
	public void setMemvrfed(Integer memvrfed) {
		this.memvrfed = memvrfed;
	}
	public Timestamp getMemnovrftime() {
		return memnovrftime;
	}
	public void setMemnovrftime(Timestamp memnovrftime) {
		this.memnovrftime = memnovrftime;
	}
	public String getMemname() {
		return memname;
	}
	public void setMemname(String memname) {
		this.memname = memname;
	}
	public String getMemmobile() {
		return memmobile;
	}
	public void setMemmobile(String memmobile) {
		this.memmobile = memmobile;
	}
	public String getMemcity() {
		return memcity;
	}
	public void setMemcity(String memcity) {
		this.memcity = memcity;
	}
	public String getMemdist() {
		return memdist;
	}
	public void setMemdist(String memdist) {
		this.memdist = memdist;
	}
	public String getMemadd() {
		return memadd;
	}
	public void setMemadd(String memadd) {
		this.memadd = memadd;
	}
	public String getMememail() {
		return mememail;
	}
	public void setMememail(String mememail) {
		this.mememail = mememail;
	}
	public Date getMembirth() {
		return membirth;
	}
	public void setMembirth(Date membirth) {
		this.membirth = membirth;
	}
	public Timestamp getMemjointime() {
		return memjointime;
	}
	public void setMemjointime(Timestamp memjointime) {
		this.memjointime = memjointime;
	}
	public Integer getUsderstatus() {
		return usderstatus;
	}
	public void setUsderstatus(Integer usderstatus) {
		this.usderstatus = usderstatus;
	}
	public Integer getEcash() {
		return ecash;
	}
	public void setEcash(Integer ecash) {
		this.ecash = ecash;
	}
}
