<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="page_title" value="DE Email Compare Testing Tool"></c:set>
<c:set var="nav_name" value="testRunning"></c:set>
<%@ include file="include.jsp"%>
<%@ include file="include/DEEmailCompareNav.jsp"%>
<link href="css/de.css" type="text/css" rel="stylesheet" />
<script src="js/de.js" type="text/javascript"></script>
<div class="container"">
    <h1>Compare template group and email</h1>
    <h2 style="margin: 5px 0">
        <a class="btn btn-small btn btn-info" href="testSuite.do?op=list">Back to Test Suite</a>
    </h2>
    <div class="row">
        <div class="span6">
            <h3>Template Group</h3>
            <div style="background-color: #FFFFDB">${form.template}</div>
            <a href="template.do?op=show&templateID=${headerID}&testCaseID=${testCaseID}" class="btn btn-small btn btn-success" style="margin-top: 3px">Modify Header</a>
            <br />
            <a href="template.do?op=show&templateID=${bodyID}&testCaseID=${testCaseID}" class="btn btn-small btn btn-success" style="margin-top: 3px">Modify Body</a>
            <br />
            <a href="template.do?op=show&templateID=${footerID}&testCaseID=${testCaseID}" class="btn btn-small btn btn-success" style="margin-top: 3px">Modify Footer</a>
            <br />
        </div>
        <div class="span6">
            <h3>Email</h3>
            <div style="background-color: #FFFFDB">${form.email}</div>
            <a href="email.do?op=show&id=${emailID}&testCaseID=${testCaseID}" class="btn btn-small btn btn-success" style="margin-top: 3px">Modify Email</a>
        </div>
    </div>
    <div style="display: none">
        <form:form id="diffForm" action="#" commandName="form">
            <form:textarea rows="10" cols="30" id="een" name="een" path="template" readonly="true" />
            <form:textarea rows="10" cols="30" id="twee" name="twee" path="email" readonly="true" />
        </form:form>
    </div>
    <table>
        <thead>
            <tr>
                <th colspan="3">
                    <a id="compareBySectionButton" class="btn btn-mini btn" style="margin-top: 3px">Compare By Section</a>
                    <a id="compareByLineButton" class="btn btn-mini btn btn-info" style="margin-top: 3px">Compare By Line</a>
                </th>
            </tr>
        </thead>
        <tbody id="res">
        </tbody>
    </table>
    <div style="height: 50px"></div>
</div>
</body>
</html>