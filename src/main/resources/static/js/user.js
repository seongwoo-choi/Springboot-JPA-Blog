let index = {
	init: function() {
		// 누군가가 btn-save를 동작하면 on안에 오는 파라미터는 이벤트가 일어날 때 무엇을 할 것인지를 적는 것이다.
		$("#btn-save").on("click", () => { // function() {}, ()=>{} this를 바인딩하기 위해서 사용했다.
			this.save();
		});
		
		$("#btn-update").on("click", () => { // function() {}, ()=>{} this를 바인딩하기 위해서 사용했다.
			this.update();
		});
		
	},

	save: function() {
		//alert('user의 save함수 호출');
		let data = {
			// username id 값을 찾는다. 찾은 값을 각각의 변수에 바인딩한다.
			// 어디서 찾느냐??? btn-save 가 동작할 때 입력받은 녀석들의 id 가 username, password, email 인 녀석들의 값이다.
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};

		// console.log(data);  // console에 데이터가 잘 들어온 것을 확인

		// ajax 호출 시 default가 비동기 호출이다.
		// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청을 할 것이다.
		// ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해준다.
		$.ajax({
			// 회원가입 수행 요청 (100초가 걸린다고 가정해도 아래 코드가 실행이 된다.)
			type:"POST", // 메서드가 POST면 INSERT 이다.
			url:"/auth/joinProc",
			data:JSON.stringify(data), // 자바스크립트 오브젝트를 json 문자열 타입으로 변경해준다.
			contentType:"application/json; charset=utf-8", // http body 데이터가 어떤 타입인지(MIME)
			dataType:"json" // 서버로 요청을 해서 응답이 왔을 때 모든 것이 버퍼로 오기 때문에 문자열 상태이고 생긴 것이 json이라면 => javascript 오브젝트로 변경해준다.
		}).done(function(resp) { // dataType 덕분에 json 문자열 타입의 데이터가 자바스크립트 오브젝트로 변경되어서 resp라는 파라미터에 저장되어서 이 함수로 넘어온다.
			// 성공하면 여기 함수를 실행 
			alert("회원가입이 완료되었습니다.");
			// alert(resp); // 즉, UserApiController @PostMapping을 처리하는 함수의 리턴값이 넘어오는 것이다.
			location.href = "/";
		}).fail(function(error) {
			// 실패하면 여기 함수를 실행을 한다.
			alert(JSON.stringify(error));
		}); 

	},
	
	update: function() {
		let data = {
			id:$("#id").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};

		$.ajax({
			type:"PUT", 
			url:"/user",
			data:JSON.stringify(data), 
			contentType:"application/json; charset=utf-8", 
			dataType:"json" 
		}).done(function(resp) {  
			alert("회원수정이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		}); 

	}
	
}

index.init();