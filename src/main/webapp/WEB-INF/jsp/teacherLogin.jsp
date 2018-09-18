
<%@ page  language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>

    <head>
        <title>Spring Demo login</title>
    </head>

    <body>
        <center>
            <h1>成绩管理系统-教师登陆</h1>
            <hr />
            <center>
                <c:if test="${!empty error}">
                    <font color="red">
                        <c:out value="${error}" />
                    </font>
                </c:if>
            </center>
        <form action="/springdemo/loginCheck.html" method="post">
            用户名： <input name="userName" type="text" >
            <br />
            密&nbsp;&nbsp;&nbsp;码： <input name="password" type="password" >
            <br />
            身&nbsp;&nbsp;&nbsp;份：
            <select name="identity">
                <!--<option value="student">学生</option> -->
                <option value="teacher" selected="selected">教师</option>
            </select>
            <br />

            <br />
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input name="login" type="submit" value="登  陆" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input name="reset" type="reset"  value="重  置" />

            <br />
            <br />
            &nbsp;&nbsp;&nbsp;&nbsp;<a href="/springdemo/register.html">注&nbsp;&nbsp;册</a>
        </form>
        </center>
    </body>
</html>