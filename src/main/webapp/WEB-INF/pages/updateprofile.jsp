<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Profile</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/updateprofile.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css" />
</head>
<body>

<c:if test="${not empty error}">
  <div style="color: red; margin-bottom: 10px;">
    ${error}
  </div>
</c:if>

<jsp:include page="header.jsp"/>

<main class="registration-main">
  <section class="registration-box">
    <h1>UPDATE YOUR PROFILE HOW YOU WANT</h1>

    <form action="${pageContext.request.contextPath}/updateprofile" method="post">
      <div class="form-row">
        <div class="form-group">
          <label>FIRST NAME</label>
          <input type="text" name="Firstname" value="${firstName}" required>
        </div>
        <div class="form-group">
          <label>LAST NAME</label>
          <input type="text" name="Lastname" value="${lastName}" required>
        </div>
      </div>

      <div class="form-row">
        <div class="form-group">
          <label>USERNAME</label>
          <input type="text" name="Username" value="${username}" required>
        </div>
        <div class="form-group">
          <label>EMAIL</label>
          <input type="email" name="Email" value="${email}" required>
        </div>
      </div>

      <div class="form-row">
        <div class="form-group">
          <label>PHONE</label>
          <input type="tel" name="Phone" value="${number}" required>
        </div>
        <div class="form-group">
          <label>PASSWORD</label>
          <input type="password" name="Password" >
        </div>
      </div>

      <div class="chess-button-wrapper">
        <div class="form-button">
          <button type="submit">UPDATE</button>
        </div>
      </div>
    </form>
  </section>
</main>

<jsp:include page="footer.jsp"/>
</body>
</html>