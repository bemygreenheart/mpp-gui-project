package Entity;

public class Person {

	public Person(String firstName, String lastName, String phoneNumber, Address address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
	
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private Address address;

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}
