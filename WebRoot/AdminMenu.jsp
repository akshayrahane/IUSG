<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml2/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Admin Menu</title>
		<meta name="Author" content="Stu Nicholls" />

		<link rel="stylesheet" type="text/css" href="./css/pro_drop_1.css" />

		<script src="css/stuHover.js" type="text/javascript">
</script>

	</head>

	<body>

		


		<ul id="nav">
			

				

				<li class="top">
					<a href="#nogo22" id="services" class="top_link"><span
						class="down">DataKeyword</span> </a>
					<ul class="sub">
						<li>
							<a href="./AddWordIndex.jsp">AddKeywordIndex</a>
						</li>



					</ul>
</li>
					<li class="top">
						<a href="#nogo22" id="services" class="top_link"><span
							class="down">MetaDataInterface</span> </a>
						<ul class="sub">

							<li>
								<a href="./ViewMetaDataKeywordListAction">AddInterfaceMetaData</a>
							</li>
							<li>
								<a href="./MainDataViewKeyWordsAction">AddMAINDATA</a>
							</li>
						</ul>
						</li>
						
						
						<li class="top">
						<a href="#nogo22" id="services" class="top_link"><span
							class="down">Search Status</span> </a>
						<ul class="sub">

							<li>
								<a href="./SearchResultAction">Search Click Through URLs</a>
							</li>
							<li>
								<a href="./SearchDataAction">Search Data</a>
							</li>
							<li>
								<a href="./userserached.jsp">User Search Info</a>
							</li>
							<li>
								<a href="./Clickthrowdata.jsp">Link Information</a>
							</li>
							
							
						</ul>
						</li>
						
						
						
						
						
						
						
						<li class="top">
							<a href="#nogo22" id="services" class="top_link"><span
								class="down">View Search Data</span> </a>
							<ul class="sub">
								<li>
								<a href="./SearchDataAction1">View Search Data</a>
							   </li>
							   <li>
								<a href="./ViewFileData.jsp">View Data</a>
							   </li>
							   <li>
								<a href="./RemoveStopWordAction">Remove Stop words</a>
							    </li>
							   <li>
								<a href="././StemmingAction">Steaming words</a>
							   </li>
							   <li>
								<a href="./ParaGraphAction" >View ParaGraph Data</a>
							   </li>
							   <li>
								<a href="./DuplicateDataCount.jsp" >Duplicate Data Count</a>
							  </li>
							</ul>
						</li>
						
			
			
			
			
			  
			
			
			
					
			    <li class="top">
						<a href="#nogo22" id="services" class="top_link"><span
							class="down">Proposed Search Data</span> </a>
						<ul class="sub">

							
                             <li>
								<a href="./SearchProposed.jsp">Search Click Through URLs</a>
							</li>
							
							
						</ul>
						</li>
			
			
			
			
			
			
			
						<li class="top">
							<a href="#nogo22" id="services" class="top_link"><span
								class="down">security</span> </a>
							<ul class="sub">
								<li>
									<a
										href="./Changepassword.jsp?userid=<%=session.getAttribute("user")%>">Change Password</a>
								</li>
							</ul>
						</li>
						
						
						
						
						
						
						<li class="top">
							<a href="./LogoutAction" class="top_link"><span>Logout</span>
							</a>
						</li>
		</ul>

	</body>
</html>
