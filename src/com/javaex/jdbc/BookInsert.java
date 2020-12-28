package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookInsert {

	public static void main(String[] args) {
				Connection conn = null;
				PreparedStatement pstmt = null;

				try {
				    	// 1. JDBC 드라이버 (Oracle) 로딩
						Class.forName("oracle.jdbc.driver.OracleDriver");
						
						// 2. Connection 얻어오기
						String url = "jdbc:oracle:thin:@localhost:1521:xe";
						conn = DriverManager.getConnection(url, "webdb", "webdb"); //db url, 아이디, 비밀번호
						
						// 3. SQL문 준비 / 바인딩 / 실행
						String query = "insert into book values (seq_author_id.nextval, ?, ?,?,?)";
						pstmt = conn.prepareStatement(query);
						
						pstmt.setString(1, "해를 품은 달");
						pstmt.setString(2, "만국");
						Date date = Date.valueOf("2016-12-28");
						pstmt.setDate(3, date);
						pstmt.setInt(4,22);
						
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
