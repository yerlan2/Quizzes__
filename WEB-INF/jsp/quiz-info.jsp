<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="includes/head.jsp"/>
<title>Quiz: ${ quiz.getTitle() } | Info</title>
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
        <div class="new-quiz">
            <h3 style="font-size:24px; font-weight:normal;">${ quiz.getTitle() }</h3>
        </div>
        <hr>
        <h3 class="page-sub-title">
            Current user list has 
            <c:choose>
                <c:when test="${ fn:length(quizInfo) <= 0 }">No users(</c:when>
                <c:when test="${ fn:length(quizInfo) == 1 }">1 user:</c:when>
                <c:otherwise>${  fn:length(quizInfo) } users:</c:otherwise>
            </c:choose>
        </h3>
        <div class="quizzes">
            <c:forEach var="item" items="${quizInfo}">
                <div class="quiz">
                    ${ item.userName }
                    <div class="quiz-options">
                        <span>
                            ${ item.score } / ${ quiz.getQNumber() }
                        </span>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</main>

<%-- <jsp:include page="includes/footer.jsp"/> --%>