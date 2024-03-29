<%@page isELIgnored="false"%>
<%@taglib prefix="frm" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Deals of Transporter</title>
</head>
<body>

	<form action="logout">
<div><a href="HomeAdmin">Home</a></div>
<div align="right"><button type="submit" value="submit">Logout</button></div>

<div align="center">
	<h2>...Admin...</h2>
	<h3>List of All Deals</h3>
	<br />
	</div>
	</form>

	<table border="1" style="width:100%">
		<tr>
			<th>startPointCityName</th>
			<th>endPointCityName</th>
			<th>startPointDate</th>
			<th>endPointDate</th>
			<th>startPointTime</th>
			<th>endPointTime</th>
			<th>dealPrice</th>
			<th>dealReview</th>
			<th>Number of Booking</th>
			<th>Reason For Deletion</th>
			<th>Delete</th>

		</tr>

		<c:forEach items="${transporterDealsList}" var="transporterDealsListVar">
		<frm:form action="deleteDeal">
			<div hidden="true">
				<input name="dealId" value="${transporterDealsListVar.dealId}"/>
				<input name="transporterId" value="${transporterDealsListVar.transporter.transporterId}"/>
			</div>
			<%-- <c:url var="DeleteDeal" value="deleteDeal">
				<c:param name="dealId" value="${transporterDealsListVar.dealId}"></c:param>
				<c:param name="transporterId" value="${transporterDealsListVar.transporter.transporterId}"></c:param>
				 <c:param name="reason" value="<%=res %>"></c:param>
			</c:url> --%>
			<tr>
				<td>${transporterDealsListVar.startPointCityName}</td>
				<td>${transporterDealsListVar.endPointCityName}</td>
				<td>${transporterDealsListVar.startPointDate}</td>
				<td>${transporterDealsListVar.endPointDate}</td>
				<td>${transporterDealsListVar.startPointTime}</td>
				<td>${transporterDealsListVar.endPointTime}</td>
				<td>${transporterDealsListVar.dealPrice}</td>
				<td>${transporterDealsListVar.dealReview}</td>
				<td>${transporterDealsListVar.numberOfBooking}</td>
				<td><input  type="text" name="reason" /></td>
				
				<td>
				<input type ="submit" value= "Delete"/>
				</td>
			</tr>
		</frm:form>
		</c:forEach>

	</table>
</body>
</html>
