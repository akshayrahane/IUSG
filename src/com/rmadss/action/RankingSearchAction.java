package com.rmadss.action;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rmadss.bean.SearchForm;
import com.rmadss.delegate.SearchDelegate;
import com.rmadss.util.UtilConstants;

public class RankingSearchAction extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		String path = "";
		HttpSession session = request.getSession();
		Vector<SearchForm> vSearchForms = null;
		long startTimeMillis = System.currentTimeMillis();
		try {
			vSearchForms = new SearchDelegate().rankingSearchRecords(request
					.getParameter("search"));
			if (!vSearchForms.isEmpty()) {
				session.setAttribute("searchkeyword", null);
				session.setAttribute("elapsedtime", null);
				session.setAttribute("vSearchForms", vSearchForms);
				session.setAttribute("searchkeyword", request
						.getParameter("search"));
				request.setAttribute("rankingsearch", "rankingsearch");
				long endTimeMillis = System.currentTimeMillis();
				session.setAttribute("elapsedtime", Float
						.toString((endTimeMillis - startTimeMillis) / 1000F));
				path = UtilConstants._VIEW_SEARCH_DATA;
			} else {
				request.setAttribute("status",
						UtilConstants._VIEW_WORDSINDEXES_FAIL);
				path = UtilConstants._VIEW_SEARCH_DATA;
			}
		} catch (Exception e) {
			path = UtilConstants._VIEW_SEARCH_DATA;
		}
		rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
