// $(document).ready(function() {
//     $(".row > .sidebar > .block > a").click(function(){
//         $(".row > .sidebar > .block > a").removeClass("active");
//         $(this).addClass("active");

//         var $item = $(this).attr("href");
//         $(".content > .block").hide();
//         $(".content > " + $item).show();
//     });
// })

 $(document).ready(function() {
    var path = window.location.pathname;

    $(".row > .sidebar > .block > a").each(function() {
        var $url = $(this).attr("href");
        if (path.match("^" + $url)) {
            $(this).addClass("active");
        }
    })

    $(".content > .block").each(function() {
        var $id = $(this).attr("id");
        if (path.match("^/mvc/cabinet/"+$id)) {
            $(this).show();
        }
    })
 })