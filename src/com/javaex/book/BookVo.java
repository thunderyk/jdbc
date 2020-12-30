package com.javaex.book;

import java.sql.Date;

public class BookVo {

	private int bookId;
	private String bookTitle;
	private String bookPubs;
	private Date bookPubDate;
	private int authorId;
	private String authorName;
	private String authorDesc;
	
	public BookVo() {
		
	}
	
	public BookVo(String bookTitle,String bookPubs, Date bookPubDate,int authorId) {
		this.bookTitle = bookTitle;
		this.bookPubs = bookPubs;
		this.bookPubDate = bookPubDate;
		this.authorId = authorId;
	}
	public BookVo(int bookId, String bookTitle, String bookPubs,Date bookPubDate, int authorId) {
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.bookPubs = bookPubs;
		this.bookPubDate = bookPubDate;
		this.authorId = authorId;
	}
	
	public int getBookId() {
		return bookId;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public String getBookPubs() {
		return bookPubs;
	}
	public Date getBookPubDate() {
		return bookPubDate;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public void setBookPubs(String bookPubs) {
		this.bookPubs = bookPubs;
	}
	public void setBookPubDate(Date bookPubDate) {
		this.bookPubDate = bookPubDate;
	}
	public String getAuthorName() {
		return authorName;
	}

	public String getAuthorDesc() {
		return authorDesc;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public void setAuthorDesc(String authorDesc) {
		this.authorDesc = authorDesc;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	
}
