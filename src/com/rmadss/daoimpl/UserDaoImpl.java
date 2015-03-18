package com.rmadss.daoimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Vector;

import com.rmadss.bean.ProfileTO;
import com.rmadss.dao.AbstractDataAccessObject;
import com.rmadss.dao.SqlConstants;
import com.rmadss.exception.ConnectionException;
import com.rmadss.util.DateWrapper;

public class UserDaoImpl {
	Connection con;
	PreparedStatement pstmt, pstmt1;
	Statement stmt;
	ResultSet rs, rs1;

	boolean flag = false;

	/**
	 * The closeConnection method of the AttendanceDaoImpl Class. <br>
	 * 
	 * This method is called when to take Employee InTime.
	 * 
	 * @throws ConnectionException
	 *             if an error occurred
	 */
	public void closeConnection() throws ConnectionException {
		try {
			if (pstmt != null)
				pstmt.close();
			if (pstmt1 != null)
				pstmt.close();
			if (stmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (SQLException ex) {
			throw new ConnectionException(
					"Server Busy please Try after Sometine");
		}
	}

	public String checkUser(String userName) throws ConnectionException {
		String user = null;
		System.out.println("username" + userName);
		try {
			con = AbstractDataAccessObject.getConnection();
			con.setAutoCommit(true);
			CallableStatement cstmt = con
					.prepareCall("{ call loginidavailablity(?,?) }");
			cstmt.setString(1, userName);
			cstmt.registerOutParameter(2, Types.VARCHAR);
			cstmt.execute();
			user = cstmt.getString(2);
		} catch (SQLException e) {
			System.out.println(e);
			throw new ConnectionException("Available");
		} catch (Exception e) {
			throw new ConnectionException(
					"Some Technical Problem Occurred please try later");
		} finally {
			closeConnection();
		}
		return user;
	}

	public Vector<ProfileTO> viewUser(String userid, String realpath)
			throws ConnectionException {
		Vector<ProfileTO> v = new Vector<ProfileTO>();
		ProfileTO pro = null;
		v.clear();
		try {
			con = AbstractDataAccessObject.getConnection();
			pstmt = con.prepareStatement(SqlConstants._VIEW_USER_PROFILE);
			pstmt.setString(1, userid);
			System.out.println(userid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pro = new ProfileTO();
				pro.setUserid(rs.getInt(1));
				pro.setFirstName(rs.getString(2));
				pro.setLastName(rs.getString(3));
				pro.setBirthdate(DateWrapper.parseDate(rs.getDate(4)));
				pro.setEmail(rs.getString(5));
				Blob b = rs.getBlob(6);
				byte b1[] = b.getBytes(1, (int) b.length());
				String path = realpath + "/" + rs.getInt(1) + ".jpg";
				System.out.println("path  :" + path);
				OutputStream fout = new FileOutputStream(path);
				fout.write(b1);
				pro.setPhoto(path);
				pro.setGender(rs.getString(7));
				pro.setAddressid(rs.getInt(8));
				pro.setAddressType(rs.getString(9));
				pro.setHouseNo(rs.getString(10));
				pro.setStreet(rs.getString(11));
				pro.setCity(rs.getString(12));
				pro.setDistrict(rs.getString(13));
				pro.setState(rs.getString(14));
				pro.setCountry(rs.getString(15));
				pro.setPin(rs.getString(16));
				pro.setPhoneType(rs.getString(17));
				pro.setPhoneNo(rs.getString(18));
				v.add(pro);
			}
		} catch (SQLException e) {
			throw new ConnectionException(
					"Some Technical Problem Occurred please try later");
		} catch (Exception e) {
			throw new ConnectionException(
					"Some Technical Problem Occurred please try later");
		} finally {
			closeConnection();
		}
		return v;
	}

	public boolean insertNewUser(ProfileTO profile)
			throws FileNotFoundException, ConnectionException {
		boolean flag = false;
		int i = 0;
		try {
			con = AbstractDataAccessObject.getConnection();
			File f = new File(profile.getPhoto());
			FileInputStream fis = new FileInputStream(f);
			System.out.println("fole=" + f.length());
			CallableStatement cstmt = con
					.prepareCall("{ call insertprocedure(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			cstmt.setBinaryStream(1, fis, (int) f.length());
			cstmt.setString(2, profile.getFirstName());
			cstmt.setString(3, profile.getLastName());
			cstmt.setString(4, DateWrapper.parseDate(profile.getBirthdate()));
			String squest = profile.getSquest();
			if (squest == null) {
				squest = profile.getOwnquest();
			}
			cstmt.setString(5, squest);
			cstmt.setString(6, profile.getSecrete());
			cstmt.setString(7, profile.getEmail());
			cstmt.setString(8, profile.getFax());
			cstmt.setString(9, profile.getGender());
			cstmt.setString(10, profile.getUserName());
			cstmt.setString(11, profile.getPassword());
			cstmt.setString(12, profile.getLogintype());
			cstmt.setString(13, profile.getAddressType());
			cstmt.setString(14, profile.getHouseNo());
			cstmt.setString(15, profile.getStreet());
			cstmt.setString(16, profile.getCity());
			cstmt.setString(17, profile.getDistrict());
			cstmt.setString(18, profile.getState());
			cstmt.setString(19, profile.getCountry());
			cstmt.setString(20, profile.getPin());
			cstmt.setString(21, profile.getPhoneNo());
			cstmt.setString(22, profile.getPhoneType());
			i = cstmt.executeUpdate();

			if (i == 1) {
				flag = true;
			} else {
				flag = false;
			}
			con.close();
		} catch (SQLException e) {
			throw new ConnectionException(
					"Some Technical Problem Occurred please try later");
		} catch (Exception e) {
			throw new ConnectionException(
					"Some Technical Problem Occurred please try later");
		} finally {
			closeConnection();
		}
		return flag;
	}

	public boolean updateUser(ProfileTO profileTO)
			throws FileNotFoundException, ConnectionException, SQLException {
		boolean flag = false;
		try {
			System.out.println("hsi");
			con = AbstractDataAccessObject.getConnection();
			int userid = profileTO.getUserid();
			String firstname = profileTO.getFirstName();
			String lastname = profileTO.getLastName();
			System.out.println(profileTO.getPhoto1());
			String birthdate = DateWrapper.parseDate(profileTO.getBirthdate());
			String photo = profileTO.getPhoto1();
			System.out.println("hsi" + photo);
			System.out.println("hao");
			if (photo.equals("")) {
				photo = profileTO.getPhoto();
			}
			String email = profileTO.getEmail();
			String gender = profileTO.getGender();
			String addresstype = profileTO.getAddressType();
			String houseno = profileTO.getHouseNo();
			String street = profileTO.getStreet();
			String city = profileTO.getCity();
			String district = profileTO.getDistrict();
			String state = profileTO.getState();
			String country = profileTO.getCountry();
			String pin = profileTO.getPin();
			String phoneno = profileTO.getPhoneNo();
			con = AbstractDataAccessObject.getConnection();
			System.out.println("photo=" + photo);
			File f = new File(photo);
			FileInputStream fis = new FileInputStream(f);
			System.out.println("fole=" + f.length());
			CallableStatement cstmt = con
					.prepareCall("{call updateprocedure(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setInt(2, userid);
			cstmt.setBinaryStream(1, fis, (int) f.length());
			cstmt.setString(3, firstname);
			System.out.println("firstname :" + firstname);
			cstmt.setString(4, lastname);
			System.out.println("lastname :" + lastname);
			cstmt.setString(5, birthdate);
			System.out.println("birthdate :" + birthdate);
			cstmt.setString(6, email);
			System.out.println("email :" + email);
			cstmt.setString(7, gender);
			System.out.println("gender :" + gender);
			cstmt.setString(8, addresstype);
			System.out.println("addresstype :" + addresstype);
			cstmt.setString(9, houseno);
			System.out.println("houseno :" + houseno);
			cstmt.setString(10, street);
			System.out.println("street :" + street);
			cstmt.setString(11, city);
			System.out.println("city :" + city);
			cstmt.setString(12, district);
			System.out.println("district :" + district);
			cstmt.setString(13, state);
			System.out.println("state :" + state);
			cstmt.setString(14, country);
			System.out.println("country :" + country);
			cstmt.setString(15, pin);
			System.out.println("pin :" + pin);
			cstmt.setString(16, phoneno);
			System.out.println("phoneno :" + phoneno);

			int i = cstmt.executeUpdate();
			if (i == 1) {
				flag = true;
			} else {
				flag = false;

			}
			System.out.println(flag);
		} catch (SQLException e) {
			con.close();
			throw new ConnectionException(
					"Some Technical Problem Occurred please try later");
		} catch (Exception e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical Problem Occurred please try later");
		} finally {
			closeConnection();
		}
		return flag;
	}

	public boolean postSuggesstion(ProfileTO pf) throws ConnectionException {
		boolean flag = false;
		String citizenname = pf.getCitizenname();
		String email = pf.getEmail();
		String suggesstion = pf.getSuggesstions();
		try {
			con = AbstractDataAccessObject.getConnection();
			pstmt = con.prepareStatement(SqlConstants._POST_SUGGESSITION);
			pstmt.setString(1, citizenname);
			pstmt.setString(2, email);
			pstmt.setString(3, suggesstion);

			int i = pstmt.executeUpdate();
			if (i > 0) {
				flag = true;
			} else
				flag = false;

		} catch (SQLException e) {
			throw new ConnectionException(
					"Some Technical Problem Occurred please try later");
		} catch (Exception e) {
			throw new ConnectionException(
					"Some Technical Problem Occurred please try later");
		} finally {
			closeConnection();
		}
		return flag;
	}

	public boolean postResume(ProfileTO pf) throws ConnectionException {
		boolean flag = false;

		String email = pf.getEmail();
		String resumepath = pf.getResumepath();
		try {
			con = AbstractDataAccessObject.getConnection();
			pstmt = con.prepareStatement(SqlConstants._POST_RESUME);
			pstmt.setString(1, email);
			File f = new File(resumepath);
			FileInputStream fis = new FileInputStream(f);
			System.out.println("fole=" + f.length());
			pstmt.setBinaryStream(2, fis, (int) f.length());

			int i = pstmt.executeUpdate();
			if (i > 0) {
				flag = true;
			} else
				flag = false;

		} catch (SQLException e) {
			throw new ConnectionException(
					"Some Technical Problem Occurred please try later");
		} catch (Exception e) {
			throw new ConnectionException(
					"Some Technical Problem Occurred please try later");
		} finally {
			closeConnection();
		}
		return flag;
	}

	public Vector<ProfileTO> viewAgencyChief() throws ConnectionException {
		Vector<ProfileTO> vprofile = new Vector<ProfileTO>();
		ProfileTO profile = null;
		try {
			con = AbstractDataAccessObject.getConnection();
			pstmt = con.prepareStatement(SqlConstants._VIEW_AGENCY_CHIEF);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				profile = new ProfileTO();
				profile.setUserid(rs.getInt(1));
				profile.setLoginid(rs.getString(2));
				vprofile.add(profile);
			}
		} catch (SQLException e) {
			throw new ConnectionException(
					"Some Technical Problem Occurred please try later");
		} catch (Exception e) {
			throw new ConnectionException(
					"Some Technical Problem Occurred please try later");
		} finally {
			closeConnection();
		}
		return vprofile;
	}

	public Vector<ProfileTO> viewAgencyChiefs() throws FileNotFoundException,
			ConnectionException {
		Vector<ProfileTO> vprofile = new Vector<ProfileTO>();
		ProfileTO profile = null;
		try {
			con = AbstractDataAccessObject.getConnection();
			pstmt = con.prepareStatement(SqlConstants._VIEW_AGENCY_CHIEF);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				profile = new ProfileTO();
				profile.setUserid(rs.getInt(1));
				profile.setLoginid(rs.getString(2));
				vprofile.add(profile);
			}
		} catch (SQLException e) {
			throw new ConnectionException("please change Agent Name");
		} catch (Exception e) {
			throw new ConnectionException(
					"Some Technical Problem Occurred please try later");
		} finally {
			closeConnection();
		}
		return vprofile;
	}

	public Vector<ProfileTO> viewListOfUsers(String usertype, String realpath,
			int userid) throws FileNotFoundException, ConnectionException {

		Vector<ProfileTO> vprofile = new Vector<ProfileTO>();
		ProfileTO profile = null;
		try {
			con = AbstractDataAccessObject.getConnection();
			System.out.println(usertype);
			if (!usertype.equalsIgnoreCase("AgentChief")) {
				pstmt = con
						.prepareStatement("select agentid from agent where agencychiefid=?");
				pstmt.setInt(1, userid);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					pstmt1 = con
							.prepareStatement(" select u.userid,u.firstname,u.lastname,u.dor,a.houseno,a.street,a.city,a.district,a.state,a.country,a.pincode,a.phoneno,u.photograph from userdetails u,logindetails l,addresses a where l.useridref=a.useridref and a.useridref=u.userid and u.userid=l.useridref and l.logintype=? and u.status='Active' and u.userid=?");
					pstmt1.setString(1, usertype);
					pstmt1.setInt(2, rs.getInt(1));
					ResultSet rs1 = pstmt1.executeQuery();
					while (rs1.next()) {
						String path = "";
						profile = new ProfileTO();
						profile.setUserid(rs1.getInt(1));
						profile.setFirstName(rs1.getString(2));
						profile.setLastName(rs1.getString(3));
						profile.setDate(DateWrapper.parseDate(rs1.getDate(4)));
						profile.setHouseNo(rs1.getString(5));
						profile.setStreet(rs1.getString(6));
						profile.setCity(rs1.getString(7));
						profile.setDistrict(rs1.getString(8));
						profile.setState(rs1.getString(9));
						profile.setCountry(rs1.getString(10));
						profile.setPin(rs1.getString(11));
						profile.setPhoneNo(rs1.getString(12));
						Blob b = rs1.getBlob(13);
						byte b1[] = b.getBytes(1, (int) b.length());
						path = realpath + "/" + rs1.getInt(1) + ".jpg";
						System.out.println("path  :" + path);
						OutputStream fout = new FileOutputStream(path);
						fout.write(b1);
						profile.setPhoto(path);
						vprofile.add(profile);
					}
				}

			} else {
				pstmt = con.prepareStatement(SqlConstants._VIEW_LIST_USERS);
				pstmt.setString(1, usertype);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					String path = "";
					profile = new ProfileTO();
					profile.setUserid(rs.getInt(1));
					profile.setFirstName(rs.getString(2));
					profile.setLastName(rs.getString(3));
					profile.setDate(DateWrapper.parseDate(rs.getDate(4)));
					profile.setHouseNo(rs.getString(5));
					profile.setStreet(rs.getString(6));
					profile.setCity(rs.getString(7));
					profile.setDistrict(rs.getString(8));
					profile.setState(rs.getString(9));
					profile.setCountry(rs.getString(10));
					profile.setPin(rs.getString(11));
					profile.setPhoneNo(rs.getString(12));
					Blob b = rs.getBlob(13);
					byte b1[] = b.getBytes(1, (int) b.length());
					path = realpath + "/" + rs.getInt(1) + ".jpg";
					System.out.println("path  :" + path);
					OutputStream fout = new FileOutputStream(path);
					fout.write(b1);
					profile.setPhoto(path);
					vprofile.add(profile);
				}
			}

		} catch (SQLException e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical Problem Occurred please try later");
		} catch (Exception e) {
			throw new ConnectionException(
					"Some Technical Problem Occurred please try later");
		} finally {
			closeConnection();
		}
		return vprofile;
	}

