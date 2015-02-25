<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
			method="post" enctype="multipart/form-data">
			<input type="text" name="foo"> <input type="file" size="60"
				id="myfile" name="myFile"> <input type="submit"
				value="Submit">
		</form>
		
		<br><br>
		<a href="/userHome.jsp">Go Back</a>
	</center>
</body>
</html>