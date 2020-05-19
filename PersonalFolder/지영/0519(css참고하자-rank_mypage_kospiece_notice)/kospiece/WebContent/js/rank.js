$(function(){
	
	login=$('input[id="login"]').val();//로그인 여부를 알려주는 변수 선언(html에 히든으로 설정함)
	
	//투자하기 눌렀을 때 반응
	$("form[id^='simulation']").submit(function(){
		
		if(login=='true'){ //로그인을 했으면
			$(this).submit(); //submit 진행
			return false;
		}else{ //로그인을 안했으면
			result=confirm("로그인 하시겠습니까?") //로그인할건지 물어보기
			if(result){ //로그인 한다고하면
				location.href="login.do" //로그인창으로 이동
				return false;
			}else{ //로그인 안한다고 하면
				return false;
			}
		}
	})
	
	//실시간 순위 페이지에서 관심주식 추가하는 버튼 눌렀을 때 반응(추가는 로그인,비로그인 상태 모두 가능)
	$("input[name^='PlusImg']").click(function(){
		if(login=='true'){//로그인 했으면
			alert("관심주식에 추가되었습니다.")
			$("form[id=='interestPlus']").submit();
			return false;
		}else{//로그인 안했으면
			result=confirm("로그인 하시겠습니까?")
			if(result){//로그인 한다고하면
				location.href="login.do"
				return false;
			}else{//로그인 안한다고 하면
				return false;
			}
		}
	})
	
	//실시간 순위 페이지에서 관심주식 삭제하는 버튼 눌렀을 때 반응(삭제는 로그인 한 상태에서만 가능)
	$("input[name^='DeleteImg']").click(function(){
		alert("관심주식에서 삭제되었습니다.")
		$("form[id=='interestPlus']").submit();
		return false;
	})
})