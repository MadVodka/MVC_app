var isUserNameValid = false;
var isPasswordValid = false;
var isPasswordMatch = false;

$(document).ready(function () {
   onUserNameChange();
   
   $("#username").keyup(onUserNameChange);

   $("#password").keyup(function () {
      var password = $("#password").val();

      if (password != '') {
         $("#password_error").show();

         if (passwordValidation(password)) {
            $("#password_error").hide();
            isPasswordValid = true;
         } else {
            isPasswordValid = false;
            $("#password_error").html("Пароль не подходит");
         }
      } else {
         isPasswordValid = false;
         $("#password_error").hide();
      }

      activateSubmitButton();
   });

   $("#password").keyup(onChange);
   $("#checkPassword").keyup(onChange);
});

function onUserNameChange() {
   var uname = $("#username").val();

      if (uname != '') {

         $("#username_check_response").show();

         $.ajax({
            url: 'registration/check_username',
            dataType: 'json',
            data: { 'username': uname },
            success: function (response) {
               if (response.result) {
                  isUserNameValid = false;
                  activateSubmitButton();
                  $("#username_check_response").html("<span class='not-exists'>* Такой логин уже занят.</span>");
               } else {
                  isUserNameValid = true;
                  activateSubmitButton();
                  $("#username_check_response").html("<span class='exists'>Доступен</span>");
               }
            }
         });
      } else {
         isUserNameValid = false;
         $("#username_check_response").hide();
         activateSubmitButton();
      }
}

function onChange() {
   var password = $("#password").val();
   var checkPassword = $("#checkPassword").val();

   if (checkPassword != '') {
      $("#check_password_error").show();

      if (password == checkPassword) {
         isPasswordMatch = true;
         $("#check_password_error").hide();
      } else {
         isPasswordMatch = false;
         $("#check_password_error").html("Введите повторно пароль правильно");
      }
   } else {
      isPasswordMatch = false;
      $("#check_password_error").hide();
   }

   activateSubmitButton();
}

function passwordValidation(password) {
   var passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[#$^+=!*()@%&]*).{6,20}$/;
   if (password.match(passwordPattern)) {
      return true;
   } else {
      return false;
   }
}

function activateSubmitButton() {
   var registerButton = $("#registerButton");

   if (isUserNameValid && isPasswordValid && isPasswordMatch) {
      registerButton.prop('disabled', false);
   } else {
      registerButton.prop('disabled', true);
   }
}