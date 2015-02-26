<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
	function validateForm() {
		var loginId = document.forms["myForm"]["loginId"].value;
		var emailId = document.forms["myForm"]["emailId"].value;
		var oldPassword = document.forms["myForm"]["oldPassword"].value;
		var newPassword = document.forms["myForm"]["newPassword"].value;
		if (loginId == null || loginId == "") {

			document.getElementById("validationError").innerHTML = "Please Fill the login Id..";
			return false;
		}
		if (emailId == null || emailId == "") {

			document.getElementById("validationError").innerHTML = "Please Fill the Email Id..";
			return false;
		}
		if (oldPassword == null || oldPassword == "") {

			document.getElementById("validationError").innerHTML = "Please Fill the Old Password..";
			return false;
		}
		if (newPassword == null || newPassword == "") {
			document.getElementById("validationError").innerHTML = "Please Fill the New Password..";
			return false;
		}
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cloud Printer HMRITM</title>
</head>
<body>
	<center>
		<form action="changepassword" method="post" name="myForm"
			onsubmit="return validateForm()">
			Enter Email-Id :<input type="text" name="emailId"> <br>
			<br> Enter Login-Id :<input type="text" name="loginId"><br>
			<br> Enter Old Password :<input type="password"
				name="oldPassword"> <br> <br> Enter New Password :<input
				type="password" name="newPassword"> <br> <br> <input
				type="submit" value="Update" />
		</form>
	</center>
	<%
		if (request.getAttribute("changePasswordError") != null) {
	%>
	<br>
	<p style="color: red;">
		<%=request.getAttribute("changePasswordError")%>
	</p>
	<p style="color: red;" id="validationError"></p>
	<%
		}
	%>
	
	<br><br>
	<a href="userHome.jsp">Go back</a>
</body>
</html>