<%@page import="member.dto.MemberDTO"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<!--
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
-->
<title>로그인 성공</title>
<!-- 
</head>
<body>
-->
<jsp:include page="../top.jsp" />
<br />
<br />
<br />

<div class="container">
	<h2>로그인 성공 페이지</h2>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>EMAIL</th>
				<th>USERNAME</th>
				<th>ADDRESS</th>
				<th>PHONENUMBER</th>
				<th>Userseq</th>
				<th>admin</th>
				<th>password</th>
			</tr>
		</thead>
		<tbody>
			<tr>

				<td>${sessionScope.memberDTO.email}</td>

				<td>${sessionScope.memberDTO.userName}</td>

				<td>${sessionScope.memberDTO.address}</td>

				<td>${sessionScope.memberDTO.phoneNumber}</td>

				<td>${sessionScope.memberDTO.userSeq}</td>

				<td>${sessionScope.memberDTO.admin}</td>

				<td>${sessionScope.memberDTO.userpw}</td>
			</tr>
			<tr>
		</tbody>
	</table>
</div>
</body>
</html>