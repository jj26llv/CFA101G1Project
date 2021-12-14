//package com.member.model;
//
//import java.util.*;
//import java.sql.*;
//
//public class MemberJDBCDAO implements MemberDAO_interface {
//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "cfa101g1:mysql://localhost:3306/mydb?serverTimezone=Asia/Taipei";
//	String userid = "cfa101g1";
//	String passwd = "cfa101g1";
//
//	private static final String INSERT_STMT = 
//			"INSERT INTO mem (MemAccount, MemPassword, MemStatus, MemName, MemMobile, MemCity, MemDist, MemAdd, MemEmail, MemBirth, MemJointime, UsderStatus, ECash) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//		private static final String GET_ALL_STMT = 
//			"SELECT * FROM mem order by memno";
//		private static final String GET_ONE_STMT = 
//			"SELECT * FROM mem where memno = ?";
//		private static final String DELETE = 
//			"DELETE FROM mem where memno = ?";
//		private static final String UPDATE = 
//			"UPDATE mem set MemAccount=?, MemPassword=?, MemStatus=?, MemName=?, MemMobile=?, MemCity=?, MemDist=?, MemAdd=?, MemEmail=?, MemBirth=?, MemJointime=?, UsderStatus=?, ECash=?) where memno = ?";
//
//	@Override
//	public void insert(MemberVO memVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setString(1,memVO.getMemaccount());
//			pstmt.setString(2,memVO.getMempassword());
//			pstmt.setInt(3,memVO.getMemstatus());
//			pstmt.setString(4,memVO.getMemname());
//			pstmt.setString(5,memVO.getMemmobile());
//			pstmt.setString(6,memVO.getMemcity());
//			pstmt.setString(7,memVO.getMemdist());
//			pstmt.setString(8,memVO.getMemadd());
//			pstmt.setString(9,memVO.getMememail());
//			pstmt.setDate(10,memVO.getMembirth());
//			pstmt.setTimestamp(11,memVO.getMemjointime());
//			pstmt.setInt(12,memVO.getUsderstatus());
//			pstmt.setInt(13,memVO.getEcash());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}
//
//	@Override
//	public void update(MemberVO memVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setString(1,memVO.getMemaccount());
//			pstmt.setString(2,memVO.getMempassword());
//			pstmt.setInt(3,memVO.getMemstatus());
//			pstmt.setString(4,memVO.getMemname());
//			pstmt.setString(5,memVO.getMemmobile());
//			pstmt.setString(6,memVO.getMemcity());
//			pstmt.setString(7,memVO.getMemdist());
//			pstmt.setString(8,memVO.getMemadd());
//			pstmt.setString(9,memVO.getMememail());
//			pstmt.setDate(10,memVO.getMembirth());
//			pstmt.setTimestamp(11,memVO.getMemjointime());
//			pstmt.setInt(12,memVO.getUsderstatus());
//			pstmt.setInt(13,memVO.getEcash());
//			pstmt.setInt(14,memVO.getMemno());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}
//
//	@Override
//	public void delete(Integer memno) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setInt(1, memno);
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}
//
//	@Override
//	public MemberVO findByPrimaryKey(Integer memno) {
//
//		MemberVO memVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setInt(1, memno);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// memVo 也稱為 Domain objects
//				memVO = new MemberVO();
//				memVO.setMemno(rs.getInt("memno"));
//				memVO.setMemaccount(rs.getString("memaccount"));
//				memVO.setMempassword(rs.getString("mempassword"));
//				memVO.setMemstatus(rs.getInt("memstatus"));
//				memVO.setMemvrfed(rs.getInt("memvrfed"));
//				memVO.setMemnovrftime(rs.getTimestamp("memnovrftime"));				
//				memVO.setMemname(rs.getString("memname"));
//				memVO.setMemmobile(rs.getString("memmobile"));
//				memVO.setMemcity(rs.getString("memcity"));
//				memVO.setMemdist(rs.getString("memdist"));
//				memVO.setMemadd(rs.getString("memadd"));
//				memVO.setMememail(rs.getString("mememail"));
//				memVO.setMembirth(rs.getDate("membirth"));
//				memVO.setMemjointime(rs.getTimestamp("memjointime"));
//				memVO.setUsderstatus(rs.getInt("usderstatus"));
//				memVO.setEcash(rs.getInt("ecash"));
//			}
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return memVO;
//	}
//
//	@Override
//	public List<MemberVO> getAll() {
//		List<MemberVO> list = new ArrayList<MemberVO>();
//		MemberVO memVO = null;
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// memVO 也稱為 Domain objects
//				memVO = new MemberVO();
//				memVO.setMemno(rs.getInt("memno"));
//				memVO.setMemaccount(rs.getString("memaccount"));
//				memVO.setMempassword(rs.getString("mempassword"));
//				memVO.setMemstatus(rs.getInt("memstatus"));
//				memVO.setMemvrfed(rs.getInt("memvrfed"));
//				memVO.setMemnovrftime(rs.getTimestamp("memnovrftime"));				
//				memVO.setMemname(rs.getString("memname"));
//				memVO.setMemmobile(rs.getString("memmobile"));
//				memVO.setMemcity(rs.getString("memcity"));
//				memVO.setMemdist(rs.getString("memdist"));
//				memVO.setMemadd(rs.getString("memadd"));
//				memVO.setMememail(rs.getString("mememail"));
//				memVO.setMembirth(rs.getDate("membirth"));
//				memVO.setMemjointime(rs.getTimestamp("memjointime"));
//				memVO.setUsderstatus(rs.getInt("usderstatus"));
//				memVO.setEcash(rs.getInt("ecash"));
//				list.add(memVO); // Store the row in the list
//			}
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return list;
//	}
//
//	public static void main(String[] args) {
//
//		MemberJDBCDAO dao = new MemberJDBCDAO();
//
//		// 新增
//		MemberVO memVO1 = new MemberVO();
//		
//		memVO1.setMemaccount("abc101_1");
//		memVO1.setMempassword("123456");
//		memVO1.setMemstatus(0);
//		memVO1.setMemname("Liu");
//		memVO1.setMemmobile("0988000001");
//		memVO1.setMemcity("台北");
//		memVO1.setMemdist("信義");
//		memVO1.setMemadd("台北路1號");
//		memVO1.setMememail("bbb123@gmail.com");
//		memVO1.setMembirth(java.sql.Date.valueOf("1992-06-27"));
//		memVO1.setMemjointime(java.sql.Timestamp.valueOf("2021-05-30 16:11:41"));
//		memVO1.setUsderstatus(0);
//		memVO1.setEcash(200);
//		dao.insert(memVO1);
//
//
//		// 修改
//		MemberVO memVO2 = new MemberVO();
//		memVO2.setMemno(15001);
//		memVO2.setMemaccount("abc101_2");
//		memVO2.setMempassword("123456");
//		memVO2.setMemstatus(1);
//		memVO2.setMemname("Liu");
//		memVO2.setMemmobile("0988000003");
//		memVO2.setMemcity("台北");
//		memVO2.setMemdist("天母");
//		memVO2.setMemadd("台北路2號");
//		memVO2.setMememail("bbb1234@gmail.com");
//		memVO2.setMembirth(java.sql.Date.valueOf("1992-06-27"));
//		memVO2.setMemjointime(java.sql.Timestamp.valueOf("2021-05-30 16:11:41"));;
//		memVO2.setUsderstatus(1);
//		memVO2.setEcash(20000);
//		dao.update(memVO2);
//
//		// 刪除
//		dao.delete(15001);
//
//		// 查詢
//		MemberVO memVO3 = dao.findByPrimaryKey(15001);
//		System.out.print(memVO3.getMemno() + ",");
//		System.out.print(memVO3.getMemaccount() + ",");
//		System.out.print(memVO3.getMempassword() + ",");
//		System.out.print(memVO3.getMemstatus() + ",");
//		System.out.print(memVO3.getMemvrfed() + ",");
//		System.out.print(memVO3.getMemnovrftime() + ",");
//		System.out.print(memVO3.getMemname() + ",");
//		System.out.print(memVO3.getMemmobile() + ",");
//		System.out.print(memVO3.getMemcity() + ",");
//		System.out.print(memVO3.getMemdist() + ",");
//		System.out.print(memVO3.getMemadd() + ",");
//		System.out.print(memVO3.getMememail() + ",");
//		System.out.print(memVO3.getMembirth() + ",");
//		System.out.print(memVO3.getMemjointime() + ",");
//		System.out.print(memVO3.getUsderstatus() + ",");
//		System.out.println(memVO3.getEcash());
//		System.out.println("---------------------");
//
//		// 查詢
//		List<MemberVO> list = dao.getAll();
//		for (MemberVO aMem : list) {
//			System.out.print(aMem.getMemno() + ",");
//			System.out.print(aMem.getMemaccount() + ",");
//			System.out.print(aMem.getMempassword() + ",");
//			System.out.print(aMem.getMemstatus() + ",");
//			System.out.print(aMem.getMemvrfed() + ",");
//			System.out.print(aMem.getMemnovrftime() + ",");
//			System.out.print(aMem.getMemname() + ",");
//			System.out.print(aMem.getMemmobile() + ",");
//			System.out.print(aMem.getMemcity() + ",");
//			System.out.print(aMem.getMemdist() + ",");
//			System.out.print(aMem.getMemadd() + ",");
//			System.out.print(aMem.getMememail() + ",");
//			System.out.print(aMem.getMembirth() + ",");
//			System.out.print(aMem.getMemjointime() + ",");
//			System.out.print(aMem.getUsderstatus() + ",");
//			System.out.println(aMem.getEcash());
//			System.out.println();
//		}
//	}
//}