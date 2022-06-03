package dataaccess;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.Author;
import business.Book;
import business.BookCopy;
import business.CheckoutEntry;
import business.CheckoutRecord;
import business.LibraryMember;

public class DataAccessFacade implements DataAccess, ICheckoutDataAccess {

	enum StorageType {
		BOOKS, BOOKCOPIES, MEMBERS, USERS, CHECKOUTRECORDS, CHECKOUTENTRIES, AUTHORS;
	}

	public static final String DATE_PATTERN = "MM/dd/yyyy";

	// implement: other save operations
	@Override
	public void saveNewMember(LibraryMember member) {
		HashMap<String, LibraryMember> mems = readMemberMap();
		String memberId = member.getMemberId();
		mems.put(memberId, member);
		saveToStorage(StorageType.MEMBERS, mems);
	}

	// implement: save for new Book
	@Override
	public void saveNewBook(Book book) {
		HashMap<String, Book> books = readBooksMap();
		String memberId = book.getIsbn();
		books.put(memberId, book);
		saveToStorage(StorageType.BOOKS, books);
	}

	@Override
	public void saveNewBookCopy(BookCopy bc) {
		// TODO Auto-generated method stub
		HashMap<String, BookCopy> books = readBookCopyMap();
		String bookID = bc.getBook().getIsbn() + bc.getCopyNum();
		books.put(bookID, bc);
		saveToStorage(StorageType.BOOKCOPIES, books);
	}

	@Override
	public boolean isBookExists(Book book) {
		HashMap<String, Book> books = readBooksMap();
		return books.containsKey(book.getIsbn());
	}

	@Override
	public void saveNewAuthor(Author author) {
		HashMap<String, Author> authors = readAuthorMap();
		String authorNameAndLastName = author.getFirstName() + author.getLastName();
		authors.put(authorNameAndLastName, author);
		saveToStorage(StorageType.AUTHORS, authors);
	}

	@Override
	public void deleteMember(LibraryMember member) {
		HashMap<String, LibraryMember> mems = readMemberMap();
		String memberId = member.getMemberId();
		mems.remove(memberId);
		saveToStorage(StorageType.MEMBERS, mems);
	}

	@Override
	@SuppressWarnings("unchecked")
	public HashMap<String, Book> readBooksMap() {
		// Returns a Map with name/value pairs being
		// isbn -> Book
		return (HashMap<String, Book>) readFromStorage(StorageType.BOOKS);
	}

//	@Override
//	@SuppressWarnings("unchecked")
//	public HashMap<String, BookCopy> readBookCopyMap() {
//		return (HashMap<String, BookCopy>) readFromStorage(StorageType.BOOKCOPIES);
//	}

	@Override
	@SuppressWarnings("unchecked")
	public HashMap<String, LibraryMember> readMemberMap() {
		// Returns a Map with name/value pairs being
		// memberId -> LibraryMember
		return (HashMap<String, LibraryMember>) readFromStorage(StorageType.MEMBERS);
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, Author> readAuthorMap() {
		// Returns a Map with name/value pairs being
		// memberId -> LibraryMember
		return (HashMap<String, Author>) readFromStorage(StorageType.AUTHORS);
	}

	@Override
	@SuppressWarnings("unchecked")
	public HashMap<String, User> readUserMap() {
		// Returns a Map with name/value pairs being
		// userId -> User
		return (HashMap<String, User>) readFromStorage(StorageType.USERS);
	}

	///// load methods - these place test data into the storage area
	///// - used just once at startup

	static void loadBookMap(List<Book> bookList) {
		HashMap<String, Book> books = new HashMap<String, Book>();
		bookList.forEach(book -> books.put(book.getIsbn(), book));
		saveToStorage(StorageType.BOOKS, books);
	}

	static void loadBookCopyMap(List<BookCopy> bookCopyList) {
		HashMap<String, BookCopy> books = new HashMap<String, BookCopy>();
		bookCopyList.forEach(bookCopy -> books.put(bookCopy.getBook().getIsbn() + bookCopy.getCopyNum(), bookCopy));
		saveToStorage(StorageType.BOOKCOPIES, books);
	}

	static void loadAuthorMap(List<Author> authors) {
		HashMap<String, Author> authorsMap = new HashMap<String, Author>();
		authors.forEach(author -> authorsMap.put(author.getFirstName() + author.getLastName(), author));
		saveToStorage(StorageType.AUTHORS, authorsMap);
	}

	static void loadUserMap(List<User> userList) {
		HashMap<String, User> users = new HashMap<String, User>();
		userList.forEach(user -> users.put(user.getId(), user));
		saveToStorage(StorageType.USERS, users);
	}

	static void loadMemberMap(List<LibraryMember> memberList) {
		HashMap<String, LibraryMember> members = new HashMap<String, LibraryMember>();
		memberList.forEach(member -> members.put(member.getMemberId(), member));
		saveToStorage(StorageType.MEMBERS, members);
	}

	static void saveToStorage(StorageType type, Object ob) {
		ObjectOutputStream out = null;
		try {
			Path path = FileSystems.getDefault().getPath(FilePath.OUTPUT_DIR, type.toString());
			out = new ObjectOutputStream(Files.newOutputStream(path));
			out.writeObject(ob);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (Exception e) {
				}
			}
		}
	}

