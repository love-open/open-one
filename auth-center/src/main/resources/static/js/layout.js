$(document).ready(function () {
    var headerheight= $('.header').height();
    var screenheight= $(window).height();
    $('.left-menu').css('height',screenheight-headerheight-20);
    $('.right-content').css('height',screenheight-headerheight-20);
    $('.modal-body').css('max-height',screenheight-250);
});