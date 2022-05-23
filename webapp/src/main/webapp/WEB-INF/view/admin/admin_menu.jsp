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

<!--<a href="controller?action=logout">Logout</a>-->

<form method="post" action="">
    <input type="hidden" name="action" value="logout">
    <button type="submit">Logout</button>
</form>

<h2>Создание нового пользователя</h2><br/>

<form method="post" action="">
    <input type="hidden" name="action" value="addUser">
    <label><input type="text" name="loginToSet" style="width:9%" minlength="5" maxlength="30" required>: Login</label><br>
    <label><input type="text" name="passwordToSet" style="width:9%" minlength="5" maxlength="30" required>: Password </label><br>
    <label><input type="email" name="emailToSet" style="width:9%">: Email </label><br>
    <label><input type="text" name="nameToSet" style="width:9%" maxlength="30" >: Name </label><br>
    <label><input type="text" name="surnameToSet" style="width:9%" maxlength="30" >: Surname </label><br>

    <label for="role">
    <select id="role" name="roleToSet" style="width:9%">
        <option value="user">USER</option>
        <option value="speaker">SPEAKER</option>
        <option value="moderator">MODERATOR</option>
    </select>: Role</label><br>

    <input type="submit" value="Ok" name="Ok"><br>
</form>

<h2>Все пользователи</h2><br/>
<h3> поиск конкретного пользователя по нику</h3>
<form method="post" action="">
    <input type="hidden" name="action" value="findUser">
    <div>
        <label for="findLogin">Search by login:</label>
        <input type="search" id="findLogin" name="findLogin" value="${findLogin}">
        <input type="submit">
    </div>
</form>

<table style="width:75%">
    <tr>
        <th>login</th>
        <th>pass</th>
        <th>name</th>
        <th>surname</th>
        <th>email</th>
        <th>role</th>
    </tr>
    <c:forEach var="user" items="${requestScope.dao}">
        <tr>
            <ul>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.login}"/></td>
                <td><c:out value="${user.password}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.surname}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.role}"/></td>
                <td>
                    <form method="post" action="" id="dell">
                        <input type="hidden" name="action" value="deleteUser">
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
                    <form method="post" action="" id="update">
                        <input type="hidden" name="action" value="updateUserPage">
                        <input type="hidden"  name="id" value="${user.id}"/>
                        <input type="submit" value="Редактированть"/>
                    </form>
                </td>
            </ul>

        </tr>
    </c:forEach>
</table>


</body>
</html>
