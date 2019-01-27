package personal.throws_advice;

import personal.throws_advice.myexception.UserException;

public interface ISomeService {

	boolean login(String username, String password) throws UserException;
	
}
