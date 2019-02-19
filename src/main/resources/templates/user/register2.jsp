<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>注册结果</title>
</head>
<body>
Hello World!<br/>
            ${register}<br/>
${name}<br/>
${same}<br/>
${namesame}<br/>

<%
    String param=(String) request.getAttribute("register");
    out.print(param);
    if(param.equals("注册成功")){
        String name=(String) request.getAttribute("name");
        request.setAttribute("name",name);
         out.print(name);%>

<input type="button" onclick="window.location.href= 'http://localhost:8080/sell/buyer/login?name=<%=name%>'" value="登录" />
<%    }
    else { %>
        <form action="register" method="get">
            <input type="submit" value="重新注册" />
        </form>
<%  }
%>
</body>
</html>