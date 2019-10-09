<%@page isELIgnored="false"%>
<%@taglib prefix="frm" uri="http://www.springframework.org/tags/form"%>


<html>
<head>
</head>
<body>
	<div align="right"><a href="index.jsp">Home</a></div>
	<frm:form action="saveTranspoterProcess"
		modelAttribute="transporterObj" enctype="multipart/form-data" method="POST">
		<div align="center">
		<h3>Transporter-Entry-Form</h3>
		<table border="1">
			<frm:hidden path="transporterId" />

			<tr>
				<td>Username</td>
				<td><frm:input path="transporterUsername" required="true" /></td>
			</tr>


			<tr>
				<td>Password</td>
				<td><frm:input path="transporterPassword" required="true" /></td>
			</tr>


			<tr>
				<td>Transport Name</td>
				<td><frm:input path="transportName" />
					<%--  <frm:errors
						path="transporterName" /> --%></td>
			</tr>

			<tr>
				<td>Transporter Name</td>
				<td><frm:input path="transporterName" />
					<%--  <frm:errors
						path="transportererName" /> --%></td>
			</tr>

			<tr>
				<td>Mobile Number</td>
				<td><frm:input path="transporterMobile" type="number" /></td>
			</tr>

			<tr>
				<td>Email</td>
				<td><frm:input path="transporterEmail" /> <frm:errors
						path="transporterEmail" /></td>
			</tr>

			<tr>
				<td>PAN Card Upload</td>
				<td><input name="PANPath" type="file" required /></td>
			</tr>
			

			<tr>
				<td>GST Number</td>
				<td><frm:input path="transporterGSTNo" required="true" /></td>
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