	public boolean deleteUser(int userid) throws ConnectionException {

		try {
			con = AbstractDataAccessObject.getConnection();
			pstmt = con.prepareStatement(SqlConstants._DELETE_USER);
			pstmt.setInt(1, userid);
			int i = pstmt.executeUpdate();
			if (i > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical Problem Occurred please try later");
		} catch (Exception e) {
			throw new ConnectionException(
					"Some Technical Problem Occurred please try later");
		} finally {
			closeConnection();
		}
		return flag;
	}

	public boolean deleteResume(int userid) throws ConnectionException {

		try {
			con = AbstractDataAccessObject.getConnection();
			pstmt = con.prepareStatement(SqlConstants._DELETE_RESUME);
			pstmt.setInt(1, userid);
			int i = pstmt.executeUpdate();
			if (i > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			System.out.println(e);
			throw new ConnectionException(
					"Some Technical Problem Occurred please try later");
		} catch (Exception e) {
			throw new ConnectionException(
					"Some Technical Problem Occurred please try later");
		} finally {
			closeConnection();
		}
		return flag;
	}

	public Vector<ProfileTO> viewResumes(String realpath)
			throws ConnectionException {
		Vector<ProfileTO> vcmp = new Vector<ProfileTO>();
		ProfileTO cmp = null;
		String path = "";
		try {
			con = AbstractDataAccessObject.getConnection();
			pstmt = con.prepareStatement(SqlConstants._VIEW_RESUME);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				cmp = new ProfileTO();
				path = realpath;
				Blob b = rs.getBlob(3);
				byte b1[] = b.getBytes(1, (int) b.length());
				path = path + "/" + rs.getInt(1) + ".doc";
				System.out.println("path  :" + path);
				OutputStream fout = new FileOutputStream(path);
				fout.write(b1);
				cmp.setResumepid(rs.getInt(1));
				cmp.setEmail(rs.getString(2));
				cmp.setResumepath(path);
				vcmp.add(cmp);
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
		} finally {
			try {
				con.close();
			} catch (SQLException se) {
			}
		}
		return vcmp;

	}

	/**
	 * The loginCheck method of the SecurityDaoImpl Class. <br>
	 * 
	 * This method is called when to Check login user details.....
	 * 
	 * @param passing
	 *            Profile bean with login id, passord as a one parameter
	 * @throws ConnectionException
	 *             if an error occurred
	 * @throws SQLException
	 *             if an error occurred
	 * @return Vector bean with user details depends upon operation
	 */
	public Vector<ProfileTO> loginCheck(ProfileTO pro)
			throws ConnectionException {
		String logintype = "";
		ProfileTO profileTO = null;
		Vector<ProfileTO> vpro = new Vector<ProfileTO>();
		try {
			con = AbstractDataAccessObject.getConnection();
			pstmt = con.prepareStatement(SqlConstants._CHECK_USER);
			String username = pro.getUserName();
			String password = pro.getPassword();
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				profileTO = new ProfileTO();
				profileTO.setUserid(rs.getInt(1));
				profileTO.setLogintype(rs.getString(3));
				profileTO.setLoginid(rs.getString(2));
				vpro.add(profileTO);
			}
		} catch (SQLException e) {
			throw new ConnectionException(
					"Some Technical prablum Occering please try later");
		} catch (Exception e) {
			throw new ConnectionException(
					"Some Technical prablum Occering please try later");
		} finally {
			closeConnection();
		}
		return vpro;
	}

	public Vector<ProfileTO> viewTips() throws ConnectionException {
		String logintype = "";
		ProfileTO profileTO = null;
		Vector<ProfileTO> vpro = new Vector<ProfileTO>();
		try {
			con = AbstractDataAccessObject.getConnection();
			pstmt = con.prepareStatement(SqlConstants._TIPS_SUGGESSTIONS);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				profileTO = new ProfileTO();
				profileTO.setTipid(rs.getInt(1));
				profileTO.setCitizen(rs.getString(2));
				profileTO.setCitizenemail(rs.getString(3));
				profileTO.setTipssuggestions(rs.getString(4));
				vpro.add(profileTO);
			}
		} catch (SQLException e) {
			throw new ConnectionException(
					"Some Technical prablum Occering please try later");
		} catch (Exception e) {
			throw new ConnectionException(
					"Some Technical prablum Occering please try later");
		} finally {
			closeConnection();
		}
		return vpro;
	}

