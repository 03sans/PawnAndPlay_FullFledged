<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
  <title>Products</title>
  <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/products.css" />
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/footer.css" />
</head>
<body>
	<jsp:include page="header.jsp"/>
	 <main class="home-main">
    <section class="featured-section">
        <h1 class="featured-title">WE HAVE SOMETHING FOR EVERYONE</h1>
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
                <img src="${pageContext.request.contextPath}/resources/images/snakes.jpeg" class="product-img" alt="Snakes and Ladders"/>
                <div class="product-name">Snakes and Ladders</div>
                <div class="product-price">$14.99</div>
            </div>
            <div class="product-card">
                <img src="${pageContext.request.contextPath}/resources/images/scrabble1.jpeg" class="product-img" alt="Scrabble"/>
                <div class="product-name">Scrabble</div>
                <div class="product-price">$21.99</div>
            </div>
            <div class="product-card">
                <img src="${pageContext.request.contextPath}/resources/images/clue.jpeg" class="product-img" alt="Clue"/>
                <div class="product-name">Clue</div>
                <div class="product-price">$19.99</div>
            </div>
            <div class="product-card">
                <img src="${pageContext.request.contextPath}/resources/images/jenga.jpeg" class="product-img" alt="Jenga"/>
                <div class="product-name">Jenga</div>
                <div class="product-price">$16.99</div>
            </div>
            <div class="product-card">
                <img src="${pageContext.request.contextPath}/resources/images/catan.jpeg" class="product-img" alt="Catan"/>
                <div class="product-name">Catan</div>
                <div class="product-price">$34.99</div>
            </div>
            <div class="product-card">
                <img src="${pageContext.request.contextPath}/resources/images/battleship.jpeg" class="product-img" alt="Battleship"/>
                <div class="product-name">Battleship</div>
                <div class="product-price">$22.49</div>
            </div>
            <div class="product-card">
                <img src="${pageContext.request.contextPath}/resources/images/twister.jpeg" class="product-img" alt="Twister"/>
                <div class="product-name">Twister</div>
                <div class="product-price">$17.49</div>
            </div>
            <div class="product-card">
                <img src="${pageContext.request.contextPath}/resources/images/trivial_pursuit.jpeg" class="product-img" alt="Trivial Pursuit"/>
                <div class="product-name">Trivial Pursuit</div>
                <div class="product-price">$27.99</div>
            </div>
            <div class="product-card">
                <img src="${pageContext.request.contextPath}/resources/images/ludo.jpeg" class="product-img" alt="Ludo"/>
                <div class="product-name">Ludo</div>
                <div class="product-price">$11.49</div>
            </div>
            <div class="product-card">
                <img src="${pageContext.request.contextPath}/resources/images/checkers.jpeg" class="product-img" alt="Checkers"/>
                <div class="product-name">Checkers</div>
                <div class="product-price">$12.99</div>
            </div>
            <div class="product-card">
                <img src="${pageContext.request.contextPath}/resources/images/risk.jpeg" class="product-img" alt="Risk"/>
                <div class="product-name">Risk</div>
                <div class="product-price">$31.99</div>
            </div>
            <div class="product-card">
                <img src="${pageContext.request.contextPath}/resources/images/connect4.jpeg" class="product-img" alt="Connect 4"/>
                <div class="product-name">Connect 4</div>
                <div class="product-price">$13.99</div>
            </div>
        </div>
    </section>
	</main>
	<jsp:include page="footer.jsp"/>
	<!-- Product Modal -->
<div id="product-modal" class="modal">
  <div class="modal-content">
    <span class="close">&times;</span>
    <img id="modal-img" src="" alt="Product Image" class="modal-img">
    <h2 id="modal-name">Product Name</h2>
    <p id="modal-desc">Product description goes here.</p>
    <div class="modal-price" id="modal-price">$0.00</div>
    <button id="add-to-cart-btn">Add to Cart</button>
  </div>
</div>

<script>
  const modal = document.getElementById('product-modal');
  const modalImg = document.getElementById('modal-img');
  const modalName = document.getElementById('modal-name');
  const modalDesc = document.getElementById('modal-desc');
  const modalPrice = document.getElementById('modal-price');
  const closeBtn = document.querySelector('.close');

  const productDescriptions = {
		    "Monopoly": "Buy, trade, and develop properties in this classic real estate game.",
		    "UNO": "Fast-paced card game of matching colors and numbers.",
		    "Chess Set": "A strategy game of kings, queens, and tactics.",
		    "Snakes & Ladders": "A classic race game of luck, climbing ladders and sliding down snakes.",
		    "Scrabble": "Build words and score points with this iconic crossword-style board game.",
		    "Clue": "Solve the mystery by deducing the who, what, and where of the crime.",
		    "Jenga": "Stack wooden blocks and remove them carefully without toppling the tower.",
		    "Catan": "Strategic resource management and trading in a race to build settlements and roads.",
		    "Battleship": "A naval combat game of strategy, where you try to sink your opponent's fleet.",
		    "Twister": "A physical game of balance and flexibility—place hands and feet on colored dots.",
		    "Trivial Pursuit": "Answer trivia questions in various categories to collect all the wedges and win.",
		    "Ludo": "A fast-paced game of moving pieces around the board to get them all home safely.",
		    "Checkers": "A two-player strategy game of diagonal moves and jumping to capture opponents.",
		    "Risk": "Conquer the world by deploying armies and engaging in battles to control territories.",
		    "Connect 4": "Drop discs into a vertical grid and connect four in a row to win."
		};

  document.querySelectorAll('.product-card').forEach(card => {
    card.addEventListener('click', () => {
      const img = card.querySelector('img').src;
      const name = card.querySelector('.product-name').innerText;
      const price = card.querySelector('.product-price').innerText;

      modalImg.src = img;
      modalName.innerText = name;
      modalDesc.innerText = productDescriptions[name] || "No description available.";
      modalPrice.innerText = price;
      modal.style.display = 'block';
    });
  });

  closeBtn.onclick = () => {
    modal.style.display = 'none';
  };

  window.onclick = (event) => {
    if (event.target == modal) {
      modal.style.display = 'none';
    }
  };
</script>
</body>
</html>