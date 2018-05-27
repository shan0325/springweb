/**
 * ajax 공통 설정
 */

// meta태그에 설정된 context path 가져오기
var BASE_CONTEXT_PATH = $("meta[name='context-path']").attr("content");
BASE_CONTEXT_PATH = BASE_CONTEXT_PATH.substr(0, BASE_CONTEXT_PATH.length - 1);

// Prepend context path to all jQuery AJAX requests
/*$.ajaxPrefilter(function(options, originalOptions, jqXHR) {
	console.log("BASE_CONTEXT_PATH = " + BASE_CONTEXT_PATH);
	console.log(options);
	console.log(originalOptions);
	console.log(jqXHR);
	if(!options.crossDomain) {
		options.url = BASE_CONTEXT_PATH + options.url;
	}
});*/

(function($) {
	$.ajaxSetup({
		beforeSend: function(jqXHR, options) {
			// url에 context path 붙이기
			if(!options.crossDomain) {
				options.url = BASE_CONTEXT_PATH + options.url;
			}
			// session 처리
			jqXHR.setRequestHeader("AJAX", true);
		},
		error: function(request, status, error) {
			var loginUrl = BASE_CONTEXT_PATH = "/cms/logout";
			if(request.status == 401) {
				alert("인증에 실패 하였습니다. 로그인 페이지로 이동합니다.");
				location.href = loginUrl;
			} else if(request.status == 403) {
				alert("세션이 만료 되었습니다. 로그인 페이지로 이동합니다.");
				location.href = loginUrl;
			} else {
				alert("code : " + request.status + "\n" 
						+ "message : " + request.responseText + "\n" 
						+ "error : " + error);
			}
		}
	});
})(jQuery);