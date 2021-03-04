<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!--  내가 있는 위치에서 한 칸 위로 올라가서 상대 경로로 찾는다. -->
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<!DOCTYPE html>
	<html lang="en">
<head>
<title>My Blog</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

</head>
<body>

	<div class="container">

		<!DOCTYPE html>
		<html lang="en">
<head>
<title>My Blog</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="container">
		<form>
			<!-- 로그인 한다는 것은 select 한다는 것!!! -->
			<!-- 데이터를 insert 하기 때문에 POST 방식이다. -->
			<div class="form-group">

				<div class="form-group">
					<label for="Username">Username:</label> <input type="username" class="form-control" id="text" placeholder="Enter username" name="username">
				</div>

				<div class="form-group">
					<label for="password">Password:</label> <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
				</div>

				<div class="form-group form-check">
					<label class="form-check-label"> <input class="form-check-input" type="checkbox" name="remember"> Remember me
					</label>
				</div>
			</div>
		</form>

		<button id="btn-login" class="btn btn-primary">로그인</button>

	</div>

	<script src="/blog/js/user.js"></script>
	<%@ include file="../layout/footer.jsp"%>