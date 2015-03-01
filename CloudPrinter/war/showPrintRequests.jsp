<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%><%@page import="java.util.ArrayList"%><%@page
	import="com.cloudprinter.dto.PrintedFilesInfo"%>
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
			if (request.getAttribute("printFileRequestList") == null) {
				response.getWriter()
						.println(
								"<center><img src='images/no-record-found.png' alt='No Records Found....:-(' /></center>");
			} else {
		%>
		<table id="filetable">

			<tr>
				<th>File Name</th>
				<th>Date of Printing</th>
				<th>Print Status</th>
				<th>Print Error</th>
				<th>Paper Type</th>
				<th>Copies</th>
				<th>Printing Location</th>
			</tr>

			<%
				ArrayList<PrintedFilesInfo> printFilesList = (ArrayList<PrintedFilesInfo>) request
							.getAttribute("printFileRequestList");
					for (PrintedFilesInfo u : printFilesList) {

						String fileKey = u.getFileKey();
						int month = u.getDateOfPrintRequest().getMonth() + 1;
						//int date=u.getDateOfUploading().getDate()+1;
						int year = u.getDateOfPrintRequest().getYear() + 1900;
			%>
			<tr>

				<td><%=u.getFileName()%></td>
				<td><%=u.getDateOfPrintRequest().getDate() + "-" + month
							+ "-" + year%></td>
				<td><%=u.getPrintStatus()%></td>
				<td><%=u.getPrintError()%></td>
				<td><%=u.getPaperType()%></td>
				<td><%=u.getPrintCopies()%></td>
				<td><%=u.getPrintingLocation()%></td>
			</tr>
			<%
				}
				}
			%>

		</table>
		<br> <br> <a href="userHome.jsp">Back</a>
	</center>
</body>
</html>