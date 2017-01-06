<%--
  Created by IntelliJ IDEA.
  User: lee
  Date: 2017-01-06
  Time: 오후 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../top.jsp" />


<form action ="/BoardServlet" method="post">
    타이틀:<input type="text" name="title" size="18"><br>
    내용:<textarea name="content"></textarea>
    비밀번호:<input type="password" name="password" size="18"><br>


</form>



</body>
</html>
