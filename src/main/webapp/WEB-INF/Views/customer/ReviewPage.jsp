<%@page isELIgnored="false"%>
<%@taglib prefix="frm" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="logout">
		<div align="right">
			<button type="submit" value="submit">Logout</button>
		</div>
		<div align="left">
			<a href='HomeCustomer'>Home</a>
		</div>
	</form>
	<br />

	<h1>Rate Transporter Deal</h1>
	<frm:form action="saveReviewGivenByCustomer" modelAttribute="dealObj">
		<table border="1">
			<tr>
				<th>startPointCityName</th>
				<th>endPointCityName</th>
				<th>startPointDate</th>
				<th>endPointDate</th>
				<th>startPointTime</th>
				<th>endPointTime</th>
				<th>dealPrice</th>
			</tr>

			<tr>
				<td>${dealObj.startPointCityName}</td>
				<td>${dealObj.endPointCityName}</td>
				<td>${dealObj.startPointDate}</td>
				<td>${dealObj.endPointDate}</td>
				<td>${dealObj.startPointTime}</td>
				<td>${dealObj.endPointTime}</td>
				<td>${dealObj.dealPrice}</td>
			</tr>


		</table>


		<p>1 is good, 10 is bad (i.e., "How much pain are you in right
			now?")</p>
		<frm:hidden path="dealId" value="${dealObj.dealId}" />	
		<input name="ratingValue" type="number" />
		<input type="submit" value="submit"/>





	</frm:form>
</body>
</html>