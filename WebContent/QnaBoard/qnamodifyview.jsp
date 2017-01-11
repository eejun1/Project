<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lee
  Date: 2017-01-06
  Time: 오후 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=EUC-KR" language="java" session="true"%>
<% request.setCharacterEncoding("euc-kr"); %>
<% response.setContentType("text/html; charset=euc-kr"); %>

<jsp:include page="../top.jsp" />

<style type="text/css">

    .view {
        width: 100%;
    }
    .view td {
        border-top: 1px solid #E5E5E5;
    }

    .gray {
        background: #FAF9FA;
    }

    .center {
        text-align: center;
    }

</style>

<script>


    $(document).ready(function() {   //글쓴 폼 전부 삭제
        $("#cancel").click(function() {
            $("form").each(function() {
                this.reset();
            });
        });
    });
</script>
<br>
<br>
<br>

<h1>QnA 글수정</h1>
<c:forEach items="${requestScope.board_content}" var="list" varStatus="index">
<table class="view" cellpadding="10">
    <form action="/BoardModifyServlet?qnabdseq=${list.qnabdseq}" method="post" name="write">
        <%--<input type="hidden" class="form-control" name="userRequest" value ="BoardModifyServlet?qnabdseq=${list.qnabdseq}">--%>
        <colgroup>
            <col width="5%"/>
            <col width="85%"/>
            <col width="5%"/>
            <col width="5%"/>
            <col width="5%"/>
        </colgroup>
        <tr>
            <td class="gray center"><b>Title</b></td>
            <td colspan="4"><input type="text" name="qnabdtitle" size="40" required value="${list.qnabdtitle}"></td>
        </tr>
        <tr>
            <td class="gray center"><b>Name</b></td>
            <td colspan="4"><input type="text" name="userName" size="20" required readonly value="${list.userName}"></td>
        </tr>
        <tr>
            <td class="gray center"><b>Password</b></td>
            <td colspan="4"><input type="password" name="qnabdpw" size="20" required value="${list.qnabdpw}"></td>
        </tr>
        <tr>
            <td class="gray center" height="300px"><b>Content</b></td>
            <td colspan="4"><textarea name="qnabdcontent" rows="20" cols="50" required >${list.qnabdcontent}</textarea></td>
        </tr>
        <tr class="bd_button">
            <td><input type="button" value="List" onclick="location.href='/QnaBoard/qnaboard.jsp'"> </td>
            <td colspan="3"></td>
            <td align="right"><input type="button" value="Cancel" id="cancel" >&nbsp;&nbsp;
                <input type="submit" value="Modify"></td>
        </tr>

    </form>
</table>
</c:forEach>
</body>
</html>
