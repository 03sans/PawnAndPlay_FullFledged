<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="now" class="java.util.Date" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Profile</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/profile.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css">
</head>
<body>

<jsp:include page="header.jsp" />

<div class="profile-container">
    <div class="profile-card">
        <h2 class="welcome-text">🎲 LET’S ROLL THE DICE, <c:out value="${user.username}" /></h2>

        <div class="profile-picture">
           <c:choose>
    <c:when test="${not empty user.image}">
        <img src="${pageContext.request.contextPath}/resources/images/${user.image}?v=${now.time}" alt="Profile Picture" id="profile-image">
    </c:when>
    <c:otherwise>
        <img src="${pageContext.request.contextPath}/resources/images/default.png" alt="Default Profile Picture" id="profile-image">
    </c:otherwise>
</c:choose>
            
        </div> 

        <div class="profile-info">
        
            <p><strong>USERNAME:</strong> <c:out value="${user.username}" /></p>
            <p><strong>EMAIL:</strong> <c:out value="${user.email}" /></p>
            <p><strong>PHONE:</strong> <c:out value="${user.number}" /></p>
            <p><strong>BIRTHDAY:</strong> <c:out value="${user.dob}" /></p>
        </div>
		<form action="${pageContext.request.contextPath}/updateprofile" method="get">
    <button class="update-button">UPDATE PROFILE</button>
</form>
    </div>
</div>

<jsp:include page="footer.jsp" />

</body>
</html>