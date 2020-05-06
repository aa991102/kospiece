<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){

	//항목추가

	$(".btn_add").click(function(){

		var html = '';

			html += '<div>';

			html += '<a href="#none" class="btn_up">위로이동</a>&nbsp;';

			html += '<a href="#none" class="btn_down">아래로이동</a>&nbsp;';

			html += '<select name="" id="">';

			html += '<option value="">선택</option>';

			html += '</select>&nbsp;';

			html += '<input type="" />&nbsp;';

			html += '<a href="#none" class="btn_del">삭제</a>';

			html += '</div>';

		$("div.add").append(html);

	});	

});

//항목삭제

$(document).on("click",".btn_del",function(){

	$(this).parent().remove();

});

//항목 위치 위로이동

$(document).on("click",".btn_up",function(){

	console.log("위로이동");

	var test = $(this).parent().prev();

	test.before($(this).parent());

});

//항목 위치 아래로이동

$(document).on("click",".btn_down",function(){

	console.log("아래로이동");

	var test = $(this).parent().next();

	test.after($(this).parent());

});
</script>

</head>
<body>
<div id="layout">

	<div>

		<a href="#" class="btn_up">위로이동</a>

		<a href="#" class="btn_down">아래로이동</a>

		<select name="" id="">

			<option value="">선택</option>

		</select>




		<a href="#" class="btn_del">0삭제</a>

	</div>

	<div>

		<a href="#" class="btn_up">위로이동</a>

		<a href="#" class="btn_down">아래로이동</a>

		<select name="" id="">

			<option value="">선택</option>

		</select>




		<a href="#" class="btn_del">1삭제</a>

	</div>

	<div>

		<a href="#" class="btn_up">위로이동</a>

		<a href="#" class="btn_down">아래로이동</a>

		<select name="" id="">

			<option value="">선택</option>

		</select>




		<a href="#" class="btn_del">2삭제</a>

	</div>

</div>



</body>
</html>