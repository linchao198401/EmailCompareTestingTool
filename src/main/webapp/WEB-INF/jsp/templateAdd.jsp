<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="page_title" value="DE Email Compare Testing Tool"></c:set>
<c:set var="nav_name" value="template"></c:set>
<%@ include file="include.jsp"%>
<%@ include file="include/DEEmailCompareNav.jsp"%>

	<div class="container"">
		<h1>New Template</h1>
		<h2 style="margin: 5px 0">
			<a class="btn btn-small btn btn-info" href="template.do?op=list">Back to Template List</a>
		</h2>
		<form action="template.do">
			<input type="hidden" name="op" value="add" /> <label>Name:</label> <input
				name="name" placeholder="Template Name" /> <label>Type:</label> <select
				name="type">
				<option>Header</option>
				<option>Body</option>
				<option>Footer</option>
			</select> <label>Domain:</label> <select name="domain">
				<option>Generic</option>
				<option>Hilton</option>
				<option>Accor</option>
			</select> <label>Content EN:</label>
			<textarea rows="15" name="en" style="width: 800px"></textarea>
			 <label>Content FR:</label>
			<textarea rows="15" name="fr" style="width: 800px"></textarea>
			 <label>Content DE:</label>
			<textarea rows="15" name="de" style="width: 800px"></textarea>
			<br />
			<button class="btn btn-large btn btn-success" type="submit">Create
				New Template</button>
		</form>
	</div>
</body>
</html>