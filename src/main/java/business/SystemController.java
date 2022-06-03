package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class SystemController implements ControllerInterface {

	public User login(String username, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> userMap = da.readUserMap();
		System.out.println(userMap);
		User loggedInUser = null;
		for (User user: userMap.values()) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				loggedInUser = user;
				break;
			}
		}
		if (loggedInUser == null) {
			throw new LoginException("Incorrect password or username");
		}
		return loggedInUser;
	}

	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}

	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}


}
