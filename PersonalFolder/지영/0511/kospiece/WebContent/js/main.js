function LoginCheck(menu,login,mid){//나중에 회사코드받는 파라미터 추가*****
	if(login){//로그인했으면
		if(menu='investPlus'){//투자페이지로 이동
			location.href="main.do?mid="+mid;//나중에 투자 로직으로 변경*****
		}else if(menu='favoritePlus'){
			location.href="main.do?mid="+mid;//나중에 즐겨찾기 로직으로 변경*****
		}
	}else{//로그인 안했으면
		goToLogin=confirm('로그인 하시겠습니까?')
		if(goToLogin){
			location.href="member/login.jsp";//로그인페이지로 이동
		}
	}
}