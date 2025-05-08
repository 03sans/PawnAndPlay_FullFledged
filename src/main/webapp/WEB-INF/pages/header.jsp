<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
HttpSession userSession = request.getSession(false);
String currentUser = (String) (userSession != null ? userSession.getAttribute("username") : null);
request.setAttribute("currentUser", currentUser);
%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<header class="header">
    <div class="logo">
        <img src="${contextPath}/resources/images/rook.png" alt="Pawn Logo" class="logo-img" />
        <span class="brand">PAWN AND PLAY</span>
    </div>
    <nav class="nav-links">
        <a href="${contextPath}/home" class="nav-item">HOME</a>
        <a href="${contextPath}/products" class="nav-item">PRODUCTS</a>
        <c:if test="${currentUser == 'admin'}">
    <a href="${contextPath}/products" class="nav-item">DASHBOARD</a>
		</c:if>
        <a href="${contextPath}/about" class="nav-item">ABOUT</a>
        <a href="${contextPath}/contact" class="nav-item">CONTACT</a>
        <div>
            <c:choose>
                <c:when test="${not empty currentUser}">
                    <form action="${contextPath}/logout" method="post">
                        <input type="submit" class="nav-item" value="LOGOUT">
                    </form>
                </c:when>
                <c:otherwise>
                    <a href="${contextPath}/login" class="nav-item">LOGIN</a>
                </c:otherwise>
            </c:choose>
        </div>
    </nav>
</header>