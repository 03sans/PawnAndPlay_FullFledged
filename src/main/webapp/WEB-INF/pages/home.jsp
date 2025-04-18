<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
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

    <div class="main-section">
        <img src="${pageContext.request.contextPath}/resources/images/scrabble.png" alt="Scrabble Board" class="scrabble-img" />

        <div class="center-content">
            <h2>Welcome to the world of<br><span>Board Games</span></h2>

            <img src="${pageContext.request.contextPath}/resources/images/cards.png" alt="Cards" class="cards-img" />

            <p class="description">
                <strong>Pawn and Play,</strong><br>
                your go-to hub for all things board games!<br>
                Dive into a world where fun meets strategy,<br>
                and every game brings people closer.<br>
                From timeless classics to the latest releases,<br>
                we offer a carefully selected range of games to suit all ages<br>
                and playstyles.<br><br>

                Whether you're into intense tactical battles, lighthearted party games, or family-friendly adventures, we've got you covered. Discover new favorites, shop with ease, and find everything you need to level up your game nights.<br>
                Let the dice roll and the good times begin!
            </p>
        </div>

        <img src="${pageContext.request.contextPath}/resources/images/games.png" alt="Stack of Games" class="stack-img" />
    </div>

    <jsp:include page="footer.jsp"/>
</body>
</html>