document.addEventListener("plusready",function(){
	var B = window.plus.bridge;
	var FirstContact = {
		
		getFirstContactInfo : function(successCallback,errorCallback){
			var success = typeof successCallback !== 'function' ? null : function(args){
				successCallback(args);
			},
			fail = typeof errorCallback !== 'function' ? null : function(code) {
                        errorCallback(code);
			},
			callbackID = B.callbackId(success,fail);
			return B.exec("FirstContact","getFirstContactInfo",[callbackID]);
		}		
	};
	
	window.plus.FirstContact = FirstContact;
	
},true);