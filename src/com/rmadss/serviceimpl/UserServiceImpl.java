package com.rmadss.serviceimpl;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import com.rmadss.bean.ProfileTO;
import com.rmadss.daoimpl.UserDaoImpl;
import com.rmadss.exception.ConnectionException;
import com.rmadss.exception.LoginException;

public class UserServiceImpl {

	Vector<ProfileTO> vpro = new Vector<ProfileTO>();
	boolean flag = false;
	UserDaoImpl rdao = new UserDaoImpl();

	public boolean insertNewUser(ProfileTO pf) throws FileNotFoundException,
			ConnectionException {
		flag = rdao.insertNewUser(pf);
		return flag;
	}

	public boolean updateUser(ProfileTO pf) throws FileNotFoundException,
			ConnectionException, SQLException {
		flag = rdao.updateUser(pf);
		return flag;
	}

	public boolean logout(String loginid) throws ConnectionException {
		// TODO Auto-generated method stub
		return false;
	}

	public Vector<ProfileTO> viewUser(String path, String user)
			throws FileNotFoundException, ConnectionException {
		vpro = rdao.viewUser(path, user);
		return vpro;
	}

	public String checkUser(String userName) throws ConnectionException {
		userName = rdao.checkUser(userName);
		return userName;
	}

	public Vector<ProfileTO> viewListOfUsers(String usertype, String path,
			int userid) throws FileNotFoundException, ConnectionException {
		return rdao.viewListOfUsers(usertype, path, userid);

	}

	public boolean deleteUser(int userid) throws ConnectionException {
		return rdao.deleteUser(userid);
	}

	public Vector<ProfileTO> loginCheck(ProfileTO pro) throws LoginException,
			ConnectionException, SQLException {
		vpro = rdao.loginCheck(pro);
		return vpro;
	}

	public boolean changePass(ProfileTO pro) throws ConnectionException,
			SQLException {
		flag = rdao.changePass(pro);
		if (flag == false) {
			throw new ConnectionException();
		}
		return flag;
	}

	public boolean passwordRecovery(ProfileTO pro) throws ConnectionException,
			SQLException {
		return rdao.passwordRecovery(pro);
	}

	public boolean forgetPass(ProfileTO pro) throws ConnectionException,
			SQLException {
		return rdao.forgetPass(pro);
	}
}
