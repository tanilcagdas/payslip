app.controller('csvExporterCtrl', [
		'$scope',
		'PayslipService',
		'FileSaver',
		'Blob',
		function($scope, PayslipService,  FileSaver, Blob) {
			console.log("csv Exporter Controller");

			$scope.exportToCSV = function() {
				let lines = $scope.fileContent.split('\n');
				exportSingleLine(lines);
			}
			
			function exportSingleLine(lines){
				let employees = [];
				var lines = lines.map(function(line){
					
					let split = line.split(',');
					if(split.length != 5){
						console.log("not valid csv");
						return null;
					}
					let employee = {
							"firstName": split[0],
							"lastName" : split[1],
							"annualSalary" :split[2],
					        "superRatio" : split[3].replace('%',''),
					        "requestDates" : split[4]
					}
					employees.push(employee);
				});
				let body = {"employees":employees}
				
				res = PayslipService.exportPayslip(body);
				res.then(function(response) {
					saveFile(response.data, "payslip.csv");
				}, function errorCallback(response) {
					console.log(response.status + " : " + response.data);
					alert(response.status + " : " + response.data);
				});
			}

			function saveFile(fileData, fileName) {
				var data = new Blob([ fileData ], {
					type : 'text/csv;charset=utf-8'
				});
				FileSaver.saveAs(data, fileName);
			}

			$scope.uploadFile = function(element) {
				$scope.file = element;
				console.log($scope.file);
			}

		} ]);
