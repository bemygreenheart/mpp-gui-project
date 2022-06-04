package DataAccess;

import Entity.*;

import java.util.List;

public class DataAccessFacade implements DataAccess {

	
	PersistenceData persistentData = new PersistenceData();

	@Override
	public Role verifyUser(int id, String password) {
		for (User user: persistentData.getUsers()) {
			if (user.login(id, password)) return user.getRole();
		}
		return null;
	}

	@Override
	public void saveNewMember(LibraryMember libraryMember) {
		
		persistentData.addLibraryMembers(libraryMember);
		
	}

	@Override
	public void saveNewBook(Book book) {
		
		persistentData.addBook(book);
	
		
	}

	@Override
	public void saveNewBookCopy(BookCopy bookCopy) {
		
		persistentData.addBookCopies(bookCopy);
	}

	@Override
	public boolean searchMember(String memberId) {
		
		boolean found= false;
		String id;
		for(LibraryMember l : persistentData.getLibraryMembers()) {
			
			if(l.getMemberId().equals(memberId)) {
				return true;
				
			}
		}
		
		return false;
		
		
	}

	@Override
	public Book searchBook(String isbn) {
		// TODO Auto-generated method stub
		
		boolean found= false;
		String id;
		for(Book book : persistentData.getBooks()) {
			
			if(book.getIsbn().equals(isbn)) {
				return book;
				
			}
		}
		
		return null;
		
	}

	@Override
	public int getMaximumCheckoutLength(String isbn) {
		// TODO Auto-generated method stub
		for(Book book : persistentData.getBooks()) {
			
			if(book.getIsbn().equals(isbn)) {
				return book.getMaxCheckOutLength();
				
			}
		}
		
		return -1;
	}

	@Override
	public BookCopy nextAvailableBookCopy(String isbn) {
		// TODO Auto-generated method stub
		
		for(Book book : persistentData.getBooks()) {
			
			if(book.getIsbn().equals(isbn)) {
				
				for(BookCopy bCopy: book.getBookCopyList()) {
					if(bCopy.getAvailability()) {
						return bCopy;
					}
				}
				
			}
		}
		
		return null;	
	}

	@Override
	public void saveMemberCheckoutRecord(String memberId, CheckOutRecordEntry entry) {
		// TODO Auto-generated method stub
		
		for(LibraryMember member: persistentData.getLibraryMembers()) {
			
			if(member.getMemberId().equals(memberId)) {
				System.out.println("Passed Entry: /n"+ entry);
				member.addCheckOutRecordEntry(entry);
			}
		}
		
	}

	@Override
	public List<CheckOutRecordEntry> getCheckOutRecord(String memberId) {

		for(LibraryMember member : persistentData.getLibraryMembers()) {

			if(member.getMemberId().equals(memberId)) {

				CheckOutRecord checkOutRecord = member.getCheckOutRecord();
				return checkOutRecord.getCheckOutRecordEntries();

			}
		}

		return null;
	}

}
