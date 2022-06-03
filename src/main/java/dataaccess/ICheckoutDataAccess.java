package dataaccess;

import java.util.HashMap;

import business.Book;
import business.BookCopy;
import business.CheckoutRecord;
import business.LibraryMember;

public interface ICheckoutDataAccess {
	public HashMap<String, LibraryMember> readMemberMap();
	public HashMap<String,Book> readBooksMap();
	public HashMap<String, CheckoutRecord> readCheckoutRecordMap();
	public void saveNewCheckoutRecord(CheckoutRecord record);
	public HashMap<String, BookCopy> readBookCopyMap();
	public void updateBookCopyForCheckoutOrReturn(BookCopy bookCopy);
	public void updateAllBookAvailable();
} 
