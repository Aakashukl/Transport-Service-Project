<%@page isELIgnored="false"%>
<%@taglib prefix="frm" uri="http://www.springframework.org/tags/form"%>


<html>
<head>
</head>
<body>
	<h3>Customer-Entry-Form</h3>
	<frm:form action="saveCustomerProcess" modelAttribute="customerObj">
		<table border="1">
			<frm:hidden path="customerId" />
			
			<tr>
				<td>Username</td>
				<td><frm:input path="customerUsername" required="true" /></td>
			</tr>
			
			
			<tr>
				<td>Password</td>
				<td><frm:input path="customerPassword" type = "password" required="true" /></td>
			</tr>
			
			
			<tr>
				<td>Full Name</td>
				<td><frm:input path="customerName" /><%--  <frm:errors
						path="customerName" /> --%></td>
			</tr>

			<tr>
				<td>Mobile Number</td>
				<td><frm:input path="customerMobileNumber" type="number" /> <%-- <frm:errors path="customerMobileNumber" /> --%></td>
			</tr>

			<tr>
				<td>Email</td>
				<td><frm:input path="cutomerEmail" /> <frm:errors
						path="cutomerEmail" /></td>
			</tr>

			<tr>
				<td>Gender</td>
				<td><frm:radiobutton path="customerGender" value="Male"
						checked="checked" />Male <frm:radiobutton path="customerGender"
						value="Female" />Female</td>
			</tr>

			<tr>
				<td>Pin Code</td>
				<td><frm:input path="customerPincode" type="number" /> <%-- <frm:errors path="customerPincode" /> --%></td>
			</tr>

			<tr>
				<td>Address</td>
				<td><frm:input path="customerAddress" required="true" /></td>
			</tr>


			<tr>
				<td></td>
				<td><input type="submit" value="Save" /><input type="reset"
					value="Reset" /></td>
			</tr>



		</table>
	</frm:form>

</body>
</html>
