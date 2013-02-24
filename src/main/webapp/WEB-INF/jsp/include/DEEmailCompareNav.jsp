<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="btn btn-navbar" data-toggle="collapse"
				data-target=".nav-collapse"> <span class="icon-bar"></span> <span
				class="icon-bar"></span> <span class="icon-bar"></span>
			</a> <a class="brand" href="index.do?op=index">DE Email Compare
				Testing Tool</a>
			<div class="nav-collapse collapse">
				<ul class="nav">
					<li <c:if test="${nav_name == 'template'}">class="active"</c:if>><a href="template.do?op=list">Template</a></li>
					<li <c:if test="${nav_name == 'templateGroup'}">class="active"</c:if>><a href="templateGroup.do?op=list">Template Group</a></li>
					<li <c:if test="${nav_name == 'email'}">class="active"</c:if>><a href="email.do?op=list">Email</a></li>
					<li <c:if test="${nav_name == 'testCase'}">class="active"</c:if>><a href="testCase.do?op=list">Test Case</a></li>
					<li <c:if test="${nav_name == 'testSuite'}">class="active"</c:if>><a href="testSuite.do?op=list">Test Suite</a></li>
					<li <c:if test="${nav_name == 'testRunning'}">class="active"</c:if>><a href="testRunning.do?op=list">Test Running</a></li>
				</ul>
			</div>
		</div>
	</div>
</div>