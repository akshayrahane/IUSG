package com.rmadss.daoimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Vector;
import java.sql.*;
import java.util.*;
import com.rmadss.bean.SearchForm;
import com.rmadss.dao.AbstractDataAccessObject;
import com.rmadss.exception.ConnectionException;
import com.rmadss.util.DateUtil;


public class SearchDAOImpl extends AbstractDataAccessObject {
	Connection connection;
	PreparedStatement preparedStatement, preparedStatement1,
			preparedStatement2, preparedStatement3;
	ResultSet resultSet, resultSet1, resultSet2, resultSet3;
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
			if (preparedStatement != null)
				preparedStatement.close();
			if (preparedStatement1 != null)
				preparedStatement1.close();
			if (preparedStatement2 != null)
				preparedStatement2.close();
			if (connection != null)
				connection.close();
			if (resultSet != null)
				resultSet.close();
			if (resultSet1 != null)
				resultSet1.close();
			if (resultSet2 != null)
				resultSet2.close();

		} catch (SQLException ex) {
			throw new ConnectionException(
					"Server Busy please Try after Sometine");
		}
	}

	public Hashtable viewResults(SearchForm searchForm) {
		return null;
		/*
		 * Hashtable hashtable = new Hashtable(); RankingModel traingModel =
		 * null; String searchword = searchForm.getSearchword(); String domain =
		 * searchForm.getCategory(); Connection con = null; try { if
		 * ("existing".equalsIgnoreCase(searchForm.getExisting())) { con =
		 * getConnection(); PreparedStatement pst = con
		 * .prepareStatement("select * from documents where  domain='" + domain
		 * + "'  and (title like '%" + searchword + "%' or desc like '%" +
		 * searchword + "%' or url like '%" + searchword + "%')"); ResultSet rs
		 * = pst.executeQuery(); int i = 0; while (rs.next()) { traingModel =
		 * new RankingModel(); traingModel.setSno(rs.getInt(1));
		 * traingModel.setTitle(rs.getString(2));
		 * traingModel.setUrl(rs.getString(3));
		 * traingModel.setDesc(rs.getString(4));
		 * traingModel.setDomain(rs.getString(5)); hashtable.put(new Integer(i),
		 * traingModel); i++; } } else { con = getConnection();
		 * PreparedStatement pst = con
		 * .prepareStatement("select * from documents where  domain='" + domain
		 * + "'  or (title like '%" + searchword + "%' or desc like '%" +
		 * searchword + "%' or url like '%" + searchword + "%')"); ResultSet rs
		 * = pst.executeQuery(); int i = 0; while (rs.next()) { traingModel =
		 * new RankingModel(); traingModel.setSno(rs.getInt(1));
		 * traingModel.setTitle(rs.getString(2));
		 * traingModel.setUrl(rs.getString(3));
		 * traingModel.setDesc(rs.getString(4));
		 * traingModel.setDomain(rs.getString(5)); hashtable.put(new Integer(i),
		 * traingModel); i++; } } } catch (Exception e) { e.printStackTrace();
		 * // TODO: handle exception } return hashtable;
		 */
	}

	;

	public boolean insertSearchKeyword(String keyword)
			throws FileNotFoundException, ConnectionException {
		boolean flag = false;
		try {
			connection = getConnection();
			preparedStatement = connection
					.prepareStatement("insert into keywords values((select nvl(max(key),1000)+1 from keywords),?)");
			preparedStatement.setString(1, keyword.toLowerCase());
			int result = preparedStatement.executeUpdate();
			if (result > 0) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			closeConnection();
		}
		return flag;
	}

	public Vector<SearchForm> viewKeyworsLists() throws ConnectionException {
		Vector<SearchForm> vSearchForms = new Vector<SearchForm>();
		SearchForm searchForm = null;
		try {
			connection = getConnection();
			preparedStatement = connection
					.prepareStatement("select * from METADATAINTERFACE");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				searchForm = new SearchForm();
				searchForm.setKey(resultSet.getInt(1));
				searchForm.setWordindex(resultSet.getString(3));
				vSearchForms.add(searchForm);
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			closeConnection();
		}
		return vSearchForms;
	}

	public Vector<SearchForm> viewInterfaceDataKeyworsLists(int keyref)
			throws ConnectionException {
		Vector<SearchForm> vSearchForms = new Vector<SearchForm>();
		SearchForm searchForm = null;
		try {
			connection = getConnection();
			preparedStatement = connection
					.prepareStatement("select * from METADATAINTERFACE where keyref=?");
			preparedStatement.setInt(1, keyref);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				searchForm = new SearchForm();
				searchForm.setKey(resultSet.getInt(1));
				searchForm.setWordindex(resultSet.getString(3));
				vSearchForms.add(searchForm);
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			closeConnection();
		}
		return vSearchForms;
	}

	public Vector<SearchForm> getSearchResults(int keyref) throws ConnectionException {

		Vector<SearchForm> vSearchForms = new Vector<SearchForm>();
		SearchForm searchForm = null;
		try {
			connection = getConnection();
			preparedStatement = connection
					.prepareStatement("select TITLE, OTHERMETADATA from MAINDATAINDEX where DATAINDEXID=?");
			preparedStatement.setInt(1, keyref);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				searchForm = new SearchForm();
				searchForm.setTitle(resultSet.getString(1));
				searchForm.setTitledata(resultSet.getString(2));
				vSearchForms.add(searchForm);

			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			closeConnection();
		}
		return vSearchForms;
	}
   
	
	public Vector<SearchForm> getSearchURLs() throws ConnectionException {

		Vector<SearchForm> vSearchForms = new Vector<SearchForm>();
		SearchForm searchForm = null;
		try {
			connection = getConnection();
			preparedStatement = connection
					.prepareStatement("select * from url");
			
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				searchForm = new SearchForm();
				searchForm.setDataindexid(resultSet.getInt(1));
				searchForm.setUrls(resultSet.getString(2));
				searchForm.setUser(resultSet.getString(3));
				searchForm.setRecordscount(resultSet.getInt(4));
				searchForm.setSno(resultSet.getInt(5));
				searchForm.setVcount(resultSet.getInt(6));
				searchForm.setTitle(resultSet.getString(7));
				searchForm.setDatadescription(resultSet.getString(8));
				vSearchForms.add(searchForm);

			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			closeConnection();
		}
		return vSearchForms;
	}
	
	public Vector<SearchForm> viewKeywordIndex() throws ConnectionException {
		Vector<SearchForm> vSearchForms = new Vector<SearchForm>();
		SearchForm searchForm = null;
		try {
			connection = getConnection();
			preparedStatement = connection
					.prepareStatement("select * from KEYWORDS");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				searchForm = new SearchForm();
				searchForm.setKey(resultSet.getInt(1));
				searchForm.setWordindex(resultSet.getString(2));
				vSearchForms.add(searchForm);
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			closeConnection();
		}
		return vSearchForms;
	}

	public boolean addWordIndexDocuments(SearchForm searchForm)
			throws ConnectionException {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			connection = getConnection();
			preparedStatement = connection
					.prepareStatement("insert into MAINDATAINDEX values((select nvl(max(DATAINDEXID),1000)+1 from MAINDATAINDEX),?,?,?,sysdate,?)");
			preparedStatement.setInt(1, searchForm.getKey());
			preparedStatement.setString(2, searchForm.getDatatitle());
			preparedStatement.setString(3, searchForm.getIndexdescription());
			preparedStatement.setString(4, searchForm.getUrls());
			int result = preparedStatement.executeUpdate();
			if (result > 0) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			closeConnection();
		}
		return flag;
	}

	public boolean addMetaDataInterfaceWordIndex(SearchForm searchForm)
			throws ConnectionException {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			connection = getConnection();
			preparedStatement = connection
					.prepareStatement("insert into METADATAINTERFACE values((select nvl(max(INTERFACEID),1000)+1 from METADATAINTERFACE),?,?,?,?)");
			preparedStatement.setInt(1, searchForm.getKey());
			preparedStatement.setString(2, searchForm.getDatatitle());
			preparedStatement.setString(3, searchForm.getDatatitle());
			preparedStatement.setString(4, searchForm.getIndexdescription());
			int result = preparedStatement.executeUpdate();
			if (result > 0) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			closeConnection();
		}
		return flag;
	}

	
	
	public boolean addurls(SearchForm searchForm)
	throws ConnectionException {
// TODO Auto-generated method stub
    boolean flag = false;
    try {
	connection = getConnection();
	preparedStatement = connection
			.prepareStatement("insert into url values(?,?,?,?,?,?,?,?)");
	preparedStatement.setInt(1, searchForm.getDataindexid());
	preparedStatement.setString(2, searchForm.getUrls());
	preparedStatement.setString(3, searchForm.getUser());
	preparedStatement.setInt(4, searchForm.getRecordscount());
	preparedStatement.setInt(5, searchForm.getSno());
	preparedStatement.setInt(6, searchForm.getVcount());
	preparedStatement.setString(7, searchForm.getTitle());
	preparedStatement.setString(8, searchForm.getDatadescription());
	int result = preparedStatement.executeUpdate();
	if (result > 0) {
		flag = true;
	} else {
		flag = false;
	}
   } catch (Exception e) {
	System.out.println(e);
	e.printStackTrace();
	// TODO: handle exception
  } finally {
	closeConnection();
  } 
  return flag;
   }

	
	public boolean updateurls(SearchForm searchForm)
	throws ConnectionException {
// TODO Auto-generated method stub
    boolean flag = false;
    try {
	connection = getConnection();
	preparedStatement = connection
			.prepareStatement("update  url set count=?  where DATAINDEXID=? and username=?");
	preparedStatement.setInt(1, searchForm.getRecordscount());
	preparedStatement.setInt(2, searchForm.getDataindexid());
	preparedStatement.setString(3, searchForm.getUser());
	preparedStatement1= connection
	.prepareStatement("update  url set vcount=?  where DATAINDEXID=? and username=?");

	preparedStatement1.setInt(1, 1);
	preparedStatement1.setInt(2, searchForm.getDataindexid());
	preparedStatement1.setString(3, searchForm.getUser());
	int result = preparedStatement.executeUpdate();
	int result1 = preparedStatement1.executeUpdate();
	if (result > 0) {
		flag = true;
	} else {
		flag = false;
	}
   } catch (Exception e) {
	System.out.println(e);
	e.printStackTrace();
	// TODO: handle exception
  } finally {
	closeConnection();
  } 
  return flag;
   }
	
	
	public String searchData(String parameter) {
		// TODO Auto-generated method stub
		String finalSearch = "";
		try {

			String keywordindex = parameter;
			String[] word = keywordindex.split(" ");

			for (int i = 0; word.length > 0; i++) {
				System.out.println(word[i]);
				if (!word[i].equals("")) {
					connection = getConnection();
					preparedStatement = connection
							.prepareStatement("select key from keywords where wordindex like '%"
									+ word[i].toLowerCase() + "%'");
					resultSet = preparedStatement.executeQuery();
					while (resultSet.next()) {
						int key = resultSet.getInt(1);
						System.out.println(key);
						preparedStatement1 = connection
								.prepareStatement("SELECT INTERFACEIREFD, COUNT(*) TotalCount,(select TITLEDATA from METADATAINTERFACE where INTERFACEID=INTERFACEIREFD  ) FROM MAINDATAINDEX where MAINDATAINDEX.METADATKEYREF=? GROUP BY INTERFACEIREFD HAVING COUNT(*) > 1 ORDER BY COUNT(*) DESC");
						preparedStatement1.setInt(1, key);
						resultSet1 = preparedStatement1.executeQuery();
						while (resultSet1.next()) {
							String data = resultSet1.getString(3);
							finalSearch += data + "\n";
						}
					}
				}
			}
		} catch (SQLException s) {
			System.out.println("SQL code does not execute." + s);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection();
			} catch (ConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return finalSearch;
	}

	
	
	 public boolean DeleteValue(){
			int i=0;
			connection = getConnection();
				boolean flag=false;
				try{
				

					preparedStatement=connection.prepareStatement("truncate table url");
				
				
			 i=preparedStatement.executeUpdate();
				 
				 if(i>0){
					 
					 flag=true;
					 connection.commit();
				 
				 }
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			
				return flag;	
			}
	  
	  
	 
	 public Vector<SearchForm> getDatas(String Title)
  	{
		
		 Vector<SearchForm> vSearchForms = new Vector<SearchForm>();
			SearchForm searchForm = null;
			connection = getConnection();
			
			
			StringTokenizer st = new StringTokenizer(Title, " , ", false);
			while(st.hasMoreTokens())
			  {
  	            String data=st.nextToken();
  	    try {
  	    	
  	    	preparedStatement=connection.prepareStatement("select * from MAINDATAINDEX  where OTHERMETADATA LIKE '%"+data+"%' order by DATAINDEXID asc");
  	    	
  	    	resultSet=preparedStatement.executeQuery();

  	       while(resultSet.next())
  	  	{
  	    	  SearchForm f=new SearchForm();
  	  		System.out.println(f);
  	  	
  	  	searchForm = new SearchForm();
		searchForm.setDataindexid(resultSet.getInt(1));
		searchForm.setInterfacerefid(resultSet.getInt(2));
		searchForm.setTitle(resultSet.getString(3));
		searchForm.setTitledata(resultSet.getString(4));
		searchForm.setDatadate(DateUtil.parseDate(resultSet
				.getDate(5)));
		searchForm.setUrls(resultSet.getString(6));
		vSearchForms.add(searchForm);
  	  		System.out.println("no of result set values"+vSearchForms.size());
  	  	        }
  	  		
  	    
  	  	}
  	    	catch(Exception e)
  	  	{
  	  		e.printStackTrace();
  	  		System.out.println(e.getMessage());
  	  	}
			  }
  	  	return vSearchForms;
  	  	
  	  }
    	
     
	 
	 
	 
	 
	public Vector<SearchForm> searchRecords(String parameter) {
		// TODO Auto-generated method stub
		Vector<SearchForm> vSearchForms = new Vector<SearchForm>();
		SearchForm searchForm = null;
		try {
			String keywordindex = parameter;
			String[] word = keywordindex.split(" ");
			connection = getConnection();
			preparedStatement3 = connection
					.prepareStatement("select * from METADATAINTERFACE where TITLEDATA like '"
							+ parameter + "'");

			resultSet3 = preparedStatement3.executeQuery();
			if (resultSet3.next()) {
				System.out.println("haiiiiiiiii" + resultSet3.getInt(1));
				preparedStatement = connection
						.prepareStatement("select * from MAINDATAINDEX where INTERFACEIREFD=?");
				preparedStatement.setInt(1, resultSet3.getInt(1));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					searchForm = new SearchForm();
					searchForm.setDataindexid(resultSet.getInt(1));
					searchForm.setInterfacerefid(resultSet.getInt(2));
					searchForm.setTitle(resultSet.getString(3));
					searchForm.setTitledata(resultSet.getString(4));
					searchForm.setDatadate(DateUtil.parseDate(resultSet
							.getDate(5)));
					searchForm.setUrls(resultSet.getString(6));
					vSearchForms.add(searchForm);
				}
			} else {
				String j = "";
				for (int i = 0; word.length > 0; i++) {
					try {

						System.out.println(word[i]);
					} catch (Exception e) {

					}
					if (!word[i].equals("")) {
						preparedStatement = connection
								.prepareStatement("select key from keywords where wordindex like '%"
										+ word[i].toLowerCase() + "%'");
						resultSet = preparedStatement.executeQuery();
						if (resultSet.next()) {
							int key = resultSet.getInt(1);
							preparedStatement1 = connection
									.prepareStatement("select * from MAINDATAINDEX where METADATKEYREF=?");
							preparedStatement1.setInt(1, key);
							resultSet1 = preparedStatement1.executeQuery();
							while (resultSet1.next()) {
								searchForm = new SearchForm();
								searchForm.setDataindexid(resultSet1.getInt(1));
								searchForm.setInterfacerefid(resultSet1
										.getInt(2));
								searchForm.setTitle(resultSet1.getString(3));
								searchForm
										.setTitledata(resultSet1.getString(4));
								searchForm.setDatadate(DateUtil
										.parseDate(resultSet1.getDate(5)));
								searchForm.setUrls(resultSet1.getString(6));
								vSearchForms.add(searchForm);
							}
						}
					}
				}
			}
		} catch (SQLException s) {
			System.out.println("SQL code does not execute." + s);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection();
			} catch (ConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return vSearchForms;
	}

	public Vector<SearchForm> searchMetaDataRecords(int key) {
		// TODO Auto-generated method stub
		Vector<SearchForm> vSearchForms = new Vector<SearchForm>();
		SearchForm searchForm = null;
		try {
			connection = getConnection();
			preparedStatement = connection
					.prepareStatement("select * from MAINDATAINDEX where INTERFACEIREFD=?");
			preparedStatement.setInt(1, key);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				searchForm = new SearchForm();
				searchForm.setDataindexid(resultSet.getInt(1));
				searchForm.setInterfacerefid(resultSet.getInt(2));
				searchForm.setTitle(resultSet.getString(3));
				searchForm.setTitledata(resultSet.getString(4));
				searchForm.setDatadate(resultSet.getString(5));
				searchForm.setUrls(resultSet.getString(6));
				vSearchForms.add(searchForm);
			}
		} catch (SQLException s) {
			System.out.println("SQL code does not execute." + s);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection();
			} catch (ConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return vSearchForms;
	}

	public boolean addMainData(SearchForm searchForm)
			throws ConnectionException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			connection = getConnection();
			preparedStatement = connection
					.prepareStatement("insert into MAINDATAINDEX values((select nvl(max(DATAINDEXID),1000)+1 from MAINDATAINDEX),?,?,?,sysdate,?,?)");
			preparedStatement.setInt(1, searchForm.getKeyref());
			preparedStatement.setString(2, searchForm.getTitlehead());
			preparedStatement.setString(3, searchForm.getDatadescription());
			preparedStatement.setString(4, searchForm.getUrls());
			preparedStatement.setInt(5, searchForm.getKey());
			int result = preparedStatement.executeUpdate();
			if (result > 0) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			closeConnection();
		}
		return flag;
	}

	public Vector<SearchForm> rankingSearchRecords(String parameter) {
		// TODO Auto-generated method stub
		Vector<SearchForm> vSearchForms = new Vector<SearchForm>();
		SearchForm searchForm = null;
		try {
			connection = getConnection();
			preparedStatement = connection
					.prepareStatement("select key from keywords where wordindex like '%"
							+ parameter.toLowerCase() + "%'");
			// preparedStatement.setString(1, parameter.toLowerCase());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int key = resultSet.getInt(1);

				preparedStatement1 = connection
						.prepareStatement("SELECT INTERFACEIREFD, COUNT(*) TotalCount,(select TITLEDATA from METADATAINTERFACE where INTERFACEID=INTERFACEIREFD  ) FROM MAINDATAINDEX where MAINDATAINDEX.METADATKEYREF=? GROUP BY INTERFACEIREFD HAVING COUNT(*) > 1 ORDER BY COUNT(*) DESC");
				preparedStatement1.setInt(1, key);
				resultSet1 = preparedStatement1.executeQuery();
				while (resultSet1.next()) {
					preparedStatement2 = connection
							.prepareStatement("select * from METADATAINTERFACE where INTERFACEID=?");
					preparedStatement2.setInt(1, resultSet1.getInt(1));
					resultSet2 = preparedStatement2.executeQuery();
					if (resultSet2.next()) {
						searchForm = new SearchForm();
						searchForm.setInterfaceid(resultSet2.getInt(2));
						searchForm.setKeyref(resultSet2.getInt(1));
						searchForm.setTitle(resultSet2.getString(3));
						searchForm.setTitledata(resultSet2.getString(4));
						searchForm.setDatadescription(resultSet2.getString(5));
						searchForm.setRecordscount(resultSet1.getInt(2));
						vSearchForms.add(searchForm);
					}
				}
			}
		} catch (SQLException s) {
			System.out.println("SQL code does not execute." + s);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection();
			} catch (ConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return vSearchForms;
	}
}
