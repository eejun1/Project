<%@ page language="java" contentType="text/html; charset=euc-kr"%>
<!--
<!DOCTYPE html>
<html>
<head>
-->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ȸ�� Ż�� ó�� ����</title>

<!--
</head>
<body>
-->
<jsp:include page="../top.jsp" />


<br />
<br />
<br />
<div class="container">
	<h2>ȸ�� Ż�� ���� ����Ʈ ������</h2>

	<%
		if (session.getAttribute("deletedEmail") != null && session.getAttribute("deletedUserName") != null) {
	%>
	<h2>Ż�� ȸ�� ����</h2>
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