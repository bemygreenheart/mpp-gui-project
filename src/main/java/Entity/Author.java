package Entity;

public class Author extends Person{
	
	private String shortBio;
	private String credentials;

	public Author(String firstName, String lastName, String phoneNumber, Address address, String credential, String shortBio) {
		super(firstName, lastName, phoneNumber, address);
		this.credentials = credential;
		this.shortBio = shortBio;
		
	}

}
