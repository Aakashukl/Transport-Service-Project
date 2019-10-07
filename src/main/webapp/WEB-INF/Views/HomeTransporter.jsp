<%@page isELIgnored="false"%>
<%@taglib prefix="frm" uri="http://www.springframework.org/tags/form"%>

<%
	if (request.getSession().getAttribute("TransporterID") == null)
		response.sendRedirect("LoginPage.jsp");
%>
<html>
<head>
</head>
<body>
	<a href="logout">logout</a>
	<h2>Transporter hai ye</h2>
	<br />
	<a href=TransporterProfileUpdatePage> Update Profile</a>
	<br />
	<a href=TransporterAddVehiclePage> ADD Vehicle</a>
	<br />
	<a href=showDealPostPageToTransporter>Post Deal</a>
	<br />
	<a href="transporterDealUpdateAndDelete">Deal Update And Delete</a>
	<br />
	<a href="customerQueryResponse">Customer Query</a>
</body>
</html>
