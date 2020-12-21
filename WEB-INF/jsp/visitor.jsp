<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="includes/head.jsp"/>
<title>${ user.userName }'s quizzes</title>
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
            <h2>${ user.userName }</h2>
        </div>
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
                    <form action="quiz.do" method="post" class="quiz-title">
                        <input type="hidden" name="userName" value="${ user.userName }">
                        <input type="hidden" name="id" value="${ item.id }">
                        <input type="submit" name="title" value="${ item.title }">
                        <input type="hidden" name="csrfToken" value="${ csrfToken }" />
                    </form>
                    <div class="quiz-options">
                        <span>
                            <c:set var = "flag" scope = "session" value = "${true}"/>
                            <c:forEach var="qI" items="${quizInfo}">
                                <c:if test="${qI.quizId == item.id}">
                                    <c:set var = "flag" scope = "session" value = "${false}"/>
                                    ${ qI.score }
                                </c:if>
                            </c:forEach>
                            <c:if test="${flag}">-</c:if>
                            / ${ item.getQNumber() }
                        </span>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</main>

<%-- <jsp:include page="includes/footer.jsp"/> --%>