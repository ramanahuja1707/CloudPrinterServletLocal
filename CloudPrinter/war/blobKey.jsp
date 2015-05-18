<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cloud Printer HMRITM</title>
</head>
<body>
	<%
	response.setContentType("text/json");
		String blobKey = (String) request.getAttribute("blobKey");
		JSONObject j = new JSONObject();
		j.put("blobKey", blobKey);
		out.println(j.toString());
	%>
</body>
</html>