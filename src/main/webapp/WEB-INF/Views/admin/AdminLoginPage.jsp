<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="loginAdminProcess" method="post">
		<div align="right">
			<a href="index.jsp">Home</a>
		</div>

		<div align="center">
			<h3>Admin Login</h3>
			<table border="1">

				<tr>
					<td>Username</td>
					<td><input name="AdminUsername" required /></td>
				</tr>


				<tr>
					<td>Password</td>
					<td><input name="AdminPassword" type="password" required /></td>
				</tr>

				<tr>
					<td></td>
					<td><input type="submit" value="Login" /><input type="reset"
						value="Reset" /></td>
				</tr>

			</table>
		</div>
	</form>
</body>
</html>