<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="page_title" value="DE Email Compare Testing Tool"></c:set>
<c:set var="nav_name" value="testSuite"></c:set>
<%@ include file="include.jsp"%>
<%@ include file="include/DEEmailCompareNav.jsp"%>

	<div class="container"">
		<h1>Test Suite List</h1>
		<h2 style="margin: 5px 0">
			<a class="btn btn-small btn btn-success" href="testSuite.do">Add
				New Test Suite</a>
		</h2>
		<table class="table table-bordered table-striped">
			<tr>
				<th>Name</th>
				<th>Action</th>
			</tr>

			<c:forEach items="${page.result}" var="testSuite">
				<tr>
					<td>${testSuite.name}</td>
					<td><a
						class="btn btn-mini btn btn-danger addTemplateGroupButton"
						href="testSuite.do?op=deleteTestSuite&id=${testSuite.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>

		<div>
			Total :
			<c:out value="${page.totalResultCount}" />
			records. &nbsp;&nbsp;&nbsp;&nbsp;
			<c:if test="${page.currentPage gt 1}">
				<a
					href="testSuite.do?op=list&currentPage=${page.currentPage - 1}">&larr;</a>
			</c:if>
			Page:
			<c:out value="${page.currentPage}" />
			/
			<c:out value="${page.totalPageCount}" />
			<c:if test="${page.currentPage lt page.totalPageCount}">
				<a
					href="testSuite.do?op=list&currentPage=${page.currentPage + 1}">&rarr;</a>
			</c:if>
		</div>
	</div>
	<script src="js/jquery-1.6.4.min.js"></script>
	<script type="text/javascript">
		
	</script>
</body>
</html>
