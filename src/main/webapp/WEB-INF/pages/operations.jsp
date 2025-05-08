<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Operations</title>
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/css/header.css" />
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/css/operations.css" />
	<link rel="stylesheet" type="text/css"
		href="${pageContext.request.contextPath}/css/footer.css" />
</head>
<body>

<jsp:include page="header.jsp"/>

<div class="container">

	<h1>Admin Operations</h1>
	
	<!-- VIEW ALL GAMES -->
	<section class="table-section">
		<h2>All Games</h2>
		<table border="1">
			<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Level</th>
					<th>Genre</th>
					<th>Age</th>
					<th>Price</th>
					<th>Stock</th>
					<th>Brand</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="game" items="${games}">
					<tr>
						<td>${game.gameID}</td>
						<td>${game.gamename}</td>
						<td>${game.level}</td>
						<td>${game.genre}</td>
						<td>${game.age}</td>
						<td>${game.price}</td>
						<td>${game.stock}</td>
						<td>${game.brand}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
	

	<div class="form-row">
	<!-- ADD GAME -->
	<section class="form-section">
		<h2>Add Game</h2>
		<form method="post" action="operations">
			<input type="hidden" name="action" value="add" />
			<input type="text" name="gamename" placeholder="Game Name" required />
			<input type="text" name="level" placeholder="Level" required />
			<input type="text" name="genre" placeholder="Genre" required />
			<input type="number" name="age" placeholder="Age" required />
			<input type="number" step="0.01" name="price" placeholder="Price" required />
			<input type="number" name="stock" placeholder="Stock" required />
			<input type="text" name="brand" placeholder="Brand" required />
			<button type="submit">Add Game</button>
		</form>
	</section>

	<!-- UPDATE GAME -->
	<section class="form-section">
		<h2>Update Game</h2>
		<form method="post" action="operations">
			<input type="hidden" name="action" value="update" />
			<input type="number" name="gameID" placeholder="Game ID" required />
			<input type="text" name="gamename" placeholder="Game Name" required />
			<input type="text" name="level" placeholder="Level" required />
			<input type="text" name="genre" placeholder="Genre" required />
			<input type="number" name="age" placeholder="Age" required />
			<input type="number" step="0.01" name="price" placeholder="Price" required />
			<input type="number" name="stock" placeholder="Stock" required />
			<input type="text" name="brand" placeholder="Brand" required />
			<button type="submit">Update Game</button>
		</form>
	</section>

	<!-- DELETE GAME -->
	<section class="form-section">
		<h2>Delete Game</h2>
		<form method="post" action="operations">
			<input type="hidden" name="action" value="delete" />
			<input type="number" name="gameID" placeholder="Game ID" required />
			<button type="submit">Delete Game</button>
		</form>
	</section>
</div>

<!-- SEARCH -->
<section class="form-section">
	<h2>Search Game by Stock</h2>
	<form method="post" action="operations">
		<input type="hidden" name="action" value="search" />
		<input type="number" name="stock" placeholder="Stock Quantity" required />
		<button type="submit">Search</button>
	</form>

	<c:if test="${not empty searchResult}">
		<div class="search-result">
			<h3>Search Result</h3>
			<p><strong>Game ID:</strong> ${searchResult.gameID}</p>
			<p><strong>Name:</strong> ${searchResult.gamename}</p>
			<p><strong>Level:</strong> ${searchResult.level}</p>
			<p><strong>Genre:</strong> ${searchResult.genre}</p>
			<p><strong>Age:</strong> ${searchResult.age}</p>
			<p><strong>Price:</strong> ${searchResult.price}</p>
			<p><strong>Stock:</strong> ${searchResult.stock}</p>
			<p><strong>Brand:</strong> ${searchResult.brand}</p>
		</div>
	</c:if>

	<c:if test="${not empty notFound}">
		<p class="not-found">${notFound}</p>
	</c:if>
</section>

	
</div>

<jsp:include page="footer.jsp"/>

</body>
</html>