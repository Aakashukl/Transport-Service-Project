<%@page isELIgnored="false"%>
<%@taglib prefix="frm" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Transporter Request</title>
</head>
<body>
	<h1>List of Transporter Request</h1>

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
			<th>transporter Approval</th>
		</tr>

		<c:forEach items="${transporterListObj}" var="transporterListObj">

			<c:url var="approveLink" value="approveRecordOfTransporter">
				<c:param name="transporterId"
					value="${transporterListObj.transporterId}"></c:param>
			</c:url>
			<c:url var="deleteLink" value="deleteRecordOfTransporter">
				<c:param name="transporterId"
					value="${transporterListObj.transporterId}"></c:param>
			</c:url>
			<tr>
				<td>${transporterListObj.transporterUsername}</td>
				<td>${transporterListObj.transportName}</td>
				<td>${transporterListObj.transporterName}</td>
				<td>${transporterListObj.transporterEmail}</td>
				<td>${transporterListObj.transporterMobile}</td>
				<%-- <td><a href="${transporterListObj.transporterPANPath}">Open Pan Card</a></td> --%>

				<td><a
					href="openFile?open=${transporterListObj.transporterPANPath}"
					target=" ">Open Pan Card</a></td>
				<td>${transporterListObj.transporterGSTNo}</td>
				<td>${transporterListObj.transporterValidation}</td>
				<td><a href="${approveLink}">Approve</a> | <a
					href="${deleteLink}">Reject</a></td>

			</tr>
		</c:forEach>

	</table>
</body>
</html>
