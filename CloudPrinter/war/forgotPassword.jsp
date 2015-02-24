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
	<center>
		<form action="forgotpassword" method="post">
			Enter your Email-Id :<input type="text" name="emailId" /><br> <br>
			Enter your Login-Id :<input type="text" name="loginId" /> <br>
			<br> <input type="submit" value="Get Password" />
		</form>
	</center>
	<br>
	<br>
	<%
		if (request.getAttribute("forgotPasswordStatus") != null) {
			response.getWriter().println(
					request.getAttribute("forgotPasswordStatus"));
		}
	%>
	<br>
	<a href="loginPage.jsp">Go Back To Login Page</a>


</body>
</html>