<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!--  내가 있는 위치에서 상대 경로로 찾는다. -->
<%@ include file="layout/header.jsp"%>

<!-- request 정보가 넘어올 때 EL 표현식으로 데이터를 받을 수 있다. ${board.content}로 받는 이유는 boards의 타입이 Page타입이기 때문에 List 형태로 타입 변환을 해줘야 한다. 그래서 .content 사용 -->
<div class="container">

	<c:forEach var="board" items="${boards.content}">>
	<div class="card m-2">
			<div class="card-body">
				<h4 class="card-title">${board.title}</h4>
				<a href="#" class="btn btn-primary stretched-link">상세보기</a>
			</div>
		</div>
	</c:forEach>

	<ul class="pagination justify-content-center">

		<c:choose>
			<c:when test="${boards.first}">
				<li class="page-item disabled"><a class="page-link" href="?page=${boards.number -1}">Previous</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${boards.number -1}">Previous</a></li>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${boards.last}">
				<li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
			</c:otherwise>
		</c:choose>
		
	</ul>

</div>

<%@ include file="layout/footer.jsp"%>