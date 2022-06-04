package data_access;

import business.*;

import java.util.ArrayList;
import java.util.List;

public class PersistenceData {
	
	
	List<Book> books = new ArrayList<>();
	List<BookCopy> bookCopies = new ArrayList<>();
	List<LibraryMember> libraryMembers = new ArrayList<>();

	public void addBook(Book book) {
		books.add(book);
	}
	
	public List<Book> getBooks(){
		return books;
	}
	
	public void addLibraryMembers(LibraryMember libraryMember) {
		libraryMembers.add(libraryMember);
	}
	
	public List<LibraryMember> getLibraryMembers(){
		return libraryMembers;
	}

	public List<BookCopy> getBookCopies() {
		return bookCopies;
	}

	public void addBookCopies(BookCopy bookCopy) {
		this.bookCopies.add(bookCopy);
	}

	public ArrayList<User> getUsers() {
		ArrayList<User> list = new ArrayList<>();
		//Admins
		User admin1 = new User("100", "admin1", AuthType.ADMIN);
		User admin2 = new User("101", "admin2", AuthType.ADMIN);

		//Librarians
		User lib1 = new User("200", "lib1", AuthType.LIBRARIAN);
		User lib2 = new User("201", "lib2", AuthType.LIBRARIAN);
		User lib3 = new User("202", "lib3", AuthType.LIBRARIAN);

		//Both
		User both1 = new User("300", "both1", AuthType.BOTH);

		list.add(admin1);
		list.add(admin2);
		list.add(lib1);
		list.add(lib2);
		list.add(lib3);
		list.add(both1);

		return list;
	}

}
