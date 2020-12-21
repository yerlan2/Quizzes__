<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>ToDoList7 -- Error Page</title>
    </head>
    
	<body>
	
		<h2>ToDoList7 Error</h2>

        <c:if test="${!(empty form)}">
            <p style="font-size:medium; color:red">
                <c:forEach var="formError" items="${form.formErrors}">
                    <h3 style="color:red"> ${formError} </h3>
                </c:forEach>
            </p>
        </c:if>
        
        <c:if test="${!(empty error)}">
            <h3 style="color:red"> ${error} </h3>
        </c:if>
        
		<c:choose>
			<c:when test="${ (empty user) }">
				Click <a href="login.do">here</a> to login.
			</c:when>
			<c:otherwise>
				Click <a href="todolist.do">here</a> to return to the To Do List.
			</c:otherwise>
		</c:choose>

	</body>
</html>