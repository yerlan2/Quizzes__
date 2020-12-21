<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="includes/head.jsp"/>
<title>New Quiz!</title>
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
        <style><%@include file="/css/new-quiz.css"%></style>
        <%--
        <c:forEach var="error" items="${form.formErrors}">
            <p class="error"> ${error} </p>
        </c:forEach>
        --%>
        <form action="check-quiz.do" method="post">
            <input type="hidden" name="csrfToken" value="${ csrfToken }"/>
            <input type="hidden" name="id" value="${ quiz.id }"/>
            <input type="hidden" name="userName" value="${ user.userName }"/>
            <label style="display:block; margin:10px 0 20px;">
                <h3 style="font-size:26px;">${ quiz.title }</h3>
            </label>
            <c:forEach var="question" items="${questions}">
                <div class="question" style="border:1px solid #94949450; border-radius:6px; padding:10px; margin:10px 0;">
                    <label style="margin:0;">
                        Question:
                        <div style="font-size:22px; margin:0 0 10px 2px;">
                            <input type="hidden" name="question" value="${ question.question }">
                            ${ question.question }
                        </div>
                        Answer:
                        <div>
                            <input type="text" name="answer">
                        </div>
                        <%-- 
                        <c:forEach var="field" items="${form.visibleFields}">
                            <c:if test="${field.name.equals('question')}">  
                                ${field.label} ${i}: 
                                <input type="${field.type}" name="${field.name}" value="${field.value}" placeholder="Max length is 1000..." <c:if test="${i==1}">required</c:if>>
                            </c:if>
                            <c:if test="${field.name.equals('answer')}">
                                ${field.label}: 
                                <input type="${field.type}" name="${field.name}" value="${field.value}" placeholder="Max length is 255...">
                            </c:if>
                        </c:forEach> 
                        --%>
                    </label>
                    <div style="text-align:center;color:#f00;margin-top:4px;">${field.error}</div>
                </div>
            </c:forEach>

            <button type="submit" class="action" name="action" value="create-quiz">Submit</button>
        </form>
    </div>
</main>

<%-- <jsp:include page="includes/footer.jsp"/> --%>