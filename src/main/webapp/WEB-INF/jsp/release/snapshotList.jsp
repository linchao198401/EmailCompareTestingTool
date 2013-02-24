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
						<li><a href="releaseChecklist.do?op=listItem">Item</a>
						</li>
						<li><a href="releaseChecklist.do?op=listRelease">Release</a>
						</li>
						<li class="active"><a href="releaseChecklist.do?op=currentRelease">Current Release</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<div class="container"">
		<h1>Current Release Snapshots</h1>
		<h2 style="margin: 5px 0">
			<a class="btn btn-small btn btn-info"
				href="releaseChecklist.do?op=currentRelease">Back to Current Release</a>
		</h2>
		<table class="table table-bordered table-striped">
			<tr>
				<th>Snapshot Name</th>
				<th>Type</th>
			</tr>
			<c:forEach var="snapshot" items="${allSnapshots}">
					<tr>
						<td><a href="releaseChecklist.do?op=displaySnapshot&snapshotID=${snapshot.id}">${snapshot.name}</a></td>
						<td>${snapshot.type}</td>
					</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>