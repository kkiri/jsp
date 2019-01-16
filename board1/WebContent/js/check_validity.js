/**
 * 아이디, 닉네임, 이메일, 휴대폰 중복체크
 */


// 아이디 중복 체크

	/*$(document).ready(function(){}); 이거 줄이면 밑에꺼//jQuery = $*/
	$(function(){
		
		/*var input = $('input[name=uid]');
		input.focusout(function(){
			alert('포커스 아웃!!');
		});*/
		$('input[name=uid]').focusout(function(){
			
			var tag = $(this);
			var uid = tag.val();
			
			$.ajax({
				
				url:'./proc/checkUid.jsp?uid='+uid,
				type : 'get',
				dataType : 'json',
				success : function(data){ //callback함수
					
					if(data.result == 1){
						$('.resultId').css('color', 'red').text("이미 사용중인 아이디 입니다.");
						tag.focus();
					}else{
						$('.resultId').css('color', 'green').text("사용 가능한 아이디 입니다.");
					}
				}
			});
		});
		// 닉네임 중복체크
		$('input[name=nick]').focusout(function(){
			
			var tag = $(this);
			var nick = tag.val();
			
			$.ajax({
				
				url:'./proc/checkNick.jsp?nick='+nick,
				type : 'get',
				dataType : 'json',
				success : function(data){ //callback함수
					
					if(data.result == 1){
						$('.resultNick').css('color', 'red').text("이미 사용중인 닉네임 입니다.");
						tag.focus();
					}else{
						$('.resultNick').css('color', 'green').text("사용 가능한 닉네임 입니다.");
					}
				}
			});
		});
		
		// 이메일 중복체크
			$('input[name=email]').focusout(function(){
			
			var tag = $(this);
			var email = tag.val();
			
			$.ajax({
				
				url:'./proc/checkEmail.jsp?email='+email,
				type : 'get',
				dataType : 'json',
				success : function(data){ //callback함수
					
					if(data.result == 1){
						$('.resultEmail').css('color', 'red').text("이미 사용중인 이메일 입니다.");
						tag.focus();
					}else{
						$('.resultEmail').css('color', 'green').text("사용 가능한 이메일 입니다.");
					}
				}
			});
		});
		
	
		
		// 휴대폰 중복체크
			$('input[name=hp]').focusout(function(){
				
				var tag = $(this);
				var hp = tag.val();
				
				$.ajax({
					
					url:'./proc/checkHp.jsp?hp='+hp,
					type : 'get',
					dataType : 'json',
					success : function(data){ //callback함수
						
						if(data.result == 1){
							$('.resultHp').css('color', 'red').text("이미 사용중인 전화번호 입니다.");
							tag.focus();
						}else{
							$('.resultHp').css('color', 'green').text("사용 가능한 전화번호 입니다.");
						}
					}
				});
			});
		
	});//약식
