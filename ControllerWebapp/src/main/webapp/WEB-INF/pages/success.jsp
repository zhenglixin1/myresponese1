<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<html>
<head>
    <title>成功</title>
</head>
<body>
    <h3>成功页面</h3>
    <form>
        <table width="100%" border=1>
            <tr>
                <td>学号</td>
                <td>学生姓名</td>
                <td>年龄</td>
                <td>出生日期</td>
                <td>性别</td>
            </tr>
            <c:forEach items="${list}" var="item">
                <tr>
                    <td> ${item.stuCode } </td>
                    <td> ${item.name } </td>
                    <td>${item.age}</td>
                    <td> <fmt:formatDate value="${item.birthday}" pattern="yyyy-MM-dd"/> </td>
                    <td>${item.sex} </td>
                </tr>

            </c:forEach>
        </table>
    </form>
</body>
</html>
