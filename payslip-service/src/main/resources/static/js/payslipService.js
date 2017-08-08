app.service('PayslipService', function($http) {


 	this.exportPayslip = function(employee,superRatio,requestDates) {
 		url = baseurl+"exportPayslip/"+superRatio+'/'+requestDates;
 		var req = {
 			method : 'POST',
 			url : url,
 			headers : headers,
 			data : {
 				'empolyee' : employee
 			}
 		}
 		return $http(req);

 	};
 });