<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="include.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Test Case</title>
<link href="css/de.css" type="text/css" rel="stylesheet" />
<script src="js/jquery-1.6.4.min.js" type="text/javascript"></script>
<script src="js/de.js" type="text/javascript"></script>
</head>

<body>

    <br />

    <h1>Test Case</h1>
    <br />
    <br />
    <br />
    
    <div>
    <table border="2">
        <c:forEach items="${page.result}" var="testCase">
            <tr>
                <td style="width: 10%">
                    <a href="testCase.do?id=${testCase.id}"><c:out
                            value="${testCase.id}" /></a></td>
                <td><c:out value="${testCase.name}" /></td>
                <td><c:out value="${testCase.isSuccess}" /></td>
                <td style="width: 10%; text-align: left">
                    <a href="compare.do?op=comapre&testCaseId=${testCase.id}">compare</a>
                    <a href="testCase.do?op=deleteTestCase&id=${testCase.id}">delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    </div>
    
    <br />
    <br />
    
    <div>
        Total : <c:out value="${page.totalResultCount}" /> records. &nbsp;&nbsp;&nbsp;&nbsp;
        <c:if test="${page.currentPage gt 1}">
            <a href="testCase.do?op=listTestCase&currentPage=${page.currentPage - 1}">&larr;</a>
        </c:if>
        Page: <c:out value="${page.currentPage}" /> / <c:out value="${page.totalPageCount}" /> 
        <c:if test="${page.currentPage lt page.totalPageCount}">
            <a href="testCase.do?op=listTestCase&currentPage=${page.currentPage + 1}">&rarr;</a>
        </c:if>
    </div>

</body>


</html>