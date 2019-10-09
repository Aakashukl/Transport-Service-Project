<%@page isELIgnored="false"%>
<%@taglib prefix="frm" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Login Page</title>
</head>
<body>

	<frm:form action="loginCustomerProcess" modelAttribute="customerObj"
		method="POST">
		<div align="right">
			<a href="index.jsp">Home</a>
		</div>

		<div align="center">
			<h3>Customer Login</h3>
			<table border="1">

				<tr>
					<td>Username</td>
					<td><frm:input path="customerUsername" required="true" /></td>
				</tr>


				<tr>
					<td>Password</td>
					<td><frm:input path="customerPassword" type="password"
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