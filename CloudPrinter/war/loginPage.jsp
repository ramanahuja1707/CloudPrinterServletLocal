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
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Cloud Printer HMRITM</title>
<link href="style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div
		style="background-image: url(images/bg.jpg); width: 1000px; text-align: center; height: 646px; margin-left: 15%;">
		<div class="banner">
						<a href="clientRegistration.jsp"> <img src="images/logo.gif"
				class="logo" /></a>
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
					<form action="login" name="myForm" method="post"
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


					<%
						if (request.getAttribute("loginError") != null) {
					%>
					<p style="color: red; font: italic; font-family: sans-serif;"><%=request.getAttribute("loginError")%></p>
					<%
						}
					%>
					<%
						if (request.getAttribute("logoutStatus") != null) {
					%>

					<p style="color: red; font: italic; font-family: sans-serif;">Logout
						Successfully...:-)</p>
					<%
						}
					%>

					<div
						style="width: 45%; text-align: right; float: left; font-family: Verdana, Geneva, sans-serif; font-size: 10px; text-decoration: underline; color: #6d7676;">
						<a href="forgotPassword.jsp"><img
							src="images/forgotpassword.png" alt="Forgot Password !!"
							style="width: 100px; height: 100px;"></a>
					</div>
					<div
						style="width: 45%; text-align: right; float: left; font-family: Verdana, Geneva, sans-serif; font-size: 10px; text-decoration: underline; color: #6d7676;">
						<a href="forgotLoginId.jsp"><img src="images/forgotid.png"
							alt="Forgot Login Id !!" style="width: 100px; height: 100px;"></a>
					</div>
					<a href="registerUser.jsp"><img alt=""
						src="images/register.png"
						style="width: 85%; height: 130px; margin-left: 15px; text-align: left; float: left; color: #6d7676;"></a>
				</div>
			</div>
			<div class="main2">
				<div class="maincontent_bg">
					<h1 style="width: 100%; text-align: center;">CLOUD PRINTING</h1>
				</div>
				<div
					style="width: 416px; height: 420px; float: right; background-color: #ebebeb;">
					<p>
						Cloud printing is the technology that enables printers to be
						accessed over a network through cloud computing.[1] There are, in
						essence, two kinds of cloud printing. <br>consumer-based
						cloud printing connects any application to cloud-enabled home
						printers that people own or have access to. <br>professional
						cloud printing enables publishers, companies and content owners to
						print their digital publications by leveraging networks of
						production facilities through cloud computing technology. <br />
						<br /> <img alt="cloud printing" src="images/cloud.jpg"
							style="width: 380px; height: 220px;">
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
