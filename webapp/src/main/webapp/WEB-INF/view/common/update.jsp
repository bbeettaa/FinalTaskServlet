<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div>login: <c:out value="${requestScope.user.login}"/></div>
<div>pass: <c:out value="${requestScope.user.password}"/></div>
<div>Email: <c:out value="${requestScope.user.email}"/></div>
<div>Namae: <c:out value="${requestScope.user.name}"/></div>
<div>surname: <c:out value="${requestScope.user.surname}"/></div>
<div>role: <c:out value="${requestScope.user.role}"/></div>

<br/>

<form method="post" action="">
    <input type="hidden" name="action" value="updateUser">

    <label>new login: <input type="text" name="loginToSet" value="${requestScope.user.login}" minlength="5" maxlength="30" required/></label><br>
    <label>new pass: <input type="text" name="passwordToSet" value="${requestScope.user.password}" minlength="5" maxlength="30" required/></label><br>
    <label>new name: <input type="text" name="nameToSet" value="${requestScope.user.name}" maxlength="30" /></label><br>
    <label>new surname: <input type="text" name="surnameToSet" value="${requestScope.user.surname}" maxlength="30" /></label><br>
    <label>new email: <input type="text" name="emailToSet" value="${requestScope.user.email}" maxlength="30" /></label><br>

    <input type="hidden" name="roleToSet" value="${requestScope.user.role}"/>

    <input type="number" hidden name="id" value="${requestScope.user.id}"/>
    <input type="submit" value="Ok" name="Ok"><br>
</form>
</body>
</html>