<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<style>
    body {
        font-family: Arial, sans-serif;
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
        margin: 0;
    }

    form {
        background: #fff;
        padding: 30px;
        width: 350px;
        border-radius: 15px;
        box-shadow: 0px 8px 20px rgba(0,0,0,0.2);
        animation: fadeIn 1s ease;
    }

    h3.alert {
        color: red;
        text-align: center;
        margin-bottom: 15px;
    }

    .container {
        margin-bottom: 15px;
    }

    label {
        display: block;
        margin-top: 10px;
        font-weight: bold;
        color: #444;
    }

    input[type=text],
    input[type=password] {
        width: 100%;
        padding: 10px;
        margin-top: 5px;
        border-radius: 8px;
        border: 1px solid #ccc;
        outline: none;
        transition: 0.3s;
    }

    input[type=text]:focus,
    input[type=password]:focus {
        border-color: #667eea;
        box-shadow: 0 0 8px rgba(102, 126, 234, 0.5);
    }

    button {
        width: 100%;
        padding: 12px;
        background: #667eea;
        color: white;
        font-weight: bold;
        border: none;
        border-radius: 8px;
        cursor: pointer;
        margin-top: 15px;
        transition: 0.3s;
    }

    button:hover {
        background: #5a67d8;
        transform: scale(1.05);
    }

    .cancelbtn {
        background: #e53e3e;
    }

    .cancelbtn:hover {
        background: #c53030;
    }

    .psw {
        float: right;
        font-size: 14px;
    }

    @keyframes fadeIn {
        from { opacity: 0; transform: translateY(-30px); }
        to { opacity: 1; transform: translateY(0); }
    }
</style>
</head>
<body>
	<form action="/example/login" method="post">
		<c:if test="${alert !=null}">
			<h3 class="alert alertdanger">${alert}</h3>
		</c:if>
		<div class="container">
			<label for="uname"><b>Username</b></label>
			<input type="text" placeholder="Enter Username" name="username" required>

			<label for="psw"><b>Password</b></label>
			<input type="password" placeholder="Enter Password" name="password" required>

			<button type="submit">Login</button>
			<label>
				<input type="checkbox" checked="checked" name="remember"> Remember me
			</label>
		</div>

		<div class="container" style="background-color: #f1f1f1; border-radius: 8px; padding: 10px;">
			<button type="button" class="cancelbtn">Cancel</button>
			<span class="psw">Forgot <a href="#">password?</a></span>
		</div>
	</form>
</body>
</html>
