<%@page isELIgnored="false"%>
<%@taglib prefix="frm" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Transporters Vehicle Request</title>
</head>
<body>

	<a href="logout">logout</a>
	<a href='HomeCustomer'>Home</a>
	<br />
	<h1>List of Transporter Vehicle Request</h1>

	<form action="dealsFilter">
		<select name="filter">
			<option value="dealsFilterCity">dealsFilterCity</option>
			<option value="dealsFilterDate">dealsFilterDate</option>
			<option value="alldealsFilter">alldealsFilter</option>
		</select> <input type="submit" value="Submit">
	</form>



	<frm:form action="alldealsFilter">
		<label>From</label>
		<input type="text" name="fromCity" value="null" />
		<label>To</label>
		<input type="text" name="toCity" value="null" />
		<label>From Date</label>
		<input type="date" name="fromDate" value="0001-01-01" />

		<label>To Date</label>
		<input type="date" name="toDate" value="0001-01-01" />
		<input type="submit" value="Show"/>
	</frm:form>

	<table border="1">
		<tr>
			<th>startPointCityName</th>
			<th>endPointCityName</th>
			<th>startPointDate</th>
			<th>endPointDate</th>
			<th>startPointTime</th>
			<th>endPointTime</th>
			<th>dealPrice</th>
			<th>Query</th>
			<th>Book</th>

		</tr>

		<c:forEach items="${dealsListObj}" var="alldealObjVar">


			<c:url var="BookDealAndGRate" value="BookDeal">
				<c:param name="dealId" value="${alldealObjVar.dealId}"></c:param>
			</c:url>
			<c:url var="query" value="customerQueryPage">
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
				<td><a href="${query}">Ask</a></td>
				<td><a href="${BookDealAndGRate}">Book</a></td>
			</tr>

		</c:forEach>

	</table>

</body>
</html>
