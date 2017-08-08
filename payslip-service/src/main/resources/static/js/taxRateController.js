app.controller('taxRateCtrl', [
		'$scope',
		'TaxRateService',
		function($scope, TaxRateService) {
			console.log("tax Rate Controller");
			
			$scope.findAllTaxRates = function() {
				res = TaxRateService.findAll();
				res.then(function(response) {
					$scope.taxRates = response.data;
				}, function errorCallback(response) {
					console.log(response.status + " : " + response.data);
					alert(response.status + " : " + response.data);
					
				});
			}
			$scope.findAllTaxRates();
			
			$scope.add = function() {
//				maxId = 0;
//				for (var i = 0; i < $scope.taxRates.length; i++) {
//					maxId = Math.max(maxId,  $scope.taxRates[i].id);
//				}
//				maxId += 1;
//				$scope.taxRates.push({
//					'id' : maxId
//				})
				$scope.taxRates.push({})
			}
			

            
            $scope.save = function(taxRate) {
				res = TaxRateService.save(taxRate);
				console.log(res);
				res.then(function(response) {
					$scope.findAllTaxRates();
				}, function errorCallback(response) {
					console.log(response.status + " : " + response.data);
					alert(response.status + " : " + response.data);
				});
			}
			
			$scope.delete = function(taxRate) {
				res = TaxRateService.delete(taxRate);
				res.then(function(response) {
					$scope.findAllTaxRates();
				}, function errorCallback(response) {
					console.log(response.status + " : " + response.data);
					alert(response.status + " : " + response.data);
				});
			}
			
		} ]);