	/**
	 * The changePass method of the SecurityDaoImpl Class. <br>
	 * 
	 * This method is called when to Password change details.....
	 * 
	 * @param passing
	 *            Profile bean with login id, passord as a one parameter
	 * @throws ConnectionException
	 *             if an error occurred
	 * @throws SQLException
	 *             if an error occurred
	 * @return Vector bean with user details depends upon operation
	 */
	public boolean changePass(ProfileTO pf) throws ConnectionException {
		try {
			con = AbstractDataAccessObject.getConnection();
			String newpass = pf.getNewpassword();
			System.out.println(" security dao new pass " + newpass);
			String user = pf.getUserName();
			System.out.println("security	  dao user :" + user);
			String oldpass = pf.getOldpassword();
			System.out.println("security dao oldpass :" + oldpass);
			pstmt = con.prepareStatement(SqlConstants._CHANGE_PASSWORD);
			pstmt.setString(1, newpass);
			pstmt.setString(2, user);
			pstmt.setString(3, oldpass);
			int c = pstmt.executeUpdate();
			if (c > 0) {
				flag = true;
				con.commit();
			}
		} catch (SQLException e) {
			throw new ConnectionException(
					"Some Technical prablum Occering please try later");
		} catch (Exception e) {
			throw new ConnectionException(
					"Some Technical prablum Occering please try later");
		} finally {
			closeConnection();
		}
		return flag;
	}

