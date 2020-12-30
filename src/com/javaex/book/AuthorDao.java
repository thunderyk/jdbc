package com.javaex.book;

import java.util.ArrayList;
import java.util.List;

public class AuthorDao extends Dao{

	
	
	public void authorInsert(AuthorVo authorVo) {
		super.dbConnect();
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
		super.dbDisConnect();
	}
	public void authorUpdate(AuthorVo authorVo) {
		super.dbConnect();
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
		super.dbDisConnect();
	}
	public void authorDelete(int num) {
		super.dbConnect();
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
		super.dbDisConnect();
	}
	public List<AuthorVo> getAuthorList() {
		super.dbConnect();
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

		super.dbDisConnect();
		return lav;
	}
}
