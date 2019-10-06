<%@page isELIgnored="false"%>
<%@taglib prefix="frm" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Transporters Vehicle Request</title>
</head>
<body>

	<a href="logout">logout</a>
	<a href='HomeTransporter'>Home</a>
	<br />
	<h1>List of Transporter Vehicle Request</h1>

	<table border="1">
		<tr>
			<th>startPointCityName</th>
			<th>endPointCityName</th>
			<th>startPointDate</th>
			<th>endPointDate</th>
			<th>startPointTime</th>
			<th>endPointTime</th>
			<th>dealPrice</th>
			<th>Activation </th>
			<th>Action</th>
			<th>Update</th>
		</tr>

		<c:forEach items="${alldealObj}" var="alldealObjVar">

			<c:url var="activateLink" value="activateDeal">
				<c:param name="dealId" value="${alldealObjVar.dealId}"></c:param>
			</c:url>
			
			<c:url var="deactivateLink" value="deactivateDeal">
				<c:param name="dealId" value="${alldealObjVar.dealId}"></c:param>
			</c:url>
			
			<c:url var="updateDealLink" value="updateDeal">
				<c:param name="dealId" value="${alldealObjVar.dealId}"></c:param>
			</c:url>
			<tr>
				<td>${alldealObjVar.startPointCityName}</td>
				<td>${alldealObjVar.endPointCityName}</td>
				<td>${alldealObjVar.startPointDate}</td>
				<td>${alldealObjVar.endPointDate}</td>
				<td>${alldealObjVar.startPointTime}</td>
				<td>${alldealObjVar.endPointTime}</td>
				<td>${alldealObjVar.dealPrice}</td>
				<td>${alldealObjVar.dealActivation}</td>
				 <td><a href="${activateLink}">Activate</a> | <a
					href="${deactivateLink}">Deactivate</a></td>
				<td>
				<a href="${updateDealLink}">Update</a>
				</td>
			</tr>
		</c:forEach>

	</table>
</body>
</html>
