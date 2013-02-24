<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>DE Email Compare Testing Tool</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<script src="js/jquery-1.6.4.min.js"></script>
<script src="js/common.js"></script>
<script src="js/date.js"></script>
<link rel="stylesheet" media="screen" type="text/css"
	href="datepicker/css/datepicker.css" />
<link rel="stylesheet" media="screen" type="text/css"
	href="jqueryUI/css/smoothness/jquery-ui-1.10.0.custom.css" />
<script type="text/javascript" src="datepicker/js/datepicker.js"></script>
<script type="text/javascript" src="tipTipv13/jquery.tipTip.js"></script>
<link href="tipTipv13/tipTip.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap.css" rel="stylesheet">
<style>
body {
	padding-top: 60px;
}
</style>
<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
</head>
<body>

	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="index.do?op=index">Release
					Check List</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li><a href="releaseChecklist.do?op=listOwner">Owner</a></li>
						<li><a href="releaseChecklist.do?op=listItem">Item</a></li>
						<li><a href="releaseChecklist.do?op=listRelease">Release</a></li>
						<li class="active"><a
							href="releaseChecklist.do?op=currentRelease">Current Release</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<div class="container"">
		<h1>
			Current Release: ${currentRelease.name}<a style="margin-left: 10px"
				id="displaySnapshotsButton" class="btn btn-small btn btn-info">Display
				All Snapshots</a>
		</h1>
		<h2 style="margin: 5px 0">
			<a class="btn btn-small btn btn-success"
				href="releaseChecklist.do?op=showRelease&releaseID=${currentRelease.id}">Modify
				Current Release</a>&nbsp;QA Stable:
			<fmt:formatDate value="${currentRelease.qaStableDate}"
				pattern="yyyy-MM-dd" />
			&nbsp;Stage:
			<fmt:formatDate value="${currentRelease.stageDate}"
				pattern="yyyy-MM-dd" />
			&nbsp;Prod:
			<fmt:formatDate value="${currentRelease.prodDate}"
				pattern="yyyy-MM-dd" />
			<select id="releaseItemFilterSelect" style="width: 140px">
				<option>This Release</option>
				<option <c:if test="${filter == 'todo'}">selected</c:if>>TODO</option>
				<option <c:if test="${filter == 'next3Days'}">selected</c:if>>Next
					3 Days</option>
				<c:forEach var="owner" items="${allOwners}">
					<option ownerID="${owner.id}"
						<c:if test="${filterOwnerID == owner.id}">selected</c:if>>User
						- ${owner.name}</option>
				</c:forEach>
				<option <c:if test="${filter == 'all'}">selected</c:if>>All</option>
				<option <c:if test="${filter == 'na'}">selected</c:if>>N/A</option>
			</select> <a style="margin-left: 10px" id="takeSnapshotButton"
				class="btn btn-small btn btn-primary">Take A Snapshot</a>
		</h2>
		<table class="table table-bordered table-striped">
			<tr>
				<th>Category</th>
				<th>Name</th>
				<th width="80px">Due Date</th>
				<th width="50px">Owner</th>
				<th width="50px">Status</th>
				<th width="50px">Comments</th>
			</tr>
			<c:forEach var="releaseItem" items="${releaseItems}">
				<tr>
					<td>${releaseItem.item.category}</td>
					<td><a id="item${releaseItem.item.id}" class="tooltipEnabled"
						title="${releaseItem.item.description}"
						href="releaseChecklist.do?op=showItem&itemID=${releaseItem.item.id}">${releaseItem.item.name}</a></td>
					<td><div class="dueDateValue">
							<fmt:formatDate value="${releaseItem.dueDate}"
								pattern="yyyy-MM-dd" />
						</div>
						<div class="dueDatePicker"
							<c:if test='${releaseItem.dueDate != null}'>style="display: none"</c:if>>
							<input style="width: 70px; visibility: hidden;"
								releaseItemID="${releaseItem.id}"
								class="releaseItemDueDateInput"
								value="<fmt:formatDate value="${releaseItem.dueDate}" pattern="yyyy-MM-dd" />" />&nbsp;<a
								class="btn btn-mini btn btn-success 0" style="margin-top: 2px">0</a>&nbsp;<a
								class="btn btn-mini btn btn-success 1" style="margin-top: 2px">+1</a>&nbsp;<a
								class="btn btn-mini btn btn-success 2" style="margin-top: 2px">+2</a>&nbsp;<a
								class="btn btn-mini btn btn-success 3" style="margin-top: 2px">+3</a>&nbsp;<a
								class="btn btn-mini btn btn-success ok" style="margin-top: 2px">OK</a>&nbsp;<a
								class="btn btn-mini btn btn-danger" style="margin-top: 2px">N/A</a>
						</div></td>
					<td><select style="width: 140px"
						releaseItemID="${releaseItem.id}" class="releaseItemOwnerSelect">
							<option></option>
							<c:forEach var="owner" items="${allOwners}">
								<option
									<c:if test="${releaseItem.owner.name == owner.name}">selected</c:if>
									ownerID="${owner.id}">${owner.name}</option>
							</c:forEach>
					</select></td>
					<td><select style="width: 100px"
						releaseItemID="${releaseItem.id}" class="releaseItemStatusSelect"><option></option>
							<option
								<c:if test='${releaseItem.status == "In Progress" }'>selected</c:if>>In
								Progress</option>
							<option
								<c:if test='${releaseItem.status == "Done" }'>selected</c:if>>Done</option>
							<option
								<c:if test='${releaseItem.status == "Delay" }'>selected</c:if>>Delay</option>
							<option na="na"
								<c:if test='${releaseItem.status == "N/A" }'>selected</c:if>>N/A</option></select></td>
					<td class="releaseItemComments" releaseItemID="${releaseItem.id}">${releaseItem.comments}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<script type="text/javascript">
		$(function() {
			// status change
			$(".releaseItemStatusSelect").change(function() {
				$.get("releaseChecklist.do?op=updateStatus", {
					releaseItemID : $(this).attr("releaseItemID"),
					status : $(this).val()
				});
				forNA($(this));
			});

			// na status ui
			function forNA(status) {
				if (status.val() == "N/A") {
					status.parents("tr").find(".releaseItemOwnerSelect").css(
							"display", "none");
					status.parents("tr").find(".dueDatePicker").css("display",
							"none");
					status.parents("tr").find(".dueDateValue").css("display",
							"none");
				} else {
					status.parents("tr").find(".releaseItemOwnerSelect").css(
							"display", "inline");
					status.parents("tr").find(".dueDateValue").css("display",
							"inline");
				}
			}

			$(".releaseItemStatusSelect").each(function() {
				forNA($(this));
			})

			$(".releaseItemOwnerSelect").change(function() {
				$.get("releaseChecklist.do?op=updateReleaseItemOwner", {
					releaseItemID : $(this).attr("releaseItemID"),
					ownerID : $(this).find(":selected").attr("ownerID")
				});
			});

			$(".releaseItemDueDateInput").each(function() {
				var input = $(this);
				setDatePicker(input, "right", function() {
					input.siblings(".ok").click();
				});
			})

			// show the due date input
			$(".dueDateValue").parent().click(
					function() {
						if ($(this).parents("tr").find(
								".releaseItemStatusSelect").val() != "N/A") {
							$(this).children(".dueDatePicker").slideDown();
							$(this).find("input").click();
						}
					})

			function updateDueDate(releaseItemID, dueDate) {
				$.get("releaseChecklist.do?op=updateDueDate", {
					releaseItemID : releaseItemID,
					dueDate : dueDate
				});
			}

			function getSameDueDate(button) {
				return button.parents("tr").prev().find("input").val();
			}

			// same day
			$(".dueDatePicker .0").click(function() {
				quickPickDate($(this));
				return false;
			})

			function quickPickDate(button, moreDate) {
				var input = button.siblings("input");
				var dueDate = getSameDueDate(button);
				if (moreDate) {
					dueDate = Date.parse(dueDate).add({
						days : moreDate
					}).toString("yyyy-MM-dd");
				}
				input.val(dueDate);
				updateDueDate(input.attr("releaseItemID"), dueDate);
				button.parent(".dueDatePicker").slideUp();
				button.parents("td").children(".dueDateValue").text(dueDate);
			}

			// add one day
			$(".dueDatePicker .1").click(function() {
				quickPickDate($(this), 1);
				return false;
			})

			// add two day
			$(".dueDatePicker .2").click(function() {
				quickPickDate($(this), 2);
				return false;
			})

			// add three day
			$(".dueDatePicker .3").click(function() {
				quickPickDate($(this), 3);
				return false;
			})

			// save the due date
			$(".dueDatePicker .ok")
					.click(
							function() {
								var input = $(this).siblings("input");
								updateDueDate(input.attr("releaseItemID"),
										input.val());
								$(this).parent(".dueDatePicker").slideUp();
								$(this).parents("td").children(".dueDateValue")
										.text(input.val());
								return false;
							})

			// cancel due date
			$(".dueDatePicker .btn-danger").click(
					function() {
						$(this).parents("tr").find(
								".releaseItemStatusSelect option[na=na]").attr(
								"selected", "selected");
						forNA($(this).parents("tr").find(
								".releaseItemStatusSelect"));
						$(this).parent(".dueDatePicker").slideUp();
						return false;
					})

			// filter change
			$("#releaseItemFilterSelect")
					.change(
							function() {
								$("#releaseItemUserSelect").css("display",
										"none");
								var optionValue = $(this).val();
								if (optionValue == "TODO") {
									window.location
											.replace("releaseChecklist.do?op=todo");
									return true;
								}
								if (optionValue == "Next 3 Days") {
									window.location
											.replace("releaseChecklist.do?op=next3Days");
									return true;
								}
								if (optionValue == "All") {
									window.location
											.replace("releaseChecklist.do?op=allCurrentReleaseItems");
									return true;
								}
								if (optionValue == "N/A") {
									window.location
											.replace("releaseChecklist.do?op=na");
									return true;
								}
								var ownerID = $(this).find("option:selected")
										.attr("ownerID");
								if (ownerID) {
									window.location
											.replace("releaseChecklist.do?op=myReleaseItems&ownerID="
													+ ownerID);
								} else {
									window.location
											.replace("releaseChecklist.do?op=currentRelease");
								}
							});

			// show comments editor
			$(".releaseItemComments").dblclick(function() {
				$("#commentsDiv").slideDown();
				// transfer the id to textarea
				var textarea = $("#commentsDiv textarea");
				textarea.attr("releaseItemID", $(this).attr("releaseItemID"));
				textarea.val($(this).text());
			});
			$("#saveComments").click(
					function() {
						var textarea = $(this).siblings("textarea");
						$.get("releaseChecklist.do?op=updateComments", {
							releaseItemID : textarea.attr("releaseItemID"),
							comments : textarea.val()
						});

						$(
								".releaseItemComments[releaseItemID="
										+ textarea.attr("releaseItemID") + "]")
								.text(textarea.val());
						$("#commentsDiv").slideUp();
					});
			$("#cancelComments").click(function() {
				$("#commentsDiv").slideUp();
			});
			$(".tooltipEnabled").tipTip();

			$("#takeSnapshotButton").click(function() {
				if (window.webkitNotifications.checkPermission() != 0) { // 0 is PERMISSION_ALLOWED
				    window.webkitNotifications.requestPermission();
				}
				$.get("releaseChecklist.do?op=takeSnapshot", function(){
					var notification = 
						webkitNotifications.createNotification("", 
								"Success", "You just create one new snapshot.");
							notification.show();
				});
			})

			$("#displaySnapshotsButton").click(function() {
				window.location.replace("releaseChecklist.do?op=getAllSnapshots");
			});
		})
	</script>
	<div id="commentsDiv"
		style="display: none; position: absolute; top: 100px; right: 100px">
		<textarea rows="8" cols="25" id="comments" releaseItemID=""></textarea>
		<br /> <a class="btn btn-mini btn btn-success"
			style="margin-top: 2px" id="saveComments">Save</a>&nbsp; <a
			class="btn btn-mini btn btn-danger" style="margin-top: 2px"
			id="cancelComments">Cancel</a>
	</div>
	<div id="tiptip_holder">
		<div id="tiptip_content">
			<div id="tiptip_arrow">
				<div id="tiptip_arrow_inner"></div>
			</div>
		</div>
	</div>
</body>
</html>