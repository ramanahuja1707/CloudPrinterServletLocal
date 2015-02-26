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


	<center>
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
					if (request.getAttribute("uploadedFiles") == null) {
						response.getWriter().println("No Files Found.....:-(");
					} else {
						ArrayList<UploadedFilesInfo> uploadedFiles = (ArrayList<UploadedFilesInfo>) request
								.getAttribute("uploadedFiles");
						for (UploadedFilesInfo u : uploadedFiles) {
							String fileType;
							if (u.getFileType().equals(FileType.USERUPLOADED)) {
								fileType = "Uploaded";
							} else {
								fileType = "Created";
							}
							String fileKey = u.getFileKey();
				%>
				<tr>

					<td><%=u.getFileName()%></td>
					<td><%=u.getDateOfUploading().getDate() + "-"
							+ u.getDateOfUploading().getMonth() + "-"
							+ u.getDateOfUploading().getYear()%></td>
					<td><%=fileType%></td>
					<td><%=u.getUploadStatus()%></td>
					<td><input type="radio" value="<%=u.getFileKey()%>"
						name="fileSelected" /></td>
					<td style="display: none;"><input type="checkbox"
						value="<%=fileKey%>" name="fileKey" /></td>
				</tr>

				<%
					}
					}
				%>
			</table>
			<input type="button" value="Print File" name="printbutton"
				onclick="validatePrintAndDownloadForm(this)"> <br> <br>
			<input type="button" value="Serve File" name="downloadbutton"
				onclick="validatePrintAndDownloadForm(this)">
		</form>

		<br> <br>
		<p id="fileSelectError" style="color: red;"></p>
		<br> <br>
		<%
			if (request.getAttribute("downloadError") != null) {
		%><%=request.getAttribute("downloadError")%>
		<%
			}
		%>
		<br> <br> <a href="userHome.jsp">Go back</a>
	</center>
</body>
</html>