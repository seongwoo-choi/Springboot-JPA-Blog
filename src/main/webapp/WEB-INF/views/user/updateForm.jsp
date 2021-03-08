<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<div class="form-group">
			<label for="username">Username:</label> 
			<input type="text"  value=${principal.user.username } class="form-control" id="username" placeholder="Enter username" readonly>
		</div>

		<div class="form-group">
			<label for="password">Password:</label> 
			<input type="password"  class="form-control" id="password" placeholder="Enter password">
		</div>

		<div class="form-group">
			<label for="email">Email:</label> 
			<input type="email" value=${principal.user.emali } class="form-control" id="email" placeholder="Enter email">
		</div>

	</form>

	<button id="btn-update" class="btn btn-primary">회원 수정 완료</button>
</div>


<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>