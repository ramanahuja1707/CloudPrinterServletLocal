<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cloud Printer HMRITM</title>
</head>
<body>
	<center>
		<h1>Cloud Printer HMRITM</h1>
		<form action="login" method="post">
			<table>
				<tr>
					<td>Login-Id :<input type="text" name="loginId" /></td>
				</tr>
				<tr>

					<td>Password :<input type="password" name="password" /></td>
				</tr>
				<tr>

					<td>Email-Id :<input type="text" name="mailId" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Login" /></td>
				</tr>
			</table>
			<br>
		</form>
		<%
			if (request.getAttribute("loginError") != null) {
				response.getWriter()
						.println(request.getAttribute("loginError"));
			}
			if (request.getAttribute("logoutStatus") != null) {
				response.getWriter().println("Logout Successfully...");
			}
		%>
		<br> <a href="forgotLoginId.jsp">Forgot Login Id ?</a><br> <a
			href="forgotPassword.jsp">Forgot Password ?</a><br> <br> <a
			href="registerUser.jsp"><input type="button" value="Register"></a>
	</center>
</body>
</html>
