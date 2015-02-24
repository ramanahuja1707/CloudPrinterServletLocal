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
	<br>
	<center>
		<form method="post" action="forgotloginid">
			Enter your Email-Id :<input type="text" name="emailId" /><input
				type="submit" value="Get Login Id" />
		</form>
	</center>

	<br>
	<%
		if (request.getAttribute("forgotLoginIdStatus") != null) {
			response.getWriter().println(
					request.getAttribute("forgotLoginIdStatus"));
		}
	%>
	<br>
	<a href="loginPage.jsp">Go Back To Login Page</a>
</body>
</html>