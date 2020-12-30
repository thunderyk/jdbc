package com.javaex.book;

import java.util.ArrayList;
import java.util.List;

public class BookDao extends Dao{

	
	
	public void BookInsert(BookVo bookVo) {
		super.dbConnect();
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
		super.dbDisConnect();
	}
	public void bookUpdate(BookVo bookVo) {
		super.dbConnect();
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
		super.dbDisConnect();
	}
	public void BookDelete(int num) {
		super.dbConnect();
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
		super.dbDisConnect();
	}
	public List<BookVo> getBookList() {
		super.dbConnect();
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
		super.dbDisConnect();
		
		return lbv;
	}
}
