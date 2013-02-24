<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="page_title" value="DE Email Compare Testing Tool"></c:set>
<c:set var="nav_name" value="email"></c:set>
<%@ include file="include.jsp"%>
<%@ include file="include/DEEmailCompareNav.jsp"%>
<div class="container"">
    <h1>All the emails in DB</h1>
    <h2 style="margin: 5px 0">
        <a class="btn btn-small btn btn-success" href="email.do?op=add">Add New Email</a>
    </h2>
    <table class="table table-bordered table-striped">
        <tr>
            <th>Name</th>
            <th>Content</th>
            <th>Binded Template Group</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${page.result}" var="email">
            <tr>
                <td><a href="email.do?op=show&id=${email.id}">${email.name}</a></td>
                <td>${email.content}</td>
                <td>Template Group Name</td>
                <td><a class="btn btn-mini btn btn-danger addTemplateGroupButton" href="email.do?op=delete&id=${email.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
    <div>
        Total :
        <c:out value="${page.totalResultCount}" />
        records. &nbsp;&nbsp;&nbsp;&nbsp;
        <c:if test="${page.currentPage gt 1}">
            <a href="email.do?op=listEmail&currentPage=${page.currentPage - 1}">&larr;</a>
        </c:if>
        Page:
        <c:out value="${page.currentPage}" />
        /
        <c:out value="${page.totalPageCount}" />
        <c:if test="${page.currentPage lt page.totalPageCount}">
            <a href="email.do?op=listEmail&currentPage=${page.currentPage + 1}">&rarr;</a>
        </c:if>
    </div>
</div>
</body>
</html>