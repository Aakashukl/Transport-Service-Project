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
	<a href='HomeTransporter'>Home</a>
	<h3>Vehicle-Entry-Form</h3>
	<frm:form action="saveNewVehicleProcess" modelAttribute="vehicleObj"
		enctype="multipart/form-data" method="POST">
		<table border="1">
			<frm:hidden path="vehicleId" />

			<tr>
				<td>Vehicle Type</td>
				<td><frm:input path="vehicleType" required="true" /></td>
			</tr>

			<tr>
				<td>Vehicle Name</td>
				<td><frm:input path="vehicleName" required="true" /></td>
			</tr>


			<tr>
				<td>Vehicle Model</td>
				<td><frm:input path="vehicleModel" required="true" /></td>
			</tr>

			<tr>
				<td>Vehicle Number</td>
				<td><frm:input path="vehicleRegistrationNumber" required="true" /></td>
			</tr>


			<tr>
				<td>Vehicle Registration Certificate</td>
				<td><input name="RegistrationCertificatePath" type="file"
					required /></td>
			</tr>

			<tr>
				<td>Vehicle Insurance Paper</td>
				<td><input name="InsurancePaperPath" type="file" required /></td>
			</tr>


			<tr>
				<td>Vehicle Fitness Certificate</td>
				<td><input name="FitnessCertificatePath" type="file" required /></td>
			</tr>


			<tr>
				<td>Vehicle Driver Driving License</td>
				<td><input name="DriverDrivingLicencePath" type="file" required /></td>
			</tr>


			<tr>
				<td></td>
				<td><input type="submit" value="Save" /><input type="reset"
					value="Reset" /></td>
			</tr>

			<tr>
				<td><input type="hidden" name="transporterID"
					value="${TransporterID}"></td>
			</tr>

		</table>
	</frm:form>
</body>
</html>
