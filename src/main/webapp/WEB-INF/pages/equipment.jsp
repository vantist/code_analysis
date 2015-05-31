<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-tw">
<head>
	<title>貴重儀器設備分享 | 北區策略聯盟</title>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link rel="icon" type="image/ico" href="./resources/favicon.ico">
	<link rel="stylesheet" href="./resources/css/normalize.css">
	<link rel="stylesheet" href="./resources/css/style.css">
	<link rel="stylesheet" href="./resources/css/jquery-ui.css" >
	<link rel="stylesheet" href="./resources/css/jquery.timepicker.css" >
	<style type="text/css">
		/* 當前位置圖片 */
		.position {
			background-image: url('./resources/pic/pos/equipment.png');
		}

		.content {
			overflow-x: hidden;
		}

		.content .title {
			color: #FFFFFF;
			font-weight: bold;
		}

		.content .detail {
			text-indent: 2em;
			margin: 10px 25px;
		}
		
		table.apply,
		table.info,
		table.list {
			text-align: left;
			margin-left: 110px;
			width: 85%;
			background-color: #DBDBDB;
		}

		table.info {
			height: 100%;
		}

		table.list td {
			cursor: pointer;
		}

		table.apply td,
		table.list td,
		table.info td {
			background-color: #FFFFFF;
		}

		table.list tr:nth-child(odd) td {
			background-color: #EEEEEE;
		}

		table.list tr:hover td {
			background-color: #CCCCCC;
			color: #888888;
		}

		table.apply th,
		table.apply td,
		table.list th,
		table.list td,
		table.info th,
		table.info td {
			margin: 0 auto;
			padding: 5px 20px;
		}

		table.list tr > td + td,
		table.apply tr > td + td,
		table.info tr > td + td {
			border-left: 2px solid #DBDBDB;
		}

		table.apply td,
		table.list td {
			border-bottom: 2px solid #DBDBDB;
		}


		table.apply th:last-child,
		table.apply td:last-child,
		table.list th:last-child,
		table.list td:last-child,
		table.info th:last-child,
		table.info td:last-child {
			width: 30%;
		}

		table.apply td:nth-child(2):not(:last-child),
		table.list td:nth-child(2):not(:last-child),
		table.info td:nth-child(2):not(:last-child) {
			width: 35%;
		}

		table.apply input {
			width: 100%;
		}

		.info {
			display: none;
		}

		.info tr:last-child {
			vertical-align: initial;
			height: 100%;
		}

		ul.files {
			padding-left: 0;
		}

		.files li {
			margin-top: 1em;
			list-style-type: none;
		}

		.files li > * {
			font-weight: bold;
			color: #000000;
		}

		.back {
			display: none;
			cursor: pointer;
		}

		.back:hover {
			/* 陰影 */
			-webkit-box-shadow: 1px 1px 1px 1px rgba(0,0,0,0.5);
			-moz-box-shadow: 1px 1px 1px 1px rgba(0,0,0,0.5);
			box-shadow: 1px 1px 1px 1px rgba(0,0,0,0.5);
		}

		.infopic {
			width: 100%;
		}

		.step {
			background: #FFFFFF;
			position: absolute;
			width: 110px;
			height: 430px;
			border-right: 5px solid #DBDBDB;
		}

		.step0 {
			cursor: pointer;
			margin: 40px 0px 0px 20px;
			width: 70px;
			height: 65px;
			position: absolute;
		}

		.search {
			position: absolute;
			margin: 210px 0px 0px 11px;
			height: 12px;
			width: 80px;
			font-size: 10px;
			text-align: center;
		}

		.pic {
			height: 400px;
		}

		.step0-content {
			margin-left: 110px;
			width: 85%;
			display: none;
		}

		.step0-content td {
			padding: 20px;
		}

		img.apply {
			cursor: pointer;
		}
	</style>
