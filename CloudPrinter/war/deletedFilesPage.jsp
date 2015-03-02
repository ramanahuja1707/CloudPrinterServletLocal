<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.cloudprinter.dto.DeletedFiles"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<style>
#fileTable {
	font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
	width: 100%;
	border-collapse: collapse;
}

#fileTable td,#customers th {
	font-size: 1em;
	border: 1px solid #99ccff;
	padding: 3px 7px 2px 7px;
}

#fileTable th {
	font-size: 1.1em;
	text-align: left;
	padding-top: 5px;
	padding-bottom: 4px;
	background-color: #99ccff;
	color: #ffffff;
}

#fileTable tr.alt td {
	color: #000000;
	background-color: #EAF2D3;
}
</style>
<script type="text/javascript">
	function submitForm(clickedButton) {
		if (clickedButton.name == "deletebutton") {
			document.deleteFilesPermanentform.action = "deletefilepermanently";
		}
		document.deleteFilesPermanentform.submit();
	}

	function validateDeleteFilesPermanentForm(clickedButton) {
		var fileSelected = document.forms["deleteFilesPermanentform"]["fileSelected"].value;
		if (fileSelected == null || fileSelected == "") {
			document.getElementById("fileSelectError").innerHTML = "Please Select a File ...";
		} else {
			submitForm(clickedButton);
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
					<p>
						<%
							if (request.getAttribute("deletedFiles") == null) {
								response.sendRedirect("noRecordFound.jsp");
							} else {
						%>
					
					<form action="#" method="get" name="deleteFilesPermanentform">

						<table id="fileTable">

							<tr>
								<th>File Name</th>
								<th>Deletion Status</th>
								<th>Select</th>
							</tr>
							<%
								ArrayList<DeletedFiles> deletedFiles = (ArrayList<DeletedFiles>) request
											.getAttribute("deletedFiles");
									for (DeletedFiles u : deletedFiles) {
							%>
							<tr>
								<td><%=u.getFileName()%></td>
								<td><%=u.getFileDeletionStatus()%></td>
								<td><input type="radio"
									value="<%=u.getFileId() + "#" + u.getFileName()%>"
									name="fileSelected" /></td>
								<td style="display: none;"><input type="text"
									value="<%=u.getFileId()%>" name="fileId" /></td>
								<td style="display: none;"><input type="text"
									value="<%=u.getFileName()%>" name="fileName" /></td>
							</tr>
							<%
								}
									request.removeAttribute("deletedFiles");
								}
							%>
						</table>

						<br> <input type="button" value="Delete File Permanently"
							name="deletebutton"
							onclick="validateDeleteFilesPermanentForm(this)">

					</form>

					<br> <br>
					<p id="fileSelectError" style="color: red;"></p>
					<br> <br>
					<%
						if (request.getAttribute("deleteFilePermanentError") != null) {
					%><p style="color: red; font: italic; font-family: sans-serif;"><%=request.getAttribute("deleteFilePermanentError")%>
					</p>
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
