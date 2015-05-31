(function() {

	var processInterval;

	jQuery(document).ready(function($) {
		if (location.hash === '#processing') {
			processInterval = setInterval(processing, 1000);
			console.log(123);
		}
	});

	function processing() {
		$('.panel-info').hide().show('swing');
		// $.getJSON('./analysis', { method: 'wait' }, function(result) {
		// 	if (!result) {
				clearInterval(processInterval);
		// 		location.hash = '#result';
		// 		result();
		// 	}
		// });
	}

	function result() {

	}

})();