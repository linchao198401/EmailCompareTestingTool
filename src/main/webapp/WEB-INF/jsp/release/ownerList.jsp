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
<style>
body {
	padding-top: 60px;
}
</style>
<script src="js/jquery-1.6.4.min.js"></script>
<script src="js/common.js"></script>
<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
</head>
<body>

	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="index.do?op=index">Release Check List</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li class="active"><a href="releaseChecklist.do?op=listOwner">Owner</a></li>
						<li><a href="releaseChecklist.do?op=listItem">Item</a></li>
						<li><a href="releaseChecklist.do?op=listRelease">Release</a></li>
						<li><a href="releaseChecklist.do?op=currentRelease">Current Release</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<div class="container"">
		<h1>Owner List</h1>
		<h2 style="margin: 5px 0">
			<a class="btn btn-small btn btn-success" href="releaseChecklist.do?op=addOwner">Create
				New Owner</a>
		</h2>
		<table class="table table-bordered table-striped">
			<tr>
				<th>Name</th>
				<th>Email Address</th>
				<th>Action</th>
			</tr>
			<c:forEach var="owner" items="${allOwners}">
					<tr>
						<td><a href="releaseChecklist.do?op=showOwner&ownerID=${owner.id}">${owner.name}</a></td>
						<td><a href="mailto:${owner.email}?Subject=Release Checklist Status Update">${owner.email}</a></td>
						<td><a class="btn btn-mini btn btn-danger btn-delete"
							href="releaseChecklist.do?op=deleteOwner&ownerID=${owner.id}">Delete</a></td>
					</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>