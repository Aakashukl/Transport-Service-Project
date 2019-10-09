<%@page isELIgnored="false"%>
<%@taglib prefix="frm" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Transporters Vehicle Request</title>
</head>
<body>

				<form action="logout">
		<div align="right">
			<button type="submit" value="submit">Logout</button>
			<div align="left"><a href='HomeTransporter'>Home</a></div>
	
	</div>
	</form>
	<div align="center">
	<h3>Query</h3>
	<table border="1">
		<tr>
			<th>Customer ID</th>
			<th>Deal ID</th>
			<th>Start City</th>
			<th>End City</th>
			<th>Date</th>
			<th>customerQuery</th>
			<th>transporterResponce</th>
			<th>Response</th>

		</tr>

		<c:forEach items="${listOfAllCustomerQuery}" var="queryListObjVar">
			<frm:form action="saveTransporterResponse">
			<div hidden="true">
				<input name="queryId" value="${queryListObjVar.queryId}"/>
			</div>
				<tr>
					<td>${queryListObjVar.customer.customerId}</td>
					<td>${queryListObjVar.deals.dealId}</td>
					<td>${queryListObjVar.deals.startPointCityName}</td>
					<td>${queryListObjVar.deals.endPointCityName}</td>
					<td>${queryListObjVar.deals.startPointDate}</td>
					<td>${queryListObjVar.customerQuery}</td>
					<td>${queryListObjVar.transporterResponce}</td>
					<td><input type="text" name="transporterResponce" /></td>
					<td><input type="submit" value="submit"></td>
				</tr>
			</frm:form>
		</c:forEach>


	</table>
	</div>
</body>
</html>
