<style><%@include file="/css/header.css"%></style>
<header>
    <div class="container">
        <%-- 
        <div class="header">
            <h2>Blog</h2>
            <h4>Share your story!</h4>
        </div> 
        --%>
        <div>
            <div class="header-btn">
                <% if (session.getAttribute("user") != null) { %>
                    <a href="/finalproject/">My quizzes</a>
                    <p style="font-size:20px;">Quizzes!..</p>
                    <a href="/finalproject/logout.do">Logout</a>
                <% } else { %>
                    <p style="font-size:20px; margin:0 auto;">Quizzes!..</p>
                <% } %>
            </div>
        </div>
    </div>
</header>
