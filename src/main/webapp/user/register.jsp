<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>注册</title>
</head>
<body>

<form action="register" method="POST">
    用户名: <input type="text" name="name">
    <br />
    密码: <input type="password" name="password1" /><br />
    确认密码: <input type="text" name="password2" /><br />
    <input type="submit" value="提交" />

</form>

</body>
</html>