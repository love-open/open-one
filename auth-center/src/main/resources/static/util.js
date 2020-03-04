/*日期格式化*/
function dateFrom(form, date) {
    var o = {
        "M+" : date.getMonth()+1,                 //月份
        "d+" : date.getDate(),                    //日
        "h+" : date.getHours(),                   //小时
        "m+" : date.getMinutes(),                 //分
        "s+" : date.getSeconds(),                 //秒
        "S"  : date.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(form))
        form=form.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(form))
            form= form.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return form;
}

// 日期,在原有日期基础上,增加days天数,默认增加1天
function addDate(date, days) {
    if (days === undefined || days === '') {
        days = 1;
    }
    var date = new Date(date);
    date.setDate(date.getDate() + days);
    var month = date.getMonth() + 1;
    var day = date.getDate();
    return date.getFullYear() + '-' + getFormatDate(month) + '-' + getFormatDate(day);
}

// 日期月份/天的显示,如果是1位数,则在前面加上'0'
function getFormatDate(arg) {
    if (arg == undefined || arg == '') {
        return '';
    }

    var re = arg + '';
    if (re.length < 2) {
        re = '0' + re;
    }

    return re;
}

/*根据datatables 中checkbox的 name获取全部的选中状态*/
function getidarray(name) {
    var stringlist = [];
    var i = 0;
    $("input[name='"+name+"']").each(function() {
        if(this.checked){
            stringlist[i] = this.value;
            i++;
        }
    });
    return stringlist;
}

/*全选,取消全选事件*/
function checkall(div,name){
    if (div.checked) {
        $("input[name='"+name+"']").each(function () {
            this.checked = true;
        });
    } else {
        $(div).removeAttr('checked');
        $("input[name='"+name+"']").each(function () {
            this.checked = false;
        });
    }
}

/*选中状态切换,并判断全选按钮状态*/
function childclick(div,id){
    var flag = true;
    if (div.checked == false) {
        $("#"+id+"").prop("checked", false);
    }else {
        $("input[name='"+div.name+"']").each(function () {
            /*查找未选中状态*/
            if(this.checked == false){
                flag = false;
                return false;
            }
        });
        if(flag == true){
            $("#"+id+"").prop("checked", true);
        }
    }
}

/*日期比较*/
function tab(date1, date2) {
    var oDate1 = new Date(date1);
    var oDate2 = new Date(date2);
    if (oDate1.getTime() > oDate2.getTime()) {
        /*提前*/
        return "0";
    } else if (oDate1.getTime() == oDate2.getTime()) {
        /*按时*/
        return "1";
    } else {
        /*延期*/
        return "2";
    }
}
/**
 * 计算两个日期差的天数
 * @param date1 字符串大日期
 * @param date2 字符串小日期
 * @returns
 */
function GetNumberOfDays(date1, date2){//获得天数
    //date1：开始日期，date2结束日期
    var a1 = Date.parse(new Date(date1));
    var a2 = Date.parse(new Date(date2));
    var day = parseInt((a1 - a2) / (1000 * 60 * 60 * 24));//核心：时间戳相减，然后除以天数
    return day
};

/**
 * 取当前月的最后一天
 * @returns Date 返回一个Date()类型的值
 */
function getCurrentMonthLastDate(){
    var currentDate = new Date();
    var currentMonth = currentDate.getMonth();
    var nextMonth = ++currentMonth;
    var nextMonthFirstDay = new Date(currentDate.getFullYear(), nextMonth, 1);
    var oneDay = 1000 * 60 * 60 * 24;
    return new Date(nextMonthFirstDay - oneDay);
}
//获取内容的高度
var yHeight = $(".content-header").parent().height();