<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/7/9
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>chat with robot</title>
</head>

<style>
    td{
        border: none;
        width: 250px;
    }
    td.foot{
        width: 500px;
        height: 50px;
    }
    table {
        width: 500px;
        display:block;
    }
    /*
        让表格thead固定，tbody带滚动条
    */
    thead {
        display: inline-block;
        width: 100%;
        height: 50px;
    }
    tbody {
        height: 480px;
        display: inline-block;
        width: 100%;
        overflow: scroll;
    }
    tfoot{
        width: 500px;
        height: 30px;
    }
</style>

<script>
    function speak(val) {
        var butVal = document.getElementById("speakBut");
        if (val == "点我开始说话")
        {
            // 调用录音
            butVal.value = "点我结束说话";
            robot.action = "/speak.action";
            robot.submit();
        }
    }

    function jumpToFoot() {
        window.location.href = "#foot";
    }
    // $.ready(function(){
    //     $.ajax({
    //         method:"post",
    //         contentType:"application/json"
    //         data:data,
    //         success:function(r){
    //             alert(r);
    //             var json = eval("("+r+")");
    //
    //             $("tbody:last-child").append("");
    //         }
    //     });
    // });

</script>

<%--
    onload 事件 ：页面加载之后触发事件，每次页面加载之后让它跳转到表格tbody部分的最下边（滚动条滚动到最下边）
--%>
<body onload="jumpToFoot()">
    <form action="/getSend.action" method="get" name="robot">
    <table border="1" align="center" width="500px">
       <thead>
           <tr>
               <th width="500px" align="center" colspan="2" style="background: lightblue">
                   <h3 > 机器人 - 小艾 </h3>
               </th>
           </tr>
       </thead>
        <tbody>
            <c:choose>
                <c:when test="${empty list}">
                    <tr>
                        <td colspan="2">
                            <div style="text-align: center;">
                                <span style="color: cornflowerblue; font-size: small; "> 没有更多聊天记录... </span>
                            </div>
                        </td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${list}" var="msg" varStatus="vs">
                        <c:if test="${vs.count%2 == 0}">
                            <tr>
                                <td height="40px" ></td>
                                <td>${msg}</td>
                            </tr>
                        </c:if>
                        <c:if test="${vs.count%2 == 1}" >
                            <tr>
                                <td height="40px" >${msg}</td>
                                <td></td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    <tr>
                        <td colspan="2">
                            <a id="foot"> </a>
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>

        </tbody>
        <tfoot>
            <tr>
                <td class="foot" colspan="2">
                    <div align="center">
                        <input id="speakBut" type="button" onclick="speak(this.value)" value="点我开始说话"/>
                        <input type="text" name="inputValue" align="center" style="height: 30px">
                        <input type="submit" value="发送">
                    </div>
                </td>
            </tr>
        </tfoot>
    </table>
    <br><br>
</form>
</body>
</html>
