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
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<link rel="stylesheet" media="screen" type="text/css"
	href="datepicker/css/datepicker.css" />
<script type="text/javascript" src="datepicker/js/datepicker.js"></script>
<style>
body {
	padding-top: 60px;
}
</style>
<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<link href="css/common.css" rel="stylesheet">
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
						<li class="active"><a
							href="releaseChecklist.do?op=listRelease">Release</a></li>
						<li><a href="releaseChecklist.do?op=currentRelease">Current
								Release</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<div class="container"">
		<c:if test="${errorMessage != null}">
			<div id="topErrorMessage">${errorMessage}</div>
		</c:if>
		<h1>Release List</h1>
		<h2 style="margin: 5px 0">
			<a class="btn btn-small btn btn-success"
				href="releaseChecklist.do?op=addRelease">Create New Release</a>
		</h2>
		<table class="table table-bordered table-striped">
			<tr>
				<th>Name</th>
				<th>QA Stable Date</th>
				<th>Stage Date</th>
				<th>Prod Date</th>
				<th>Current Release</th>
				<th>Action</th>
			</tr>
			<c:forEach var="release" items="${allReleases}">
				<tr>
					<td><a
						href="releaseChecklist.do?op=showRelease&releaseID=${release.id}">${release.name}</a></td>
					<td><fmt:formatDate value="${release.qaStableDate}"
							type="date" />
						<div class="enableDatePicker"
							date="<fmt:formatDate value="${release.qaStableDate}" pattern="yyyy-MM-dd" />"></div></td>
					<td><fmt:formatDate value="${release.stageDate}" pattern="yyyy-MM-dd" />
						<div class="enableDatePicker"
							date="<fmt:formatDate value="${release.stageDate}" pattern="yyyy-MM-dd" />"></div></td>
					<td><fmt:formatDate value="${release.prodDate}" pattern="yyyy-MM-dd" />
						<div class="enableDatePicker"
							date="<fmt:formatDate value="${release.prodDate}" pattern="yyyy-MM-dd" />"></div></td>
					<td><c:choose>
							<c:when test="${release.currentRelease}">
								Current Release
							</c:when>
							<c:otherwise>
								<a class="btn btn-mini btn btn-primary"
									href="releaseChecklist.do?op=setCurrentRelease&releaseID=${release.id}">Set
									As Current Release</a>
							</c:otherwise>
						</c:choose></td>
					<td><a class="btn btn-mini btn btn-danger btn-delete"
						href="releaseChecklist.do?op=deleteRelease&releaseID=${release.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script type="text/javascript">
		$(function() {
			$('.enableDatePicker').each(function() {
				var date = $(this).attr("date");
				$(this).DatePicker({
					flat : true,
					date : date,
					current : date,
					calendars : 1,
					starts : 0,
					disabled : true
				});
			});
		});
	</script>
</body>
</html>