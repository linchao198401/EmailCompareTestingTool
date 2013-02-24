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
<script src="js/common.js"></script>
<script src="js/date.js"></script>
<link rel="stylesheet" media="screen" type="text/css"
	href="datepicker/css/datepicker.css" />
<link rel="stylesheet" media="screen" type="text/css"
	href="jqueryUI/css/smoothness/jquery-ui-1.10.0.custom.css" />
<script type="text/javascript" src="datepicker/js/datepicker.js"></script>
<script type="text/javascript" src="tipTipv13/jquery.tipTip.js"></script>
<link href="tipTipv13/tipTip.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
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
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="index.do?op=index">Release
					Check List</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li><a href="releaseChecklist.do?op=listOwner">Owner</a></li>
						<li><a href="releaseChecklist.do?op=listItem">Item</a></li>
						<li><a href="releaseChecklist.do?op=listRelease">Release</a></li>
						<li class="active"><a
							href="releaseChecklist.do?op=currentRelease">Current Release</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<div class="container"">
		<h1>Current Release Snapshot: ${snapshot.name}</h1>
		<h2 style="margin: 5px 0">
			<a class="btn btn-small btn btn-info"
				href="releaseChecklist.do?op=getAllSnapshots">Back to Snapshot List</a>
		</h2>
		<table class="table table-bordered table-striped">
			<tr>
				<th>Category</th>
				<th>Name</th>
				<th width="80px">Due Date</th>
				<th width="50px">Owner</th>
				<th width="50px">Status</th>
				<th width="50px">Comments</th>
			</tr>
			<c:forEach var="releaseItem" items="${snapshot.releaseItems}">
				<tr>
					<td>${releaseItem.category}</td>
					<td><a class="tooltipEnabled"
						title="${releaseItem.description}">${releaseItem.name}</a></td>
					<td><fmt:formatDate value="${releaseItem.dueDate}"
							pattern="yyyy-MM-dd" /></td>
					<td>${releaseItem.owner}</td>
					<td>${releaseItem.status }</td>
					<td>${releaseItem.comments}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<script type="text/javascript">
		$(function() {
			$(".tooltipEnabled").tipTip();
		})
	</script>
	<div id="tiptip_holder">
		<div id="tiptip_content">
			<div id="tiptip_arrow">
				<div id="tiptip_arrow_inner"></div>
			</div>
		</div>
	</div>
</body>
</html>