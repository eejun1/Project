
<%@ page contentType="text/html; charset=euc-kr" language="java"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../top.jsp" />

<style type="text/css">

	.list {
		width: 100%;
	}

	.list thead th {
		padding: 5px 0;
		border-top: 1px solid #E5E5E5;
		border-bottom: 1px solid #E5E5E5;
		background: #FAF9FA;
	}

	.list tbody td {
		text-align: center;
		padding: 10px 0;
		border-bottom: 1px solid #E5E5E5;
	}


</style>
<br>
<br>
<br>


	<h1>QnA Board</h1>


<table class="list">
	<colgroup>
		<col width="15%"/>
		<col width="35%"/>
		<col width="25%"/>
		<col width="25%"/>
	</colgroup>
	<thead>
	<tr>
		<th>Title</th>
		<th>Name</th>
		<th>Date</th>
		<th>BoardSeq</th>
	</tr>
	</thead>
<core:forEach items="${requestScope.board_list }" var="list" varStatus="index">
	<tbody>

	<tr>
		<td>
			<a href ="/ViewBoardServlet?qnabdseq=${list.qnabdseq}">${list.qnabdtitle}</a>
		</td>
		<td>${list.userName}</td>
		<td>${list.qnabddate}</td>
		<td>${list.qnabdseq}</td>

	</tr>

	</tbody>
</core:forEach>

</table>

<core:if test="${!empty sessionScope.memberDTO }">
<div class="dropdown">
	<button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">MENU</button>
	<ul class="dropdown-menu">
			<li>
				<a href="/QnaBoard/qnawrite.jsp">글쓰기</a>
			</li>
		<core:if test="${sessionScope.memberDTO.admin =='1'}">
			<li>
				<a> 삭제 </a>
			</li>
		</core:if>
	</ul>
</div>
		</core:if>



</body>
</html>
