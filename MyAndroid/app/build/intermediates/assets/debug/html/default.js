/**
 * 
 */
function disp_alert() {
	alert("我是警告框！！")
}

function checkInput() {
	var userName = document.getElementById("username").value;
	var passwd = document.getElementById("passwd").value;
	if(userName.length < 6 || userName.length> 12) {
		alert("用户名长度不合法");
		window.demo.showErrMsg("用户名长度不合法");
		return false;
	} else if(passwd.length < 6 || passwd.length> 12) {
		alert("密码长度不合法");
		window.demo.showErrMsg("密码长度不合法");
		return false;
	}
	return true;
}
	
