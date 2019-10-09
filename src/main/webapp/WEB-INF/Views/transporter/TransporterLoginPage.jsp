<%@page isELIgnored="false"%>
<%@taglib prefix="frm" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Transporter Login Page</title>
</head>
<body>
	<frm:form action="loginTranspoterProcess"
		modelAttribute="transporterObj" method="POST">
		<div align="right">
			<a href="index.jsp">Home</a>
		</div>

		<div align="center">
			<h3>Transport Login</h3>
			<table border="1">

				<tr>
					<td>Username</td>
					<td><frm:input path="transporterUsername" required="true" /></td>
				</tr>


				<tr>
					<td>Password</td>
					<td><frm:input path="transporterPassword" type="password"
							required="true" /></td>
				</tr>


				<tr>
					<td></td>
					<td><input type="submit" value="Login" /><input type="reset"
						value="Reset" /></td>
				</tr>

			</table>
		</div>
	</frm:form>
</body>
</html>