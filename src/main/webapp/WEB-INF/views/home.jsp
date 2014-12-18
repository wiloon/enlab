<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>EnLab</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/themes/base/jquery.ui.all.css"/>

    <script src="${pageContext.request.contextPath}/resources/bower_components/jquery/dist/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bower_components/jqueryui/ui/core.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bower_components/jqueryui/ui/widget.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bower_components/jqueryui/ui/button.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bower_components/jqueryui/ui/effect-highlight.js"></script>
    <script src="${pageContext.request.contextPath}/resources/bower_components/jqueryui/ui/effect-fade.js"></script>
    <script src="${pageContext.request.contextPath}/js/ui/jquery.effects.fade.js"></script>

    <!-- enLab.js -->
    <script src="${pageContext.request.contextPath}/js/enLab.js">

    </script>

    <!-- enLab.css -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/enLab.css" media="screen">

    <!-- table style.css -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/tableStyle0.css"
          media="screen">

</head>

<body>
<!-- header -->
<header>
    <!-- project name -->
    <div id="projectName">EnLab</div>
    <!-- information field, for query ,update info ; -->
    <div class="info">
        <div id="status"></div>
    </div>
</header>
<!-- search -->
<div id="search">
    <div id="idInputText">

        <!-- English -->
        <div id="idInputTextEn" class="inputField">
            <span id="idLabelEn">English: </span>
            <input id="english" name="ecp.english" type="text"
                         onclick="setKeyLocationEn(this);" onkeyup='keyEventEn(this.value);'
                         onkeydown="onKeyDownEn(this)" onmouseover="enOnMouseOver(this)"
                         autofocus="autofocus"/>

            <!-- button search -->
            <input id="btnSearch" type="button" value="Search"
                   onclick="searchDic('')"/>

            <!-- button search you dao -->
            <input id="btnSearchYD" type="button" value="SearchYD"
                   onclick="searchYD()"/>


            <!-- button to lower case -->
            <input id="btnLC" type="button" value="TLC" alt="to lower case"
                   onclick="enToLowerCase()"/>

            <!-- button clear text field -->
            <input id="btnClear" type="button" value="Clear"
                   onclick="clearField('ECP')"/>
        </div>

        <!-- chinese -->
        <div id="idInputTextCn" class="inputField">
            <span>Chinese: </span>
            <input id="chinese" name="ecp.chinese" onkeyup='cnKeyEvent();' type="text"
                   onclick='setKeyLocationCn()' onkeydown="onKeyDownCn(this)"
                         onmouseover="cnOnMouseOver(this)"/>
        </div>

        <!-- pronunciation -->
        <div id="idInputTextP" class="inputField">
            <span>Pronunciation:</span>
            <input id="pronunciation" name="ecp.pronunciation" type="text"
                   onkeyup="prnKeyEvent();" onclick='setKeyLocationPro(this)'
                         onmouseover="pronOnMouseOver(this)"/>

            <!-- button add or update -->
            <input id="btnAddOrUpdate" type="button" value="Add/Update"
                   onclick="addOrUpdate()"/>


            <!-- button count+ -->
            <input id="btnCount" type="button" value="Count+"
                   onclick="updateCount()"/>

        </div>
    </div>

    <div id="debug">
        <span id="d1"></span>
    </div>
    <div class="xxx">
        <span id="wordCountDec" class="count">----</span> <span
            id="wordCountHex" class="count">------</span> <span
            id="wordCountBin" class="count">----------------</span>
        <!-- 0000000000000 -->
    </div>


</div>

<!-- display -->
<div id="display">
    <div id="dicTitle">
        <div class="dicTitleItem">Dictionary</div>
        <div class="dicTitleItem" id="iconLoading">
            <img alt="loading..."
                 src="${pageContext.request.contextPath}/img/loading.gif">
        </div>
        <div id="summaryInfo">
            Total: <span id="total">----</span> ; Today Add: <span id="today">-</span>
            ; Today Update: <span id="todayUpdate">-</span>
        </div>
    </div>
    <!-- dictionary -->
    <div id="dictionary">

        <table id="datas" style="border-collapse: collapse">
            <tr id="template">
                <td id="idColor" class="colunmColor"></td>
                <td id="en" class="columnEn"></td>
                <td id="cn" class="columnCn"></td>
                <td id="p" class="columnP"></td>
                <td id="idCount" class="classColumnCount"></td>
            </tr>
        </table>
    </div>
    <div id="top10">
        <ul id="top10ul">
            <li id="top10liTemplate" onclick="top10Click(this)"></li>
        </ul>
    </div>

</div>
<!-- display end -->
<footer>
</footer>
<!-- <div id="progressbar"></div> -->

</body>
</html>