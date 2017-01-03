<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>

<title>��ٱ��� ��ȸ</title>

<jsp:include page="../top.jsp" />
<br />
<br />
<br />

<div class="container">
	<h2>My cart</h2>
	<core:forEach items="${requestScope.cart_list }" var="cartList" varStatus="index">
			<form action="MemberFrontController" method="post"
				style="margin-left: auto; margin-right: auto; margin: 2px; border: 1px solid black;">
				<input type="hidden" class="form-control" name="userRequest" value="RemoveMemberForAdminServlet">

				User Sequence
				<input class="form-control" name="userSeq" id="userSeq" value="${list.userSeq}" readonly />

				Admin Authority
				<input class="form-control" name="admin" id="admin" value="${list.admin}" readonly />

				Email
				<input class="form-control" name="deletedEmail" value="${list.email}" readonly />

				Username
				<input class="form-control" name="deletedUserName" value="${list.userName}" readonly />

				Address
				<input class="form-control" value="${list.address}" readonly />

				Phone Number
				<input class="form-control" value="${list.phoneNumber}" readonly />

				<button type="submit" class="btn btn-warning">����</button>
			</form>
	</core:forEach>
</div>
</body>
</html>