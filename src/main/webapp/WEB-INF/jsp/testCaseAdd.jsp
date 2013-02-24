<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="page_title" value="DE Email Compare Testing Tool"></c:set>
<c:set var="nav_name" value="testCase"></c:set>
<%@ include file="include.jsp"%>
<%@ include file="include/DEEmailCompareNav.jsp"%>

	<div class="container"">
		<h1>Add new test case</h1>
		<h2 style="margin: 5px 0">
			<a class="btn btn-small btn btn-info" href="testCase.do?op=list">Back
				to Test Cases List</a>
		</h2>
		<form action="testCase.do" method="post">
			<input type="hidden" name="op" value="add" /> <label for="name">Name:</label>
			<input id="name" name="name" /> <br />

			<p>
				<label for="templateGroupId">Template Group:</label> <select
					id="templateGroupId" name="templateGroupId">
					<c:forEach items="${templateGroups}" var="templateGroup">
						<option value="${templateGroup.id}">
							<c:out value="${templateGroup.name}" />
						</option>
					</c:forEach>
				</select>

			</p>
			<p>
				<label for="email">Email:</label> <select id="email" name="emailId">
					<c:forEach items="${emails}" var="email">
						<option value="${email.id}">
							<c:out value="${email.name}" />
						</option>
					</c:forEach>
				</select>
			</p>
			<button class="btn btn-large btn btn-success" type="submit">Create
				New Test Case</button>
		</form>
	</div>
	
	<script type="text/javascript">
	$(function() {
		$("#templateGroupId option").each(function() {
			var selectedTemplateGroupID = ${selectedTemplateGroup.id};
			if ($(this).attr("value") == selectedTemplateGroupID) {
				$(this).attr("selected", "selected");
			}
		})
	})
	</script>

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