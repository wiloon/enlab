// for active field control.
var strActiveField;
// for status field effect: fade.
var timerTmp;

// timer for search
var timerSearch;
// ecp backup field
var intEcpId;
var intEcpCount;
var strEcpEn;
var strEcpEnTmp;
var strEcpCnTmp;
var strEcpPronTmp;
var strFlgMatch;

// is ctrl pressed
// ctrl's key code =17
var isCtrlPressed

function onKeyDownEn(obj) {
    var intKeyCode;
    intKeyCode = window.event.keyCode;

    // alert(window.event.keyCode);

    // if ctrl pressed set isCtrlPressed as true
    // if (17 == intKeyCode) {
    // isCtrlPressed = true;
    // }

    // for en field if table pressed set active field as cn.
    if (intKeyCode == 9) {
        strActiveField = "CN";
    }

    // if ESC pressed clear the en field
    if (intKeyCode == 27) {
        clearField("ECP");
    }
}

function onKeyDownCn(obj) {
    var intKeyCode;
    intKeyCode = window.event.keyCode;
    // alert(window.event.keyCode);

    // in cn field if table pressed set active field as pro.
    if (intKeyCode == 9) {
        strActiveField = "PRO";
    }
}

function setKeyLocationEn(obj) {
    strActiveField = "EN";
    obj.focus();
    obj.select();
}

function setKeyLocationCn() {
    strActiveField = "CN";
}
function setKeyLocationPro(obj) {
    strActiveField = "PRO";
    obj.select();
}

// english field; when key up, call this function.
function keyEventEn(strEnglish) {

    // set active field as EN
    strActiveField = "EN";

    // if end with space, and ctrl have not been pressed , no need trigger the
    // search action
    // if (isEndWithSpace(strEnglish) && !isCtrlPressed) {
    // return;
    // }
    // set isCtrlPressed back to false
    // isCtrlPressed = false;

    // check if english field is empty, if empty do nothing.
    if (strEnglish.trim() != '') {

        // check if chinese character
        if (isChinese(strEnglish)) {
            // if input chinese, return, no search action.
            return;
        }

        // get key code.
        var intKeyCode = window.event.keyCode;

        // alert(intKeyCode);

        // press enter, set next field active.
        // if enter pressed, and currently active filed is "English", set
        // active field as "Chinese".
        if (window.event.keyCode == 13 && strActiveField == "EN") {

            // set active field as CN.
            strActiveField = "CN";

            // change focus to CN.
            $("#chinese").focus();

            // keycode: space 32,
        } else if ((intKeyCode >= 65 && intKeyCode <= 90) || intKeyCode == 229
            || intKeyCode == 8 || intKeyCode == 32) {

            // clear timeout
            clearTimeout(timerSearch);
            clearField("CP");
            // set timeout for search action,
            timerSearch = setTimeout("searchDic('" + strEnglish + "')", 400);

        }

    } else {
        // if English field is empty, clear time out.
        clearTimeout(timerSearch);
        clearField("ECP");
    }
}

// for field chinese, when key up call this function.
function cnKeyEvent() {

    // if enter pressed, change focus to next field.
    if (window.event.keyCode == 13 && strActiveField == "CN") {
        strActiveField = "PRO";
        $("#pronunciation").focus();
        $("#pronunciation").select();
    }
}

// pronunciation field , when key up.
function prnKeyEvent() {
    if (window.event.keyCode == 13 && strActiveField == "PRO") {
        addOrUpdate();
    }
}

function addOrUpdate() {

    if (strFlgMatch == 'MATCH') {
        var strEn = $("#english").attr("value");
        var strCn = $("#chinese").attr("value");
        var strPr = $("#pronunciation").attr("value");
        if ((strEcpCnTmp != strCn) || (strEcpPronTmp != strPr)
            || (strEcpEnTmp != strEn)) {
            update();
        } else {
            updateCount();
        }

    } else {
        addWord();
    }
    // if enter pressed , set en as active field;
    setKeyLocationEn($("#english"));

}
// insert current string into DB
function addWord() {
    showLoadingIcon(true);
    // if the field english is empty, do nothing.
    if ($("#english").attr("value").trim() == '') {
        return;
    } else {
        $
            .ajax({
                url: 'addWordAction.action',
                type: 'POST',
                dataType: 'json',
                data: 'ecp.english=' + $("#english").attr("value")
                    + '&ecp.chinese=' + $("#chinese").attr("value")
                    + '&ecp.pronunciation='
                    + $("#pronunciation").attr("value"),
                timeout: 5000,
                error: function () {
                    showStatus('add word error');
                    showLoadingIcon(false);
                },
                success: function (data) {
                    showStatus(data.message);
                    updatePage(data);
                    showLoadingIcon(false);
                }
            });
    }
}

