<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="page_title" value="DE Email Compare Testing Tool"></c:set>
<c:set var="nav_name" value="template"></c:set>
<%@ include file="include.jsp"%>
<%@ include file="include/DEEmailCompareNav.jsp"%>

	<div class="container"">
		<h1>Template List</h1>
		<h2 style="margin: 5px 0">
			<a class="btn btn-small btn btn-success" href="template.do?op=add">Create
				New Template</a>
		</h2>
		<table class="table table-bordered table-striped">
			<tr>
				<th>Name</th>
				<th>Type</th>
				<th>Domain</th>
				<th>EN</th>
				<th>FR</th>
				<th>DE</th>
				<th>Group</th>
				<th>Action</th>
			</tr>
			<c:forEach var="templateList" items="${categoriedTemplates}">
				<c:forEach var="template" items="${templateList.value}">
					<tr>
						<td><a href="template.do?op=show&templateID=${template.id}">${template.name}</a></td>
						<td>${template.type}</td>
						<td>${template.domain}</td>
						<td><c:if test="${template.en != null}"><a title="test" class="tooltipEnabled">YES</a></c:if></td>
						<td><c:if test="${template.fr != null}"><a title="test" class="tooltipEnabled">YES</a></c:if></td>
						<td><c:if test="${template.de != null}"><a title="test" class="tooltipEnabled">YES</a></c:if></td>
						<td><a
							class="btn btn-mini btn btn-primary addTemplateGroupButton"
							href="#" type="${template.type}" name="${template.name}" templateID="${template.id}">Add
								To Group</a></td>
						<td><a class="btn btn-mini btn btn-danger"
							href="template.do?op=delete&templateID=${template.id}">Delete</a></td>
					</tr>
				</c:forEach>
			</c:forEach>
		</table>
		<div id="createTemplateGroupPanel" class="dropdown clearfix"
			style="position: absolute; top: 40px; right: 10px; display: none;">
			<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu"
				style="display: block; position: relative; margin-bottom: 5px; *width: 180px;">
				<li><a id="groupHeader">Header</a></li>
				<li><a id="groupBody">Body</a></li>
				<li><a id="groupFooter">Footer</a></li>
				<li class="divider"></li>
				<li><a id="createNewTemplateGroupConfirm" href="">Create New Template Group</a></li>
				<li><a id="cancelCreateNewTemplate" href="#">Cancel</a></li>
			</ul>
		</div>
	</div>
	<script src="js/jquery-1.6.4.min.js"></script>
	<script type="text/javascript">
	$(function(){
		// add to group
		$(".addTemplateGroupButton").click(function() {
			var template = $(this);
			var type = template.attr("type").toLowerCase();
			if (type != "header" && type != "body" && type != "footer") {
				return;
			}

			$("#createTemplateGroupPanel").slideDown(600);
			template.toggleClass("btn-primary");
			var name = template.attr("name");
			var templateID = template.attr("templateID")
			if (type == "header") {
				$("#groupHeader").text("Header - " + name);
				$("#createNewTemplateGroupConfirm").attr("headerID", templateID);
			}
			if (type == "body") {
				$("#groupBody").text("Body - " + name);
				$("#createNewTemplateGroupConfirm").attr("bodyID", templateID);
			}
			if (type == "footer") {
				$("#groupFooter").text("Footer - " + name);
				$("#createNewTemplateGroupConfirm").attr("footerID", templateID);
			}
		});
		// cancel the group
		$("#cancelCreateNewTemplate").click(function() {
			$("#createTemplateGroupPanel").slideUp(600);
			$(".addTemplateGroupButton").each(function() {
				if (!$(this).hasClass("btn-primary")) {
					$(this).addClass("btn-primary");
				}
			})
			$("#groupHeader").text("Header");
			$("#groupBody").text("Body");
			$("#groupFooter").text("Footer");
		});
		// create the group
		$("#createNewTemplateGroupConfirm").click(function() {
			var headerID = $("#createNewTemplateGroupConfirm").attr("headerID");
			var bodyID = $("#createNewTemplateGroupConfirm").attr("bodyID");
			var footerID = $("#createNewTemplateGroupConfirm").attr("footerID");
			
			var url = "templateGroup.do?op=setGroupName";
			if (headerID) {
				url += "&headerID=" + headerID;
			}
			if (bodyID) {
				url += "&bodyID=" + bodyID;
			}
			if (footerID) {
				url += "&footerID=" + footerID;
			}
			$(this).attr("href", url);
			return true;
		});
		$(".tooltipEnabled").tipTip();
	})
	</script>
	<div id="tiptip_holder">
		<div id="tiptip_content">
			<div id="tiptip_arrow">
				<div id="tiptip_arrow_inner"></div>
			</div>
		</div>
	</div>
</body>
</html>