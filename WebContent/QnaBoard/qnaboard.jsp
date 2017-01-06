<%@ page contentType="text/html; charset=euc-kr" language="java"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../top.jsp" />
<div class="container">
	<h1>QnA Board</h1>



	<div class="panel panel-default">
		<div class="row">
			<div class="col-xs-12">
				<div class="panel panel-info">
					<div class="panel-heading">
						<a href="#"> Title </a>
					</div>
				</div>

				<div class="row">
					<div class="col-xs-4">
						<div class="panel panel-default">
							<div class="panel-body">Username</div>
						</div>
					</div>
					<div class="col-xs-4">
						<div class="panel panel-default">
							<div class="panel-body">Date</div>
						</div>
					</div>
					<div class="col-xs-4">
						<div class="panel panel-default">
							<div class="panel-body">Hits</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="dropup">
		<button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">MENU</button>
		<ul class="dropdown-menu">
		<core:if test="${!empty sessionScope.memberDTO }">
			<li>
				<a href="/QnaBoard/qnawrite.jsp">글쓰기</a>
			</li>
		</core:if>
			<li>
				<a href="#">버튼2</a>
			</li>
			<li>
				<a href="#">버튼3</a>
			</li>
		</ul>
	</div>

</div>

<script>
	$(document).ready(function() {
		$('[data-toggle="popover"]').popover();
	});
</script>
</body>
</html>
