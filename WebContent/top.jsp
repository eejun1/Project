<%@ page contentType="text/html; charset=euc-kr" language="java" session="true"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	response.setHeader("Cache-Control", "no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<%-- Cache에서 페이지를 로드하지 않고 항상 최신의 페이지를 보여주도록 하기 위해서 No-Cache 설정 --%>

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
						<a href="/Item/itemboard.jsp" >상품 페이지</a>
					</li>
					<li>
						<form action="/BoardFrontController" method="post">
							<input type="hidden" class="form-control" name="userRequest" value ="GetAllBoardServlet">
							<button type="submit" class="btn btn-default btn-lg">QnA 페이지</button>
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
					<!-- admin 전용 메뉴 (일반 사용자 안보임) -->
					<core:if test="${sessionScope.memberDTO.admin == '1'}">
						<li>
							<form action="/MemberFrontController" method="post">
								<input type="hidden" class="form-control" name="userRequest" value="getAllMemberInfoServlet">
								<button type="submit" class="btn btn-default btn-lg">전체회원정보조회</button>
							</form>
						</li>
					</core:if>

					<!-- 로그인 세션 없을때 -->
					<core:if test="${empty sessionScope.memberDTO }">
						<li>
							<a href="#" data-toggle="modal" data-target="#login-modal">LOGIN</a>
						</li>
					</core:if>

					<!-- 로그인 세션 있을때 -->
					<core:if test="${!empty sessionScope.memberDTO }">
						<li>
							<a href="#" data-toggle="modal" data-target="#myInfo-modal">Welcome, ${sessionScope.memberDTO.userName}</a>
						</li>
						<li>
							<form action="/MemberFrontController" method="post">
								<input type="hidden" class="form-control" name="userRequest" value="RemoveMemberServlet">
								<button type="submit" class="btn btn-danger btn-lg">탈퇴하기</button>
							</form>
						</li>
						<li>
							<form action="/MemberFrontController" method="post">
								<input type="hidden" class="form-control" name="userRequest" value="LogoutServlet">
								<button type="submit" class="btn btn-primary btn-lg">로그아웃</button>
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
					<h4 class="modal-title">로그인</h4>
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
								이메일 주소 기억하기 (쿠키에 정보가 기록됩니다)
							</label>
						</div>
						<div></div>
						<button type="submit" class="btn btn-primary btn-lg">로그인</button>
						<button type="button" class="btn btn-danger btn-lg" data-dismiss="modal">창 닫기</button>
					</form>
					<a href="#" data-toggle="modal" data-target="#signup-modal">회원 가입</a>
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
					<h4 class="modal-title">회원 가입</h4>
				</div>
				<div class="modal-body">
					<form action="/MemberFrontController" method="post" name="signUpModalForm" id="signUpModalForm">
						<input type="hidden" class="form-control" name="userRequest" value="RegisterMemberServlet">
						<div class="form-group">
							<input type="email" class="form-control" name="signUpEmail" id="signUpEmail" placeholder="이메일" required>
						</div>
						<div class="form-group">
							<input type="text" class="form-control" name="userName" id="username" placeholder="사용자 이름" required>
						</div>
						<div class="form-group">
							<input type="password" class="form-control" name="userpw" id="userpw" placeholder="비밀 번호" required>
						</div>
						<div class="form-group">
							<input type="text" class="form-control" name="phoneNumber" id="phoneNumber" placeholder="전화 번호" required>
						</div>
						추가 정보 (선택 사항)
						<div class="form-group">
							<input type="text" class="form-control" name="address" id="address" placeholder="주소">
						</div>

						<button type="submit" class="btn btn-primary btn-lg">회원 가입</button>
						<button type="button" class="btn btn-danger btn-lg" data-dismiss="modal">창 닫기</button>
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
					<h4 class="modal-title">내 정보 보기 / 수정</h4>
				</div>
				<div class="modal-body">
					<form action="/MemberFrontController" method="post" name="myInfoModalForm" id="myInfoModalForm">
						<input type="hidden" class="form-control" name="userRequest" value="ModifyMemberServlet">
						<div class="form-group">
							이메일
							<input type="text" class="form-control" name="modifyEmail" id="modifyEmail" placeholder="USERNAME"
								value="${sessionScope.memberDTO.email}" readonly />
						</div>
						<div class="form-group">
							사용자 이름
							<input type="text" class="form-control" name="userName" id="userName" placeholder="USERNAME"
								value="${sessionScope.memberDTO.userName}" readonly>
						</div>
						<div class="form-group">
							비밀번호
							<input type="password" class="form-control" name="userpw" id="userpw" placeholder="PASSWORD" required="required">
						</div>
						<div class="form-group">
							전화 번호
							<input type="text" class="form-control" name="phoneNumber" id="phoneNumber" placeholder="PHONE NUMBER" required="required"
								   value="${sessionScope.memberDTO.phoneNumber}">
						</div>
						<div class="form-group">
							주소(선택 사항)
							<input type="text" class="form-control" name="address" id="address" placeholder="ADDRESS"
								value="${sessionScope.memberDTO.address}">
						</div>
						<button type="submit" class="btn btn-primary btn-lg">수정하기</button>
						<button type="button" class="btn btn-danger btn-lg" data-dismiss="modal">창 닫기</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- jQuery -->
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<!-- 쿠키 관련 스크립트 -->
	<script type="text/javascript">
		$(document).ready(function() {

	  var email = getCookie("email"); // email로 저장된 쿠키 정보 가져오기
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