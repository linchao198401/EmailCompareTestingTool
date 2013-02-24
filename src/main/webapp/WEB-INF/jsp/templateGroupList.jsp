<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="page_title" value="DE Email Compare Testing Tool"></c:set>
<c:set var="nav_name" value="templateGroup"></c:set>
<%@ include file="include.jsp"%>
<%@ include file="include/DEEmailCompareNav.jsp"%>

	<div class="container"">
		<h1>All the template groups in DB</h1>
		<h2 style="margin: 5px 0">
			<a class="btn btn-small btn btn-success" href="templateGroup.do?op=add">Add
				New Template Group</a>
		</h2>
		<table class="table table-bordered table-striped">
			<tr>
				<th>Name</th>
				<th>Domain</th>
				<th>Template</th>
				<th>Test Case</th>
				<th>Action</th>
			</tr>
			<c:forEach var="templateGroup" items="${templateGroups}">
				<tr>
					<td>${templateGroup.name}</td>
					<td>${templateGroup.domain}</td>
					<td><c:forEach var="templateSequence"
							items="${templateGroup.templateSequences}">
						${templateSequence.template.name} <br />
						</c:forEach></td>
					<td><a class="btn btn-mini btn btn-success"
						href="testCase.do?op=add&templateGroupId=${templateGroup.id}">Create
							Test Case</a></td>
					<td><a class="btn btn-mini btn btn-danger"
						href="templateGroup.do?op=delete&templateGroupID=${templateGroup.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<script src="bootstrap/js/jquery.js"></script>
	<script src="bootstrap/js/bootstrap-transition.js"></script>
	<script src="bootstrap/js/bootstrap-alert.js"></script>
	<script src="bootstrap/js/bootstrap-modal.js"></script>
	<script src="bootstrap/js/bootstrap-dropdown.js"></script>
	<script src="bootstrap/js/bootstrap-scrollspy.js"></script>
	<script src="bootstrap/js/bootstrap-tab.js"></script>
	<script src="bootstrap/js/bootstrap-tooltip.js"></script>
	<script src="bootstrap/js/bootstrap-popover.js"></script>
	<script src="bootstrap/js/bootstrap-button.js"></script>
	<script src="bootstrap/js/bootstrap-collapse.js"></script>
	<script src="bootstrap/js/bootstrap-carousel.js"></script>
	<script src="bootstrap/js/bootstrap-typeahead.js"></script>
</body>
</html>