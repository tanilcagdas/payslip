app.service('TaxRateService', function($http) {


 	this.findAll = function() {
 		url = baseurl+"taxRate/findAll";
 		var req = {
 			method : 'GET',
 			url : url,
 			headers : headers
 		}
 		return $http(req);

 	};
 	
 	this.save = function(taxRate) {
 		url = baseurl+"taxRate/save";
 		var req = {
 			method : 'POST',
 			url : url,
 			headers : headers,
 			data : {
 				'taxRate' : taxRate
 			}
 		}
 		return $http(req);

 	};
 	
 	this.delete = function(taxRate) {
 		url = baseurl+"taxRate/delete";
 		var req = {
 			method : 'POST',
 			url : url,
 			headers : headers,
 			data : {
 				'taxRate' : taxRate
 			}
 		}
 		return $http(req);

 	};
 });