<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title> ${name}</title>
</head>
<body>

        ${name}<br/>
        <form action="/buyer/index" method="get">
            <input type="submit" value="去首页" />
        </form>
        <form action="/buyer/logout" method="get">
            <input type="submit" value="退出登录" />
        </form>
</body>
</html>