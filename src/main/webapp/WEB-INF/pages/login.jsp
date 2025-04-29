<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Login</title>
  <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/login.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/footer.css" />
</head>
<body>
  <jsp:include page="header.jsp"/>

  <main class="login-main">
    <section class="login-box">
      <h1>WELCOME BACK!</h1>
      <form action="${pageContext.request.contextPath}/login" method="post">
    <label>USERNAME:</label>
    <input type="text" name="username" required>
    <label>PASSWORD:</label>
    <input type="password" name="password" required>

    <button type="submit" class="login-btn">LOGIN</button>
    <p class="or">OR</p>
    <a href="${pageContext.request.contextPath}/registration">
        <button type="button" class="signup-btn">SIGN UP</button>
    </a>
</form>
    </section>

    <section class="login-image">
      <p class="board-text">THE BOARD IS SET.<br>ALL THAT IS MISSING IS <em>YOU!</em></p>
      <img src="${pageContext.request.contextPath}/resources/images/login.png" alt="Game Collage">
    </section>
  </main>

  <jsp:include page="footer.jsp"/>
</body>
</html>