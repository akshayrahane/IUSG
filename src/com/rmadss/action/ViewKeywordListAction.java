package com.rmadss.action;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rmadss.bean.SearchForm;
import com.rmadss.delegate.SearchDelegate;
import com.rmadss.util.UtilConstants;

public class ViewKeywordListAction extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2941564269120432640L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		boolean flag = false;
		String path = "";
		Vector<SearchForm> vSearchForms = new Vector<SearchForm>();
		try {
			vSearchForms = new SearchDelegate().viewKeyworsLists();
			if (!vSearchForms.isEmpty()) {
				request.setAttribute("status",
						UtilConstants._VIEW_WORDSINDEXES_SUCCESS);
				request.setAttribute("vSearchForms", vSearchForms);
				path = UtilConstants._ADD_MAIN_DATA_INDEX;
			} else {
				request.setAttribute("status", UtilConstants._VIEW_WORDSINDEXES_FAIL);
				path = UtilConstants._ADD_MAIN_DATA_INDEX;
			}
		} catch (Exception e) {
			request.setAttribute("status", e.getMessage());
			path = UtilConstants._ADD_MAIN_DATA_INDEX;
		}
		rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
