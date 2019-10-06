<%@page isELIgnored="false"%>
<%@taglib prefix="frm" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<html>
<head>
</head>
<body>

	<a href="logout">logout</a>
	<a href='HomeTransporter'>Home</a>
	<br />
	<h3>Update-Deal-Form</h3>
	<frm:form action="savePostedDeal" modelAttribute="dealsObj">
		<table border="1" style="width:50%" >
			<frm:hidden path="dealId" />


			<%-- <tr>
				<td>Vehicle Name</td>
				<td><frm:select path="vehicle" items="${vehicleName} }" /></td>
			</tr>
			
			<tr>
				<td>Driver Name</td>
				<td><frm:select path="vehicle" items="${vehicleDriverName} }" /></td>
			</tr>
			
			<tr>
				<td>Vehicle Number</td>
				<td><frm:select path="vehicle" items="${vehicleNumber} }" /></td>
			</tr> --%>

			<tr>
				<td>Vehicle ID</td>
				<td>
				<select name="vehicleIdd">
						<c:forEach items="${vehicleID}" var="id">
							<option value="${id.vehicleId}">${id.vehicleId}
								${id.vehicleType} ${id.vehicleName} ${id.vehicleModel}
								${id.vehicleRegistrationNumber}</option>
						</c:forEach>

				</select>
				Previous Selected Id => ${dealsObj.vehicle.vehicleId}
				<td>
			</tr>

			<tr>
				<td>startPointCityName</td>
				<td><frm:input path="startPointCityName" required="true" /></td>
			</tr>


			<tr>
				<td>endPointCityName</td>
				<td><frm:input path="endPointCityName" required="true" /></td>
			</tr>


			<tr>
				<td>startPointDate</td>
				<td><frm:input path="startPointDate" type="date" />
				<td>
			</tr>

			<tr>
				<td>endPointDate</td>
				<td><frm:input path="endPointDate" type="date" /></td>
			</tr>

			<tr>
				<td>startPointTime</td>
				<td><frm:input path="startPointTime" type="time" />
				<td>
			</tr>

			<tr>
				<td>endPointTime</td>
				<td><frm:input path="endPointTime" type="time" /></td>
			</tr>


			<tr>
				<td>dealPrice</td>
				<td><frm:input path="dealPrice" required="true" /></td>
			</tr>

			<tr>
				<td></td>
				<td><input type="submit" value="Save" /><input type="reset"
					value="Reset" /></td>
			</tr>



		</table>
	</frm:form>

</body>
</html>
