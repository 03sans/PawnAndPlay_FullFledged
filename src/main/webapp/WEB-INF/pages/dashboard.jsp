<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <link rel="stylesheet" type="text/css"
        href="${pageContext.request.contextPath}/css/header.css" />
    <link rel="stylesheet" type="text/css"
        href="${pageContext.request.contextPath}/css/dashboard.css" />
    <link rel="stylesheet" type="text/css"
        href="${pageContext.request.contextPath}/css/footer.css" />
</head>
<body>
    <jsp:include page="header.jsp" />

    <div class="dashboard-container">
        <h1>WELCOME! LET'S LOOK AT THE STATS TODAY</h1>

        <h2>Recent Users</h2>
        <table>
            <thead>
                <tr>
                    <th>Username</th>
                    <th>Email</th>
                    <th>Phone</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${recentUsers}">
                    <tr>
                        <td>${user.username}</td>
                        <td>${user.email}</td>
                        <td>${user.number}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="stats-boxes">
            <div class="stat-box">
                <h3>Total Revenue</h3>
                <p>$${totalRevenue}</p>
            </div>
            <div class="stat-box">
                <h3>Total Users</h3>
                <p>${totalUsers}</p>
            </div>
            <div class="stat-box">
                <h3>Total Orders</h3>
                <p>${totalOrders}</p>
            </div>
            <div class="stat-box">
                <h3>Total Games</h3>
                <p>${totalGames}</p>
            </div>
        </div>

        <div class="operations-section">
            <h2>PEEK AT THE INVENTORY</h2>
            <form action="${pageContext.request.contextPath}/operations" method="get">
                <button type="submit" class="operations-button">OPERATIONS</button>
            </form>
        </div>
    </div>

    <jsp:include page="footer.jsp" />
</body>
</html>