// search word in DB
function searchDic(strEnglish) {
    if (strEnglish == '') {
        strEnglish = $("#english").val();

    }
    showLoadingIcon(true);
    // update status.
    showStatus('Loading... '.concat(strEnglish));

    var requestJson = {'word': strEnglish};
    // ajax, send search request to server;
    $.ajax({
        url: 'search',
        type: 'GET',
        data: requestJson,
        dataType: 'json',
        timeout: 5000,
        error: function () {
            showStatus('Error when loading "' + strEnglish + '"');
            $("#iconLoading").css("display", "none");
        },
        success: function (data) {
            console.log("response=" + data);
            updatePage(data);
            showLoadingIcon(false);
        }
    });
}

function showStatus(strContent) {
    clearTimeout(timerTmp);
    var intContentSize = strContent.length * 7;
    $("#status").css("display", "block");
    $("#status").css("width", intContentSize + "px");
    $("#status").html(strContent);
    timerTmp = setTimeout("fadeKey()", 2000);
}

function updateStatusColor(strColor) {
    $("#status").css("background", strColor);
}

function fadeKey() {
    var options = {};
    //$("#status").effect("fade", options, 1000, callback);
}

function highlightId(strId) {
    var options = {};
    $("#" + strId).effect("highlight", options, 800, callback);
}
function updateTable(data) {
    clearTable();

    var bolMatch = false;
    var bolLike = false;
    var highlightNoMatchFlag = true;
    strFlgMatch = 'UNMATCH';
    $.each(data.lstEcp, function (i, n) {

        var row = $("#template").clone();
        var ecpId = n.ID;
        row.find("#en").text(n.english);
        row.find("#cn").text(n.chinese);
        row.find("#p").text(n.pronunciation);
        row.find(".classColumnCount").attr("id", "idCount" + ecpId);
        row.find(".classColumnCount").text(n.count);
        row.find("#enId").text(n.ID);

        if (i % 2 == 1) {
            row.attr('class', 'colorStyleTable');
        }

        var strKey = $("#english").val().trim();
        strKey = strKey.toLowerCase();
        var strTmp = n.english;

        strTmp = strTmp.toLowerCase();
        // like
        if (bolMatch == false && strTmp.indexOf(strKey) == 0) {
            // row.attr('class', 'highlightLike');
            row.find("#idColor").addClass('highlightLike');

            // update status's background color.
            // updateStatusColor("#88B2F4");
            bolLike = true;
        }
        // match
        if (strKey == strTmp) {
            // row.attr('class', 'highlightEqual');
            row.find("#idColor").addClass('highlightEqual');
            row.find("#idColor").removeClass('highlightLike');
            // update status's background color.
            // updateStatusColor("#B6FF77");
            // if match backup the current en, cn, pron.
            updateTextFieldBaseOnDic(n);
            strFlgMatch = 'MATCH';
            bolMatch = true;

        } else {

        }

        // unmatch
        // if the system can not find the word in DB, hight light first line as
        // red
        if (bolMatch == false && bolLike == false
            && highlightNoMatchFlag == true) {
            // row.attr('class', 'highlightNoMatch');
            row.find("#idColor").addClass('highlightNoMatch')
            row.find("#idColor").removeClass('highlightLike');
            row.find("#idColor").removeClass('highlightEqual');
            // update status's background color.
            // updateStatusColor("#FFB7B7");
            // $("#idInputTextEn").attr('class', 'highlightNoMatch');
            highlightNoMatchFlag = false;
        }

        row.attr("id", "item");
        row.appendTo("#datas");

    });
    if (bolMatch == false) {
        clearTextField();
    }
}
function updateTextFieldBaseOnDic(ecp) {
    intEcpId = ecp.ID;
    strEcpEnTmp = ecp.english;

    strEcpCnTmp = ecp.chinese;

    strEcpPronTmp = ecp.pronunciation;
    intEcpCount = ecp.count;
    updateTextField(ecp);
}

function updateTextFieldBaseOnYD(ecp) {
    updateTextField(ecp);
    $("#english").focus();
    $("#english").select();
}
function updateTextField(ecp) {

    // need to keep the blank space, comment out the reset en line.
    // $("#english").val(ecp.english);
    $("#chinese").val(ecp.chinese);
    $("#pronunciation").val(ecp.pronunciation);
    $("#english").focus();

}

function clearTable() {
    $("tr").remove("#item");

}

