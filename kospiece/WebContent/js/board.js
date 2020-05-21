
// 검색어를 입력하지 않고 검색을 누르는 것을 방지
function checkForm() {
	if (document.getElementById("search-value").value == "") {
		alert("검색어를 입력해주세요")
		return false;
	}
}