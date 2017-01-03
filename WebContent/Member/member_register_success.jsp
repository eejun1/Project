<%@ page language="java" contentType="text/html; charset=euc-kr"%>
<%@ page import="member.dto.MemberDTO"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<!--
<!DOCTYPE html>
<html>
<head>
-->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 가입 성공</title>
<!--
</head>
<body>
-->
<jsp:include page="../top.jsp" />
<br />
<br />
<br />
<div class="container">
	<h2>회원 가입 정보</h2>
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
				<td>${sessionScope.memberDTO.email }</td>

				<td>${sessionScope.memberDTO.userName }</td>

				<td>
					<core:if test="${empty sessionScope.memberDTO.address }">
						입력 정보 없음
					</core:if>
					<core:if test="${!empty sessionScope.memberDTO.address }">
						${sessionScope.memberDTO.address }
					</core:if>
				</td>

				<td>
					<core:if test="${empty sessionScope.memberDTO.phoneNumber }">
						입력 정보 없음
					</core:if>
					<core:if test="${!empty sessionScope.memberDTO.phoneNumber }">
						${sessionScope.memberDTO.phoneNumber }
					</core:if>
				</td>
			</tr>
			<tr>
		</tbody>
	</table>
</div>
</body>
</html>