<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<<<<<<< HEAD

=======
<<<<<<< HEAD

=======
>>>>>>> be8386b914d7722fa601a3c71114ad5334248cb2
>>>>>>> 52d752a39d59d916720cc07b6d15e5bf1b2b517f
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 52d752a39d59d916720cc07b6d15e5bf1b2b517f
		<input type="hidden" id="id" value="${principal.user.id}" />
		<div class="form-group">
			<label for="username">Username</label> 
			<input type="text" value="${principal.user.username }" class="form-control" placeholder="Enter username" id="username" readonly>
		</div>
		
		<div class="form-group">
			<label for="password">Password</label> 
			<input type="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		
		<div class="form-group">
			<label for="email">Email</label> 
			<input type="email" value="${principal.user.email}" class="form-control" placeholder="Enter email" id="email">
		</div>
		
	</form>
	<button id="btn-update" class="btn btn-primary">회원수정완료</button>

</div>

<<<<<<< HEAD
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>


=======
=======
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


>>>>>>> be8386b914d7722fa601a3c71114ad5334248cb2
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>
>>>>>>> 52d752a39d59d916720cc07b6d15e5bf1b2b517f
