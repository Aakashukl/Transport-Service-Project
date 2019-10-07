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
	<h1>Query</h1>

	<table border="1">
		<tr>

			<th>customerQuery</th>
			<th>transporterResponce</th>

		</tr>

		<c:forEach items="${queryListObj}" var="queryListObjVar">

			<tr>
				<td>${queryListObjVar.customerQuery}</td>
				<td>${queryListObjVar.transporterResponce}</td>
			</tr>

		</c:forEach>

	</table>
	<frm:form action="saveCustomerQuery" modelAttribute="queryObj">
		<div hidden="true">
			<input name="transporterId" value="${transporterId}"/>
			<input name="dealId" value="${dealId}"/>
		</div>
		<table border="1">
			<tr>
				<th><frm:input type="text" path="customerQuery" /></th>
				<th><input type="submit" value="Submit" /></th>
			</tr>

		</table>
	</frm:form>
</body>
</html>
