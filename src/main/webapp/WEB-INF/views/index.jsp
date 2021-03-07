<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!--  내가 있는 위치에서 상대 경로로 찾는다. -->
<%@ include file="layout/header.jsp"%>

<div class="container">

<c:forEach var ="board" items="${boards }">>
	<div class="card m-2">
		<div class="card-body">
			<h4 class="card-title">${board.title}</h4>
			<a href="#" class="btn btn-primary stretched-link">상세보기</a>
		</div>
	</div>
</c:forEach>



	


</div>

<%@ include file="layout/footer.jsp"%>