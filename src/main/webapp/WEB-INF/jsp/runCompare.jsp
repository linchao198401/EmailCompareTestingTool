<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="page_title" value="DE Email Compare Testing Tool"></c:set>
<c:set var="nav_name" value="testRunning"></c:set>
<%@ include file="include.jsp"%>
<%@ include file="include/DEEmailCompareNav.jsp"%>
<link href="css/de.css" type="text/css" rel="stylesheet" />
<script src="js/de.js" type="text/javascript"></script>

<div class="container">
	<h1>Compare template group and email</h1>
	<br /> <br />
	<div class="row">
		<div class="span6">
			<h3>Template Group</h3>
			<div style="background-color: #FFFFDB">${form.template}</div>
		</div>
		<div class="span6">
			<h3>Email</h3>
			<div style="background-color: #FFFFDB">${form.email}</div>
		</div>
	</div>
	<div style="display: none">
		<form:form id="diffForm" action="#" commandName="form">
			<form:textarea rows="10" cols="30" id="een" name="een"
				path="template" readonly="true" />
			<form:textarea rows="10" cols="30" id="twee" name="twee" path="email"
				readonly="true" />
		</form:form>
	</div>
	<br /> <br />
	<table>
		<thead>
			<tr>
				<th colspan="3"><a id="compareBySectionButton"
					class="btn btn-mini btn" style="margin-top: 3px">Compare By
						Section</a> <a id="compareByLineButton"
					class="btn btn-mini btn btn-info" style="margin-top: 3px">Compare
						By Line</a></th>
			</tr>
		</thead>
		<tbody id="res">
		</tbody>
	</table>
	<br /> <br />
	<p>
		<a class="btn btn-small btn btn-success"
			href="testRunning.do?op=succeed&testCaseId=${form.testCaseId}&testSuiteId=${form.testSuiteId}">Pass</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a class="btn btn-small btn btn-danger"
			href="testRunning.do?op=fail&testCaseId=${form.testCaseId}&testSuiteId=${form.testSuiteId}">Fail</a>
		<label>Report to Dev</label> <select id="reportToDev">
			<option></option>
			<c:forEach var="owner" items="${allOwners}">
				<option email="${owner.email}">Send email to ${owner.name}</option>
			</c:forEach>
		</select>
	</p>
	<div style="height: 50px"></div>
	<script type="text/javascript">
		$("#reportToDev").change(function(){
			var pre = "mailto:";
			var post = "?Subject=Found one defect&body= Hi Dev, Found one compare difference, please have a look. <br /> <a href='" + window.location.href + "'>Click here to see the compare result</a>";
			var option = $("option:selected", this).attr("email");
			window.location = pre + option + post;
		});
	</script>
</div>
</body>
</html>