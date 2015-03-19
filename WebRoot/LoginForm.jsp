
<!DOCTYPE HTML PUBLIC "-//w3c//dtd html 4.0 transitional//en">
<html>
	<head>
	<script language="JavaScript" src="<%=request.getContextPath()+"/scripts/gen_validatorv31.js"%>" type="text/javascript"></script>
		<style type="text/css">
.Title {
font-family:Verdana;
font-weight:bold;
font-size:8pt
}
.Title1 {font-family:Verdana;
font-weight:bold;
font-size:8pt
}
        </style>
	</head>
	<body>
	

 <jsp:include page="Header.jsp"></jsp:include>
<br/>
         
       
        <form action="<%=request.getContextPath()+"/LoginAction"%>" method=post name="login">
        
        <table  border="0" align="center" width="80%">
               <tr>
               <td  background="#F5FFFA"> <img src="<%=request.getContextPath()+"/img/log.jpg"%>"  height="300" width="300"/>
                 </td>
                 <td height="120" align="right"></td>
                 <td><table border="0" align="center" width="304" height="257">
                <tr align="center"><strong><h3><font align="center" color="black" >Login Form</font></h3></strong>
 	</tr>
                   <tr>
                     <td ><font color="#CD5C5C" size=""><b>UserID</b></font></td>
                      <td ><input type="text" name="username"> </td>
                      </tr>
                   <tr>
                     <td><font color="#CD5C5C" size=""><b>Password</b></font></td>
                      <td>
                        <input type="password" name="password"><br><br>                      </td>
                      </tr>
                    <tr>
                     <td colspan="2">
                       <div align="center" class="style11">
                         <input type="image" name="Submit"src="img/login.png" height="50" width="120">
                          &nbsp;
                           <input type="image" name="reset"src="img/resetbutton.png" height="50" width="120">
                         </div> 
                         </tr>   
                                           <tr><br/>
                                                               
                         <td><div>
                         <a href="./RegistrationForm.jsp"><font color="#228B22" size="3" style="verdana">NewUserSignUp??</font></a>
                         
                         </div></td>
                         
                      </tr>
                      
               
                      <tr>                    
                         <td><div>
                         <a href="./Recoverpassword.jsp"><font color="#228B22" size="3" style="verdana">ForgotPassword??</font></a>
                         
                         </div></td>
                         
                      </tr>
                 </table>
                 
              
               
               
               
             </table>
		
		
          </form>
        
          <script language="JavaScript" type="text/javascript">
//You should create the validator only after the definition of the HTML form
  var frmvalidator  = new Validator("login");

  frmvalidator.addValidation("username","req","Login Name is required");
  frmvalidator.addValidation("password","req","Password is required");
   
  </script>
          <br/>
                 
     <br/>
    <jsp:include page="./Footer.jsp"></jsp:include>
	</body>
</html>