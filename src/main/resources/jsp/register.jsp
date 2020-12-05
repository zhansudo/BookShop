<%--
  Created by IntelliJ IDEA.
  User: 16048
  Date: 2020/12/4
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/register" method="post">
    邮箱：<input type="text" name="email"/><br/>
    用户名：<input type="text" name="username"/><br/>
    密码：<input type="password" name="password"/><br/>
    性别：<input type="radio" name="gender" value="男" checked>男<input type="radio" name="gender" value="女">女<br/>
    联系电话：<input type="text" name="telephone"/><br/>
    个人介绍：<textarea name="introduce"></textarea><br/>
    <input type="submit" value="注册">
</form>
</body>
</html>
