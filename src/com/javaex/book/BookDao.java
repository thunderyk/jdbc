package com.javaex.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

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
	
	public void BookInsert(BookVo bookVo) {
		try {
			String query = "insert into book values (seq_book_id.nextval, ?, ?,?,?)";
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, bookVo.getBookTitle());
			pstmt.setString(2, bookVo.getBookPubs());
			pstmt.setDate(3, bookVo.getBookPubDate());
			pstmt.setInt(4,bookVo.getAuthorId());

			int count = pstmt.executeUpdate();
			// 4.결과처리
			System.out.println("insert "+count + "건이 처리되었습니다.");
		} catch (Exception e) {
			System.out.println("error"+e);
		}
	}
	public void bookUpdate(BookVo bookVo) {
		try {
			String query = "";
			query+="update book ";
			query+= "set title = ?, ";
			query+=	"pubs = ?, ";
			query+= "pub_date=?, ";
			query+= "author_id=? ";
			query+= "where book_id = ?";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString( 1 , bookVo.getBookTitle());
			pstmt.setString( 2 , bookVo.getBookPubs());
			pstmt.setDate(3, bookVo.getBookPubDate());
			pstmt.setInt(4,bookVo.getAuthorId());
			pstmt.setInt(5, bookVo.getBookId());
			
			int count = pstmt.executeUpdate();
			System.out.println("update "+count + "건이 처리되었습니다.");
		}catch(Exception e) {
			System.out.println("error"+e);
		}
	}
	public void BookDelete(int num) {
		try {
			String query = "";
			query+="delete from book where book_id = ?";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, num);
			int count = pstmt.executeUpdate();
			System.out.println("delete "+count + "건이 처리되었습니다.");
		}catch(Exception e) {
			System.out.println("erroe"+e);
		}
	}
	public List<BookVo> getBookList() {
		List<BookVo> lbv = new ArrayList<BookVo>();
		try {
			String query = "";
			query += "select book_id, ";
			query += "title, ";
			query += "pubs,";
			query += "pub_date,";
			query += "author_id ";
			query += "from book";

			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			// 4.결과처리

			while (rs.next()) {
				BookVo bVo = new BookVo();
				bVo.setBookId(rs.getInt(1));
				bVo.setBookTitle(rs.getString(2));
				bVo.setBookPubs(rs.getString(3));
				bVo.setBookPubDate(rs.getDate(4));
				bVo.setAuthorId(rs.getInt(5));
				
				lbv.add(bVo);
			}
		}catch(Exception e) {
			System.out.println("erroe"+e);
		}
		// 3. SQL문 준비 / 바인딩 / 실행
		return lbv;
	}
}