	static Object readFromStorage(StorageType type) {
		ObjectInputStream in = null;
		Object retVal = null;
		try {
			Path path = FileSystems.getDefault().getPath(FilePath.OUTPUT_DIR, type.toString());
			in = new ObjectInputStream(Files.newInputStream(path));
			retVal = in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
				}
			}
		}
		return retVal;
	}

	final static class Pair<S, T> implements Serializable {

		S first;
		T second;

		Pair(S s, T t) {
			first = s;
			second = t;
		}

		@Override
		public boolean equals(Object ob) {
			if (ob == null)
				return false;
			if (this == ob)
				return true;
			if (ob.getClass() != getClass())
				return false;
			@SuppressWarnings("unchecked")
			Pair<S, T> p = (Pair<S, T>) ob;
			return p.first.equals(first) && p.second.equals(second);
		}

		@Override
		public int hashCode() {
			return first.hashCode() + 5 * second.hashCode();
		}

		@Override
		public String toString() {
			return "(" + first.toString() + ", " + second.toString() + ")";
		}

		private static final long serialVersionUID = 5399827794066637059L;
	}

	// lamintun
	@Override
	public void saveNewCheckoutRecord(CheckoutRecord record) {
		HashMap<String, CheckoutRecord> mems = readCheckoutRecordMap();
		String memberId = record.getMember().getMemberId();

		if (mems == null)
			mems = new HashMap<String, CheckoutRecord>();

		mems.put(memberId, record);
		saveToStorage(StorageType.CHECKOUTRECORDS, mems);
	}

	@Override
	public void updateBookCopyForCheckoutOrReturn(BookCopy bookCopy) {
		HashMap<String, Book> books = readBooksMap();

		BookCopy copy = Arrays.asList(books.get(bookCopy.getBook().getIsbn()).getCopies()).stream()
				.filter(c -> c.getCopyNum() == bookCopy.getCopyNum()).findAny().orElse(null);

		copy.changeAvailability();

		saveToStorage(StorageType.BOOKS, books);
	}

	@Override
	public void updateAllBookAvailable() {
		HashMap<String, Book> books = readBooksMap();

		if (books != null) {
			for (Map.Entry<String, Book> entry : books.entrySet()) {
				Book book = entry.getValue();

				if (book != null)
					for (BookCopy copy : book.getCopies()) {
						copy.changeAvailability();
					}

			}
			saveToStorage(StorageType.BOOKS, books);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public HashMap<String, CheckoutRecord> readCheckoutRecordMap() {
		// Returns a Map with name/value pairs being
		// memberId -> LibraryMember
		return (HashMap<String, CheckoutRecord>) readFromStorage(StorageType.CHECKOUTRECORDS);
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, CheckoutEntry> readCheckoutEntryMap() {
		// Returns a Map with name/value pairs being
		// memberId -> LibraryMember
		return (HashMap<String, CheckoutEntry>) readFromStorage(StorageType.CHECKOUTENTRIES);
	}

	@Override
	@SuppressWarnings("unchecked")
	public HashMap<String, BookCopy> readBookCopyMap() {
		// Returns a Map with name/value pairs being
		// isbn -> Book
		return (HashMap<String, BookCopy>) readFromStorage(StorageType.BOOKCOPIES);
	}

}
