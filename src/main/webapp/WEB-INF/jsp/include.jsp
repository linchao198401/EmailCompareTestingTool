<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
    String contextPath = request.getContextPath();
%>
<c:set var="contextPath" scope="request"><%= contextPath %></c:set>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>${page_title}</title>
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
<link href="css/common.css" rel="stylesheet">
<style>
body {
	padding-top: 60px;
}
</style>
<link href="bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
</head>
<body>