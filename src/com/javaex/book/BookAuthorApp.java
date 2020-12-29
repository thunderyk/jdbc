package com.javaex.book;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class BookAuthorApp {

	public static void main(String[] args) {

		AuthorDao authorDao = new AuthorDao();
		authorDao.dbConnect();
		BookDao bookDao = new BookDao();
		bookDao.dbConnect();

		// 작가 6명 등록
		// AuthorDao, AuthorVo 이용해서 등록
		AuthorVo authorVo01 = new AuthorVo("김문열", "경북 양양");
		authorDao.authorInsert(authorVo01);
		AuthorVo authorVo02 = new AuthorVo("박경리", "경상남도 통영");
		authorDao.authorInsert(authorVo02);
		AuthorVo authorVo03 = new AuthorVo("유시민", "17대 국회의원");
		authorDao.authorInsert(authorVo03);
		AuthorVo authorVo04 = new AuthorVo("기안84", "기안동에서 산 84년생");
		authorDao.authorInsert(authorVo04);
		AuthorVo authorVo05 = new AuthorVo("강풀", "온라인 만화가 1세대");
		authorDao.authorInsert(authorVo05);
		AuthorVo authorVo06 = new AuthorVo("김영하", "알쓸신잡");
		authorDao.authorInsert(authorVo06);
		AuthorVo authorVo07 = new AuthorVo("김영관", "서울시 도봉구");
		authorDao.authorInsert(authorVo07);

		// authorSelect
		List<AuthorVo> authorList = authorDao.getAuthorList();
		authorPrint(authorList);

		// authorUpdate
		AuthorVo authorVo08 = new AuthorVo(7, "김영관", "호주");
		authorDao.authorUpdate(authorVo08);
		authorList = authorDao.getAuthorList();
		authorPrint(authorList);

		// authorDelete
		authorDao.authorDelete(7);
		authorList = authorDao.getAuthorList();
		authorPrint(authorList);

		// 책 8권 등록
		BookVo bookVo01 = new BookVo("우리들의 일그러진 영웅", "다림", Date.valueOf("1998-02-22"), authorList.get(0).getAuthorId());
		bookDao.BookInsert(bookVo01);
		BookVo bookVo02 = new BookVo("삼국지", "민음사", Date.valueOf("2002-03-01"), authorList.get(0).getAuthorId());
		bookDao.BookInsert(bookVo02);
		BookVo bookVo03 = new BookVo("토지", "마로니에북스", Date.valueOf("2012-08-15"), authorList.get(1).getAuthorId());
		bookDao.BookInsert(bookVo03);
		BookVo bookVo04 = new BookVo("유시민의 글쓰기 특강", "생각의길", Date.valueOf("2015-04-01"),
				authorList.get(2).getAuthorId());
		bookDao.BookInsert(bookVo04);
		BookVo bookVo05 = new BookVo("패션왕", "중앙북스(books)", Date.valueOf("2012-02-22"), authorList.get(3).getAuthorId());
		bookDao.BookInsert(bookVo05);
		BookVo bookVo06 = new BookVo("순정만화", "재미주의", Date.valueOf("2011-08-03"), authorList.get(4).getAuthorId());
		bookDao.BookInsert(bookVo06);
		BookVo bookVo07 = new BookVo("오직두사람", "문학동네", Date.valueOf("2017-05-04"), authorList.get(5).getAuthorId());
		bookDao.BookInsert(bookVo07);
		BookVo bookVo08 = new BookVo("26년", "재미주의", Date.valueOf("2012-02-04"), authorList.get(4).getAuthorId());
		bookDao.BookInsert(bookVo08);
		BookVo bookVo09 = new BookVo("해를 품은달", "만국", Date.valueOf("2017-12-28"), authorList.get(0).getAuthorId());
		bookDao.BookInsert(bookVo09);
		// 책 8권 출력 작가 정보까지

		// bookSelect
		List<BookVo> bookList = bookDao.getBookList();
		bookPrint(authorList, bookList);

		// bookUpdate
		BookVo bookVo10 = new BookVo(9, "해를 품은달", "음양출판사", Date.valueOf("2017-12-28"), authorList.get(0).getAuthorId());
		bookDao.bookUpdate(bookVo10);
		bookList = bookDao.getBookList();
		bookPrint(authorList, bookList);

		// bookDelete
		bookDao.BookDelete(9);
		bookList = bookDao.getBookList();
		bookPrint(authorList, bookList);

		Scanner sc = new Scanner(System.in);

		System.out.println("");
		System.out.println("");
		System.out.print("찾을 문자를 입력해주세요: ");
		String word = sc.nextLine();

		for (BookVo books : bookList) {
			for (AuthorVo authors : authorList) {
				if (books.getAuthorId() == authors.getAuthorId()) {
					if (books.getBookTitle().contains(word) || books.getBookPubs().contains(word)
							|| authors.getAuthorName().contains(word) || authors.getAuthorDesc().contains(word)) {
						System.out.println(books.getBookId() + ", " + books.getBookTitle() + ", " + books.getBookPubs()
								+ ", " + books.getBookPubDate() + ", " + books.getAuthorId() + ", "
								+ authors.getAuthorName() + ", " + authors.getAuthorDesc());
					}
					break;
				}
			}
		}

		sc.close();

		authorDao.dbDisConnect();
		bookDao.dbDisConnect();

	}

	public static void authorPrint(List<AuthorVo> authorList) {
		System.out.println("=======작가 리스트=======");
		for (int i = 0; i < authorList.size(); i++) {
			AuthorVo vo = authorList.get(i);
			System.out.println(vo.getAuthorId() + ", " + vo.getAuthorName() + ", " + vo.getAuthorDesc());
		}
	}

	public static void bookPrint(List<AuthorVo> authorList, List<BookVo> bookList) {
		System.out.println("=======책 리스트=======");
		for (int i = 0; i < bookList.size(); i++) {
			BookVo bookVo = bookList.get(i);
			for (int j = 0; j < authorList.size(); j++) {
				AuthorVo authorVo = authorList.get(j);
				if (bookList.get(i).getAuthorId() == authorVo.getAuthorId()) {

					System.out.println(bookVo.getBookId() + ", " + bookVo.getBookTitle() + ", " + bookVo.getBookPubs()
							+ ", " + bookVo.getBookPubDate() + ", " + bookVo.getAuthorId() + ", "
							+ authorVo.getAuthorName() + ", " + authorVo.getAuthorDesc());
					break;
				}
			}
		}
	}
}
