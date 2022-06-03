package business;

import java.io.Serializable;
import java.util.List;

import dataaccess.User;

public class CheckoutRecord implements Serializable {

	private static final long serialVersionUID = 1L;
	private LibraryMember member;
	private List<CheckoutEntry> checkoutEntries;

	CheckoutRecord(LibraryMember member) {
		this.member = member;
	}

	public List<CheckoutEntry> getCheckoutEntries() {
		return checkoutEntries;
	}
	
	public void setCheckoutEntries(List<CheckoutEntry> checkoutEntries) {
		this.checkoutEntries = checkoutEntries;
	}

	public LibraryMember getMember() {
		return member;
	}

	public void setMember(LibraryMember member) {
		this.member = member;
	}		
}
