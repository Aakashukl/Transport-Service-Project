<%-- <%
	if (request.getSession().getAttribute("CustomerID") == null)
		response.sendRedirect("LoginPage.jsp");
%>
 --%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="logout">
<div align="right"><button type="submit" value="submit">Logout</button></div>
<div align="center">
<!-- <a href="HomeCustomer">Home</a> -->
<!-- <a href="logout">logout</a> -->
<h2>Customer Home Page</h2>
<br/>
<br/>
<a href="CustomerUpdateProfilePage">Update Profile</a>
<br/>
<a href="ShowAllDeals">Deals</a>
</div>
</form>
</body>
</html>