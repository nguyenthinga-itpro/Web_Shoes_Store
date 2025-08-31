<%-- 
    Document   : Signup.jsp
    Created on : Mar 19, 2024, 4:53:49 PM
    Author     : Nguyen Thi Nga - CS171351
--%>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <link href="css/login.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10/dist/sweetalert2.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />

        <title>KiA Shop - Be good, Be bad, Be yourself | Login</title>
        <style>
            .error {
                color: #fff;
                font-size: 16px;
                display: block;
                margin-top: 5px;
            }
        </style>
        <!-- Favicon  -->
        <link rel="icon" href="img/core-img/favicon2.ico">
    </head>
    <body>
        <div class="wel">
            <h1>Welcome to KiA Shop</h1>
            <p>Be good, Be bad, Be yourself</p>
        </div>
        <div id="logreg-forms">

            <form action="signup" method="post" class="form-signup" id="formregister" style="display: block;">
                <h1 class="h3 mb-3 font-weight-normal" style="text-align: center"> Sign up</h1>
                <input name="username" type="text" id="username" class="form-control" placeholder="FullName">
                <span class="error" id="username-error"></span>
                <input name="emails" type="text" id="emails" class="form-control" placeholder="Email">
                <span class="error" id="emails-error"></span>
                <input name="passwords" type="password" id="passwords" class="form-control" placeholder="Password">
                <span class="error" id="passwords-error"></span>
                <input name="repassword" type="password" id="repassword" class="form-control" placeholder="Repeat Password">
                <span class="error" id="repassword-error"></span>
                <span class="error" id="confirmpass-error"></span>
                <button class="btn btn-warning btn-block" name="submitsignup" id="submitsignup" value="Signup" type="submit"><i class="fas fa-user-plus"></i> Sign Up</button>
                <a id="cancelsignup" href="javascript:void(0);" onclick="goBack()"><i class="fas fa-angle-left"></i> Back</a>
            </form>

            <br>

        </div>
        <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>

                    function goBack() {
                        window.history.back();
                    }
                    // check nhập dư liệu email vào
                    $(document).ready(function () {
                        $("#passwords, #repassword").on("input", function () {
                            checkPasswordMatch();
                        });

                        $("#submitsignup").click(function (event) {
                            event.preventDefault(); // Prevent form submission
                            performAjaxRequestRegister();
                        });
                    });

                    function checkPasswordMatch() {
                        var passwords = $("#passwords").val().trim();
                        var repassword = $("#repassword").val().trim();

                        if (passwords !== repassword) {
                            $("#confirmpass-error").text("Passwords do not match.").css("color", "red");
                        } else {
                            $("#confirmpass-error").text("");
                        }
                    }

                    function performAjaxRequestRegister() {
                        var username = $("#username").val().trim();
                        var emails = $("#emails").val().trim();
                        var passwords = $("#passwords").val().trim();
                        var repassword = $("#repassword").val().trim();
                        var hasErrors = false;
                        $("#emails-error, #passwords-error, #repassword-error").text("");

                        // Check if email is empty or missing '@'
                        if (username.length < 4) {
                            $("#username-error").text("Please enter your fullname,It has at least 4 characters.").css("color", "red");
                            hasErrors = true;
                        } else {
                            $("#username-error").text("");
                        }
                        if (emails === "" || emails.indexOf('@') === -1) {
                            $("#emails-error").text("Please enter a valid email address ex: abc@gmail.com.").css("color", "red");
                            hasErrors = true;
                        } else {
                            $("#emails-error").text("");
                        }
                        // Check if password is empty
                        if (passwords.length < 6) {
                            $("#passwords-error").text("Please enter password,It has at least 6 characters.").css("color", "red");
                            hasErrors = true;
                        } else {
                            $("#passwords-error").text("");
                        }
                        if (repassword.length < 6) {
                            $("#repassword-error").text("Please enter password,It has at least 6 characters.").css("color", "red");
                            hasErrors = true;
                        } else {
                            $("#repassword-error").text("");
                        }
                        // If there is an error, stop further processing
                        if (hasErrors) {
                            return;
                        }

                        $.ajax({
                            type: "POST",
                            url: "signup",
                            data: {submitsignup: "Signup", username: username, emails: emails, passwords: passwords, repassword: repassword},
                            success: function (response) {
                                if (response === "SUCCESS") {
                                    // Redirect to another page after successful login
                                    Swal.fire({
                                        // title: 'Register Success',
                                        text: 'Check email to verify pin code',
                                        icon: 'success',
                                        confirmButtonText: 'OK'
                                    }).then(() => {
                                        // Redirect to another page after clicking OK
                                        window.location.href = 'pin.jsp'; // Change this to the desired URL
                                    });
                                } else {
                                    // Display SweetAlert for failure
                                    Swal.fire({
                                        title: 'Email Exit',
                                        text: 'Email already exists, please choose another email.',
                                        icon: 'error',
                                        confirmButtonText: 'OK'
                                    });
                                }
                            },
                            error: function () {
                                // Display SweetAlert for error
                                Swal.fire({
                                    title: 'System Error',
                                    text: 'The system is failing',
                                    icon: 'error',
                                    confirmButtonText: 'OK'
                                });
                            }
                        });
                    }
        </script>
    </body>
</html>