// (function() {
	function App() {
		var init = init;
		var report;
		var loadReport = loadReport;
		var getReport = getReport;


		return {
			init: init,
			getReport: getReport
		}

		function getReport() {
			return report;
		}

		function init() {
			loadReport();
		}

		function loadReport() {
			$.post('./'+location.hash.substring(1), function(data) {
				// report = xml2json.parser(data);
				report = data;
				$('.report').html(data);
			});
		}
	}

var app = new App();
app.init();

	// jQuery(document).ready(function($) {
	// 	$('.report').empty();
	// 	$.post('./'+location.hash.substring(1), function(data) {
	// 		console.log(data);
	// 		console.log(xml2json.parser(data));
	// 		// $('.report').append(xml2json.parser(data, '', 'html'));
	// 	});
	// });
// })();