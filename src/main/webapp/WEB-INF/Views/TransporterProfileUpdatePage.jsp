<%@page isELIgnored="false"%>
<%@taglib prefix="frm" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
	if (request.getSession().getAttribute("TransporterID") == null)
		response.sendRedirect("LoginPage.jsp");
%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Yhi hai wo</h1>
</body>
</html>