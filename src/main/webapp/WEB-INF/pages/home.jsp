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
<style>
    @font-face {
  font-family: 'SortsMillGoudy';
  src: url('${pageContext.request.contextPath}/resources/fonts/AlexBrush-Regular.ttf') format('truetype');
}</style>
    <meta charset="UTF-8">
    <title>home</title>
    <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/home.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/footer.css" />
</head>
<body>
    <jsp:include page="header.jsp"/>

    <div class="home-container">
  <div class="welcome-section">
    <div class="welcome-text">
      <h2>Welcome to the world of</h2>
      <h1>Board Games</h1>
    </div>
    <img src="${pageContext.request.contextPath}/resources/images/cards.png" alt="Playing Cards" class="cards-img" />
    <div class="intro-text">
      <p><strong>Pawn & Play,</strong><br>
        your go-to hub for all things board games!<br>
        Dive into a world where fun meets strategy,<br>
        and every game brings people closer.<br>
        From timeless classics to the latest releases,<br>
        we offer a carefully selected range of games to suit all ages<br>
        and playstyles.<br>
        Whether you’re into intense tactical battles, lighthearted<br>
        party games, or family-friendly adventures, we’ve got<br>
        you covered. Discover new favorites, shop with ease,<br>
        and find everything you need to level up your game nights.<br>
        Let the dice roll and the good times begin!
      </p>
    </div>
  </div>

  <div class="featured-section">
    <h2 class="featured-title">FEATURED PRODUCTS</h2>
    <div class="product-cards">
      <div class="product-card">
        <img src="${pageContext.request.contextPath}/resources/images/monopoly.jpeg" alt="Product 1" class="product-img">
      </div>
      <div class="product-card">
        <img src="${pageContext.request.contextPath}/resources/images/snakes.jpeg" class="product-img">
      </div>
      <div class="product-card">
        <img src="${pageContext.request.contextPath}/resources/images/chess_set.jpeg" class="product-img">
      </div>
      <div class="product-card">
        <img src="${pageContext.request.contextPath}/resources/images/uno.jpeg" class="product-img">
      </div>
    </div>
    <img src="${pageContext.request.contextPath}/resources/images/games.png" alt="Stack of Games" class="games-stack-img" />
  </div>
</div>

    <jsp:include page="footer.jsp"/>
</body>
</html>