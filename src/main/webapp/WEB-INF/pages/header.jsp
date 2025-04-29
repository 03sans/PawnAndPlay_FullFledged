<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession"%>
<%@ page import="jakarta.servlet.http.HttpServletRequest"%>

<%
// Initialize necessary objects and variables
HttpSession userSession = request.getSession(false);
String currentUser = (String) (userSession != null ? userSession.getAttribute("username") : null);

String contextPath = request.getContextPath();

String actionUrl;
String formMethod;
String buttonLabel;

if (currentUser != null) {
	actionUrl = contextPath + "/logout";
	formMethod = "post";
	buttonLabel = "Logout";
} else {
	actionUrl = contextPath + "/login";
	formMethod = "get";
	buttonLabel = "Login";
}
%>
    
<style>
	@font-face {
  font-family: 'SortsMillGoudy';
  src: url('${pageContext.request.contextPath}/resources/fonts/SortsMillGoudy-Regular.ttf') format('truetype');
}
</style>

<header class="header">
    <div class="logo">
        <img src="${pageContext.request.contextPath}/resources/images/rook.png" alt="Pawn Logo" class="logo-img" />
        <span class="brand">PAWN AND PLAY</span>
    </div>
    <nav class="nav-links">
        <a href="${pageContext.request.contextPath}/home.jsp" class="nav-item">HOME</a>
        <a href="${pageContext.request.contextPath}/products.jsp" class="nav-item">PRODUCTS</a>
        <a href="${pageContext.request.contextPath}/about" class="nav-item">ABOUT</a>
        <a href="${pageContext.request.contextPath}/contact" class="nav-item">CONTACT</a>
        <a href="${pageContext.request.contextPath}/login.jsp" class="nav-item login-link">LOGIN</a>
    </nav>
</header>