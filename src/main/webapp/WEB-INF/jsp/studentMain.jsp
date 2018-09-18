
<%@ page  language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>SpringDemo</title>
    </head>

    <body>
        <span id="username">${userName}</span>, welcome！ you are a ${identity}.
        <span style="align:right"><a href="/springdemo/index.jsp">退出</a></span>
        <hr />
        <center>

            <h1>成绩查询</h1>
            <label for="coursename">课程名：</label>
            <input id="coursename" name="coursename" type="text" />
            <input id="query" name="query" type="button" value="查  询" onclick="retriveScore();" />
            <br />（输入课程名查询特定课程成绩，或者什么都不输入查询全部）
            <div id="result"></div>
        </center>

        <script type="text/javascript">
            var xhr = new XMLHttpRequest();

            function retriveScore()
            {
                var url = "/springdemo/queryScore.html";
                xhr.open("POST", url, true);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
                xhr.onreadystatechange = processResponse;

                var courseName = document.getElementById("coursename").value;
                var userName = document.getElementById("username").innerHTML;
                xhr.send("userName=" + userName + "&courseName=" + courseName);
            }

            function processResponse()
            {
                if (xhr.status == 200)
                {
                    document.getElementById("result").innerHTML = xhr.responseText;
                }
                /*else
                {
                    window.alert("您所请求的页面有异常。");
                }*/
            }

        </script>


    </body>
</html>