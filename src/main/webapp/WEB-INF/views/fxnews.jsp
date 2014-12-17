<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>fxnews</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.8.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/fxnews.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/fxnews.css" media="screen">
</head>
<body>
<div id="info">
    <input id="btnRefresh" type="BUTTON" value="Refresh" onclick="refresh()"/>
    <s:select list="{'0.5','1','3','10', '30','60'}" onchange="updateSyncInteval(this.value)"></s:select>
    <span id="lastCheckTime"></span>
</div>
<ul>
    <li id="template"><span class="timestamp"></span><span class="content"></span></li>
</ul>
</body>
</html>