<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>About</title>
<meta charset="UTF-8">
  <title>Login</title>
  <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/about.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/footer.css" />
</head>
<body>
	<jsp:include page="header.jsp"/>
	
	<c:if test="${not empty sessionScope.successMsg}">
    <p class="success-message">${sessionScope.successMsg}</p>
    <c:remove var="successMsg" scope="session" />
</c:if>

	<div class="home-container">
  <section class="about-section">
    <div class="about-left">
      <h2 class="get-to-know-title">GET TO KNOW</h2>
      <h3 class="brand-name">PAWN AND PLAY</h3>
      <img src="${pageContext.request.contextPath}/resources/images/deck.png" alt="Cards" class="about-img" />
    </div>

    <div class="mission-box">
      <h4 class="mission-title">Our Mission</h4>
      <p>
        At Pawn and Play, our mission is simple:<br>
        to bring people together through the joy of board games.<br>
        We believe in the power of play to spark laughter,<br>
        build connections, and create lasting memories.
      </p>
    </div>

    <div class="about-right">
      <img src="${pageContext.request.contextPath}/resources/images/dart.png" alt="Dartboard" class="dart-img" />
    </div>
  </section>

  <section class="offer-section">
    <h3 class="offer-title">WHAT WE OFFER</h3>
    <ul class="offer-list">
      <li>A curated selection of the best board games—from family classics to indie treasures</li>
      <li>Easy browsing and seamless shopping experience</li>
      <li>Reviews, tutorials, and tips to enhance your play</li>
      <li>A growing community of board game fans just like you</li>
    </ul>
  </section>

  <section class="who-section">
    <h4 class="who-title">Who We Are</h4>
    <p class="who-text">
      Founded by a team of lifelong board game lovers, Pawn and Play was born from our shared passion for tabletop gaming.
      What started as a weekend hobby turned into a dream of sharing that excitement with the world.
    </p>
  </section>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>