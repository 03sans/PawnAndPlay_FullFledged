<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/registration.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/footer.css" />
</head>
<body>
    <jsp:include page="header.jsp"/>

    <section class="form-container">
        <h2>NEW PLAYER? LET’S GET YOU SET UP!</h2>
        <form action="${contextPath}/register" method="post">
            <div class="form-grid">
                <label>FIRST NAME</label>
                <input type="text" name="firstName" required>

                <label>LAST NAME</label>
                <input type="text" name="lastName" required>

                <label>USERNAME</label>
                <input type="text" name="username" required>

                <label>EMAIL</label>
                <input type="email" name="email" required>

                <label>BIRTHDAY</label>
                <input type="date" name="dob" required>

                <label>PHONE</label>
                <input type="tel" name="phone" required>

                <label>PASSWORD</label>
                <input type="password" name="password" required>

                <label>RE-ENTER</label>
                <input type="password" name="confirmPassword" required>
            </div>
            <button type="submit" class="login-btn">LOGIN</button>
        </form>
        
        <div class="footer-center">START YOUR STORY. ONE GAME AT A TIME!</div>

        <div class="chess-row">
            <img src="${contextPath}/resources/images/chess_pieces.png" alt="Chess Row">
        </div>
    </section>

    <jsp:include page="footer.jsp"/>
</body>
</html>