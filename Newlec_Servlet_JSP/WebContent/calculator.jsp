<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
input {
	width:50px;
	height:50px;
}
.output{
	height: 50px;
	background: #e9e9e9;
	font-size: 24px;
	font-weight: bold;
	text-align: right;
	padding: 0px 5px;
}
</style>
</head>
<body>
	<form method="post" action="calculator">
		<table>
			<tr>
				<td class="output" colspan="4">${1+4}</td>
			</tr>
			<tr>
				<td><input name="operator" value="CE" type="submit"/></td>
				<td><input name="operator" value="C" type="submit"/></td>
				<td><input name="operator" value="BACK" type="submit"/></td>
				<td><input name="operator" value="/" type="submit"/></td>
			</tr>
			<tr>
				<td><input name="value" value="7" type="submit"/></td>
				<td><input name="value" value="8" type="submit"/></td>
				<td><input name="value" value="9" type="submit"/></td>
				<td><input name="operator" value="*" type="submit"/></td>
			</tr>
			<tr>
				<td><input name="value" value="4" type="submit"/></td>
				<td><input name="value" value="5" type="submit"/></td>
				<td><input name="value" value="6" type="submit"/></td>
				<td><input name="operator" value="-" type="submit"/></td>
			</tr>
			<tr>
				<td><input name="value" value="1" type="submit"/></td>
				<td><input name="value" value="2" type="submit"/></td>
				<td><input name="value" value="3" type="submit"/></td>
				<td><input name="operator" value="+" type="submit"/></td>
			</tr>
			<tr>
				<td></td>
				<td><input name="value" value="0" type="submit"/></td>
				<td><input name="dot" value="." type="submit"/></td>
				<td><input name="operator" value="=" type="submit"/></td>
			</tr>
		</table>
	</form>
</body>
</html>