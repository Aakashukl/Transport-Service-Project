<%@page isELIgnored="false"%>
<%@taglib prefix="frm" uri="http://www.springframework.org/tags/form"%>


<html>
<head>
</head>
<body>
	<div align="right">
		<a href="index.jsp">Home</a>
	</div>
	<frm:form action="saveTranspoterProcess"
		modelAttribute="transporterObj" enctype="multipart/form-data"
		method="POST">
		<div align="center">
			<h3>Transporter-Entry-Form</h3>
			<table border="1">
				<frm:hidden path="transporterId" />

				<tr>
					<td>Username</td>
					<td><frm:input path="transporterUsername" /><frm:errors
						path="transporterUsername" /></td>
				</tr>


				<tr>
					<td>Password</td>
					<td><frm:input path="transporterPassword" /><%-- <frm:errors
						path="transporterPassword" /> --%></td>
				</tr>


				<tr>
					<td>Transport Name</td>
					<td><frm:input path="transportName" />   <frm:errors
						path="transportName" /> </td>
				</tr>

				<tr>
					<td>Transporter Name</td>
					<td><frm:input path="transporterName" /> <frm:errors
							path="transporterName" /></td>
				</tr>

				<tr>
					<td>Mobile Number</td>
					<td><frm:input path="transporterMobile" type="number" /><frm:errors
						path="transporterMobile" /></td>
				</tr>

				<tr>
					<td>Email</td>
					<td><frm:input path="transporterEmail" /> <frm:errors
							path="transporterEmail" /></td>
				</tr>

				<tr>
					<td>PAN Card Upload</td>
					<td><input name="PANPath" accept="application/pdf" type="file" required /></td>
				</tr>


				<tr>
					<td>GST Number</td>
					<td><frm:input path="transporterGSTNo" /><%-- <frm:errors
						path="transporterGSTNo" /> --%></td>
				</tr>


				<tr>
					<td></td>
					<td><input type="submit" value="Save" /><input type="reset"
						value="Reset" /></td>
				</tr>



			</table>
		</div>
	</frm:form>
</body>
</html>
