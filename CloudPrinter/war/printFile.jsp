<%@page import="com.cloudprinter.enums.PageSpecification"%>
<%@page import="com.cloudprinter.enums.PageNumberCustomization"%>
<%@page import="java.awt.print.Paper"%>
<%@page import="com.cloudprinter.enums.PrintingLocation"%>
<%@page import="com.cloudprinter.enums.PaperType"%>
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
	<form action="printfile" method="get" name="myForm"
		onsubmit="return validateForm()">
		<table>
			<tr>
				<td style="display: none;"><input type="text"
					value="<%=fileData[0]%>" name="fileKey"></td>
			</tr>
			<tr>
				<td>FileName</td>
				<td><input type="text" name="fileName" value="<%=fileData[1]%>"
					readonly="readonly" /></td>
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
						<option value="<%=PrintingLocation.DELHI.getPrintingLocation()%>"><%=PrintingLocation.DELHI%></option>
						<option
							value="<%=PrintingLocation.HYDERABAD.getPrintingLocation()%>"><%=PrintingLocation.HYDERABAD%></option>
						<option value="<%=PrintingLocation.MUMBAI.getPrintingLocation()%>"><%=PrintingLocation.MUMBAI%></option>
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

	<br>
	<br>
	<a href="getallfiles">Go back</a>
	<br>
	<br>
	<a href="userHome.jsp">Home</a>
</body>
</html>