function updateInfo(data) {
    $("#total").html(data.wordCount);
    $("#today").html(data.wordCountToday);
    $("#todayUpdate").html(data.countTodayUpdate);
    $("#wordCountBin").html(data.wordCountBin);
    $("#wordCountHex").html(data.wordCoountHex);
    $("#wordCountDec").html(data.wordCount);

}

function clearTextField() {
    intEcpId = -1;
    $("#chinese").val('');
    $("#pronunciation").val('');
}

// update a word.
function update() {
    showLoadingIcon(true);
    $.ajax({
        url: 'enUpdateAction.action',
        type: 'POST',
        dataType: 'json',
        data: 'ecp.english=' + $("#english").val() + '&ecp.chinese='
            + $("#chinese").val() + '&ecp.pronunciation='
            + $("#pronunciation").val() + '&ecp.ID=' + intEcpId
            + '&ecp.count=' + intEcpCount,
        timeout: 5000,
        error: function () {
            showStatus('Error - update');
            showLoadingIcon(false);
        },
        success: function (data) {
            showStatus(data.message)
            updatePage(data);
            showLoadingIcon(false);
        }
    });

}

// callback function to bring a hidden box back
function callback() {
    // do nothing.
}
;

// update top 10 list
function updateTop10(data) {
    $(".top10Item").remove();
    var li;
    $.each(data.lstTop10, function (i, n) {
        li = $("#top10liTemplate").clone();
        li.text(n.english);
        li.attr("id", "idTop10Item");
        li.attr("class", "top10Item");
        li.appendTo("#top10ul");
    });
}

// top 10 on click, do search
function top10Click(obj) {
    $("#english").val(obj.innerHTML);
    searchDic(obj.innerHTML);
    setKeyLocationEn($("#english"));
}

// update page
function updatePage(data) {
    updateTable(data);
    updateTop10(data);
    updateInfo(data);
}

// update count
function updateCount() {
    showLoadingIcon(true);
    // if the field english is empty, do nothing.
    if ($("#english").val().trim() == '') {
        showLoadingIcon(false);
        return;
    } else {
        $.ajax({
            url: 'updateCountAction.action',
            type: 'POST',
            dataType: 'json',
            data: '&ecp.ID=' + intEcpId + '&ecp.count=' + intEcpCount,
            timeout: 5000,
            error: function () {
                showStatus('ajax Error');

                showLoadingIcon(false);
            },
            success: function (data) {
                var ecp = data.ecp
                var strId = "idCount" + ecp.ID
                showStatus(data.message);
                intEcpCount = ecp.count;
                $("#" + strId).text(intEcpCount);
                highlightId(strId);
                updateTop10(data);
                showLoadingIcon(false);
            }
        });
    }
}

function isChinese(str) {
    var i = 0;

    var blnRtn;
    blnRtn = false;
    for (i = 0; i < str.length; i++) {
        if (str.charCodeAt(i) > 127 || str.charCodeAt(i) == 94) {
            blnRtn = true
            return (blnRtn);
        }
    } // end for

    return (blnRtn);
}

function clearField(str) {
    if (str.indexOf("E") > -1) {
        $("#english").val('');
    }
    if (str.indexOf("C") > -1) {
        $("#chinese").val('');
    }
    if (str.indexOf("P") > -1) {
        $("#pronunciation").val('');
    }
    $("#english").focus();
}

function showLoadingIcon(blnTmp) {
    if (blnTmp) {
        $("#iconLoading").css("display", "block");
    } else {
        $("#iconLoading").css("display", "none");
    }

}

function enOnMouseOver(obj) {
    strActiveField = "EN";
    obj.select();
}

function cnOnMouseOver(obj) {
    strActiveField = "CN";
    obj.focus();
}

function pronOnMouseOver(obj) {
    strActiveField = "PRO";
    obj.focus();
}

function enToLowerCase() {
    str = $("#english").val();
    str = str.toLowerCase();
    $("#english").val(str);
    $("#english").focus();
}

function searchYD() {

    strEnglish = $("#english").val();

    showLoadingIcon(true);
    // update status.
    showStatus('Searching YouDao Dict... '.concat(strEnglish));

    // ajax, send search request to server;
    $.ajax({
        url: 'searchYD.action' + '?ecp.english=' + strEnglish,
        type: 'GET',
        dataType: 'json',
        timeout: 5000,
        error: function () {
            showStatus('Error "' + strEnglish + '"');
            showLoadingIcon(false);
        },
        success: function (data) {
            updateTextFieldBaseOnYD(data.ecp);
            showLoadingIcon(false);
        }
    });
}

function isEndWithSpace(str) {
    var length = str.length;
    str = str.trim()
    var lengthTrim = str.length;
    if (length != lengthTrim) {
        return true;
    } else {
        return false;
    }
}