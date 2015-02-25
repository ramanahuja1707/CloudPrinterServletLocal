<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cloud Printer HMRITM</title>
</head>
<body>
	<br>
	<br>
	<%
		/*if(request.getAttribute("registrationStatus")!=null && request.getAttribute("registrationError")!=null)
		 {
		 if(request.getAttribute("registrationStatus").equals("success"))
		 {*/
		//response.getWriter().println(request.getAttribute("registrationStatus"));
		/*}else if(request.getAttribute("registrationError").equals("failure"))
		 {*/
		if (request.getAttribute("registrationError") != null)
			response.getWriter().println(
					request.getAttribute("registrationError"));
		//}
		//}
	%>
	<br>
	<br>
	<center>
		<form action="registeruser" method="post">
			Name :<input type="text" name="userName" /><br> <br>
			Login Id :<input type="text" name="loginId" /><br> <br>
			Age :<input type="text" name="age" /><br> <br> Male :<input
				type="radio" value="Male" name="gender" /> Female :<input
				type="radio" value="Female" name="gender" /><br> <br>
			User Designation :<input type="text" name="userDesignation" /><br>
			<br> User Work Location :<input type="text"
				name="userWorkLocation" /><br> <br> User Email-Id :<input
				type="text" name="emailId" /><br> <br> User Alternate
			Email-Id :<input type="text" name="alternateEmailId" /><br> <br>
			User Contact No. :<input type="text"  name="contactNo" /><br>
			<br> <input type="submit" value="Register" />
		</form>
	</center>
	<a href="loginPage.jsp">Go Back To Login Page</a>
</body>
</html>