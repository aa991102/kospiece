$(function(){ 
	
	//전체선택 체크박스 클릭 
	$("#allCheck").click(function(){ 
		//만약 전체 선택 체크박스가 체크된상태일경우 
		if($("#allCheck").prop("checked")) { 
			//해당화면에 전체 checkbox들을 체크해준다 
			$("input[type=checkbox]").prop("checked",true); 
		// 전체선택 체크박스가 해제된 경우 
		} else { 
			//해당화면에 모든 checkbox들의 체크를 해제시킨다. 
			$("input[type=checkbox]").prop("checked",false); 
		} 
	}) 
	

})

//가상투자하기 버튼 눌렀을 시 선택한 회사가 한 개 이상일 경우 alert
function goToInvest(){
	if($("input[type=checkbox]:checked").length>1){
		alert("회사는 하나만 선택해주세요.");
		return false;
	}
}		

//관심주식에서 삭제 버튼 눌렀을 때 확인(체크박스 체크유무/삭제 재확인)
function deleteCheck(){
	
	if($("input[type=checkbox]:checked").length>0){
		if (confirm("정말 삭제하시겠습니까?") == true){//확인
			
		}else{   //취소
			return false;
		}
	}else{
		alert("삭제할 주식을 선택해주세요.");
		return false;
		
	}
	
	
}

function deleteBoardCheck(){
	
	if($("input[type=checkbox]:checked").length>0){
		if (confirm("정말 삭제하시겠습니까?") == true){//확인
			
		}else{   //취소
			return false;
		}
	}else{
		alert("삭제할 게시글을 선택해주세요.");
		return false;
		
	}
	
	
}