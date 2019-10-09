<%@page isELIgnored="false"%>
<%@taglib prefix="frm" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

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
	<frm:form action="showVehicleDetailForUpdation" modelAttribute="vehicleListObj">
		
		<div align="center">
		<h1>Select Vehicle</h1>
		<table border="1" style="width:50%" >
			<tr>
				<td>Select Vehicle</td>
				<td>
				<select name="vehicleIdd">
						<c:forEach items="${vehicleListObj}" var="id">
							<option value="${id.vehicleId}">${id.vehicleId}
								${id.vehicleType} ${id.vehicleName} ${id.vehicleModel}
								${id.vehicleRegistrationNumber}</option>
						</c:forEach>

				</select>
				</td>
				<td><input type="submit" value="submit"></td>
			</tr>
		</table>
		</div>
	</frm:form>
</body>
</html>
