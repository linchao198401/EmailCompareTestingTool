<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="page_title" value="DE Email Compare Testing Tool"></c:set>
<c:set var="nav_name" value="testSuite"></c:set>
<%@ include file="include.jsp"%>
<%@ include file="include/DEEmailCompareNav.jsp"%>

	<div class="container"">
		<h1>Create new test suite</h1>
		<h2 style="margin: 5px 0">
			<a class="btn btn-small btn btn-info" href="testSuite.do?op=list">Back
				To Test Suite</a>
		</h2>
		<form action="testSuite.do?op=addTestSuite" method="post">

			<label for="testCaseName">Test Suite Name</label><br /> <input
				id="testCaseName" name="name" />
			<div class="clearfix">

				<p class="links">
					<label for="testCases">Test Cases (Double click to pick):</label> <select
						id="testCases" multiple="multiple" style="width: 80%" size="20">
						<c:forEach items="${testCases}" var="testCase">
							<option value="${testCase.id}">
								<c:out value="${testCase.name}" />
							</option>
						</c:forEach>
					</select>

				</p>
				<p class="rechts">
					<label for="items">Included In Test Suite:</label> <select
						id="items" name="items" size="20" multiple="multiple"
						style="width: 80%">
						<c:forEach items="${selectedCases}" var="selectedCase">
							<option value="${selectedCase.id}">
								<c:out value="${selectedCase.name}" />
							</option>
						</c:forEach>
					</select>
				</p>
			</div>

			<p class="button">
				<button class="btn btn-large btn btn-success" type="submit">Add
					New Test Suite</button>
			</p>
		</form>
	</div>
	<script src="js/jquery-1.6.4.min.js"></script>
	<script type="text/javascript">
		$(function() {
			moveFromOneSelectToAnotherSelect($('#testCases'), $('#items'));
		});
	</script>
</body>
</html>
