package data_access;

import business.*;

import java.util.List;
public interface DataAccess {
	
	public void saveNewMember(LibraryMember libraryMember);
	public void saveNewBook(Book book);
	public void saveNewBookCopy(BookCopy bookCopy);
	public boolean searchMember(String memberId);
	public Book searchBook(String isbn);
	public int getMaximumCheckoutLength(String isbn);
	public BookCopy nextAvailableBookCopy(String isbn );
	public void saveMemberCheckoutRecord(String memberId, CheckOutRecordEntry entry);
	public AuthType verifyUser(String id, String password);
	public List<CheckOutRecordEntry> getCheckOutRecord(String memberId);

}
