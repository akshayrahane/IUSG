package com.rmadss.delegate;

import java.io.FileNotFoundException;
import java.util.Vector;

import com.rmadss.bean.ProfileTO;
import com.rmadss.bean.SearchForm;
import com.rmadss.exception.ConnectionException;
import com.rmadss.serviceimpl.SearchServiceImpl;
import com.rmadss.serviceimpl.UserServiceImpl;

public class SearchDelegate {
	Vector<ProfileTO> vpro = null;
	SearchServiceImpl searchServiceImpl = new SearchServiceImpl();

	public boolean insertSearchKeyword(String keyword)
			throws FileNotFoundException, ConnectionException {
		return searchServiceImpl.insertSearchKeyword(keyword);
	}

	public Vector<SearchForm> viewKeyworsLists() throws ConnectionException {
		// TODO Auto-generated method stub
		return searchServiceImpl.viewKeyworsLists();
	}

	public Vector<SearchForm> viewKeywordIndex() throws ConnectionException {
		// TODO Auto-generated method stub
		return searchServiceImpl.viewKeywordIndex();
	}

	public Vector<SearchForm> viewInterfaceDataKeyworsLists(int keyref)
			throws ConnectionException {
		// TODO Auto-generated method stub
		return searchServiceImpl.viewInterfaceDataKeyworsLists(keyref);
	}

	public boolean addWordIndexDocuments(SearchForm searchForm)
			throws ConnectionException {
		// TODO Auto-generated method stub
		return searchServiceImpl.addWordIndexDocuments(searchForm);
	}

	public boolean addMainData(SearchForm searchForm)
			throws ConnectionException {
		// TODO Auto-generated method stub
		return searchServiceImpl.addMainData(searchForm);
	}

	public boolean addMetaDataInterfaceWordIndex(SearchForm searchForm)
			throws ConnectionException {
		// TODO Auto-generated method stub
		return searchServiceImpl.addMetaDataInterfaceWordIndex(searchForm);
	}

	public String searchData(String parameter) {
		// TODO Auto-generated method stub
		return searchServiceImpl.searchData(parameter);
	}

	public Vector<SearchForm> searchRecords(String parameter) {
		// TODO Auto-generated method stub
		return searchServiceImpl.searchRecords(parameter);
	}

	public Vector<SearchForm> searchMetaDataRecords(int key) {
		// TODO Auto-generated method stub
		return searchServiceImpl.searchMetaDataRecords(key);
	}

	public Vector<SearchForm> rankingSearchRecords(String parameter) {
		// TODO Auto-generated method stub
		return searchServiceImpl.rankingSearchRecords(parameter);
	}
}
