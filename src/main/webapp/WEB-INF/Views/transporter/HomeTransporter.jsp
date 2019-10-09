<%@page isELIgnored="false"%>
<%@taglib prefix="frm" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
</head>
<body>
	<form action="logout">
		<div align="right">
			<button type="submit" value="submit">Logout</button>
		</div>
		<div align="center">
			<h2>Transporter</h2>
			<br /> <a href=TransporterProfileUpdatePage> Update Profile</a> <br />
			<a href=TransporterAddVehiclePage> ADD Vehicle</a> <br /> <a
				href=showDealPostPageToTransporter>Post Deal</a> <br /> <a
				href="transporterDealUpdateAndDelete">Deal Update And Delete</a> <br />
			<a href="customerQueryResponse">Customer Query</a> <br /> <a
				href="transporterUpdateVehicledropdown">Update Vehicle</a>
				</div>
				</form>
</body>
</html>
