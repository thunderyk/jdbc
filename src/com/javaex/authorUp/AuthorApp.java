package com.javaex.authorUp;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AuthorDao authorDao = new AuthorDao();
		authorDao.dbConnect();
//		authorDao.authorInsert("김영관","호주");
//		authorDao.authorUpdate(29,"김영관","미국");
		//authorDao.authorDelete(28);
		
		List<AuthorVo> authorList =  authorDao.getAuthorList();
		for(int i=0;i<authorList.size();i++) {
			authorList.get(i).showData();
		}
		authorDao.dbDisConnect();
	}

}
