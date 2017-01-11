<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=EUC-KR" language="java" session="true"%>

<jsp:include page="../top.jsp" />

<style type="text/css">

    .view {
        width: 100%;
    }
    .view td {
        border-top: 1px solid #E5E5E5;
        text-align: center;
    }

    .gray {
        background: #FAF9FA;
    }

</style>

</head>
<body>
<br>
<br>
<br>
<br>



<c:forEach items="${requestScope.board_content}" var="list" varStatus="index">

<table class="view" cellpadding="10">
    <colgroup>
        <col width="5%"/>
        <col width="60%"/>
        <col width="10%"/>
        <col width="25%"/>
    </colgroup>
    <tr>
        <td class="gray"><b>Title</b></td>
        <td colspan="4">${list.qnabdtitle} </td>
    </tr>
    <tr>
        <td class="gray"><b>Name</b></td>
        <td> ${list.userName}</td>
        <td class="gray"><b>Date</b></td>
        <td colspan="2">${list.qnabddate}</td>
    </tr>
    <tr>
        <td class="gray" height="300px"><b>Content</b></td>
        <td colspan="4">${list.qnabdcontent}</td>
    </tr>
    <tr class="bd_button">
        <td><input type="button" value="List" onclick="location.href='/QnaBoard/qnaboard.jsp'"></td>
        <core:if test="${sessionScope.memberDTO.userSeq == list.user_userseq}">
        <td colspan="2"></td>
        <td align="right"><input type="button" value="Modify" onclick="modifycheck()">&nbsp;&nbsp;
            <input type="button" value="Delete" onclick="deletecheck()"></td>
        </core:if>
    </tr>
</table>

</c:forEach>

<c:forEach items="${requestScope.board_content}" var="list" varStatus="index">
<script>
    function deletecheck() {
        var check = prompt("게시글 비밀번호를 입력해주세요");
        if(check==${list.qnabdpw}){
            location.href="/BoardDeleteServlet?qnabdseq=${list.qnabdseq}";

        }else{
            alert("비밀번호가 틀렸습니다");
        }
    }

    function modifycheck() {
        var check = prompt("게시글 비밀번호를 입력해주세요");

        if(check==${list.qnabdpw}){
            location.href="/BoardModifyViewServlet?qnabdseq=${list.qnabdseq}";

        }else{
            alert("비밀번호가 틀렸습니다");
        }

    }
</script>
</c:forEach>
</body>
</html>

