<!doctype html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>������������� �����������</title>
    <style>
        .btn {
            display: inline-block;
            background: mediumspringgreen;
            color: #ffffff;
            padding: 1rem 1.5rem;
            text-decoration: none;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<h1>Hello, dear ${first_name} ${last_name}</h1>
<p>You've created an account with ${email}</p>
<p>To end the registration process, please click the button below</p>
<a class="btn" href="http://localhost:8080/api/students-giftery/confirm/${confirm_code}">Confirm your email</a>
<p>A warm welcome,</p>
<p>STUDENTS GIFTERY</p>
</body>
</html>
