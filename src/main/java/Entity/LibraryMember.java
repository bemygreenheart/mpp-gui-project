package Entity;

public class LibraryMember extends Person {

	private String memberId;
	private CheckOutRecord checkoutRecord = new CheckOutRecord();;
	
	
	public LibraryMember( String memberId, String firstName, String lastName, String phoneNumber, Address address) {
		super(firstName, lastName, phoneNumber, address);
		
		this.memberId = memberId;
		
	}

	public String getMemberId() {
		return memberId;
	}
	
	public CheckOutRecord getCheckOutRecord() {
		return checkoutRecord;
	}

	public void addCheckOutRecordEntry(CheckOutRecordEntry entry) {
		checkoutRecord.addCheckOutRecordEntry(entry);
		entry.setCheckOutRecord(checkoutRecord);
	}
}