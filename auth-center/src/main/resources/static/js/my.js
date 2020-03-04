function request(strParame) {
    var args = new Object();
    var query = location.search.substring(1);

    var pairs = query.split("&"); // Break at ampersand
    for (var i = 0; i < pairs.length; i++) {
        var pos = pairs[i].indexOf('=');
        if (pos == -1) continue;
        var argname = pairs[i].substring(0, pos);
        var value = pairs[i].substring(pos + 1);
        value = decodeURIComponent(value);
        args[argname] = value;
    }
    return args[strParame];
}

//路径
var message_zh_min_url = '/jquery-validation-1.19.0/dist/localization/messages_zh.min.js';

//多语言跳转
function setLang(lang) {
    var url = location.href;
    //包含lang ，替换
    if (url.indexOf('lang=') != -1) {
        if ('en_US' == lang) {
            location.href = url.replace(/lang=[^\&]*/, "lang=en_US");
        } else {
            location.href = url.replace(/lang=[^\&]*/, "lang=zh_CN");
        }
    }
    //不包含lang(indexOf()>-1)，附加
    else {
        if ('en_US' == lang) {
            location.href = url + "?lang=en_US";
        } else {
            location.href = url + "?lang=zh_CN";
        }
    }
}

