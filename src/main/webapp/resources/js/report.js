(function() {
	jQuery(document).ready(function($) {
		$('.report').empty();
		$.post('./'+location.hash.substring(1), function(data) {
			console.log(data);
			console.log(xml2json.parser(data));
			$('.report').append(xml2json.parser(data, '', 'html'));
		});
	});
})();