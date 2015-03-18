package com.rmadss.serviceimpl;

import java.io.FileNotFoundException;
import java.util.Vector;
import com.rmadss.bean.SearchForm;
import com.rmadss.daoimpl.SearchDAOImpl;
import com.rmadss.exception.ConnectionException;

public class SearchServiceImpl {
	SearchDAOImpl searchDAOImpl = new SearchDAOImpl();

	public boolean insertSearchKeyword(String keyword)
			throws FileNotFoundException, ConnectionException {
		return searchDAOImpl.insertSearchKeyword(keyword);
	}

	public Vector<SearchForm> viewKeyworsLists() throws ConnectionException {
		// TODO Auto-generated method stub
		return searchDAOImpl.viewKeyworsLists();
	}

	public Vector<SearchForm> viewKeywordIndex() throws ConnectionException {
		// TODO Auto-generated method stub
		return searchDAOImpl.viewKeywordIndex();
	}

	public Vector<SearchForm> viewInterfaceDataKeyworsLists(int keyref)
			throws ConnectionException {
		// TODO Auto-generated method stub
		return searchDAOImpl.viewInterfaceDataKeyworsLists(keyref);
	}

	public boolean addWordIndexDocuments(SearchForm searchForm)
			throws ConnectionException {
		// TODO Auto-generated method stub
		return searchDAOImpl.addWordIndexDocuments(searchForm);
	}

	public boolean addMetaDataInterfaceWordIndex(SearchForm searchForm)
			throws ConnectionException {
		// TODO Auto-generated method stub
		return searchDAOImpl.addMetaDataInterfaceWordIndex(searchForm);
	}

	public String searchData(String parameter) {
		// TODO Auto-generated method stub
		return searchDAOImpl.searchData(parameter);
	}

	public Vector<SearchForm> searchRecords(String parameter) {
		// TODO Auto-generated method stub
		return searchDAOImpl.searchRecords(parameter);
	}

	public Vector<SearchForm> searchMetaDataRecords(int key) {
		// TODO Auto-generated method stub
		return searchDAOImpl.searchMetaDataRecords(key);
	}

	public boolean addMainData(SearchForm searchForm)
			throws ConnectionException {
		return searchDAOImpl.addMainData(searchForm);
	}

	public Vector<SearchForm> rankingSearchRecords(String parameter) {
		// TODO Auto-generated method stub
		return searchDAOImpl.rankingSearchRecords(parameter);
	}
}
