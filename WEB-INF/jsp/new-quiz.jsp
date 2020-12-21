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
                <input type="hidden" name="csrfToken" value="${ csrfToken }"/>
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
        <div class="df aic jcsb">
            <h2>New quiz</h2>
            <div class="df aic">
                <input  id="numQ" class="new-number" type="number" size="3" min="1" max="100" value="3">
                <div class="new-bttn" onclick="addQuestion()">Add Questions</div>
            </div>
        </div>
        <form class="create-quiz" action="create-quiz.do" method="post">
            <input type="hidden" name="csrfToken" value="${ csrfToken }"/>
            <label>
                Title:
                <input type="text" class="title" name="title" placeholder="Title..." required>
            </label>
            <div class="questions">
                <div class="question" style="border:1px solid #94949450; border-radius:6px; padding:10px; margin:10px 0;">
                    <label>
                    <c:forEach var="field" items="${form.visibleFields}">
                        <c:if test="${field.name.equals('question')}">
                            <div style="margin-bottom:10px;">  
                                ${field.label} 1: 
                                <input type="${field.type}" name="${field.name}" value="${field.value}" placeholder="Max length is 1000..." required>
                            </div>
                        </c:if>
                        <c:if test="${field.name.equals('answer')}">
                            <div>  
                                ${field.label}: 
                                <input type="${field.type}" name="${field.name}" value="${field.value}" placeholder="Max length is 255..." required>
                            </div>
                        </c:if>
                    </c:forEach>
                    </label>
                    <div style="text-align:center;color:#f00;margin-top:4px;">${field.error}</div>
                </div>

                <c:forEach var="i" begin="2" end="${ number }" step="1" varStatus="status">
                    <div class="question" style="border:1px solid #94949450; border-radius:6px; padding:10px; margin:10px 0;">
                        <label>
                        <c:forEach var="field" items="${form.visibleFields}">
                                <c:if test="${field.name.equals('question')}">  
                                    <div style="margin-bottom:10px;">
                                        ${field.label} ${i}: 
                                        <input type="${field.type}" name="${field.name}" value="${field.value}" placeholder="Max length is 1000..." <c:if test="${i==1}">required</c:if>>
                                    </div>
                                </c:if>
                                <c:if test="${field.name.equals('answer')}">
                                    <div>
                                        ${field.label}: 
                                        <input type="${field.type}" name="${field.name}" value="${field.value}" placeholder="Max length is 255...">
                                    </div>
                                </c:if>
                        </c:forEach>
                        </label>
                        <div style="text-align:center;color:#f00;margin-top:4px;">${field.error}</div>
                    </div>
                </c:forEach>
            </div>

            <button type="submit" class="action" name="action" value="create-quiz">Submit</button>
        </form>
    </div>
    <script>
        let createQuiz = document.querySelector('.questions');
        function addQuestion() {
            let numQ = document.querySelector('#numQ').value;
            for (let i=0; i<numQ; i++) {
                createQuiz.innerHTML += 
                '<div class="question" style="border:1px solid #94949450; border-radius:6px; padding:10px; margin:10px 0;">'+
                    '<label>'+
                    '<c:forEach var="field" items="${form.visibleFields}">'+
                            '<c:if test="${field.name.equals('question')}">'+
                                '<div style="margin-bottom:10px;">'+
                                    '${field.label}: '+ 
                                    '<input type="${field.type}" name="${field.name}" value="${field.value}" placeholder="Max length is 1000..." <c:if test="${i==1}">required</c:if>>'+
                                '</div>'+
                            '</c:if>'+
                            '<c:if test="${field.name.equals('answer')}">'+
                                '<div>'+
                                    '${field.label}: '+
                                    '<input type="${field.type}" name="${field.name}" value="${field.value}" placeholder="Max length is 255...">'+
                                '</div>'+
                            '</c:if>'+
                    '</c:forEach>'+
                    '</label>'+
                    '<div style="text-align:center;color:#f00;margin-top:4px;">${field.error}</div>'+
                '</div>';
            }
        }
    </script>
</main>

<%-- <jsp:include page="includes/footer.jsp"/> --%>