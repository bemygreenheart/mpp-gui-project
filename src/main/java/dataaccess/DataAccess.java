package dataaccess;

import java.util.HashMap;

import business.Author;
import business.Book;
import business.BookCopy;
import business.LibraryMember;

public interface DataAccess {
	public HashMap<String, Book> readBooksMap();

	public HashMap<String, User> readUserMap();

	public HashMap<String, LibraryMember> readMemberMap();

	public HashMap<String, BookCopy> readBookCopyMap();

	public boolean isBookExists(Book book);

	public void saveNewMember(LibraryMember member);

	public void saveNewBookCopy(BookCopy bc);

	public void deleteMember(LibraryMember member);

	public void saveNewBook(Book book);

	public void saveNewAuthor(Author author);

}
