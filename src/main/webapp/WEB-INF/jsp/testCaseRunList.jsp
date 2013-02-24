<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="page_title" value="DE Email Compare Testing Tool"></c:set>
<c:set var="nav_name" value="testRunning"></c:set>
<%@ include file="include.jsp"%>
<%@ include file="include/DEEmailCompareNav.jsp"%>

<div class="container">
	<h1>Run Test Suite</h1>
	<h2 style="margin: 5px 0">
		<c:out value="${testSuite.name}" />
		<a class="btn btn-small btn btn-primary" href="testSuite.do?op=markTestSuiteComplete&id=${testSuite.id}">Mark Test Suite Complete</a>
	</h2>
	<table class="table table-bordered table-striped">
		<tr>
			<th>Name</th>
			<th>Template Group</th>
			<th>Email</th>
			<th>IsSuccess</th>
			<th>Compare</th>
		</tr>

		<c:forEach items="${testSuite.testCases}" var="testCase">
			<tr>
				<td>${testCase.name}</td>
				<td>${testCase.templateGroup.name}</td>
				<td>${testCase.email.name}</td>
				<td><label class="runStatus">${testCase.isSuccess}</label></td>
				<td><a
					class="btn btn-mini btn btn-success addTemplateGroupButton"
					href="testRunning.do?op=comapre&testCaseId=${testCase.id}&testSuiteId=${testSuite.id}">Compare</a></td>
			</tr>
		</c:forEach>
	</table>
</div>
<script src="js/jquery-1.6.4.min.js"></script>
</body>
</html>