<%@ page language="java" contentType="text/html; charset=euc-kr"%>
<!--
<!DOCTYPE html>
<html>
<head>
-->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 탈퇴 처리 성공</title>

<!--
</head>
<body>
-->
<jsp:include page="../top.jsp" />


<br />
<br />
<br />
<div class="container">
	<h2>회원 탈퇴 성공 리디렉트 페이지</h2>

	<%
		if (session.getAttribute("deletedEmail") != null && session.getAttribute("deletedUserName") != null) {
	%>
	<h2>탈퇴 회원 정보</h2>
	<h3>
		EMAIL :
		<%=session.getAttribute("deletedEmail")%>
	</h3>
	<h3>
		USERNAME :
		<%=session.getAttribute("deletedUserName")%></h3>
	<%
		session.removeAttribute("deletedUser");
			session.removeAttribute("deletedUserName");
		}
	%>

</div>
</body>
</html>