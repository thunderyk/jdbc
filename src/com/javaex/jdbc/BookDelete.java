package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookDelete {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
				
		try {
				// 1. JDBC 드라이버 (Oracle) 로딩
				Class.forName("oracle.jdbc.driver.OracleDriver");
						
				// 2. Connection 얻어오기
				String url = "jdbc:oracle:thin:@localhost:1521:xe";
				conn = DriverManager.getConnection(url, "webdb", "webdb"); //db url, 아이디, 비밀번호
						
				// 3. SQL문 준비 / 바인딩 / 실행
				String query = "delete from book where book_id = ?";
						
						
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, 26);
						
				int count = pstmt.executeUpdate();
				// 4.결과처리
				System.out.println(count+"건이 처리되었습니다.");
						
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
				   
			// 5. 자원정리
			try {
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
	}

}
