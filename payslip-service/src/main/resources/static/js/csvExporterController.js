app.controller('csvExporterCtrl', [
		'$scope',
		'PayslipService',
		'FileSaver',
		'Blob',
		function($scope, PayslipService,  FileSaver, Blob) {
			console.log("csv Exporter Controller");

			$scope.exportToCSV = function() {
				let lines = $scope.fileContent.split('\n');
				for ( var i in lines) {
					exportSingleLine(lines[i]);
				}
			}
			
			function exportSingleLine(line){
				let split = line.split(',');
				if(split.length != 5){
					alert("not valid csv")
				}
				let employee = {
						"firstName": split[0],
						"lastName" : split[1],
						"annualSalary" :split[2]
				}
				let superRatio = split[3].replace('%','')
				let requestDates = split[4]
				res = PayslipService.exportPayslip(employee, superRatio,
						requestDates);
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
