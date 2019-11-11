$(document).ready(function(){

   $("#username").keyup(function(){

      var uname = $("#username").val();

      if(uname != ''){

         $("#username_check_response").show();

         $.ajax({
            url: 'registration/check_username',
            dataType: 'json',   
            data: {'username':uname},
            success: function(response){
                if(response.result){
                    $("#username_check_response").html("<span class='not-exists'>* Такой логин уже занят.</span>");
                }else{
                    $("#username_check_response").html("<span class='exists'>Доступен</span>");
                }

             }
          });
      }else{
         $("#username_check_response").hide();
      }

    });

 });