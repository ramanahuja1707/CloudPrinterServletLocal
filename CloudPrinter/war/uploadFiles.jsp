<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
function validateUploadForm() {
	var fileSelected = document.forms["uploadForm"]["myFile"].value;
	if (fileSelected == null || fileSelected == "") {
		document.getElementById("uploadFileError").innerHTML = "Please Select a File ...";
		return false;
	}
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cloud Printer HMRITM</title>
</head>
<body>
	<%
		BlobstoreService blobstoreService = BlobstoreServiceFactory
				.getBlobstoreService();
		if (session.getAttribute("emailId") == null
				&& session.getAttribute("password") == null
				&& session.getAttribute("loginId") == null) {
			RequestDispatcher rd = request
					.getRequestDispatcher("/notInSession.jsp");
			rd.forward(request, response);
		}
		if(request.getAttribute("fileUploadStatus")!=null)
		{
			response.getWriter().println(request.getAttribute("fileUploadStatus"));
		}
	%>
	<center>
		<form action="<%=blobstoreService.createUploadUrl("/uploadfile")%>"
			method="post" enctype="multipart/form-data" name="uploadForm" onsubmit="return validateUploadForm()">
			<input type="text" name="foo"> <input type="file" size="60"
				id="myfile" name="myFile"> <input type="submit"
				value="Upload -> Cloud">
		</form>
		
		<br><p style="color: red;" id="uploadFileError"></p><br>
		<a href="/userHome.jsp">Go Back</a>
	</center>
</body>
</html>