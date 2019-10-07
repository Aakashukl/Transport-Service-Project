<%@page isELIgnored="false"%>
<%@taglib prefix="frm" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Transporter Request</title>
</head>
<body>
	<h1>List of All Transporter</h1>

	<a href="logout">logout</a>
	<a href='HomeAdmin'>Home</a>
	<br />

	<table border="1">
		<tr>
			<th>Transporter Username</th>
			<th>Transport Name</th>
			<th>Transporter Name</th>
			<th>Transporter Email</th>
			<th>Transporter Mobile</th>
			<th>Open Transporter PAN Card</th>
			<th>Transporter GST Number</th>
			<th> Rating </th>
			<th>transporter Delete</th>
			<th>Deals</th>
		</tr>

		<c:forEach items="${allTransporterObj}" var="transporterListObj">

			
			<c:url var="deleteLink" value="deleteRecordOfTransporter">
				<c:param name="transporterId"
					value="${transporterListObj.transporterId}"></c:param>
			</c:url>
			<c:url var="dealslink" value="dealsOfTransporter">
				<c:param name="transporterId"
					value="${transporterListObj.transporterId}"></c:param>
			</c:url>
			<tr>
				<td>${transporterListObj.transporterUsername}</td>
				<td>${transporterListObj.transportName}</td>
				<td>${transporterListObj.transporterName}</td>
				<td>${transporterListObj.transporterEmail}</td>
				<td>${transporterListObj.transporterMobile}</td>
				<td><a
					href="openFile?open=${transporterListObj.transporterPANPath}"
					target=" ">Open Pan Card</a></td>
				<td>${transporterListObj.transporterGSTNo}</td>
				<td>${transporterListObj.transporterRating}</td>
				<td><a href="${deleteLink}">Reject</a></td>
				<td><a href="${dealslink}">Deals</a></td>

			</tr>
		</c:forEach>

	</table>
</body>
</html>
