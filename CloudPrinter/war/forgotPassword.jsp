<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script>
	function validateForm() {
		var loginId = document.forms["myForm"]["loginId"].value;
		var emailId = document.forms["myForm"]["mailId"].value;
		var password = document.forms["myForm"]["password"].value;
		if (loginId == null || loginId == "") {
			alert("Login-Id must be filled out");
			return false;
		}
		if (emailId == null || emailId == "") {
			alert("Email-Id must be filled out");
			return false;
		}
		if (password == null || password == "") {
			alert("Password must be filled out");
			return false;
		}
	}
	function validate() {
		var emailId = document.forms["myForm1"]["emailId"].value;
		var loginId = document.forms["myForm1"]["loginId"].value;
		if (emailId == null || emailId == "") {
			alert("Email-Id must be filled out");
			return false;
		}
		if (loginId == null || loginId == "") {
			alert("Login-Id must be filled out");
			return false;
		}

	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
<link href="style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div
		style="background-image: url(images/bg.jpg); width: 1000px; text-align: center; height: 646px; margin-left: 15%;">
		<div class="banner">
			<img src="images/logo.gif" class="logo" />
			<div class="bannertext">Cloud Printing</div>
			<div class="menu">
				<a href="loginPage.jsp"><img src="images/home.jpg" /></a>Home
			</div>
			<div style="margin-left: 1%;" class="menu">
				<a href="aboutUs.jsp"><img src="images/aboutus.jpg" /></a>About Us
			</div>
			<div style="margin-left: 1.5%;" class="menu">
				<a href="contactUs.jsp"><img src="images/contact-us.jpg" /></a>Contact
				Us
			</div>
		</div>

		<div class="main">
			<div class="login">
				<div class="loginbg">
					<h1 style="margin-top: 56px">Login</h1>
				</div>
				<div class="loginbox">
					<form action="login" method="post" name="myForm"
						onsubmit="return validateForm()">
						<div class="name">
							Login Id: <input name="loginId" type="text"
								style="float: right; width: 62%; border-radius: 5px; border: 1px solid #e4e4e4; text-align: left;" />
						</div>
						<div class="name">
							Password:<input name="password" type="password"
								style="float: right; width: 60%; border-radius: 5px; border: 1px solid #e4e4e4; text-align: left;" />
						</div>
						<div class="name">
							Email Id:<input name="mailId" type="text"
								style="float: right; width: 62%; border-radius: 5px; border: 1px solid #e4e4e4; text-align: left;" />
						</div>
						<div class="submit_btn">
							<input type="submit" value="submit" /> <br>
						</div>
					</form>
					<br />
					<%
						if (request.getAttribute("forgotPasswordStatus") != null) {
							response.getWriter().println(
									request.getAttribute("forgotPasswordStatus"));
						}
					%>
					<div
						style="width: 45%; text-align: right; float: left; font-family: Verdana, Geneva, sans-serif; font-size: 10px; text-decoration: underline; color: #6d7676;">
						<a href="forgotPassword.jsp">Forgot Password!!</a>
					</div>
					<div
						style="width: 45%; text-align: right; float: left; font-family: Verdana, Geneva, sans-serif; font-size: 10px; text-decoration: underline; color: #6d7676;">
						<a href="forgotLoginId.jsp">Forgot Login Id!!</a>
					</div>
					<br> <a href="registerUser.jsp"><img alt=""
						src="images/register.jpg"
						style="width: 85%; height: 220px; margin-left: 15px; text-align: left; float: left; color: #6d7676;"></a>
				</div>
			</div>
			<div class="main2">
				<div class="maincontent_bg">
					<h1 style="width: 100%; text-align: center;">CLOUD PRINTING</h1>
				</div>
				<div
					style="width: 416px; height: 420px; float: right; background-color: #ebebeb;">
					<p>
						<b><font size="4px" style="color: #84D7FD;"><i>FORGOT
									PASSWORD</i></font></b> <br /> <br />
					<form action="forgotpassword" method="post" name="myForm1"
						onsubmit="return validate()">
						<div class="name">
							Enter your Email-Id :<input name="emailId" type="text"
								style="float: right; width: 62%; border-radius: 5px; border: 1px solid #e4e4e4; text-align: left;" />
						</div>
						<br> <br>

						<div class="name">
							Enter your Login-Id :<input name="loginId" type="text"
								style="float: right; width: 62%; border-radius: 5px; border: 1px solid #e4e4e4; text-align: left;" />
						</div>
						<br>
						<div class="submit_btn">
							<input type="submit" value="Get Password" />
						</div>
					</form>
					<b><font size="4px" style="color: #84D7FD;"></font></b> <br />
					</p>
				</div>
			</div>
		</div>


		<div
			style="width: 100%; float: left; height: 200px; background-image: url(images/footerbg.jpg);">
			<img src="images/logo.gif" width="79px" height="57px;"
				style="float: left; margin-top: 14%; margin-left: 14%;" />
			<div
				style="width: 558px; margin-left: 2%; text-align: left; float: left; font-family: Verdana, Geneva, sans-serif; font-size: 12px; font-style: italic; line-height: 16px; color: #fff; margin-top: 15%;">
				Designed , developed and hosted by Cyber Parliament <br />Copyright
				@ Cyber Parliament ... All rights reserved
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
