<%@page import="member.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<!--
<!DOCTYPE html>
<html>
<head>
-->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ȸ������ ���� ����</title>
<!--
</head>
<body>
-->
<jsp:include page="../top.jsp" />


<br />
<br />
<br />
<div class="container">
	<h2>���� �Ϸ� , ���� Ȯ�� ������</h2>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>EMAIL</th>
				<th>USERNAME</th>
				<th>ADDRESS</th>
				<th>PHONENUMBER</th>
			</tr>
		</thead>
		<tbody>
			<tr>

				<td>${sessionScope.memberDTO.email}</td>

				<td>${sessionScope.memberDTO.userName}</td>

				<td>${sessionScope.memberDTO.address}</td>

				<td>${sessionScope.memberDTO.phoneNumber}</td>
			</tr>
			<tr>
		</tbody>
	</table>
</div>


</body>
</html>