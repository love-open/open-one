$(document).ready(function () {
    //全局hover事件
    $(".hover-layer").hover(function(){
        $(this).removeClass("out");
        $(this).addClass("in");
    },function(){
        $(this).removeClass("in");
        $(this).addClass("out");
    });
    //全局click事件
    $(".click-layer").toggle(function(){
        $(this).removeClass("out");
        $(this).addClass("in");
    },function(){
        $(this).removeClass("in");
        $(this).addClass("out");
    });
    //table checked
    $(".td-checked").change(function() {
        if ($(this).is(':checked')) {
            $(this).parent().parent().addClass('tr-checked')
        } else {
            $(this).parent().parent().removeClass('tr-checked')
        }
    });
    //form collspan
    var lheight = $('.collapse-left').height();
    var rheight = $('.collapse-right').height();
    var winwidth = $('html').width();
    function formheight() {
        if ( lheight > rheight){
            $('.form-infobox').css({
                "height": lheight + 70
            })
        }
        else {
            $('.form-infobox').css({
                "height": rheight + 70
            })
        }
    }

    if ( winwidth > 1300){
        formheight();
    }
    else {

    }

    $(window).resize(function () {
        var winwidth = $('html').width();
        if ( winwidth > 1300){
            formheight();
        }
        else {
            $('.form-infobox').css({
                "height": 'auto'
            })
        }
    });

    $("#form-collspan").click(function(){
        if( $(".form-infobox").hasClass("in") ){
            $(".form-infobox").removeClass("in");
            $(".form-infobox").addClass("out");
            $(this).text("显示信息");
        }else{
            $(".form-infobox").removeClass("out");
            $(".form-infobox").addClass("in");
            $(this).text("隐藏信息");
        }
    });
});