
<%@ page language="java" contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 
<!DOCTYPE html>
<html>
<head> 
-->

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>���� �߻�</title>

</head>
<body>
	<jsp:include page="top.jsp" />
	<br />
	<br />
	<br />
	<h2 style="text-align: center">���� �߻� ����Ʈ ������</h2>
	<%
		request.getAttribute("error_message");
	%>

	<core:if test="${!empty requestScope.error_message }">
		<h3>${requestScope.error_message }</h3>
	</core:if>
</body>
</html>