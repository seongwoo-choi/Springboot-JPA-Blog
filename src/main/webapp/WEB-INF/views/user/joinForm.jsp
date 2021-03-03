<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>Bootstrap Example</title>
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

				<div class="form-group">
					<label for="username">Username:</label> <input type="text" class="form-control" id="username" placeholder="Enter username" name="username">
				</div>
				
				<div class="form-group">
					<label for="password">Password:</label> <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
				</div>

				<div class="form-group">
					<label for="email">Email:</label> <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
				</div>

				
		</form>
		
		<button id="btn-save" class="btn btn-primary">회원 가입</button>
	</div>
	
	
	<script src="/blog/js/user.js"></script>
	<%@ include file="../layout/footer.jsp"%>