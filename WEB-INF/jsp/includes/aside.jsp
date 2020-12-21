<style><%@include file="/css/aside.css"%></style>
<aside>
    <c:forEach var="user" items="${users}">
        <p style="font-weight:300;font-style:italic;" class="error"> ${user.getFirstName()} ${user.getLastName()} </p>
    </c:forEach>
    <a href="#">Hello</a>
    <a href="#">World</a>
    <a href="#">Aigul Bayadil</a>
    <a href="/hwblog/visitor.do">Visitor</a>
</aside>
