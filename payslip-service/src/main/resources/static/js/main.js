

var headers = {
	"Access-Control-Allow-Origin" : "*",
	'Access-Control-Allow-Credentials' : true
};


var baseurl = window.location.href.split('?')[0].split('#')[0];

/**
 * Main AngularJS Web Application
 */
var app = angular.module('payslipWebApp', [ 'ngRoute' ,'ngFileSaver']);

/**
 * Configure the Routes
 */
app.config([ '$routeProvider', function($routeProvider) {
	$routeProvider
	.when("/", {
		templateUrl : "partials/csvexporter.html",
		controller : "csvExporterCtrl"
	})
	.when("/taxrate", {
		templateUrl : "partials/taxrate.html",
		controller : "taxRateCtrl"
	})

	.otherwise("/404", {
		templateUrl : "partials/404.html",
		controller : "csvexporterCtrl"
	});
} ]);

app.filter('split', function() {
    return function(input, splitChar, splitIndex) {
        return input.split(splitChar)[splitIndex];
    }
});


app.directive('fileReader', function() {
	  return {
	    scope: {
	      fileReader:"="
	    },
	    link: function(scope, element) {
	      $(element).on('change', function(changeEvent) {
	        var files = changeEvent.target.files;
	        if (files.length) {
	          var r = new FileReader();
	          r.onload = function(e) {
	              var contents = e.target.result;
	              scope.$apply(function () {
	                scope.fileReader = contents;
	              });
	          };
	          
	          r.readAsText(files[0]);
	        }
	      });
	    }
	  };
	});