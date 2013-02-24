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
		<h1>Update Owner</h1>
		<h2 style="margin: 5px 0">
			<a class="btn btn-small btn btn-info" href="releaseChecklist.do?op=listOwner">Back to Owner List</a>
		</h2>
		<form action="releaseChecklist.do">
			<input type="hidden" name="op" value="updateOwner" />
			<input type="hidden" name="id" value="${owner.id}" />
			 <label>Name:</label> <input
				name="name" placeholder="Owner Name" value="${owner.name}" /> <label>Email:</label> <input
				name="email" placeholder="Email Address" value="${owner.email }"/>
			<br />
			<button class="btn btn-large btn btn-success" type="submit">Update Owner</button>
		</form>
	</div>
</body>
</html>