/**
 * 공통 자바스크립트
 */

function fn_menuRouting(menuId) {
	
	if(gfn_isNull(menuId)) {
		alert("menuId가 존재하지 않습니다.");
		return false;
	}
	
	$.ajax({
		type: "GET",
		url: "/cms/menu/routing/" + menuId,
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
				alert("이동할 URL이 존재하지않습니다.");
			}
		}
	});
}