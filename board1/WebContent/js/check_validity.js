/**
 * 
 */

$(function(){
				
		$('#regForm').submit(function(){
			
			var uid = $('input[name=uid]').val();
			var pw1 = $('input[name=pw1]').val();
			var pw2 = $('input[name=pw2]').val();
			var name = $('input[name=name]').val();
			
			
			if(!isUidOk){
				alert('아이디가 이미 사용중 입니다.');
				return false;
			}
			
			if(!isNickOk){
				alert('닉네임이 이미 사용중 입니다.');
				return false;
			}
			
			if(!isEmailOk){
				alert('이미 사용중인 이메일 입니다.');
				return false;
			}
			
			if(!isHpOk){
				alert('이미 사용중 번호입니다.');
				return false;
			}
			
			
			
			// 아이디 자리수 체크
			if(uid.length < 4){
				alert('아이디는 최소 4자리 이상이여야 합니다.');
				return false;
			}
			// 비번 일치여부 확인
			if(pw1 != pw2){
				alert('비밀번호가 서로 일치하지 않습니다.');
				return false;
			}
			// 이름 영문 숫자 포함여부 체크
			/*if(){
				return false;
			}*/
			
			return true;
			
		})
		
	});