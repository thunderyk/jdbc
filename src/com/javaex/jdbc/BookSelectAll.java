package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookSelectAll {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // insert문에서 필요

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb"); // db url, 아이디, 비밀번호

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += "select book_id, ";
			query += "title, ";
			query += "pubs,";
			query += "pub_date,";
			query += "a.author_id,";
			query += "author_name,";
			query += "author_desc ";
			query += "from book b, author a ";
			query += "where a.author_id = b.author_id";
			
			
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			// 4.결과처리

			while (rs.next()) {
				int bookId = rs.getInt(1);
				String title = rs.getString(2);
				String pubs = rs.getString(3);
				Date pubDate = rs.getDate(4);
				int authorId = rs.getInt(5);
				String authorName = rs.getString(6);
				String authorDesc = rs.getString(7);
				System.out.println(bookId + "," + title + "," + pubs + "," +
								   pubDate + "," + authorId+ "," + authorName+ "," + authorDesc);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
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
