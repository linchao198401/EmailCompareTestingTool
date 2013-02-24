<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="page_title" value="DE Email Compare Testing Tool"></c:set>
<c:set var="nav_name" value="email"></c:set>
<%@ include file="include.jsp"%>
<%@ include file="include/DEEmailCompareNav.jsp"%>
<div class="container"">
    <h1>Add new email</h1>
    <h2 style="margin: 5px 0">
        <a class="btn btn-small btn btn-info" href="email.do?op=list">Back to Email List</a>
    </h2>
    <form action="email.do" method="post">
        <input type="hidden" name="op" value="add" /> <label>Name:</label> <input name="name" placeholder="Email Name" /><label>Content:</label>
        <textarea id="emailContent" rows="25" name="content" style="width: 800px"></textarea>
        <br />
        <button id="addEmailButton" class="btn btn-large btn btn-success" type="submit">Create New Email</button>
    </form>
</div>
<script type="text/javascript">
	$(function() {
		$("#addEmailButton").click(function() {
			$emailContent = $("#emailContent");
			$emailContent.val(replaceEmailWithHTMLTag($emailContent.val()));
		});

		$("#emailContent").val(
				replaceEmailWithTextAreaTag($("#emailContent").val()));
	})
</script>
</body>
</html>