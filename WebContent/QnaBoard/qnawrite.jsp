<%--
  Created by IntelliJ IDEA.
  User: lee
  Date: 2017-01-06
  Time: ���� 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=euc-kr" language="java" session="true"%>
<jsp:include page="../top.jsp" />


<%--<form action ="/BoardServlet" method="post">--%>
    <%--Ÿ��Ʋ:<input type="text" name="title" size="18"><br>--%>
    <%--����:<textarea name="content"></textarea>--%>
    <%--��й�ȣ:<input type="password" name="password" size="18"><br>--%>


<%--</form>--%>
<script>

    function cancel() {
        alert("��� �ƽ��ϴ�");
        location.href='/QnaBoard/qnawrite.jsp';

    }
</script>
<br>
<br>
<br>

<table>
    <tr>
        <td>
            <table width="100%" cellpadding="0" cellspacing="0" border="0">
                <tr style=" text-align:center;">
                    <td>�۾���</td>
                </tr>
            </table>
            <table>
                <form action="/BoardServlet" method="post">
                <tr>
                    <td>&nbsp;</td>
                    <td align="center">����</td>
                        <td><input name="qnabdtitle" size="50" maxlength="100" required></td>
                    <td>&nbsp;</td>
                </tr>
                <tr height="1" bgcolor="#dddddd"><td colspan="4"></td></tr>
                <tr>
                    <td>&nbsp;</td>
                    <td align="center">�̸�</td>
                    <td><input name="userName" size="50" maxlength="50" readonly value="${sessionScope.memberDTO.userName}"></td>
                    <td>&nbsp;</td>
                </tr>
                <tr height="1" bgcolor="#dddddd"><td colspan="4"></td></tr>
                <tr>
                    <td>&nbsp;</td>
                    <td align="center">��й�ȣ</td>
                    <td><input name="qnabdpw" size="50" maxlength="50" required></td>
                    <td>&nbsp;</td>
                </tr>
                <tr height="1" bgcolor="#dddddd"><td colspan="4"></td></tr>
                <tr>
                    <td>&nbsp;</td>
                    <td align="center">����</td>
                    <td><textarea name="qnabdcontent" cols="50" rows="13" required></textarea></td>
                    <td>&nbsp;</td>
                </tr>
                <tr height="1" bgcolor="#dddddd"><td colspan="4"></td></tr>
                <tr height="1" bgcolor="#82B5DF"><td colspan="4"></td></tr>
                <tr align="center">
                    <td>&nbsp;</td>
                    <td colspan="2"><input type=submit>
                        <input type=button value="���" onclick="cancel()">
                    <td>&nbsp;</td>
                </tr>
                </form>
            </table>
        </td>
    </tr>
</table>


<table class="table table-striped">
    <thead>
    <tr>
        <th>EMAIL</th>
        <th>USERNAME</th>
        <th>ADDRESS</th>
        <th>PHONENUMBER</th>
        <th>Userseq</th>
        <th>admin</th>
        <th>password</th>
    </tr>
    </thead>
    <tbody>
    <tr>

        <td>${sessionScope.memberDTO.email}</td>

        <td>${sessionScope.memberDTO.userName}</td>

        <td>${sessionScope.memberDTO.address}</td>

        <td>${sessionScope.memberDTO.phoneNumber}</td>

        <td>${sessionScope.memberDTO.userSeq}</td>

        <td>${sessionScope.memberDTO.admin}</td>

        <td>${sessionScope.memberDTO.userpw}</td>
    </tr>
    <tr>
    </tbody>
</table>

</body>
</html>
