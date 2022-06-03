package business;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import dataaccess.User;

public class CheckoutEntry implements Serializable {

	private static final long serialVersionUID = 1L;
	private Date checkoutDate;
	private Date dueDate;
	private Double fine;
	private Date finePaymentDate;
	private Date returnDate;
	private BookCopy bookCopy;
	private CheckoutRecord checkoutRecord;
	private User user;

	public CheckoutEntry(CheckoutRecord checkoutRecord, User user) {
		this.checkoutRecord = checkoutRecord;
		this.fine = 0.0;
		this.user = user;
	}

	@Override
	public String toString() {
		
		String dateFormat = "MM/dd/yyyy hh:mm aa";
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		
		return 	(checkoutDate == null? "" : sdf.format(checkoutDate) + "\t"
				+ (dueDate == null? "" : sdf.format(dueDate)) + "\t" 
				+ bookCopy.getBook().getIsbn() + "\t" + bookCopy.getBook().getTitle());
	}

	public Date getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(Date checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getFinePaymentDate() {
		return finePaymentDate;
	}

	public void setFinePaymentDate(Date finePaymentDate) {
		this.finePaymentDate = finePaymentDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public BookCopy getBookCopy() {
		return bookCopy;
	}

	public void setBookCopy(BookCopy bookCopy) {
		this.bookCopy = bookCopy;
	}

	public void setFine(Double fine) {
		this.fine = fine;
	}

	public double getFine() {
		return fine;
	}

	public CheckoutRecord getCheckoutRecord() {
		return checkoutRecord;
	}

	public void setCheckoutRecord(CheckoutRecord checkoutRecord) {
		this.checkoutRecord = checkoutRecord;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
