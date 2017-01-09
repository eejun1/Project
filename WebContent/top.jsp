<%@ page contentType="text/html; charset=euc-kr" language="java" session="true"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	response.setHeader("Cache-Control", "no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<%-- Cache���� �������� �ε����� �ʰ� �׻� �ֽ��� �������� �����ֵ��� �ϱ� ���ؼ� No-Cache ���� --%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>IT'S PARTY HARDY</title>

<!-- Bootstrap Core CSS -->
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
	<div class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#header-collapse">
					<span class="sr-only"> Toggle navigation </span>
					<span class="icon-bar"> </span>
					<span class="icon-bar"> </span>
					<span class="icon-bar"> </span>
				</button>

				<a class="navbar-brand" href="/index.jsp">Party Hardy</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="header-collapse">
				<ul class="nav navbar-nav">
					<li>
						<a href="/QnaBoard/qnaboard.jsp" > ABOUT US </a>
					</li>
					<li>
						<a href="/Item/itemboard.jsp" >��ǰ ������</a>
					</li>
					<li>
						<form action="/BoardFrontController" method="post">
							<input type="hidden" class="form-control" name="userRequest" value ="GetAllBoardServlet">
							<button type="submit" class="btn btn-default btn-lg">QnA ������</button>
						</form>
					</li>
					<li>
						<form action="/ItemFrontController" method="post">
							<input type="hidden" class="form-control" name="userRequest" value="GetAllItemInfoServlet">
							<button type="submit" class="btn btn-default btn-lg">SEARCH</button>
						</form>
					</li>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<!-- admin ���� �޴� (�Ϲ� ����� �Ⱥ���) -->
					<core:if test="${sessionScope.memberDTO.admin == '1'}">
						<li>
							<form action="/MemberFrontController" method="post">
								<input type="hidden" class="form-control" name="userRequest" value="getAllMemberInfoServlet">
								<button type="submit" class="btn btn-default btn-lg">��üȸ��������ȸ</button>
							</form>
						</li>
					</core:if>

					<!-- �α��� ���� ������ -->
					<core:if test="${empty sessionScope.memberDTO }">
						<li>
							<a href="#" data-toggle="modal" data-target="#login-modal">LOGIN</a>
						</li>
					</core:if>

					<!-- �α��� ���� ������ -->
					<core:if test="${!empty sessionScope.memberDTO }">
						<li>
							<a href="#" data-toggle="modal" data-target="#myInfo-modal">Welcome, ${sessionScope.memberDTO.userName}</a>
						</li>
						<li>
							<form action="/MemberFrontController" method="post">
								<input type="hidden" class="form-control" name="userRequest" value="RemoveMemberServlet">
								<button type="submit" class="btn btn-danger btn-lg">Ż���ϱ�</button>
							</form>
						</li>
						<li>
							<form action="/MemberFrontController" method="post">
								<input type="hidden" class="form-control" name="userRequest" value="LogoutServlet">
								<button type="submit" class="btn btn-primary btn-lg">�α׾ƿ�</button>
							</form>
						</li>
					</core:if>
				</ul>
			</div>
		</div>
	</div>

	<!-- login-modal -->
	<div id="login-modal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">�α���</h4>
				</div>
				<div class="modal-body">
					<form action="/MemberFrontController" method="post" name="loginModalForm" id="loginModalForm">
						<input type="hidden" class="form-control" name="userRequest" value="LoginServlet">
						<div class="form-group">
							<input type="email" class="form-control" name="email" id="email" placeholder="EMAIL" required>
						</div>
						<div class="form-group">
							<input type="password" class="form-control" name="userpw" id="userpw" placeholder="PASSWORD" required>
						</div>
						<div class="form-group">
							<label>
								<input type="checkbox" id="idSaveCheck">
								�̸��� �ּ� ����ϱ� (��Ű�� ������ ��ϵ˴ϴ�)
							</label>
						</div>
						<div></div>
						<button type="submit" class="btn btn-primary btn-lg">�α���</button>
						<button type="button" class="btn btn-danger btn-lg" data-dismiss="modal">â �ݱ�</button>
					</form>
					<a href="#" data-toggle="modal" data-target="#signup-modal">ȸ�� ����</a>
				</div>
			</div>
		</div>
	</div>

	<!-- sign up modal -->
	<div id="signup-modal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">ȸ�� ����</h4>
				</div>
				<div class="modal-body">
					<form action="/MemberFrontController" method="post" name="signUpModalForm" id="signUpModalForm">
						<input type="hidden" class="form-control" name="userRequest" value="RegisterMemberServlet">
						<div class="form-group">
							<input type="email" class="form-control" name="signUpEmail" id="signUpEmail" placeholder="�̸���" required>
						</div>
						<div class="form-group">
							<input type="text" class="form-control" name="userName" id="username" placeholder="����� �̸�" required>
						</div>
						<div class="form-group">
							<input type="password" class="form-control" name="userpw" id="userpw" placeholder="��� ��ȣ" required>
						</div>
						<div class="form-group">
							<input type="text" class="form-control" name="phoneNumber" id="phoneNumber" placeholder="��ȭ ��ȣ" required>
						</div>
						�߰� ���� (���� ����)
						<div class="form-group">
							<input type="text" class="form-control" name="address" id="address" placeholder="�ּ�">
						</div>

						<button type="submit" class="btn btn-primary btn-lg">ȸ�� ����</button>
						<button type="button" class="btn btn-danger btn-lg" data-dismiss="modal">â �ݱ�</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- myInfo modal -->
	<div id="myInfo-modal" class="modal fade" role="dialog">
		<div class="modal-dialog modal-sm">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">�� ���� ���� / ����</h4>
				</div>
				<div class="modal-body">
					<form action="/MemberFrontController" method="post" name="myInfoModalForm" id="myInfoModalForm">
						<input type="hidden" class="form-control" name="userRequest" value="ModifyMemberServlet">
						<div class="form-group">
							�̸���
							<input type="text" class="form-control" name="modifyEmail" id="modifyEmail" placeholder="USERNAME"
								value="${sessionScope.memberDTO.email}" readonly />
						</div>
						<div class="form-group">
							����� �̸�
							<input type="text" class="form-control" name="userName" id="userName" placeholder="USERNAME"
								value="${sessionScope.memberDTO.userName}" readonly>
						</div>
						<div class="form-group">
							��й�ȣ
							<input type="password" class="form-control" name="userpw" id="userpw" placeholder="PASSWORD" required="required">
						</div>
						<div class="form-group">
							��ȭ ��ȣ
							<input type="text" class="form-control" name="phoneNumber" id="phoneNumber" placeholder="PHONE NUMBER" required="required"
								   value="${sessionScope.memberDTO.phoneNumber}">
						</div>
						<div class="form-group">
							�ּ�(���� ����)
							<input type="text" class="form-control" name="address" id="address" placeholder="ADDRESS"
								value="${sessionScope.memberDTO.address}">
						</div>
						<button type="submit" class="btn btn-primary btn-lg">�����ϱ�</button>
						<button type="button" class="btn btn-danger btn-lg" data-dismiss="modal">â �ݱ�</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- jQuery -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<!-- ��Ű ���� ��ũ��Ʈ -->
	<script type="text/javascript">
		$(document).ready(function() {

	  var email = getCookie("email"); // email�� ����� ��Ű ���� ��������
	  $("input[name='email']").val(email);

	  if ($("input[name='email']").val() != "") {
		  $("#idSaveCheck").attr("checked", true);
	  }

	  $("#idSaveCheck").change(function() {
		  if ($("#idSaveCheck").is(":checked")) {
			  var email = $("input[name='email']").val();
			  setCookie("email", email, 7);
		  } else {
			  deleteCookie("email");
		  }
	  });

	  $("input[name='email']").keyup(function() {
		  if ($("#idSaveCheck").is(":checked")) {
			  var email = $("input[name='email']").val();
			  setCookie("email", email, 7);
		  }
	  });
  });

  function setCookie(cookieName, value, expDays) {
	  var expDate = new Date();
	  expDate.setDate(expDate.getDate() + expDays);
	  var cookieValue = escape(value)
	    + ((expDays == null) ? "" : "; expires=" + expDate.toGMTString());
	  document.cookie = cookieName + "=" + cookieValue;
  }

  function deleteCookie(cookieName) {
	  var expDate = new Date();
	  expDate.setDate(expDate.getDate() - 1);
	  document.cookie = cookieName + "= " + "; expires=" + expDate.toGMTString();
  }

  function getCookie(cookieName) {
	  cookieName = cookieName + "=";
	  var cookieData = document.cookie;
	  var start = cookieData.indexOf(cookieName);
	  var cookieValue = '';

	  if (start != -1) {
		  start += cookieName.length;
		  var end = cookieData.indexOf(';', start);
		  if (end == -1) {
			  end = cookieData.length;
		  }
		  cookieValue = cookieData.substring(start, end);
	  }

	  return unescape(cookieValue);


  }
	</script>