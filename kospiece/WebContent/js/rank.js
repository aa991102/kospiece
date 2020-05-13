
//투자하기 눌렀을 때 반응
function simulationClick(login){
	if(login){
		document.getElementById('simulation').submit();
	}else{//로그인 안했으면
		result=confirm("로그인 하시겠습니까?")
		if(result){//로그인 한다고하면
			location.href="login.do"

		}else{//로그인 안한다고 하면
			
		}
	}
}
//실시간 순위 페이지에서 관심주식 추가하는 버튼 눌렀을 때 반응(추가는 로그인,비로그인 상태 모두 가능)
function interestPlusClick(login){
	if(login){//로그인 했으면
		alert("관심주식에 추가되었습니다.")
		document.getElementById('interestPlus').submit();
	}else{//로그인 안했으면
		result=confirm("로그인 하시겠습니까?")
		if(result){//로그인 한다고하면
			location.href="login.do"

		}else{//로그인 안한다고 하면
			
		}
	}
}

//실시간 순위 페이지에서 관심주식 삭제하는 버튼 눌렀을 때 반응(삭제는 로그인 한 상태에서만 가능)
function interestDeleteClick(){
	alert("관심주식에서 삭제되었습니다.")
	document.getElementById('interestDelete').submit();
}
