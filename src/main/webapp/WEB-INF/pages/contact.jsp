<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Contact</title>
  <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/contact.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/footer.css" />
</head>
<body>
<jsp:include page="header.jsp" />

<main class="contact-page">
  <div class="contact-content">
    <!-- Left side: Team members -->
    <section class="team-section">
      <h2 class="section-title">MEET OUR TEAM</h2>
      <div class="team-members">
        <div class="member">
          <img src="${pageContext.request.contextPath}/resources/images/mario.jpeg" alt="Mario">
          <h3>MARIO</h3>
          <p class="email">mr.mario@gmail.com</p>
          <p class="phone">9800021000</p>
          <p class="desc">
            Mario leads the charge on user interface and front-end design, ensuring the site is as smooth and fun to use as your favorite board game. A natural problem-solver, he jumps on bugs and squashes them with style.
          </p>
        </div>
        <div class="member">
          <img src="${pageContext.request.contextPath}/resources/images/luigi.jpg" alt="Luigi">
          <h3>LUIGI</h3>
          <p class="email">mr.luigi@gmail.com</p>
          <p class="phone">9800021001</p>
          <p class="desc">
            Luigi is the backbone of our server-side logic and database management. Quietly powerful, he keeps everything running behind the scenes — making sure the shop never skips a beat.
          </p>
        </div>
      </div>
    </section>

    <!-- Right side: Feedback form -->
    <section class="feedback-section">
      <h2 class="section-title">FEEDBACK FORM</h2>
      <form class="feedback-form">
        <label for="name">NAME</label>
        <input type="text" id="name" name="name" required>

        <label for="email">EMAIL</label>
        <input type="email" id="email" name="email" required>

        <label for="feedback">FEEDBACK</label>
        <textarea id="feedback" name="feedback" rows="4" required></textarea>

        <button type="submit" class="submit-btn">SUBMIT</button>
      </form>
    </section>
  </div>

  <!-- Bottom location section (separate from the dark green background) -->
  <section class="location-section">
    <h2 class="section-title location-title">WHERE TO FIND US</h2>
    <div class="location-box">
      <p>
        We’re currently based in the vibrant world of the web — accessible to board game lovers everywhere! While we don’t have a physical store just yet, our digital doors are always open.
      </p>
      <p><strong>Visit Us At:</strong><br>
        123 Gameboard Lane, Level-Up City, PL 45678<br>
        Open Mon–Fri | 9 AM – 6 PM
      </p>
      <p><strong>Coordinates:</strong><br>
        Mushroom Kingdom, Cloud Avenue — just past Bowser’s Castle but before Rainbow Road
      </p>
    </div>
  </section>
</main>

<jsp:include page="footer.jsp" />
</body>
</html>