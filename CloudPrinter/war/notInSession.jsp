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

			<div style="text-align:right;margin-top:200px; margin-left:100px; float:left;width:61%; font-family:Verdana, Geneva, sans-serif; font-size:10px; text-decoration:underline; color:#6d7676;">
				<br>
				
				 <img alt="cloud printing" src="images/session-expired.png"
							style="width: 380px; height: 220px;">
							<p style="text-align:right; margin-left:100px; float:left;width:61%; font-family:Verdana, Geneva, sans-serif; font-size:25px; text-decoration:underline; color:#6d7676;" > <a href="loginPage.jsp"> Click here to Login</a></p>
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
