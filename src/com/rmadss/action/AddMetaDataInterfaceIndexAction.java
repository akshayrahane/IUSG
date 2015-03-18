package com.rmadss.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rmadss.bean.SearchForm;
import com.rmadss.delegate.SearchDelegate;
import com.rmadss.util.UtilConstants;
import com.sun.org.apache.commons.beanutils.BeanUtils;

public class AddMetaDataInterfaceIndexAction extends HttpServlet {
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
		boolean flag = false;
		String path = "";
		SearchForm searchForm = new SearchForm();
		Map map = request.getParameterMap();
		try {
			BeanUtils.populate(searchForm, map);

			flag = new SearchDelegate()
					.addMetaDataInterfaceWordIndex(searchForm);
			if (flag) {
				request.setAttribute("status",
						UtilConstants._ADD_INDEXWORD_METADATA_SUCCESS);
				path = UtilConstants._ADD_METADATA_INDEXWORD;
			} else {
				request.setAttribute("status",
						UtilConstants._ADD_INDEXWORD_METADATA_FAILURE);
				path = UtilConstants._ADD_METADATA_INDEXWORD;
			}
		} catch (Exception e) {
			request.setAttribute("status", e.getMessage());
			path = UtilConstants._ADD_METADATA_INDEXWORD;
		}
		rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
