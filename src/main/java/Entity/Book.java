package Entity;

import java.util.ArrayList;
import java.util.List;

public class Book {
	
	private String title;
	private String isbn;
//	private boolean availability;
	private int maxCheckOutLength;
	private List<Author> authors;
	private List<BookCopy> bookCopyList;
	
	public Book(String title, String isbn, int maxCheckOutLength, List<Author> authors) {
		super();
		this.title = title;
		this.isbn = isbn;
		this.maxCheckOutLength = maxCheckOutLength;
		this.authors = authors;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public List<BookCopy> getBookCopyList() {
		return bookCopyList;
	}

	public String getTitle() {
		return title;
	}

	public String getIsbn() {
		return isbn;
	}
	
	public int getMaxCheckOutLength() {
		return maxCheckOutLength;
	}

	public void addBookCopy(BookCopy bookCopy) {
		if (bookCopyList == null) bookCopyList = new ArrayList<>();
		this.bookCopyList.add(bookCopy);
		bookCopy.setBook(this);
	}
	
	
	
	

}
