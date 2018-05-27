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
		type: "GET",
		url: "/cms/1/system",
		cache: false,
		dataType:"JSON",
		success: function(data) {
			var redirectUrl = BASE_CONTEXT_PATH + data.cmsUrl;
			if(!gfn_isNull(redirectUrl)) {
				var target = data.cmsUrlTarget;
				if(target == "_blank") {
					window.open(redirectUrl);
				} else {
					window.location.href = redirectUrl;
				}
			} else {
				alert("이동할 메뉴가 존재하지않습니다.");
			}
		}
	});
}


