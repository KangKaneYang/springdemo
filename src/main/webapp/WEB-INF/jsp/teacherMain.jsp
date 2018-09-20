
<%@ page  language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var="ctx"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>SpringDemo</title>

        <script type="text/javascript">
            var xhr = new XMLHttpRequest();

            function retriveScore()
            {
                //window.alert("即将发送异步请求！");

                var url = "/springdemo/addScore.html";
                xhr.open("POST", url, true);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
                xhr.onreadystatechange = processRetriveResponse;

                var teacherName = document.getElementById("teacherName").innerHTML;
                var studentName = document.getElementById("studentName").value;
                document.getElementById("studentName").value = "";
                var courseName = document.getElementById("courseName").value;
                document.getElementById("courseName").value = "";
                var score = document.getElementById("score").value;
                document.getElementById("score").value = "";
                xhr.send("teacherName=" + teacherName + "&studentName=" + studentName + "&courseName=" + courseName + "&score=" + score);
            }

            function processRetriveResponse()
            {
                if (xhr.readyState==4 && xhr.status==200)
                {
                    document.getElementById("result").innerHTML = xhr.responseText;
                    alert(document.getElementById("insertMessage").innerHTML);
                }
            }

            function retriveInitScore()
            {
                var url = "/springdemo/getInitScore.html";
                xhr.open("POST", url, true);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
                xhr.onreadystatechange = processRetriveInitResponse;

                var teacherName = document.getElementById("teacherName").innerHTML;
                var studentName = document.getElementById("studentName").value;
                var courseName = document.getElementById("courseName").value;
                var score = document.getElementById("score").value;
                xhr.send("teacherName=" + teacherName + "&studentName=" + studentName + "&courseName=" + courseName + "&score=" + score);
            }

            function processRetriveInitResponse()
            {
                if (xhr.readyState==4 && xhr.status==200)
                {
                    document.getElementById("result").innerHTML = xhr.responseText;
                    //alert(document.getElementById("insertMessage").innerHTML);
                }
            }


            function confirmDelete(deleteBtn)
            {
                var deleted = confirm("确定要删除吗?");
                if(deleted)
                {
                    deleteScore(deleteBtn);

                }
            }

            function deleteScore(deleteBtn)
            {
                var url = "/springdemo/deleteScore.html";
                xhr.open("POST", url, true);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
                xhr.onreadystatechange = function()
                {
                    if (xhr.readyState==4 && xhr.status==200)
                    {
                        var thisRowIndex;
                        thisRowIndex = ((deleteBtn.parentNode).parentNode).rowIndex;
                        document.getElementById("studentScore").deleteRow(thisRowIndex);

                        //删除之后的返回消息
                        document.getElementById("deleteMessage").innerHTML = xhr.responseText;
                        alert(document.getElementById("deleteMessage").innerHTML);
                    }
                };

                var thisRowCells = deleteBtn.parentNode.parentNode.cells;
                var scoreId = thisRowCells[0].innerHTML;

                xhr.send("scoreId=" + scoreId);
            }


            function modifyScore(modifyBtn)
            {
                var thisRowCells = modifyBtn.parentNode.parentNode.cells;
                for (var cellIndex = 2; cellIndex < thisRowCells.length - 1; cellIndex++)
                {
                    var thisCell = thisRowCells[cellIndex];
                    var thisCellContent = thisCell.innerHTML;

                    thisCell.innerHTML = "<input type='text' value = '" + thisCellContent + "' />"
                }

                //隐藏 删除和修改 按钮
                var btnInThisCell = modifyBtn.parentNode.getElementsByTagName("input");
                for (var index = 0; index < btnInThisCell.length - 2; index++)
                {
                    btnInThisCell[index].style.display = "none";
                }
                for (var index = 2; index < btnInThisCell.length; index++)
                {
                    btnInThisCell[index].style.display = "inline";
                }
            }

            function confirmModify(modifyConfirmBtn)
            {
                var thisRowCells = modifyConfirmBtn.parentNode.parentNode.cells;

                //确认当前行的数据
                for (var index = 2; index < thisRowCells.length - 1; index++)
                {
                    thisRowCells[index].innerHTML = thisRowCells[index].getElementsByTagName("input")[0].value;
                }

                //隐藏确认和取消按钮，显示删除和修改按钮
                var btnInThisCell = modifyConfirmBtn.parentNode.getElementsByTagName("input");
                for (var index = 0; index < btnInThisCell.length - 2; index++)
                {
                    btnInThisCell[index].style.display = "inline";
                }
                for (var index = 2; index < btnInThisCell.length; index++)
                {
                    btnInThisCell[index].style.display = "none";
                }

                var url = "/springdemo/confirmModify.html";
                xhr.open("POST", url, true);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
                xhr.onreadystatechange = function()
                {
                    if (xhr.readyState==4 && xhr.status==200)
                    {
                    }
                };
                var id = parseInt(thisRowCells[0].innerHTML);
                var teacherName = thisRowCells[1].innerHTML;
                var studentName = thisRowCells[2].innerHTML;
                var courseName = thisRowCells[3].innerHTML;
                var score = parseInt(thisRowCells[4].innerHTML);
                xhr.send("id=" + id + "&teacherName=" + teacherName + "&studentName=" + studentName + "&courseName=" + courseName + "&score=" + score);
            }

            function cancelModify(modifyCancelBtn)
            {
                var thisRowCells = modifyCancelBtn.parentNode.parentNode.cells;
                var id = parseInt(thisRowCells[0].innerHTML);

                var url = "/springdemo/cancelModify.html";
                xhr.open("POST", url, false);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
                xhr.onreadystatechange = function()
                {
                    if (xhr.readyState==4 && xhr.status==200)
                    {
                        //得到后台返回的数据
                        document.getElementById("cancelModify").innerHTML = xhr.responseText;

                        //恢复当前行的数据
                        var divsInCancelModify = document.getElementById("cancelModify").getElementsByTagName("div");
                        for (var index = 2; index < thisRowCells.length - 1; index++)
                        {
                            thisRowCells[index].innerHTML = divsInCancelModify[index].innerHTML;
                        }

                        //隐藏确认和取消按钮，显示删除和修改按钮
                        var btnInThisCell = modifyCancelBtn.parentNode.getElementsByTagName("input");
                        for (var index = 0; index < btnInThisCell.length - 2; index++)
                        {
                            btnInThisCell[index].style.display = "inline";
                        }
                        for (var index = 2; index < btnInThisCell.length; index++)
                        {
                            btnInThisCell[index].style.display = "none";
                        }
                    }
                };
                xhr.send("id=" + id);
            }
        </script>
    </head>

    <body  onload="retriveInitScore();">

        <span id="teacherName">${userName}</span>, welcome！you are a ${identity}.
        <span style="text-align:right;"><a href="/springdemo/index.jsp">退出</a></span>
        <hr />

        <center>
            <h1>成绩录入</h1>

            <label for="studentName">学生姓名:</label>
            <input id="studentName" name="studentName" type="text" />
            <label for="courseName">课程名:</label> <!--这里输入的课程名称要求要记住-->
            <input id="courseName" name="courseName" type="text" />
            <label for="score">成绩:</label>
            <input id="score" name="score" type="text" />
            <input id="add" name="add" type="button" value="录  入" onclick="retriveScore();" />

            <div id="insertTip"></div>
            <div id="result"></div>
        </center>

        <div id="deleteMessage" hidden="hidden"></div>
        <div id="cancelModify" hidden="hidden"></div>
    </body>
</html>