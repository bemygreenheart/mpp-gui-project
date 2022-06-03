package business;

import java.util.List;

import business.Book;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public interface ControllerInterface {
	public User login(String username, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();

}
