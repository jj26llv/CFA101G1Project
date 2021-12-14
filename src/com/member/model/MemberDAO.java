package com.member.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDAO implements MemberDAO_interface {
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CFA101G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
		"INSERT INTO mem (MemAccount, MemPassword, Memstatus, Memvrfed, MemName, MemMobile, MemCity, MemDist, MemAdd, MemEmail, MemBirth, memjointime, UsderStatus, ECash) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM mem order by memno";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM mem where memno = ?";
	private static final String GET_ONE_ACCT_PWD = 
		"SELECT * FROM mem where memaccount =? and mempassword =?";	
	private static final String GET_ONE_ACCT = 
			"SELECT * FROM mem where memaccount =?";	
	private static final String GET_ONE_EMAIL = 
			"SELECT * FROM mem where mememail =?";	
	private static final String UPDATE_EMAILSTATUS =
		"UPDATE mem SET memstatus = 1, MemVrfed = 1 WHERE mememail = ?";
	private static final String UPDATE_APV_USDERSTATUS =
		"UPDATE mem SET usderstatus = 1 WHERE mememail = ?";
	private static final String DELETE = 
		"DELETE FROM mem where memno = ?";
	private static final String UPDATE = 
		"UPDATE mem set MemAccount=?, MemPassword=?, MemStatus=?, Memvrfed=?, MemName=?, MemMobile=?, MemCity=?, MemDist=?, MemAdd=?, MemEmail=?, MemBirth=?, UsderStatus=?, ECash=? where memno = ?";
	private static final String updateMemEcash =
		"UPDATE mem SET ecash=? WHERE memno = ?";
	
	
	
	//FrontEnd 會員註冊
	@Override
	public int registerInsert(MemberVO memVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1,memVO.getMemaccount());
			pstmt.setString(2,memVO.getMempassword());
			pstmt.setInt(3,memVO.getMemstatus());
			pstmt.setInt(4,memVO.getMemvrfed());
			pstmt.setString(5,memVO.getMemname());
			pstmt.setString(6,memVO.getMemmobile());
			pstmt.setString(7,memVO.getMemcity());
			pstmt.setString(8,memVO.getMemdist());
			pstmt.setString(9,memVO.getMemadd());
			pstmt.setString(10,memVO.getMememail());
			pstmt.setDate(11,memVO.getMembirth());
			pstmt.setTimestamp(12,memVO.getMemjointime());			
			pstmt.setInt(13,memVO.getUsderstatus());
			pstmt.setInt(14,memVO.getEcash());
			
			int executeUpdate = pstmt.executeUpdate();
			return executeUpdate;

			// Handle any SQL errors
		} catch (SQLException se) {
			//se.printStackTrace();
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			//return 0;
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	
	//信箱更新會員驗證狀態
	@Override
	public void updateEmailStatus(String mememail) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_EMAILSTATUS);
			pstmt.setString(1, mememail);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	
	//開通會員賣家狀態
	@Override
	public void approveUsderStatus(String mememail) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_APV_USDERSTATUS);
			pstmt.setString(1, mememail);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("A database error occured. " + e.getMessage());
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	//BackEnd 會員註冊
	@Override
	public void insert(MemberVO memVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1,memVO.getMemaccount());
			pstmt.setString(2,memVO.getMempassword());
			pstmt.setInt(3,memVO.getMemstatus());
			pstmt.setInt(4,memVO.getMemvrfed());
			pstmt.setString(5,memVO.getMemname());
			pstmt.setString(6,memVO.getMemmobile());
			pstmt.setString(7,memVO.getMemcity());
			pstmt.setString(8,memVO.getMemdist());
			pstmt.setString(9,memVO.getMemadd());
			pstmt.setString(10,memVO.getMememail());
			pstmt.setDate(11,memVO.getMembirth());
			pstmt.setTimestamp(12,memVO.getMemjointime());			
			pstmt.setInt(13,memVO.getUsderstatus());
			pstmt.setInt(14,memVO.getEcash());
			
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	
	//BackEnd 會員修改
	@Override
	public void update(MemberVO memVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1,memVO.getMemaccount());
			pstmt.setString(2,memVO.getMempassword());
			pstmt.setInt(3,memVO.getMemstatus());
			pstmt.setInt(4,memVO.getMemvrfed());			
			pstmt.setString(5,memVO.getMemname());
			pstmt.setString(6,memVO.getMemmobile());
			pstmt.setString(7,memVO.getMemcity());
			pstmt.setString(8,memVO.getMemdist());
			pstmt.setString(9,memVO.getMemadd());
			pstmt.setString(10,memVO.getMememail());
			pstmt.setDate(11,memVO.getMembirth());
			pstmt.setInt(12,memVO.getUsderstatus());
			pstmt.setInt(13,memVO.getEcash());
			pstmt.setInt(14,memVO.getMemno());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	
	//FrontEnd 會員修改
	@Override
	public void memberUpdate(MemberVO memVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1,memVO.getMemaccount());
			pstmt.setString(2,memVO.getMempassword());
			pstmt.setInt(3,memVO.getMemstatus());
			pstmt.setInt(4,memVO.getMemvrfed());			
			pstmt.setString(5,memVO.getMemname());
			pstmt.setString(6,memVO.getMemmobile());
			pstmt.setString(7,memVO.getMemcity());
			pstmt.setString(8,memVO.getMemdist());
			pstmt.setString(9,memVO.getMemadd());
			pstmt.setString(10,memVO.getMememail());
			pstmt.setDate(11,memVO.getMembirth());
			pstmt.setInt(12,memVO.getUsderstatus());
			pstmt.setInt(13,memVO.getEcash());
			pstmt.setInt(14,memVO.getMemno());
			
			pstmt.executeUpdate();
			
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	
	//更新錢包餘額
	@Override
	public void updateEcash(MemberVO memberVO){

	        Connection con = null;
	        PreparedStatement pstmt = null;

	        try {

	            
	            con = ds.getConnection();
	            pstmt = con.prepareStatement(updateMemEcash);                        
	            pstmt.setInt(1, memberVO.getEcash());
	            pstmt.setInt(2, memberVO.getMemno());
	            
	            pstmt.executeUpdate();

	            // Handle any driver errors
	        } catch (SQLException se) {
	            throw new RuntimeException("A database error occured. "
	                    + se.getMessage());
	            // Clean up JDBC resources
	        } finally {
	            if (pstmt != null) {
	                try {
	                    pstmt.close();
	                } catch (SQLException se) {
	                    se.printStackTrace(System.err);
	                }
	            }
	            if (con != null) {
	                try {
	                    con.close();
	                } catch (Exception e) {
	                    e.printStackTrace(System.err);
	                }
	            }
	        }

	}
	
	
	
	//BackEnd 刪除會員
	@Override
	public void delete(Integer memno) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memno);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	
	
	//FrontEnd Login確認帳密是否正確
	@Override
	public MemberVO confirmAccountAndPassword(String memaccount, String mempassword) {

		MemberVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ACCT_PWD);

			pstmt.setString(1, memaccount);
			pstmt.setString(2, mempassword);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVo 也稱為 Domain objects
				memVO = new MemberVO();
				memVO.setMemno(rs.getInt("memno"));
				memVO.setMemaccount(rs.getString("memaccount"));
				memVO.setMempassword(rs.getString("mempassword"));
				memVO.setMemstatus(rs.getInt("memstatus"));
				memVO.setMemvrfed(rs.getInt("memvrfed"));
				memVO.setMemnovrftime(rs.getTimestamp("memnovrftime"));				
				memVO.setMemname(rs.getString("memname"));
				memVO.setMemmobile(rs.getString("memmobile"));
				memVO.setMemcity(rs.getString("memcity"));
				memVO.setMemdist(rs.getString("memdist"));
				memVO.setMemadd(rs.getString("memadd"));
				memVO.setMememail(rs.getString("mememail"));
				memVO.setMembirth(rs.getDate("membirth"));
				memVO.setMemjointime(rs.getTimestamp("memjointime"));
				memVO.setUsderstatus(rs.getInt("usderstatus"));
				memVO.setEcash(rs.getInt("ecash"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return memVO;
	}
	
	//FrontEnd 驗證帳號是否被使用
	@Override
	public MemberVO checkaccount(String memaccount) {
		
		MemberVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ACCT);
			
			pstmt.setString(1, memaccount);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// memVo 也稱為 Domain objects
				memVO = new MemberVO();
				memVO.setMemno(rs.getInt("memno"));
				memVO.setMemaccount(rs.getString("memaccount"));
				memVO.setMempassword(rs.getString("mempassword"));
				memVO.setMemstatus(rs.getInt("memstatus"));
				memVO.setMemvrfed(rs.getInt("memvrfed"));
				memVO.setMemnovrftime(rs.getTimestamp("memnovrftime"));				
				memVO.setMemname(rs.getString("memname"));
				memVO.setMemmobile(rs.getString("memmobile"));
				memVO.setMemcity(rs.getString("memcity"));
				memVO.setMemdist(rs.getString("memdist"));
				memVO.setMemadd(rs.getString("memadd"));
				memVO.setMememail(rs.getString("mememail"));
				memVO.setMembirth(rs.getDate("membirth"));
				memVO.setMemjointime(rs.getTimestamp("memjointime"));
				memVO.setUsderstatus(rs.getInt("usderstatus"));
				memVO.setEcash(rs.getInt("ecash"));
			}
			
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return memVO;
	}
	
	//FrontEnd 驗證信箱是否被使用
	@Override
	public MemberVO checkemail(String mememail) {
		
		MemberVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_EMAIL);
			
			pstmt.setString(1, mememail);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// memVo 也稱為 Domain objects
				memVO = new MemberVO();
				memVO.setMemno(rs.getInt("memno"));
				memVO.setMemaccount(rs.getString("memaccount"));
				memVO.setMempassword(rs.getString("mempassword"));
				memVO.setMemstatus(rs.getInt("memstatus"));
				memVO.setMemvrfed(rs.getInt("memvrfed"));
				memVO.setMemnovrftime(rs.getTimestamp("memnovrftime"));				
				memVO.setMemname(rs.getString("memname"));
				memVO.setMemmobile(rs.getString("memmobile"));
				memVO.setMemcity(rs.getString("memcity"));
				memVO.setMemdist(rs.getString("memdist"));
				memVO.setMemadd(rs.getString("memadd"));
				memVO.setMememail(rs.getString("mememail"));
				memVO.setMembirth(rs.getDate("membirth"));
				memVO.setMemjointime(rs.getTimestamp("memjointime"));
				memVO.setUsderstatus(rs.getInt("usderstatus"));
				memVO.setEcash(rs.getInt("ecash"));
			}
			
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return memVO;
	}
	
	
	//BackEnd 抓取一位會員
	@Override
	public MemberVO findByPrimaryKey(Integer memno) {

		MemberVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memno);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVo 也稱為 Domain objects
				memVO = new MemberVO();
				memVO.setMemno(rs.getInt("memno"));
				memVO.setMemaccount(rs.getString("memaccount"));
				memVO.setMempassword(rs.getString("mempassword"));
				memVO.setMemstatus(rs.getInt("memstatus"));
				memVO.setMemvrfed(rs.getInt("memvrfed"));
				memVO.setMemnovrftime(rs.getTimestamp("memnovrftime"));				
				memVO.setMemname(rs.getString("memname"));
				memVO.setMemmobile(rs.getString("memmobile"));
				memVO.setMemcity(rs.getString("memcity"));
				memVO.setMemdist(rs.getString("memdist"));
				memVO.setMemadd(rs.getString("memadd"));
				memVO.setMememail(rs.getString("mememail"));
				memVO.setMembirth(rs.getDate("membirth"));
				memVO.setMemjointime(rs.getTimestamp("memjointime"));
				memVO.setUsderstatus(rs.getInt("usderstatus"));
				memVO.setEcash(rs.getInt("ecash"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return memVO;
	}
	
	//BackEnd 抓取全部會員
	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memVO 也稱為 Domain objects
				memVO = new MemberVO();
				memVO.setMemno(rs.getInt("memno"));
				memVO.setMemaccount(rs.getString("memaccount"));
				memVO.setMempassword(rs.getString("mempassword"));
				memVO.setMemstatus(rs.getInt("memstatus"));
				memVO.setMemvrfed(rs.getInt("memvrfed"));
				memVO.setMemnovrftime(rs.getTimestamp("memnovrftime"));				
				memVO.setMemname(rs.getString("memname"));
				memVO.setMemmobile(rs.getString("memmobile"));
				memVO.setMemcity(rs.getString("memcity"));
				memVO.setMemdist(rs.getString("memdist"));
				memVO.setMemadd(rs.getString("memadd"));
				memVO.setMememail(rs.getString("mememail"));
				memVO.setMembirth(rs.getDate("membirth"));
				memVO.setMemjointime(rs.getTimestamp("memjointime"));
				memVO.setUsderstatus(rs.getInt("usderstatus"));
				memVO.setEcash(rs.getInt("ecash"));
				list.add(memVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
}
