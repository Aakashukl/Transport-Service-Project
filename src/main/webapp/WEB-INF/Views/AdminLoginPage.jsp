<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="loginAdminProcess">
		<table>

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
	</form>
</body>
</html>