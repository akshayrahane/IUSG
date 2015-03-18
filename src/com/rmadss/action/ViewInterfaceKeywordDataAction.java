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

public class ViewInterfaceKeywordDataAction extends HttpServlet {
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
		RequestDispatcher requestDispatcher = null;
		Vector<SearchForm> vSearchFormsInterface;
		String path = "";
		String keywordindex = request.getParameter("key");
		String[] extension = keywordindex.split(",");
		try {
			vSearchFormsInterface = new SearchDelegate()
					.viewInterfaceDataKeyworsLists(Integer
							.parseInt(extension[0]));
			if (!vSearchFormsInterface.isEmpty()) {
				request.setAttribute("key", Integer.parseInt(extension[0]));
				request.setAttribute("wordindex", extension[1]);
				request.setAttribute("vSearchFormsInterface",
						vSearchFormsInterface);
				path = UtilConstants._ADD_FINAL_DATA;
			} else {
				request.setAttribute("status",
						UtilConstants._VIEW_WORDSINDEXES_FAIL);
				path = UtilConstants._ADD_FINAL_DATA;
			}
		} catch (Exception e) {
			request.setAttribute("status", e.getMessage());
			path = UtilConstants._ADD_FINAL_DATA;
		}
		requestDispatcher = request.getRequestDispatcher(path);
		requestDispatcher.forward(request, response);
	}
}
