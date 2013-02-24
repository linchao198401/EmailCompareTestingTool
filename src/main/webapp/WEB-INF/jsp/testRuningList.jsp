<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="page_title" value="DE Email Compare Testing Tool"></c:set>
<c:set var="nav_name" value="testRunning"></c:set>
<%@ include file="include.jsp"%>
<%@ include file="include/DEEmailCompareNav.jsp"%>

<div class="container">
	<h1>Run Test Suite</h1>

	<div>
		<table class="table table-bordered table-striped">
			<tr>
				<th>Name</th>
				<th>IsSuccess</th>
				<th>Start Testing</th>
				<th>Action</th>
			</tr>
			<c:forEach items="${page.result}" var="testSuite">
				<tr>
					<td><c:out value="${testSuite.name}" /></td>
					<td><c:if test="${!testSuite.start}">
							Not Start Yet
						</c:if> <c:if test="${!testSuite.complete && testSuite.start}">Running</c:if>
						<c:if test="${testSuite.complete && testSuite.start}">
							<label class="runStatus">${testSuite.success}</label>
						</c:if></td>
					<td><a
						class="btn btn-mini btn btn-success addTemplateGroupButton"
						href="testRunning.do?op=run&id=${testSuite.id}">Run</a></td>
					<td><a
						class="btn btn-mini btn btn-danger addTemplateGroupButton"
						href="testSuite.do?op=deleteTestSuite&id=${testSuite.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<br /> <br />

	<div>
		Total :
		<c:out value="${page.totalResultCount}" />
		records. &nbsp;&nbsp;&nbsp;&nbsp;
		<c:if test="${page.currentPage gt 1}">
			<a href="testSuite.do?op=list&currentPage=${page.currentPage - 1}">&larr;</a>
		</c:if>
		Page:
		<c:out value="${page.currentPage}" />
		/
		<c:out value="${page.totalPageCount}" />
		<c:if test="${page.currentPage lt page.totalPageCount}">
			<a href="testSuite.do?op=list&currentPage=${page.currentPage + 1}">&rarr;</a>
		</c:if>
	</div>
</div>
</body>
</html>