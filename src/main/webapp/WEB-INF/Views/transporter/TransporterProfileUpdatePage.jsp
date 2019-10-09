<%@page isELIgnored="false"%>
<%@taglib prefix="frm" uri="http://www.springframework.org/tags/form"%>


<html>
<head>
</head>
<body>
	<form action="logout">
		<div align="right">
			<button type="submit" value="submit">Logout</button>
			<div align="left">
				<a href='HomeTransporter'>Home</a>
			</div>

		</div>
	</form>
	<frm:form action="updateTranspoterProcess"
		modelAttribute="transporterObj" enctype="multipart/form-data"
		method="POST">
		<div align="center">
			<h3>Profile Update</h3>
			<table border="1">
				<frm:hidden path="transporterId" />

				<tr>
					<td>Username</td>
					<td><frm:input path="transporterUsername" />
						<frm:errors path="transporterUsername" /></td>
				</tr>


				<tr>
					<td>Password</td>
					<td><frm:input path="transporterPassword" /> <frm:errors
							path="transporterPassword" /></td>
				</tr>


				<tr>
					<td>Transport Name</td>
					<td><frm:input path="transportName" /> <frm:errors
							path="transporterName" /></td>
				</tr>

				<tr>
					<td>Transporter Name</td>
					<td><frm:input path="transporterName" />  <frm:errors
						path="transportererName" /></td>
				</tr>

				<tr>
					<td>Mobile Number</td>
					<td><frm:input path="transporterMobile" /> <frm:errors
							path="transporterMobile" /></td>
				</tr>

				<tr>
					<td>Email</td>
					<td><frm:input path="transporterEmail" /> <frm:errors
							path="transporterEmail" /></td>
				</tr>

				<tr>
					<td>PAN Card Upload</td>
					<td><input name="PANPath" type="file" /></td>
				</tr>


				<tr>
					<td>GST Number</td>
					<td><frm:input path="transporterGSTNo" /> <frm:errors path="transporterGSTNo" /></td>
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
