package com.rmadss.delegate;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Vector;

import com.rmadss.bean.ProfileTO;
import com.rmadss.exception.ConnectionException;
import com.rmadss.exception.LoginException;
import com.rmadss.serviceimpl.UserServiceImpl;

public class UserDelegate {

	Vector<ProfileTO> vpro = null;
	UserServiceImpl usi = new UserServiceImpl();

	public boolean insertNewUser(ProfileTO pf) throws FileNotFoundException,
			ConnectionException {
		return usi.insertNewUser(pf);
	}

	public boolean updateUser(ProfileTO pf) throws FileNotFoundException,
			ConnectionException, SQLException {
		return usi.updateUser(pf);
	}

	public String checkUser(String userName) throws ConnectionException {

		return usi.checkUser(userName);

	}

	public Vector<ProfileTO> viewUser(String path, String user)
			throws FileNotFoundException, ConnectionException {
		return usi.viewUser(path, user);
	}

	public Vector<ProfileTO> viewListOfUsers(String usertype, String path,
			int userid) throws FileNotFoundException, ConnectionException {
		return usi.viewListOfUsers(usertype, path, userid);
	}

	public Vector<ProfileTO> loginCheck(ProfileTO pro) throws LoginException,
			ConnectionException, SQLException {
		vpro = usi.loginCheck(pro);
		return vpro;
	}

	public boolean changePass(ProfileTO pro) throws ConnectionException,
			SQLException {
		return usi.changePass(pro);
	}

	public boolean passwordRecovery(ProfileTO pro) throws ConnectionException,
			SQLException {
		return usi.passwordRecovery(pro);
	}

	public boolean forgetPass(ProfileTO pro) throws ConnectionException,
			SQLException {
		return usi.forgetPass(pro);
	}

	public boolean deleteUser(int userid) throws ConnectionException {
		return usi.deleteUser(userid);
	}

}
