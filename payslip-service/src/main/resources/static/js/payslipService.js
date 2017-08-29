app.service('PayslipService', function($http) {


 	this.exportPayslip = function(data) {
 		url = baseurl+"exportPayslip";
 		var req = {
 			method : 'POST',
 			url : url,
 			headers : headers,
 			data : data
 		}
 		return $http(req);

 	};
 });