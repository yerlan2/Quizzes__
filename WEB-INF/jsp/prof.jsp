<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="includes/head.jsp"/>
<title>My quizzes</title>
<jsp:include page="index.jsp"/>
<jsp:include page="includes/header.jsp"/>

<style><%@include file="/css/aside.css"%></style>

<main class="container">
    <aside>
        <c:forEach var="user" items="${users}">
            <form action="visitorshow.do" method="post">
                <input type="hidden" name="csrfToken" value="${ csrfToken }" />
                <input type="submit" name="userName" value="${user.userName}"/>
            </form>
        </c:forEach>
    </aside>
    <div class="main">
        <style><%@include file="/css/prof.css"%></style>
        
        <c:forEach var="error" items="${form.formErrors}">
            <p class="error"> ${error} </p>
        </c:forEach>
        <c:forEach var="error" items="${quizForm.formErrors}">
            <p class="error"> ${error} </p>
        </c:forEach>

        <form action="new-quiz.do" method="post" class="new-quiz">
            <div class="top-title">
                ${ user.userName }
            </div>
            <div>
                <input type="hidden" name="csrfToken" value="${ csrfToken }" />
                <input type="number" name="qNumber" size="3" min="1" max="100" value="10" required>
                <button class="new-bttn">New</button>
            </div>
        </form>
        <hr>
        <h3 class="page-sub-title">
            Current quiz list has 
            <c:choose>
                <c:when test="${ fn:length(quizzes) <= 0 }">No quizzes(</c:when>
                <c:when test="${ fn:length(quizzes) == 1 }">1 quiz:</c:when>
                <c:otherwise>${ fn:length(quizzes) } quizzes:</c:otherwise>
            </c:choose>
        </h3>
        <div class="quizzes">
            <c:forEach var="item" items="${quizzes}">
                <div class="quiz">
                    <form action="quiz-info.do" method="post" class="quiz-title">
                        <input type="hidden" name="csrfToken" value="${ csrfToken }" />
                        <input type="hidden" name="id" value="${ item.id }">
                        <input type="submit" name="title" value="${ item.title }">
                    </form>
                    <div class="quiz-options">
                        <span>${ item.getQNumber() }</span>
                        <form class="delete-quiz" method="post" action="delete-quiz.do">
                            <input type="hidden" name="csrfToken" value="${ csrfToken }" />
                            <input type="hidden" name="id" value="${ item.id }" />
                            <button type="submit">&#10799;</button>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</main>

<%-- <jsp:include page="includes/footer.jsp"/> --%>