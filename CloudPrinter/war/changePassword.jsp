<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.cloudprinter.dto.DeletedFiles"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Cloud Printer HMRITM</title>
<link href="style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<%
		if (session.getAttribute("emailId") == null
				&& session.getAttribute("password") == null
				&& session.getAttribute("loginId") == null) {
			RequestDispatcher rd = request
					.getRequestDispatcher("/notInSession.jsp");
			rd.forward(request, response);
		}
	%>

	<div
		style="background-image: url(images/bg.jpg); width: 1000px; text-align: center; height: 646px; margin-left: 15%;">
		<div class="banner">
			<img src="images/logo.gif" class="logo" />
			<div class="bannertext">Cloud Printing</div>
			<div class="menu">
				<a href="help.jsp"><img src="images/help.png" /></a>Help
			</div>
			<div style="margin-left: 1%;" class="menu">
				<a href="changePassword.jsp"><img
					src="images/password-change.png" /></a>Change Password
			</div>
			<div style="margin-left: 1.5%;" class="menu">
				<a href="logout"><img src="images/logout.png" /></a>Logout
			</div>
		</div>

		<div class="main">
			<div class="login">
				<div class="loginbg">
					<h1 style="margin-top: 56px">
						<a href="userHome.jsp">Home</a>
					</h1>
				</div>
				<div class="loginbox">
					<br> <a href="uploadFiles.jsp"><img alt="Upload File"
						src="images/uploadfile.png"></a> <br> <a href="getallfiles"><img
						alt="My files" src="images/myfiles.png"></a> <br> <a
						href="getdeletedfiles"><img alt="Deleted Files"
						src="images/deletedfiles.png"></a> <br> <a
						href="getallprintrequests"><img alt="Print Files Status"
						src="images/printfilestatus.png"></a>
				</div>
			</div>
			<div class="main2">
				<div class="maincontent_bg">
					<h1 style="width: 100%; text-align: center;">
						Welcome:<%=request.getSession(false).getAttribute("loginId")%></h1>
				</div>
				<div
					style="width: 416px; height: 420px; float: right; background-color: #ebebeb;">
					<center>
						<p>
						<form action="changepassword" method="post" name="myForm"
							onsubmit="return validateForm()">

							<div class="name">
								Enter Email-Id :<input name="emailId" type="text"
									style="float: right; width: 62%; border-radius: 5px; border: 1px solid #e4e4e4; text-align: left;" />
							</div>

							<div class="name">
								Enter Login-Id :<input name="loginId" type="text"
									style="float: right; width: 62%; border-radius: 5px; border: 1px solid #e4e4e4; text-align: left;" />
							</div>


							<div class="name">
								Enter Old Password :<input name="oldPassword" type="password"
									style="float: right; width: 62%; border-radius: 5px; border: 1px solid #e4e4e4; text-align: left;" />
							</div>


							<div class="name">
								Enter New Password :<input name="newPassword" type="password"
									style="float: right; width: 62%; border-radius: 5px; border: 1px solid #e4e4e4; text-align: left;" />
							</div>


							<div class="submit_btn">
								<input type="submit" value="Update Password" />
							</div>
						</form>
						</p>
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
					%></p>



				</div>
			</div>
		</div>


		<div
			style="width: 100%; float: left; height: 200px; background-image: url(images/footerbg.jpg);">
			<img src="images/logo.gif" width="79px" height="57px;"
				style="float: left; margin-top: 14%; margin-left: 14%;" />
			<div
				style="width: 558px; margin-left: 2%; text-align: left; float: left; font-family: Verdana, Geneva, sans-serif; font-size: 12px; font-style: italic; line-height: 16px; color: #fff; margin-top: 15%;">
				Designed , developed and hosted by Cloud Printing Apps <br />Copyright
				@ Cloud Printer HMRITM ... All rights reserved
			</div>
			<div
				style="width: 10%; text-align: right; margin-right: 10px; float: left; margin-top: 15%;">
				<a href="#"><img src="images/fb_icon.jpg" /></a> <a href="#"><img
					src="images/twtr_icon.jpg" /></a> <a href="#"><img
					src="images/gm_icon.jpg" /></a>
			</div>
		</div>
	</div>
</body>
</html>
