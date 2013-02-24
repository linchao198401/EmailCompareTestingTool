<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>DE Email Compare Testing Tool</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<script src="jqueryUI/js/jquery-1.9.0.js"></script>
<script src="jqueryUI/js/jquery-ui-1.10.0.custom.js"></script>
<script src="js/common.js"></script>
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
						<li class="active"><a href="releaseChecklist.do?op=listItem">Item</a>
						</li>
						<li><a href="releaseChecklist.do?op=listRelease">Release</a>
						</li>
						<li><a href="releaseChecklist.do?op=currentRelease">Current Release</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<div class="container"">
		<h1>Add New Item</h1>
		<h2 style="margin: 5px 0">
			<a class="btn btn-small btn btn-info"
				href="releaseChecklist.do?op=listItem">Back to Item List</a>
		</h2>
		<form action="releaseChecklist.do">
			<input type="hidden" name="op" value="addItem" /> <label>Name:</label>
			<input name="name" placeholder="Item Name" /><label>Sequence:</label>
			<input id="sequence" name="sequence" placeholder="Sequence" /><label>Category:</label>
			<select name="category" size="15">
				<option>General Preparation</option>
				<option>QA Stable Build</option>
				<option>QA Internal Build</option>
				<option>DB Related</option>
				<option>DEV Related</option>
				<option>Stage Build</option>
				<option>Deployment Related</option>
				<option>QA Related</option>
				<option>PTN Review</option>
				<option>IMD Tool Related</option>
				<option>Performance Related</option>
				<option>Post Release</option>
				<option>Release Specific</option>
			</select> <label>Description:</label>
			<textarea rows="6" name="description" style="width: 800px"></textarea>
			<br />
			<button class="btn btn-large btn btn-success" type="submit" id="createNewItem">Create
				New Item</button>
		</form>
	</div>
	<script type="text/javascript">
	$(function() {
		$("#createNewItem").click(function(){
			var sequence = $("#sequence").val();
			if (sequence == "") {
				$("#sequence").val(10000);
			}
		})
	})
	</script>
</body>
</html>