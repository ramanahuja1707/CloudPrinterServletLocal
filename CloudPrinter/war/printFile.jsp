<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.cloudprinter.enums.PageSpecification"%>
<%@page import="com.cloudprinter.enums.PageNumberCustomization"%>
<%@page import="java.awt.print.Paper"%>
<%@page import="com.cloudprinter.enums.PrintingLocation"%>
<%@page import="com.cloudprinter.enums.PaperType"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<style>
#filetable {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	width: 50%;
	border-collapse: collapse;
}

#filetable td,#filetable th {
	font-size: 1em;
	border: 1px solid #98bf21;
	padding: 3px 7px 2px 7px;
}

#filetable th {
	font-size: 1.1em;
	text-align: left;
	padding-top: 5px;
	padding-bottom: 4px;
	background-color: #A7C942;
	color: #ffffff;
}
</style>
<script type="text/javascript">
	function validateForm() {
		var printCopies = document.forms["myForm"]["printCopies"].value;

		if (printCopies == null || printCopies == "") {
			alert("Print Copies must be filled out");
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
		String fileSelected = request.getParameter("fileSelected");
		String[] fileData = null;
		if (fileSelected == null) {
			request.setAttribute("printError", "Please select a file...");
			RequestDispatcher rd = request
					.getRequestDispatcher("/getallfiles");
			rd.forward(request, response);
		} else {
			fileData = fileSelected.split("#");
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
					<h1 style="margin-top: 56px"><a href="userHome.jsp">Home</a></h1>
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
					<p>
					<form action="printfile" method="get" name="myForm"
						onsubmit="return validateForm()">
						<table>
							<tr>
								<td style="display: none;"><input type="text"
									value="<%=fileData[0]%>" name="fileKey"></td>
							</tr>
							<tr>
								<td>FileName</td>
								<td><input type="text" name="fileName"
									value="<%=fileData[1]%>" readonly="readonly" /></td>
							</tr>
							<tr>
								<td>Print Copies</td>
								<td><input type="text" name="printCopies" /></td>
							</tr>
							<tr>
								<td>Paper Type</td>

								<td><select name="paperType">
										<option selected="selected"
											value="<%=PaperType.A4.getPaperType()%>"><%=PaperType.A4%></option>
										<option value="<%=PaperType.A5.getPaperType()%>"><%=PaperType.A5%></option>
										<option value="<%=PaperType.A6.getPaperType()%>"><%=PaperType.A6%></option>
										<option value="<%=PaperType.ENVELOPE10.getPaperType()%>"><%=PaperType.ENVELOPE10%></option>
										<option value="<%=PaperType.EXECUTIVE.getPaperType()%>"><%=PaperType.EXECUTIVE%></option>
										<option value="<%=PaperType.LEGAL.getPaperType()%>"><%=PaperType.LEGAL%></option>
										<option value="<%=PaperType.LETTER.getPaperType()%>"><%=PaperType.LETTER%></option>
								</select></td>
							</tr>
							<tr>
								<td>Printing Location</td>

								<td><select name="printingLocation">
										<option selected="selected"
											value="<%=PrintingLocation.BANGLORE.getPrintingLocation()%>"><%=PrintingLocation.BANGLORE%></option>
										<option
											value="<%=PrintingLocation.CHENNAI.getPrintingLocation()%>"><%=PrintingLocation.CHENNAI%></option>
										<option
											value="<%=PrintingLocation.DELHI.getPrintingLocation()%>"><%=PrintingLocation.DELHI%></option>
										<option
											value="<%=PrintingLocation.HYDERABAD.getPrintingLocation()%>"><%=PrintingLocation.HYDERABAD%></option>
										<option
											value="<%=PrintingLocation.MUMBAI.getPrintingLocation()%>"><%=PrintingLocation.MUMBAI%></option>
								</select></td>
							</tr>
							<tr>
								<td>Page Specification</td>

								<td><select name="pageSpecification">
										<option selected="selected"
											value="<%=PageSpecification.BLACKWHITE.getPageSpecification()%>"><%=PageSpecification.BLACKWHITE%></option>
										<option
											value="<%=PageSpecification.COLOURED.getPageSpecification()%>"><%=PageSpecification.COLOURED%></option>
								</select></td>
							</tr>
							<tr>
								<td><input type="submit" value="Print" /></td>
							</tr>
						</table>
					</form>
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
