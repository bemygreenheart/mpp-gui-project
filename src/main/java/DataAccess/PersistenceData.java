package DataAccess;

import Entity.*;

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
		User admin1 = new User(Role.ADMIN, 100, "admin1");
		User admin2 = new User(Role.ADMIN, 101, "admin2");

		//Librarians
		User lib1 = new User(Role.LIBRARIAN, 200, "lib1");
		User lib2 = new User(Role.LIBRARIAN, 201, "lib2");
		User lib3 = new User(Role.LIBRARIAN, 202, "lib3");

		//Both
		User both1 = new User(Role.BOTH, 300, "both1");

		list.add(admin1);
		list.add(admin2);
		list.add(lib1);
		list.add(lib2);
		list.add(lib3);
		list.add(both1);

		return list;
	}

}
