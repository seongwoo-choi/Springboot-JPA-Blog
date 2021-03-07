<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!--  내가 있는 위치에서 상대 경로로 찾는다. -->
<%@ include file="../layout/header.jsp"%>



<div class="container">

	<form>

		<div class="form-group">
			<input type="username" class="form-control" id="title" placeholder="Enter title">
		</div>

		<div class="form-group">
			<textarea class="form-control summernote" rows="5" id="content"></textarea>
		</div>
	</form>
	<button id="btn-save" class="btn btn-primary">글쓰기 완료</button>

</div>
<script>
  $('.summernote').summernote({
    tabsize: 2,
    height: 300
  });
</script>
 <script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>



