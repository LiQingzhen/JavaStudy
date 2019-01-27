package personal.throws_advice;

import personal.throws_advice.myexception.PasswordException;
import personal.throws_advice.myexception.UserException;
import personal.throws_advice.myexception.UsernameException;

public class SomeService implements ISomeService {

	@Override
	public boolean login(String username, String password) throws UserException {

		if (!"LiQingzhen".equals(username)) {
			throw new UsernameException("用户名错误！");
		}
		if (!"123456".equals(password)) {
			throw new PasswordException("密码错误！");
		}

		return true;
	}

}
