package com.javaex.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public void dbConnect() {
			try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb"); // db url, 아이디, 비밀번호

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	
	public void dbDisConnect() {
		try {
			if (rs != null) { rs.close(); }
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	
	public void authorInsert(AuthorVo authorVo) {
		try {
			String query = "insert into author values (seq_author_id.nextval, ?, ?)";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, authorVo.getAuthorName());
			pstmt.setString(2, authorVo.getAuthorDesc());

			int count = pstmt.executeUpdate();
			// 4.결과처리
			System.out.println("insert "+count + "건이 처리되었습니다.");
		} catch (Exception e) {
			System.out.println("error"+e);
		}
	}
	public void authorUpdate(AuthorVo authorVo) {
		try {
			String query = "";
			query+="update author set author_name=?, author_desc=? where author_id=?";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, authorVo.getAuthorName());
			pstmt.setString(2,authorVo.getAuthorDesc());
			pstmt.setInt(3,authorVo.getAuthorId());
			int count = pstmt.executeUpdate();
			System.out.println("update "+count + "건이 처리되었습니다.");
		}catch(Exception e) {
			System.out.println("error"+e);
		}
	}
	public void authorDelete(int num) {
		try {
			String query = "";
			query+="delete from author where author_id = ?";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, num);
			int count = pstmt.executeUpdate();
			System.out.println("delete "+count + "건이 처리되었습니다.");
		}catch(Exception e) {
			System.out.println("error"+e);
		}
	}
	public List<AuthorVo> getAuthorList() {
		List<AuthorVo> lav = new ArrayList<AuthorVo>();
		try {
			String query = "";
			query += "select author_id, ";
			query += "author_name, ";
			query += "author_desc ";
			query += "from author";

			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			// 4.결과처리

			while (rs.next()) {
				AuthorVo aVo = new AuthorVo();
				aVo.setAuthorId(rs.getInt(1));
				aVo.setAuthorName(rs.getString(2));
				aVo.setAuthorDesc(rs.getString(3));
				lav.add(aVo);
			}
		}catch(Exception e) {
			System.out.println("erroe"+e);
		}
		// 3. SQL문 준비 / 바인딩 / 실행

		return lav;
	}
}
