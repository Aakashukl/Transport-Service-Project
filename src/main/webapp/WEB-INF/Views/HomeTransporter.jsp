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
	<a href=TransporterAddVehiclePage> ADD Vehicle</a>
	<a href=showDealPostPageToTransporter>Post Deal</a>
	<a href="transporterDealUpdateAndDelete">Deal Update And Delete</a>
</body>
</html>
