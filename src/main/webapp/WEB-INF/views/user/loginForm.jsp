<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!--  내가 있는 위치에서 한 칸 위로 올라가서 상대 경로로 찾는다. -->
<%@ include file="../layout/header.jsp"%>


<div class="container">
	<form action="/auth/loginProc" method="POST">
		<!-- 로그인 한다는 것은 select 한다는 것!!! -->

		<div class="form-group">
			<label for="Username">Username:</label> 
			<input type="username" name="username" class="form-control" id="text" placeholder="Enter username">
		</div>

		<div class="form-group">
			<label for="password">Password:</label> 
			<input type="password" name="password" class="form-control" id="password" placeholder="Enter password">
		</div>

		<button id="btn-login" class="btn btn-primary">로그인</button>
	</form>
</div>

<%@ include file="../layout/footer.jsp"%>