</head>
<body>
	<div class="container shadow">
		<div class="header"></div>
		<div class="sidebar"></div>
		<div class="position"></div>
		<div class="content">
			<div class="step center">
				<a class="step0" href="#"></a>
				<input class="search" type="text" name="search" placeholder="請輸入名稱" />
				<img src="./resources/pic/equipment/step.png">
			</div>

			<table class="step0-content">
				<thead>
					<tr>
						<th class="title blue">租借辦法</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							${rent_rule}
						</td>
					</tr>
				</tbody>
			</table>

			<table class="list" style="display: none;">
				<thead>
					<tr>
						<th class="title blue">貴重儀器設備名稱</th>
						<th class="title darkblue center">所屬學校</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			
			<table class="info">
				<tbody>
					<tr>
						<td class="title blue">貴重儀器設備名稱</td>
						<td class="title darkblue center">所屬學校</td>
						<td rowspan="2" class="center"><img class="apply" src="./resources/pic/equipment/apply.png"></td>
					</tr>
					<tr class="name">
						<td></td>
						<td class="center"></td>
					</tr>
					<tr>
						<td colspan="2" class="title blue">型號</td>
						<td class="title darkblue center">聯絡人</td>
					</tr>
					<tr class="information">
						<td colspan="2"></td>
						<td class="center"></td>
					</tr>
					<tr>
						<td class="title blue">功能簡述</td>
						<td class="title blue">設備照片</td>
						<td class="title darkblue center">聯絡方式</td>
					</tr>
					<tr class="description">
						<td></td>
						<td class="center"></td>
						<td class="center"></td>
					</tr>
					<tr>
						<td colspan="3" class="title blue">收費項目</td>
					</tr>
					<tr class="pay">
						<td colspan="3"></td>
					</tr>
				</tbody>
			</table>
			<input type="text" name="equipment_id" style="display: none;" value="-1"/>
			<table class="apply" style="display: none;">
				<tbody>
					<tr>
						<td colspan="4" class="title blue center">借用設備申請單</td>
					</tr>
					<tr>
						<td>借用者名稱</td>
						<td><input type="text" name="name" /></td>
						<td>Email</td>
						<td><input type="email" name="email" /></td>
					</tr>
					<tr>
						<td>學校名稱</td>
						<td><input type="text" name="school"/></td>
						<td>院/系名稱</td>
						<td><input type="text" name="department"/></td>
					</tr>
					<tr>
						<td>電話</td>
						<td><input type="text" name="phone"/></td>
						<td>職稱</td>
						<td>
							<select name="title">
							  <option value="教授">教授</option>
							  <option value="教職員">教職員</option>
							  <option value="學生">學生</option>
							  <option value="已畢業之社會人士">已畢業之社會人士</option>
							</select> 
						</td>
					</tr>
					<tr>
						<td colspan="1">借用日期</td>
						<td colspan="3">起<input name="start_date"/> 迄<input name="end_date"/></td>
					</tr>
					<tr>
						<td colspan="1">借用時間</td>
						<td colspan="3"><input name="start_time"/><input name="end_time"/></td>
					</tr>
					<tr>
						<td colspan="1">使用人數</td>
						<td colspan="3">老師<input type="number" name="teacher_count"/>學生<input type="number" name="student_count"/></td>
					</tr>
					<tr>
						<td colspan="1">自備耗材</td>
						<td colspan="3"><input type="radio" name="provide_for_oneself" value="true" checked/>是<input type="radio" name="provide_for_oneself" value="false" />否(請主動詢問設備管理員細項)</td>
					</tr>
					<tr>
						<td colspan="4">請檢附教職員證/學生證(僅作為設備申請確認身分使用)<input id="file" type="file" accept="image/*"></td>
					</tr>
					<tr>
						<td colspan="4" class="center"><button type="button" class="apply" />填寫完成 送出</button></td>
					</tr>
				</tbody>
			</table>
			
			<table class="result" style="display: none;">
				<tbody>
					<tr>
						<td colspan="4" class="center"><button>1234</button></td>
					</tr>
				</tbody>
			</table>
		</div>

		<div class="contentaction">
			<span class="back">回上一層</span>
		</div>
		<div class="footer"></div>
	</div>
	<script src="./resources/js/jquery-1.11.1.min.js"></script>
	<script src="./resources/js/jquery-ui.js"></script>
	<script src="./resources/js/jquery.timepicker.min.js"></script>
	<script>
		$().ready(function() {
			// load header
			$('.header').load('./resources/template/header.tpl', function() {
				highlightHeader(4);
				highlightDropdown('貴重儀器設備分享');
			});

			// load sidebar
			$('.sidebar').load('./resources/template/sidebar.tpl', function(response, status, xhr) {
				highlightQuickMenu(0);
			});

			// load footer
			$('.footer').load('./resources/template/footer.tpl');

			(function Equipments() {
				$("input[name$='start_date']").datepicker({ dateFormat: 'yy-mm-dd' });
				$("input[name$='end_date']").datepicker({ dateFormat: 'yy-mm-dd' });
				$("input[name$='start_time']").timepicker();
				$("input[name$='end_time']").timepicker();
				
				var equipments;

				$.getJSON('/manage/equipment/list', function(data) {
					equipments = data;
					bindList();
					createList();	
				});

				var bindList = function() {
					var $table = $('table.list');
					$('table.list tbody').on('click', 'tr', function() {
						$table.hide();
						renderContent(JSON.parse($(this).data('data')));
					});
				}

				var renderContent = function(target) {
					$("input[name$='equipment_id']").val(target.id);
					var nameTemplate = '<td>$1</td><td class="center">$2</td>';
					var informationTemplate = '<td colspan="2">$1</td><td class="center">$2</td>';
					var descriptionTemplate = '<td>$1</td><td class="center">$2</td><td>$3</td>';
					var payTemplate = '<td colspan="3">$1</td>';
					var $table = $('table.info');
					var $tbody = $('table.info tbody');

					// title
					$tbody.find('tr.name').empty().append(nameTemplate.replace('$1', target.name).replace('$2', target.ownerData.name));
					// 型號, 聯絡人
					// var types = '';
					// for (var i = 0; i < target.types.length; i++)
					// 	types += target.types[i] + '<br/>';
					$tbody.find('tr.information').empty().append(informationTemplate.replace('$1', target.type).replace('$2', target.contact_person));
					// 簡述, 照片, 聯絡方式
					var pics = '';
					// for (var i = 0; i < target.pic.length; i++)
						pics += '<img class="infopic" src="' + target.picture_path + '" /><br/>';
					$tbody.find('tr.description').empty().append(descriptionTemplate.replace('$1', target.description).replace('$2', pics).replace('$3', target.contact_method));
					// 付費
					$tbody.find('tr.pay').empty().append(payTemplate.replace('$1', '收費項目：□耗材 ■租借費 ■技術費 □其他'));

					$table.show();
					$('.back').show();
					$('.content').scrollTop(0);
				}

				var createList = function() {
					var template = '<tr><td>$0. $1</td><td class="center">$2</td></tr>';
					var $table = $('table.list');
					var $tbody = $('table.list tbody');
					var content = '';

					var search = $('input.search').val();

					$tbody.empty();

					for (var i = 0, j = 0; i < equipments.length; i++) {
						if (equipments[i].name.indexOf(search) >= 0 || search === '') {
							var $list = $(template.replace('$0', ++j).replace('$1', equipments[i].name).replace('$2', equipments[i].ownerData.name));
							$list.data('data', JSON.stringify(equipments[i]));
							$list.appendTo($tbody);
						}
					}

					$('.back').hide();
					$table.show();
					$('.content').scrollTop(0);
				}

				$('.back').on('click', function() {
					$('.content').scrollTop(0);
					$('table.info').hide();
					$('table.step0-content').hide();
					$('table.apply').hide();
					$('.back').hide();
					createList();
				});

				$('.step0').on('click', function() {
					$('.content').scrollTop(0);
					$('.step0-content').show();
					$('table.info').hide();
					$('table.list').hide();
					$('table.apply').hide();
					$('.back').show();
				});

				$('input.search').on('keyup', function() {
					if ($('.back:visible').length > 0)
						$('.back:visible').click();

					createList();
				});

				$('img.apply').on('click', function() {
					$('table.apply').show().siblings('table').hide();
					$('.back').show();
					$('.content').scrollTop(0);
				});
				

				$('img.apply').on('click', function() {
					$('table.apply').show().siblings('table').hide();
					$('.back').show();
					$('.content').scrollTop(0);
				});

				$('button.apply').on('click', function() {
					var data = new FormData();
					var nofile = true;
					jQuery.each($('#file')[0].files, function(i, file) {
						data.append('file', file);
						nofile = false;
					});
					
					if(nofile){
						alert("請檢附教職員證/學生證");
						return;
					}
					else if(!$.isNumeric($("input[name$='teacher_count']").val())||!$.isNumeric($("input[name$='student_count']").val())){
						alert("[使用人數]請輸入數字");
						return;						
					}

					data.append('equipment_id', $("input[name$='equipment_id']").val());
					data.append('name', $("input[name$='name']").val());
					data.append('email', $("input[name$='email']").val());
					data.append('school', $("input[name$='school']").val());
					data.append('department', $("input[name$='department']").val());
					data.append('phone', $("input[name$='phone']").val());
					data.append('title', $("select[name$='title']").val());
					data.append('start_date', $("input[name$='start_date']").val());
					data.append('end_date', $("input[name$='end_date']").val());
					data.append('start_time', $("input[name$='start_time']").val());
					data.append('end_time', $("input[name$='end_time']").val());
					data.append('teacher_count', $("input[name$='teacher_count']").val());
					data.append('student_count', $("input[name$='student_count']").val());
					data.append('provide_for_oneself', $("input[name$='provide_for_oneself']:checked").val());
					
					console.log($("input[name$='equipment_id']").val());
					
					 $.ajax({
						url: "/rentEquipment",
						type: 'POST',
						data: data,
						cache: false,
						processData: false, // Don't process the files
						contentType: false, // Set content type to false as jQuery will tell the server its a query string request
						//dataType: "json",
						success: function(data, textStatus, jqXHR)
						{
							console.log("server response:");
							console.log(data);
							alert(data);
							$('#errorMessage').html(JSON.stringify(data));
						},
						error: function(jqXHR, textStatus, errorThrown)
						{
							// Handle errors here
							console.log('ERRORS: ' + textStatus +"|"+ errorThrown);
							// STOP LOADING SPINNER
						}
					});
					 return;
					console.log(name);
				});
			})();
		});
	</script>
</body>
</html>