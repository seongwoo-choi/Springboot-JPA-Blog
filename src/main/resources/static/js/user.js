let index  = {
	init: function(){
		// 누군가가 btn-save를 동작하면 on안에 오는 파라미터는 이벤트가 일어날 때 무엇을 할 것인지를 적는 것이다.
		$("#btn-save").on("click",  ()=>{
			this.save();
		});
	},
		
	save: function(){
		//alert('user의 save함수 호출');
		let data = {
			// username id 값을 찾는다. 찾은 값을 각각의 변수에 바인딩한다.
			// 어디서 찾느냐??? btn-save 가 동작할 때 입력받은 녀석들의 id 가 username, password, email 인 녀석들의 값이다.
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		
		// console.log(data);  // console에 데이터가 잘 들어온 것을 확인
		
		// ajax 요청
		$.ajax().done().fail(); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청을 할 것이다.
	}
}

index.init();