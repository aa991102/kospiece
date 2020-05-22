$(function(){
	$('#helpBtn').click(function(){
		$('#help').attr('class','helpShow');
	})
	
	$('#helpClose').click(function(){
		$('#help').attr('class','helpDiv');
	})
	
	$('#helpBtn2').click(function(){
		$('#help2').attr('class','helpShow2');
	})
	
	$('#helpClose').click(function(){
		$('#help2').attr('class','helpDiv');
	})
	
	
	//myInfo 재확인 버튼!!
	//회원정보 수정시 재확인
	$('#infoUpdateFrm').submit(function(){
		if (confirm("정말 수정하시겠습니까?") == true){ //확인
		}else{ //취소
			return false;
		}
	})
	//비밀번호 변경시 재확인
	$('#passChangeFrm').submit(function(){
		if (confirm("정말 수정하시겠습니까?") == true){ //확인
		}else{ //취소
			return false;
		}
	})	
	//회원 탈퇴시 재확인
	$('#withdrawalFrm').submit(function(){
		if (confirm("정말 탈퇴하시겠습니까?") == true){ //확인
		}else{ //취소
			return false;
		}
	})
	
	//myInfo 취소 버튼!!
	//회원정보수정/비밀번호변경/회원탈퇴 취소시 재확인
	$('#Nope').click(function(){
		if (confirm("정말 취소하시겠습니까?\n작성한 내용은 모두 사라집니다.") == true){ //확인
		}else{ //취소
			return false;
		}
	})
	
	//현재 비밀번호 입력할때 "보기"버튼 클릭 시 패스워드 보이기 
	$('.seeCurBtn').on("mousedown", function(){
	    $('.curPw').attr('type',"text");
	}).on('mouseup mouseleave', function() {
	    $('.curPw').attr('type',"password");
	});
	//새로운 비밀번호 입력할때 "보기"버튼 클릭 시 패스워드 보이기 
	$('.seeNewBtn').on("mousedown", function(){
	    $('.newPw').attr('type',"text");
	}).on('mouseup mouseleave', function() {
	    $('.newPw').attr('type',"password");
	});
	
	
	$('.deleteCurBtn').on("mousedown", function(){
	    $('.curPw').val('');
	});
	$('.deleteNewBtn').on("mousedown", function(){
	    $('.newPw').val('');
	});
	
})

//내 게시글에서 삭제 버튼 눌렀을 때 재확인)
function deleteCheck2(){
	
	if (confirm("정말 삭제하시겠습니까?") == true){//확인
		
	}else{   //취소
		return false;
	}
	
}
