<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Home</title>
  <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/home.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/footer.css" />
</head>
<body>

    <jsp:include page="header.jsp"/>

    <main class="home-main">
        <!-- Intro Section -->
        <section class="intro-section">
            <div class="intro-left">
                <h2 class="welcome-title">Welcome to the world of<br><span>Board Games</span></h2>
                <img src="${pageContext.request.contextPath}/resources/images/cards.png" alt="Playing Cards" class="cards-image"/>
            </div>
            <div class="intro-right">
                <p class="description">
                <strong>Pawn and Play</strong>,<br/> 
				your go-to hub for all things board games!<br/>
				Dive into a world where fun meets strategy, <br/>
				and every game brings people closer.<br/> 
				From timeless classics to the latest releases, <br/>
				we offer a carefully selected range of games to suit all ages<br/>
				                 and playstyles. <br/>
				Whether you’re into intense tactical battles, lighthearted<br/>
				 party games, or family-friendly adventures, we’ve got <br/>
				you covered. Discover new favorites, shop with ease, <br/>
				and find everything you need to level up your game nights. <br/>
				Let the dice roll and the good times begin!
            </p>
            </div>
        </section>

        <!-- Featured Products Section -->
        <section class="featured-section">
            <h3 class="featured-title">FEATURED PRODUCTS</h3>
            <div class="featured-products">
                <div class="product-card">
                    <img src="${pageContext.request.contextPath}/resources/images/monopoly.jpeg" class="product-img" alt="Monopoly"/>
                    <div class="product-name">Monopoly</div>
                    <div class="product-price">$29.99</div>
                </div>
                <div class="product-card">
                    <img src="${pageContext.request.contextPath}/resources/images/uno.jpg" class="product-img" alt="UNO"/>
                    <div class="product-name">UNO</div>
                    <div class="product-price">$9.99</div>
                </div>
                <div class="product-card">
                    <img src="${pageContext.request.contextPath}/resources/images/chess_set.jpeg" class="product-img" alt="Chess Set"/>
                    <div class="product-name">Chess Set</div>
                    <div class="product-price">$24.99</div>
                </div>
                <div class="product-card">
                    <img src="${pageContext.request.contextPath}/resources/images/snakes.jpeg" class="product-img" alt="Snakes & Ladders"/>
                    <div class="product-name">Snakes and Ladders</div>
                    <div class="product-price">$14.99</div>
                </div>
            </div>
        </section>

        <!-- Game Stack Section 
       <section class="game-stack-section">
            <img src="${pageContext.request.contextPath}/resources/images/games.png" alt="Game Stack"/>
       </section> -->
    </main>

    <jsp:include page="footer.jsp"/>

</body>
</html>