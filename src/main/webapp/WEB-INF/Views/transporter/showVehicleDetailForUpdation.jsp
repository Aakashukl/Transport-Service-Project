<%@page isELIgnored="false"%>
<%@taglib prefix="frm" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
</head>
<body>
			<form action="logout">
		<div align="right">
			<button type="submit" value="submit">Logout</button>
			<div align="left"><a href='HomeTransporter'>Home</a></div>
	
	</div>
	</form>
	
	<frm:form action="updateVehicleProcess" modelAttribute="vehicleObj"
		enctype="multipart/form-data">
		<div align="center">
		<h3>Vehicle-Entry-Form</h3>
		<table border="1">
			<frm:hidden path="vehicleId" />

			<tr>
				<td>Vehicle Type</td>
				<td><frm:input path="vehicleType"  /></td>
			</tr>

			<tr>
				<td>Vehicle Name</td>
				<td><frm:input path="vehicleName"  /></td>
			</tr>


			<tr>
				<td>Vehicle Model</td>
				<td><frm:input path="vehicleModel"  /></td>
			</tr>

			<tr>
				<td>Vehicle Number</td>
				<td><frm:input path="vehicleRegistrationNumber"  /></td>
			</tr>


			<tr>
				<td>Vehicle Registration Certificate</td>
				<td><input name="RegistrationCertificatePath" type="file"
					 /></td>
			</tr>

			<tr>
				<td>Vehicle Insurance Paper</td>
				<td><input name="InsurancePaperPath" type="file"  /></td>
			</tr>


			<tr>
				<td>Vehicle Fitness Certificate</td>
				<td><input name="FitnessCertificatePath" type="file"  /></td>
			</tr>


			<tr>
				<td>Vehicle Driver Driving License</td>
				<td><input name="DriverDrivingLicencePath" type="file"  /></td>
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
		</div>
	</frm:form>
</body>
</html>
