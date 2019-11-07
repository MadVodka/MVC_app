$(document).ready(function() {
    $(".row > .sidebar > .block > a").click(function(){
        $(".row > .sidebar > .block > a").removeClass("active");
        $(this).addClass("active");

        var $item = $(this).attr("href");
        $(".content > .block").hide();
        $(".content > " + $item).show();
    });
})