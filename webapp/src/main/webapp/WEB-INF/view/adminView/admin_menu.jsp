
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }

        th, td {
            padding: 5px;
            text-align: center;
        }
    </style>
</head>
<body>

<a href="<c:url value='/logout' />">Logout</a>
<h2>Создание нового пользователя</h2><br/>

<form method="post" action="<c:url value='/addUser'/>">
    <label><input type="text" name="login" style="width:9%">: Login</label><br>
    <label><input type="text" name="password" style="width:9%">: Password </label><br>

    <label for="role">
    <select id="role" name="role" style="width:9%">
        <option value="user">USER</option>
        <option value="saab">saab</option>
    </select>
        : Role</label><br>

    <input type="submit" value="Ok" name="Ok"><br>
</form>

<h2>Все пользователи</h2><br/>
<h3> поиск конкретного пользователя по нику</h3>
<form method="post" action="<c:url value='/findUsers'/>">
    <div>
        <label for="login">Search by login:</label>
        <input type="search" id="login" name="login" value="${findLogin}">
        <input type="submit">
    </div>
</form>

<table style="width:75%">
    <tr>
        <th>login</th>
        <th>pass</th>
        <th>role</th>
    </tr>
    <c:forEach var="user" items="${requestScope.dao}">
        <tr>
            <ul>
                <td><c:out value="${user.login}"/></td>
                <td><c:out value="${user.password}"/></td>
                <td><c:out value="${user.role}"/></td>
                <td>
                    <form method="post" action="<c:url value='/dellUser'/>" id="dell">
                        <input type="number" hidden name="id" value="${user.id}"/>
                        <c:if test="${user.role != ADMIN}"/>

                        <c:choose>
                            <c:when test="${user.role != 'ADMIN'}">
                                <input type="submit" name="delete" value="Удалить"/>
                            </c:when>
                        </c:choose>
                    </form>
                </td>
                <td>
                    <form method="get" action="<c:url value='/updateUser'/>" id="update">
                        <input type="number" hidden name="id" value="${user.id}"/>
                        <input type="submit" value="Редактированть"/>
                    </form>
                </td>
            </ul>

        </tr>
    </c:forEach>
</table>


</body>
</html>
