<%--
  Created by IntelliJ IDEA.
  User: lee
  Date: 2017-01-09
  Time: 오후 3:14
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



<table class="view" cellpadding="10">
    <colgroup>
        <col width="5%"/>
        <col width="60%"/>
        <col width="10%"/>
        <col width="25%"/>
    </colgroup>
    <tr>
        <td class="gray"><b>Title</b></td>
        <td colspan="4"> 타 이 틀</td>
    </tr>
    <tr>
        <td class="gray"><b>Name</b></td>
        <td> 이 름 </td>
        <td class="gray"><b>Date</b></td>
        <td colspan="2"> 날 짜</td>
    </tr>
    <tr>
        <td class="gray" height="300px"><b>Content</b></td>
        <td colspan="4"> 내 용</td>
    </tr>
    <tr class="bd_button">
        <td><input type="button" value="List"></td>
        <td colspan="2"></td>
        <td align="right"><input type="button" value="Modify">&nbsp;&nbsp;
            <input type="button" value="Delete"></td>
    </tr>
</table>
</body>
</html>
</body>
</html>
