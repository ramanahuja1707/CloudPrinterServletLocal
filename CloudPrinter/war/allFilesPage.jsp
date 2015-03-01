<%@page import="com.cloudprinter.enums.FileType"%>
<%@page import="com.cloudprinter.enums.UploadStatus"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cloudprinter.dto.UploadedFilesInfo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
	function submitForm(clickedButton) {
		if (clickedButton.name == "printbutton") {
			document.printanddownloadform.action = "/printFile.jsp";
		} else if (clickedButton.name == "downloadbutton") {

			document.printanddownloadform.action = "downloadfile";
		} else if (clickedButton.name == "deletebutton") {
			document.printanddownloadform.action = "deletefile";
		}
		document.printanddownloadform.submit();
	}

	function validatePrintAndDownloadForm(clickedButton) {
		var fileSelected = document.forms["printanddownloadform"]["fileSelected"].value;
		if (fileSelected == null || fileSelected == "") {
			document.getElementById("fileSelectError").innerHTML = "Please Select a File ...";
		} else {
			submitForm(clickedButton);
		}
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cloud Printer HMRITM</title>
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
	<center>
		<%
			if (request.getAttribute("uploadedFiles") == null) {
				response.getWriter()
						.println(
								"<center><img src='images/no-record-found.png' alt='No Records Found....:-(' /></center>");
			} else {
		%>
		<form action="#" method="get" name="printanddownloadform">

			<table id="filetable">

				<tr>
					<th>File Name</th>
					<th>Date of Uploading</th>
					<th>File Type</th>
					<th>Upload Status</th>
					<th>Select</th>
				</tr>
				<%
					ArrayList<UploadedFilesInfo> uploadedFiles = (ArrayList<UploadedFilesInfo>) request
								.getAttribute("uploadedFiles");
						for (UploadedFilesInfo u : uploadedFiles) {
							String fileType;
							if (u.getFileType().equals(
									FileType.USERUPLOADED.getFileType())) {
								fileType = "Uploaded";
							} else {
								fileType = "Created";
							}
							String fileKey = u.getFileKey();
							int month = u.getDateOfUploading().getMonth() + 1;
							//int date=u.getDateOfUploading().getDate()+1;
							int year = u.getDateOfUploading().getYear() + 1900;
				%>
				<tr>

					<td><%=u.getFileName()%></td>
					<td><%=u.getDateOfUploading().getDate() + "-" + month
							+ "-" + year%></td>
					<td><%=fileType%></td>
					<td><%=u.getUploadStatus()%></td>
					<td><input type="radio"
						value="<%=u.getFileKey() + "#" + u.getFileName()%>"
						name="fileSelected" /></td>
					<td style="display: none;"><input type="text"
						value="<%=u.getFileId()%>" name="fileId" /></td>
				</tr>
				<%
					}
					}
				%>

			</table>
			<%
				if (request.getAttribute("uploadedFiles") != null) {
			%>
			<input type="button" value="Print File" name="printbutton"
				onclick="validatePrintAndDownloadForm(this)"> <br> <br>
			<input type="button" value="Serve File" name="downloadbutton"
				onclick="validatePrintAndDownloadForm(this)"> <input
				type="button" value="Delete File" name="deletebutton"
				onclick="validatePrintAndDownloadForm(this)">
			<%
				}
			%>
		</form>

		<br> <br>
		<p id="fileSelectError" style="color: red;"></p>
		<br> <br>
		<%
			if (request.getAttribute("downloadError") != null) {
		%><%=request.getAttribute("downloadError")%>
		<%
			}
		%><br>
		<%
			if (request.getAttribute("printError") != null) {
		%><%=request.getAttribute("printError")%>
		<%
			}
		%>
		<br>
		<%
			if (request.getAttribute("deleteError") != null) {
		%><%=request.getAttribute("deleteError")%>
		<%
			}
		%>
		<br> <br> <a href="userHome.jsp">Go back</a> <br> <br>
		<%
			if (request.getAttribute("filePrintStatus") != null) {
				response.getWriter().println(
						request.getAttribute("filePrintStatus"));
			}
		%>
	</center>
</body>
</html>