<%@ page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<center>
    <%--插入的消息--%>
    <div id="insertMessage" style="color: red;">${insertMessage}</div>
    <br />



    <hr />
    <h2>已录入的成绩</h2>
    <table border="1px solid" width="70%" id="studentScore">
        <thead>
            <td hidden="hidden">编号</td>
            <td>教师名</td>
            <td>学生名</td>
            <td>课程名</td>
            <td>成绩</td>
            <td>操作</td>
        </thead>
        <c:if test="${!empty scores}">
            <c:forEach items="${scores}" var="score" varStatus="status">
                <tr>
                    <td hidden="hidden">${score.id}</td>
                    <td>${score.teacherName}</td>
                    <td>${score.studentName}</td>
                    <td>${score.courseName}</td>
                    <td>${score.score}</td>
                    <td style="width: 150px;">
                        <input type="button" name="delete" id="delete${status.index}"  value="删 除" onclick="confirmDelete(this)" />
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="button" name="modify" id="modify${status.index}" value="修 改" onclick="modifyScore(this)" />

                        <input type="button" id="confirmModify${status.index}"  value="确认" onclick="confirmModify(this)" hidden="hidden" />
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="button" id="cancelModify${status.index}" value="取消" onclick="cancelModify(this)"  hidden="hidden"/>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</center>