	/**
	 * The passwordRecovery method of the SecurityDaoImpl Class. <br>
	 * 
	 * This method is called when to Recover password user details.....
	 * 
	 * @param passing
	 *            Profile bean with login id, passord as a one parameter
	 * @throws ConnectionException
	 *             if an error occurred
	 * @throws SQLException
	 *             if an error occurred
	 * @return true or false depends upon operation
	 */
	public boolean passwordRecovery(ProfileTO pf) throws ConnectionException {
		try {
			con = AbstractDataAccessObject.getConnection();
			String question = pf.getSquest();
			if (question == null)
				question = pf.getOwnquest();
			String ans = pf.getSecrete();
			String loginid = pf.getUserName();
			pstmt = con.prepareStatement(SqlConstants._RECOVER_PASSWORD);
			pstmt.setString(1, loginid);
			pstmt.setString(2, question);
			pstmt.setString(3, ans);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			throw new ConnectionException(
					"Some Technical prablum Occering please try later");
		} catch (Exception e) {
			throw new ConnectionException(
					"Some Technical prablum Occering please try later");
		} finally {
			closeConnection();
		}
		return flag;
	}

	/**
	 * The forgetPass method of the SecurityDaoImpl Class. <br>
	 * 
	 * This method is called when to forget password user details.....
	 * 
	 * @param passing
	 *            Profile bean with login id, passord as a one parameter
	 * @throws ConnectionException
	 *             if an error occurred
	 * @throws SQLException
	 *             if an error occurred
	 * @return true or false depends upon operation
	 */
	public boolean forgetPass(ProfileTO pf) throws ConnectionException {
		boolean flag = true;

		try {
			con = AbstractDataAccessObject.getConnection();
			String pwd = pf.getPassword();
			System.out.println("in Dao pwd is..." + pwd);
			String loginid = pf.getUserName();
			System.out.println("in Dao loginid is..." + loginid);
			pstmt = con.prepareStatement(SqlConstants._NEW_PASSWORD);
			pstmt.setString(1, pwd);
			pstmt.setString(2, loginid);

			int update = pstmt.executeUpdate();
			if (update > 0) {
				con.commit();
			} else {
				flag = false;
				con.rollback();
			}
		} catch (SQLException e) {
			throw new ConnectionException(
					"Some Technical prablum Occering please try later");
		} catch (Exception e) {
			throw new ConnectionException(
					"Some Technical prablum Occering please try later");
		} finally {
			closeConnection();
		}
		return flag;
	}
}
