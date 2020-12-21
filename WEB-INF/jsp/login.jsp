<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="includes/head.jsp"/>
<title>My quizzes</title>
<jsp:include page="index.jsp"/>
<jsp:include page="includes/header.jsp"/>

<style><%@include file="/css/authorization.css"%></style>

<body onload="document.getElementById('userName').focus()" style="background:#f8f9fa">
    <div class="df fdc aic jcc" style="margin-top:20px;">
        <div class="login">
            <div class="title tac">Login</div>
            <form action="login.do" method="POST">
                <input type="hidden" name="csrfToken" value="${csrfToken}" />
                <table>
                    <c:forEach var="field" items="${form.visibleFields}">
                        <tr>
                        <td><label for="${field.name}">${field.label}</label></td>
                        <td><input id="${field.name}" type="${field.type}" name="${field.name}" value="${field.value}"/></td>
                        <td><span class="error">${field.error}</span></td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td></td>
                        <td>
                            <button type="submit" name="action" value="Login">Login</button>
                            <button type="submit" name="action" value="Register">Register</button>
                        </td>
                    </tr>
                </table>
            </form>
            
            <c:forEach var="error" items="${form.formErrors}">
                <h3 style="color:red"> ${error} </h3>
            </c:forEach>
        </div>
    </div>
</body>
</html>