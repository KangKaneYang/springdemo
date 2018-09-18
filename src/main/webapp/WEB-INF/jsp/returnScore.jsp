<%@ page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<center>
    <hr />

    <table border="1px solid" width="20%" >

        <thead>
            <td>课程名</td>
            <td>成绩</td>
        </thead>
        <c:if test="${!empty score}">
            <tr>
                <td> ${score.courseName} </td>
                <td> ${score.score} </td>
            </tr>
        </c:if>
    </table>
</center>





