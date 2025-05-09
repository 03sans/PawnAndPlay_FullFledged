<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

    <main class="registration-main">
        <section class="registration-box">
            <h1>NEW PLAYER? LET’S GET YOU SET UP!</h1>

            <!-- Display error message -->
            <c:if test="${not empty error}">
                <div class="error-message">${error}</div>
            </c:if>

            <form action="${pageContext.request.contextPath}/registration" method="post" enctype="multipart/form-data">
                <div class="form-row">
                    <div class="form-group">
                        <label>FIRST NAME</label>
                        <input type="text" name="firstName" value="${firstName}" required>
                    </div>
                    <div class="form-group">
                        <label>LAST NAME</label>
                        <input type="text" name="lastName" value="${lastName}" required>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label>USERNAME</label>
                        <input type="text" name="userName" value="${username}" required>
                    </div>
                    <div class="form-group">
                        <label>EMAIL</label>
                        <input type="email" name="email" value="${email}" required>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label>PHONE</label>
                        <input type="tel" name="number" value="${number}" required>
                    </div>
                    <div class="form-group">
                        <label>BIRTHDAY</label>
                        <input type="date" name="dob" value="${dob}" required>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label>PASSWORD</label>
                        <input type="password" name="password" required>
                    </div>
                    <div class="form-group">
                        <label>RE-ENTER</label>
                        <input type="password" name="confirmpassword" required>
                    </div>
                </div>

                <div class="chess-button-wrapper">
                    <div class="profile-and-button">
                        <div class="profile-photo">
                            <label for="image">PROFILE PICTURE</label>
                            <input type="file" id="image" name="image">
                        </div>

                        <div class="form-button">
                            <button type="submit">REGISTER</button>
                        </div>
                    </div>
                    <img src="${pageContext.request.contextPath}/resources/images/chess_pieces.png" alt="Chess Pieces" class="chess-image">
                </div>
            </form>
        </section>

        <section class="slogan-footer">
            <p>START YOUR STORY. ONE GAME AT A TIME!</p>
            <img src="${pageContext.request.contextPath}/resources/images/register.png" alt="Dice" class="dice-image">
        </section>
    </main>

    <jsp:include page="footer.jsp"/>
</body>
</html>