<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Findbugs 分析</title>

	<link rel="stylesheet" href="./resources/css/bootstrap.min.css">

	<script src="./resources/js/jquery-1.11.1.min.js"></script>
	<script src="./resources/js/bootstrap.min.js"></script>
	<script src="./resources/js/analysis.js"></script>
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="./">
						<!-- <img alt="Findbugs" style="height: 100%;" src="./images/findbugs.png"> -->
						程式碼分析
					</a>
				</div>

				<div class="collapse navbar-collapse" id="navbar-collapse">
					<ul class="nav navbar-nav">
						<li class="active"><a href="./">分析 <span class="sr-only">(current)</span></a></li>
					</ul>
				</div>
			</div>
		</nav>

		<div class="panel panel-primary">
			<div class="panel-heading">上傳Jar檔</div>
			<div class="panel-body">
				<form action="./analysis" method="post" enctype="multipart/form-data">
					<div class="form-group">
						<label for="jarFile">File input</label>
						<input type="file" id="jarFile" accept=".jar" name="file">
						<p class="help-block">上傳欲分析之JAR(*.jar)</p>
					</div>
					<button type="submit" class="btn btn-primary">分析</button>
				</form>
			</div>
		</div>
		<div class="panel panel-info">
			<div class="panel-heading">分析結果</div>
			<div class="panel-body">
				<table class="table table-hover table-result">
					<thead>
						<tr>
							<th>#</th>
							<th>檔名</th>
							<th>上傳時間</th>
							<th>狀態</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>