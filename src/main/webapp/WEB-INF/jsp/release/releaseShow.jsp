<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>DE Email Compare Testing Tool</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<script src="js/jquery-1.6.4.min.js"></script>
<link rel="stylesheet" media="screen" type="text/css"
	href="datepicker/css/datepicker.css" />
<script type="text/javascript" src="datepicker/js/datepicker.js"></script>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<script src="js/common.js" type="text/javascript"></script>
<style>
body {
	padding-top: 60px;
}
</style>
<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> </a> <a
					class="brand" href="releaseChecklist.do?op=currentRelease">Release
					Check List</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li><a href="releaseChecklist.do?op=listOwner">Owner</a>
						</li>
						<li><a href="releaseChecklist.do?op=listItem">Item</a>
						</li>
						<li class="active"><a href="releaseChecklist.do?op=listRelease">Release</a>
						</li>
						<li><a href="releaseChecklist.do?op=currentRelease">Current Release</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<div class="container"">
		<h1>Update Release</h1>
		<h2 style="margin: 5px 0">
			<a class="btn btn-small btn btn-info"
				href="releaseChecklist.do?op=listRelease">Back to Release List</a>
		</h2>
		<form action="releaseChecklist.do">
			<input type="hidden" name="op" value="addRelease" />
			<input type="hidden" name="releaseID" value="${release.id}" /> <label>Name:</label>
			<input name="name" placeholder="Release Name" value="${release.name}"/><label>QA Stable Deploy Date:</label>
			<input id="qaStableDate" name="qaStableDate" placeholder="QA Stable Date"  value="<fmt:formatDate value="${release.qaStableDate}"type="date" />"/><label>Stage Deploy Date:</label>
			<input id="stageDate" name="stageDate" placeholder="Stage Date"  value="<fmt:formatDate value="${release.stageDate}"type="date" />"/><label>Prod Deploy Date:</label>
			<input id="prodDate" name="prodDate" placeholder="Prod Date"  value="<fmt:formatDate value="${release.prodDate}"type="date" />"/>
			<label>General Release Items: <a id="addAllGeneralReleaseItems">(Add All)</a></label>
			<select
					id="generalReleaseItemIDs" size="10" style="width:500px">
					<c:forEach items="${filterItems}" var="releaseItem">
						<option value="${releaseItem.id}">
							<c:out value="${releaseItem.name}" />
						</option>
					</c:forEach>
				</select>
			<select
					id="generalSelectedReleaseItemIDs" size="10" name="items" multiple="multiple" style="width:500px">
					<c:forEach items="${filterExistingItems}" var="releaseItem">
						<option value="${releaseItem.id}">
							<c:out value="${releaseItem.name}" />
						</option>
					</c:forEach>
				</select>
			<label>Specific Release Items:</label>
			<select
					id="specificReleaseItemIDs" size="10" style="width:500px">
					<c:forEach items="${speficItems}" var="releaseItem">
						<option value="${releaseItem.id}">
							<c:out value="${releaseItem.name}" />
						</option>
					</c:forEach>
				</select>
			<select
					id="specificSelectedReleaseItemIDs" size="10" name="items" multiple="multiple" style="width:500px">
					<c:forEach items="${speficExistingItems}" var="releaseItem">
						<option value="${releaseItem.id}">
							<c:out value="${releaseItem.name}" />
						</option>
					</c:forEach>
				</select>
			<br />
			<button id="createNewReleaseButton" class="btn btn-large btn btn-success" type="submit">Update
				Release</button>
		</form>
	</div>
	<script type="text/javascript">
	$(function() {
		var qaStableDate = $('#qaStableDate');
		var stageDate = $('#stageDate');
		var prodDate = $('#prodDate');
		
		function setDatePicker(date) {
			date.DatePicker({
				format:'Y-m-d',
				date: date.val(),
				current: date.val(),
				starts: 1,
				position: 'right',
				onChange: function(formated, dates){
					date.val(formated);
					date.DatePickerHide();
				}
			});
		}
		
		setDatePicker(qaStableDate);
		setDatePicker(stageDate);
		setDatePicker(prodDate);
		moveFromOneSelectToAnotherSelect($('#specificReleaseItemIDs'), $('#specificSelectedReleaseItemIDs'));
		moveFromOneSelectToAnotherSelect($('#generalReleaseItemIDs'), $('#generalSelectedReleaseItemIDs'));
		
		$("#createNewReleaseButton").click(function(){
			$("#generalSelectedReleaseItemIDs option").attr("selected", "selected");
			$("#specificSelectedReleaseItemIDs option").attr("selected", "selected");
		});
		
		$("#addAllGeneralReleaseItems").click(function(){
			$('#generalReleaseItemIDs option').each(function() {
				$('#generalSelectedReleaseItemIDs').append($(this).remove());
			});
		})
	});
	
	</script>
</body>
</html>