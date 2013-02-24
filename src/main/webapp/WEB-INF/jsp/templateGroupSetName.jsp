<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="page_title" value="DE Email Compare Testing Tool"></c:set>
<c:set var="nav_name" value="templateGroup"></c:set>
<%@ include file="include.jsp"%>
<%@ include file="include/DEEmailCompareNav.jsp"%>

	<div class="container"">
		<h1>Add new template group</h1>
		<h2 style="margin: 5px 0">
			<a class="btn btn-small btn btn-info" href="template.do?op=list">Back
				to Template List</a>
		</h2>
		<form action="templateGroup.do">
			<input type="hidden" name="op" value="add" /> <label>Name:</label> <input
				name="name" /> <br /> <label>Domain:</label> <select name="domain">
				<option>Generic</option>
				<option>Hilton</option>
				<option>Accor</option>
			</select> <br /> <input type="hidden" name="templateIDs" value="${headerID}"><input
				type="hidden" name="templateIDs" value="${bodyID}"><input
				type="hidden" name="templateIDs" value="${footerID}"> <label>The
				templates you pick:</label>
			<div style="margin:10px 0 10px 10px">
				<span>${tgHeader.name}</span><br />
				<span>${tgBody.name}</span><br />
				<span>${tgFooter.name}</span><br />
			</div>
			<button class="btn btn-large btn btn-success" type="submit">Add
				New Template Group</button>
		</form>
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