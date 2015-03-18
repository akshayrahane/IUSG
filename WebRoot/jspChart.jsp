
<%@page import="java.util.Vector"%>
<%@page import="com.rmadss.bean.SearchForm"%><%@page
	import="com.rmadss.delegate.SearchDelegate"%><jsp:useBean id="zz"
	scope="session" class="javaside.Rbl.jspChart" />

<%
	int iW, iH; // Taille de l image a generer
	int iColor; // Couleur du fond
	int iPres; // Presentation
	boolean b1; // Legend
	boolean b2; // Bullet
	String sFormat; // Format (gif/png)
	Vector<SearchForm> vSearchForms1 = null, vSearchForms2 = null;
	double elapsedtime1 = 0, elapsedtime2 = 0;
	String t = null;
	t = request.getParameter("width");
	if (t == null)
		iW = 400;
	else
		iW = java.lang.Integer.parseInt(t);

	t = request.getParameter("height");
	if (t == null)
		iH = 350;
	else
		iH = java.lang.Integer.parseInt(t);

	t = request.getParameter("color");
	if (t == null)
		iColor = -1;
	else
		iColor = java.lang.Integer.parseInt(t, 16);

	t = request.getParameter("pres");
	if (t == null)
		iPres = 1;
	else
		iPres = java.lang.Integer.parseInt(t);

	t = request.getParameter("format");
	if (t == null)
		sFormat = "gif";
	else
		sFormat = t;

	t = request.getParameter("b1");
	if (t == null)
		b1 = false;
	else
		b1 = t.equalsIgnoreCase("on");

	t = request.getParameter("b2");
	if (t == null)
		b2 = false;
	else
		b2 = t.equalsIgnoreCase("on");

	// Initialisation et definition de la taille de l image
	zz.init(iW, iH);

	zz.setFontA("Dialog", 0, 8);
	zz.setFontTitre("Dialog", 100, 15);
	zz.setFontLegend("Dialog", 100, 13);

	zz.setCol(2);
	zz.addCol(0, 55255, "RankSearch");
	zz.addCol(1, 255, "Search");

	zz
			.setTitle("--------------------                                  Time Cost vs. #Query");
	zz.setLegend("Adaptation Query number ", "Time( Million Seconds )");

	zz.setRotate(true);
	zz.setBkColor(iColor);

	try {
		long startTimeMillis1 = System.currentTimeMillis();
		vSearchForms1 = new SearchDelegate()
				.searchRecords((String) session
						.getAttribute("searchkeyword"));
		long endTimeMillis1 = System.currentTimeMillis();
		elapsedtime1 = (endTimeMillis1 - startTimeMillis1) / 1000F;
		System.out.println("elapsedtime1   " + elapsedtime1);
		long startTimeMillis2 = System.currentTimeMillis();
		vSearchForms2 = new SearchDelegate()
				.rankingSearchRecords((String) session
						.getAttribute("searchkeyword"));
		long endTimeMillis2 = System.currentTimeMillis();
		elapsedtime2 = (endTimeMillis2 - startTimeMillis2) / 1000F;
		System.out.println("elapsedtime2     " + elapsedtime2);

	} catch (Exception e) {
		System.out.println(e);
	}

	int sizevalue = 0;
	double graphtime1 = 0, grapphtime2 = 0;
	double time1 = elapsedtime1 / ((int) vSearchForms1.size() / 10);
	double time2 = elapsedtime2 / ((int) vSearchForms1.size() / 10);
	for (int size = 0; size <=(int) vSearchForms1.size() / 10; size++) {
		zz.addRow(sizevalue + ";" + graphtime1 + ";" + grapphtime2
				+ ";");
		if (vSearchForms1 != null)
			graphtime1 = graphtime1 + time1;
		if (vSearchForms2 != null)
			grapphtime2 = grapphtime2 + time2;
		sizevalue = sizevalue + 10;
	}
	zz.setPress(iPres);
	zz.setOrigine(40, 50);
	zz.setXPress(b1, b2, false);
	//      zz.setMinMax (0, 35) ;

	zz.build(true);

	response.reset();
	response.setContentType("image/" + sFormat);
	response
			.addHeader("Content-Disposition", "filename=acx." + sFormat);

	if (sFormat.equalsIgnoreCase("png"))
		response.getOutputStream().write(zz.getImage(1));
	else
		response.getOutputStream().write(zz.getImage(0));

	response.flushBuffer();

	zz.clear();
%>