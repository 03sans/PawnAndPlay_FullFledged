<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<style>
    @font-face {
  font-family: 'SortsMillGoudy';
  src: url('${pageContext.request.contextPath}/resources/fonts/SortsMillGoudy-Regular.ttf') format('truetype');
}</style>
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

    <main class="registration-main">
    <section class="registration-box">
      <h1>NEW PLAYER? LET’S GET YOU SET UP!</h1>
      <form action="${pageContext.request.contextPath}/register" method="post">
        <div class="form-row">
          <div class="form-group">
            <label>FIRST NAME</label>
            <input type="text" name="firstName" required>
          </div>
          <div class="form-group">
            <label>LAST NAME</label>
            <input type="text" name="lastName" required>
          </div>
        </div>

        <div class="form-row">
          <div class="form-group">
            <label>USERNAME</label>
            <input type="text" name="username" required>
          </div>
          <div class="form-group">
            <label>EMAIL</label>
            <input type="email" name="email" required>
          </div>
        </div>

        <div class="form-row">
          <div class="form-group">
            <label>BIRTHDAY</label>
            <input type="date" name="dob" required>
          </div>
          <div class="form-group">
            <label>PHONE</label>
            <input type="tel" name="number" required>
          </div>
        </div>

        <div class="form-row">
          <div class="form-group">
            <label>PASSWORD</label>
            <input type="password" name="password" required>
          </div>
          <div class="form-group">
            <label>RE-ENTER</label>
            <input type="password" name="confirmPassword" required>
          </div>
        </div>

        <div class="form-button">
          <button type="submit">LOGIN</button>
        </div>
      </form>

      <img src="${pageContext.request.contextPath}/resources/images/chess_pieces.png" alt="Chess Pieces" class="chess-image">
    </section>

    <section class="slogan-footer">
      <p>START YOU STORY. ONE GAME AT A TIME!</p>
      <img src="${pageContext.request.contextPath}/resources/images/register.png" alt="Dice" class="dice-image">
    </section>
  </main>

    <jsp:include page="footer.jsp"/>
</body>
</html>