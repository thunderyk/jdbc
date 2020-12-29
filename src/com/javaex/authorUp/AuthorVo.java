package com.javaex.authorUp;

public class AuthorVo {

	int authorId;
	String authorName;
	String authorDesc;
	
	public void showData() {
		System.out.println(authorId+", "+authorName+", "+authorDesc);
	}
}
