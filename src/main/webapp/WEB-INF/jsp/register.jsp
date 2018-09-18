
<%@ page  language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

<head>
    <title>学生注册</title>
</head>

<body>
    <center>
        <h1>成绩管理系统-用户注册</h1>
        <hr />
        <center>
            <c:if test="${!empty msg}">
                <font color="red">
                    <c:out value="${msg}" />
                </font>
            </c:if>
        </center>
    <form action="/springdemo/registerCheck.html" method="post">
        用户名： <input name="userName" type="text" >
        <br />
        密&nbsp;&nbsp;&nbsp;码： <input name="password" type="password" >
        <br />
        身&nbsp;&nbsp;&nbsp;份：
        <select name="identity" >
            <option value="student">学生&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /></option>
            <option value="teacher">教师&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br /></option>
        </select>
        <br />
        <br />
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input name="submit" type="submit" value="提  交" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input name="reset" type="reset"  value="重  置" />
        <br />
        <br />
        &nbsp;&nbsp;&nbsp;&nbsp;<a href="/springdemo/index.jsp">登&nbsp;&nbsp;陆</a>
    </form>
    </center>


</body>
</html>