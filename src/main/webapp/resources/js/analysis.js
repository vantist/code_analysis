(function() {
	function App() {
		var states;
		var filesList;
		var checking = checking;
		var refleshTable = refleshTable;
		var formatStates = formatStates;

		return {
			checking: checking
		}

		function checking() {
			$.getJSON('./analysis/states', function(data) {
				states = data;
				refleshList();
				refleshTable();
			});
		}

		function refleshList() {
			filesList = [];

			for (var i = states.reports.length - 1; i >= 0; i--) {
				var file = states.reports[i];
				file.time = file.fileName.split('_')[0];
				file.fileName = '<a href="./report/#' + i + '">' + file.fileName.substring(file.fileName.indexOf('_')+1, file.fileName.lastIndexOf('.')) + '</a>';
				file.state = 'finished';
				filesList.push(file);
			};

			for (var i = states.waitingJobs.length - 1; i >= 0; i--) {
				var file = states.waitingJobs[i];
				file.time = file.fileName.split('_')[0];
				file.fileName = file.fileName.substring(file.fileName.indexOf('_')+1);
				file.state = 'waiting';
				filesList.push(file);
			};

			for (var i = states.runningJobs.length - 1; i >= 0; i--) {
				var file = states.runningJobs[i];
				file.time = file.fileName.split('_')[0];
				file.fileName = file.fileName.substring(file.fileName.indexOf('_')+1);
				file.state = 'running';
				filesList.push(file);
			};

			filesList = filesList.sort(function(a, b) { return parseInt(a.time)-parseInt(b.time); });

			console.log(filesList);
		}

		function refleshTable() {
			var $table = $('.table-result tbody');
			var template = '<tr class="{class}"><td>{index}</td><td>{fileName}</td><td>{time}</td><td>{state}</td></tr>';
			var styleClass = {
				finished: 'success',
				running: 'info',
				waiting: ''
			}

			$table.empty();
			for (var i = 0; i < filesList.length; i++) {
				var file = filesList[i];
				$table.append(
					template.replace("{class}", styleClass[file.state])
							.replace("{index}", i)
							.replace("{fileName}", file.fileName)
							.replace("{time}", new Date(parseInt(file.time)))
							.replace("{state}", file.state)
				);
			};
		}
	}

	jQuery(document).ready(function($) {
		var app = new App();
		app.checking();

		setInterval(function() {
			app.checking();
		}, 1000);
	});
})();