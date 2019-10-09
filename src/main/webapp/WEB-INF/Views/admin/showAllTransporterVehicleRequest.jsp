<%@page isELIgnored="false"%>
<%@taglib prefix="frm" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Transporters Vehicle Request</title>
</head>
<body>

	<form action="logout">
<div><a href="HomeAdmin">Home</a></div>
<div align="right"><button type="submit" value="submit">Logout</button></div>

<div align="center">
	<h2>...Admin...</h2>
	<h3>Vehicle Request</h3>
	<br />
	</div>
	</form>
	
	<table border="1" style="width:100%">
		<tr>
			<th>Vehicle Type</th>
			<th>Vehicle Name</th>
			<th>Vehicle Model</th>
			<th>Registration Certificate</th>
			<th>Insurance Paper</th>
			<th>Fitness Certificate</th>
			<th>Driver Driving Licence</th>
			<th>Approval</th>
			<th>Action</th>
		</tr>

		<c:forEach items="${vehicleListObj}" var="vehicleListObjVar">

			<c:url var="approveLink" value="approveRecordOfTransporterVehicle">
				<c:param name="vehicleId" value="${vehicleListObjVar.vehicleId}"></c:param>
			</c:url>
			<c:url var="deleteLink" value="deleteRecordOfTransporterVehicle">
				<c:param name="vehicleId" value="${vehicleListObjVar.vehicleId}"></c:param>
			</c:url>
			<tr>
				<td>${vehicleListObjVar.vehicleType}</td>
				<td>${vehicleListObjVar.vehicleName}</td>
				<td>${vehicleListObjVar.vehicleModel}</td>
				<td><a
					href="openFile?open=${vehicleListObjVar.vehicleRegistrationCertificatePath}"
					target=" ">Open Registration Certificate</a></td>
				<td><a
					href="openFile?open=${vehicleListObjVar.vehicleInsurancePaperPath}"
					target=" ">Open Insurance Paper</a></td>

				<td><a
					href="openFile?open=${vehicleListObjVar.vehicleFitnessCertificatePath}"
					target=" ">Open Fitness Certificate</a></td>

				<td><a
					href="openFile?open=${vehicleListObjVar.vehicleDriverDrivingLicencePath}"
					target=" ">Open Driver Driving Licence</a></td>
				<td>${vehicleListObjVar.vehicleApproval}</td>
				<td><a href="${approveLink}">Approve</a> | <a
					href="${deleteLink}">Reject</a></td>
			</tr>
		</c:forEach>

	</table>
</body>
</html>
