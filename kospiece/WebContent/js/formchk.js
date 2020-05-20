function formChk(){
	frm = document.join;
	
	if(frm.mid.value.length<1){
		alert('아이디를 입력하세요.')
		frm.mid.focus();
		return false;
	}
	
	if(frm.mid.value.length<5||frm.mid.value.length>15){
		alert('아이디는 5자 이상 15자이내로 제한되어 있습니다.')
		frm.mid.focus();
		return false;
	}
	
	if(frm.mpw.value != frm.mpw2.value){
		alert('비밀번호가 일치하지 않습니다.')
		frm.mpw.focus();
		return false;
	}
	
	if(frm.mname.value.length<1){
		alert('이름을 입력하세요.')
		frm.mname.focus();
		return false;
	}
	
	if(frm.mnick.value.length<1){
		alert('nickname을 입력하세요.')
		frm.mnick.focus();
		return false;
	}
	
	if(frm.mnick.value.length>10){
		alert('nickname은 10자 이내로 제한되어 있습니다.')
		frm.mnick.focus();
		return false;
	}
	
	if(frm.mmail.value.length<1){
		alert('mmail을 입력하세요.')
		frm.mmail.focus();
		return false;
	}
	
	if(frm.mphone.value.length<1){
		alert('mphone을 입력하세요.')
		frm.mphone.focus();
		return false;
	}
	
}