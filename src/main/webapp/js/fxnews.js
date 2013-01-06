var initial = true;
//sync inteval, default 0.5 minute
var newsSyncInteval = 0.5;
var intervalId;
$(document).ready(fetchNews)
function fetchNews() {
    if (initial) {
        getAllNews();
        initial = false;
    }
    intervalId = setInterval(syncNews, newsSyncInteval * 60 * 1000);
}

function syncNews() {
    var newsUpdated = false;
    $.ajax({
        url:'isNewsUpdated.action',
        type:'GET',
        dataType:'json',
        timeout:10000,
        error:function () {
        },
        success:function (data) {
            newsUpdated = data.newsUpdated;
            if (newsUpdated) {
                updatePage();
            }
            updateLastCheckTime();
        }
    });
}
function updatePage() {
    $.ajax({
        url:'fetchFxNews.action',
        type:'GET',
        dataType:'json',
        timeout:10000,
        error:function () {

        },
        success:function (data) {
            updateNewsList(data);
        }
    });
}
function getAllNews() {
    $.ajax({
        url:'fetchAllNews.action',
        type:'GET',
        dataType:'json',
        timeout:10000,
        error:function () {

        },
        success:function (data) {
            updateNewsList(data);

        }
    });
}

function updateNewsList(data) {
    var size = data.news.length;
    $.each(data.news, function (i, n) {
        var row = $("#template").clone();
        row.find(".timestamp").text(n.timeStamp);
        row.find(".content").text(n.content);
        row.removeAttr("id");
        row.attr("class", "newsItem");
        //row.appendTo("#datas");
        row.insertAfter("#template");
    });
}
function refresh() {
    syncNews();
}
function updateLastCheckTime() {
    var d = new Date();
    $("#lastCheckTime").html(d.getMonth() + 1 + "/" + d.getDate() + " " + d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds());

}
function updateSyncInteval(arg0) {
    if (arg0 >= 0.5) {
        newsSyncInteval = arg0;
        clearInterval(intervalId);
        intervalId = setInterval(syncNews, newsSyncInteval * 60 * 1000);
    }
}