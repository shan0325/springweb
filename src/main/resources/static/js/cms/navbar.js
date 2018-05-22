/**
 * navbar에서 공통으로 사용되는 스크립트
 */

//시스템 버튼 클릭 시
$(document).ready(function() {
	$("#mainNav #system").on("click", function(e) {
		e.preventDefault();
		goSystemView();
	});
});

function goSystemView() {
	$.ajax({
		type: "POST",
		url: "/cms/1/system",
		success: function(data) {
			alert(JSON.stringify(data));
		},
		error: function(xhr, status, error) {
			
			alert(JSON.stringify(xhr));
			alert(status);
			alert(error);
		}
	});
}


