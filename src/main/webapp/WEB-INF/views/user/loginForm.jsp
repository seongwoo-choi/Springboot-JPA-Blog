<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!--  내가 있는 위치에서 한 칸 위로 올라가서 상대 경로로 찾는다. -->
<%@ include file="../layout/header.jsp"%>


<div class="container">
	<form action="#" method="POST">
	
		<!-- 로그인 한다는 것은 select 한다는 것!!! -->
		<!-- 데이터를 insert 하기 때문에 POST 방식이다. -->
		<div class="form-group">
			<div class="form-group">
				<label for="Username">Username:</label> 
				<input type="username" name = "username" class="form-control" id="text" placeholder="Enter username">
			</div>

			<div class="form-group">
				<label for="password">Password:</label> 
				<input type="password" name = "password" class="form-control" id="password" placeholder="Enter password">
			</div>

			<div class="form-group form-check">
				<label class="form-check-label"> 
				<input class="form-check-input" name = "remember" type="checkbox"> Remember me
				</label>
			</div>
			
		</div>
		<button id="btn-login"  class="btn btn-primary">로그인</button>
	</form>
</div>

<%@ include file="../layout/footer.jsp"%>