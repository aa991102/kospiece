//submit버튼!
// index가 1이면 /kospiece/myInterestInsert.do 로
// index가 2면    /kospiece/myInterestDelete.do 로
// 이동

$(function(){ 
	//전체선택 체크박스 클릭 
	$("#allCheck").click(function(){ 
		//만약 전체 선택 체크박스가 체크된상태일경우 
		if($("#allCheck").prop("checked")) { 
			//해당화면에 전체 checkbox들을 체크해준다 
			$("input[type=checkbox]").prop("checked",true); 
			// 전체선택 체크박스가 해제된 경우 
		} else { 
			//해당화면에 모든 checkbox들의 체크를해제시킨다. 
			$("input[type=checkbox]").prop("checked",false); 
		} 
	}) 
})
		

