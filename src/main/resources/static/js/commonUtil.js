/**
 * null 체크
 * @param str 체크 값
 * @returns {Boolean}
 */
function gfn_isNull(str) {
    if (str == null) return true;
    if (str == "NaN") return true;
    if (new String(str).valueOf() == "undefined") return true;    
    var chkStr = new String(str);
    if( chkStr.valueOf() == "undefined" ) return true;
    if (chkStr == null) return true;    
    if (chkStr.toString().length == 0 ) return true;   
    return false; 
}

/**
 * str1이 null 이면 str2로 변경
 * @param str1
 * @param str2
 * @returns {String}
 */
function gfn_nvl(str1, str2) {
	
	var returnStr = "";
	
	if(gfn_isNull(str1)) {
		returnStr = !gfn_isNull(str2) ? str2 : "";
	} 
	else {
		returnStr = str1;
	}
	
	return returnStr;
}