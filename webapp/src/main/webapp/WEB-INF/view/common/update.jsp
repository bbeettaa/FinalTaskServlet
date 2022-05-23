<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div>Имя: <c:out value="${requestScope.user.login}"/></div>
<div>Возраст: <c:out value="${requestScope.user.password}"/></div>
<div>role: <c:out value="${requestScope.user.role}"/></div>

<br/>

<form method="post" action="">
    <input type="hidden" name="action" value="updateUser">

    <label>new login: <input type="text" name="loginToSet" value="${requestScope.user.login}" minlength="5" maxlength="40" required/></label><br>
    <label>new pass: <input type="text" name="passwordToSet" value="${requestScope.user.password}" minlength="5" maxlength="40" required/></label><br>

    <input type="hidden" name="roleToSet" value="${requestScope.user.role}"/>

    <input type="number" hidden name="id" value="${requestScope.user.id}"/>
    <input type="submit" value="Ok" name="Ok"><br>
</form>
</body>
</html>