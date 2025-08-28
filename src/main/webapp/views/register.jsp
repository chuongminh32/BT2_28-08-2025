<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Register</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background: linear-gradient(to right, #4facfe, #00f2fe);
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.register-container {
	max-width: 450px;
	margin: 5% auto;
	background: #fff;
	padding: 30px;
	border-radius: 12px;
	box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
}

.register-container h2 {
	text-align: center;
	margin-bottom: 20px;
	color: #333;
}

.form-control:focus {
	border-color: #00c6ff;
	box-shadow: 0 0 0 0.2rem rgba(0, 198, 255, .25);
}

.btn-custom {
	background: linear-gradient(90deg, #4facfe, #00f2fe);
	border: none;
	color: white;
	font-weight: bold;
}

.btn-custom:hover {
	background: linear-gradient(90deg, #00f2fe, #4facfe);
}

.text-small {
	font-size: 0.9rem;
}
</style>
</head>
<body>
	<div class="register-container">
		<h2>Register</h2>
		<form action="register" method="post">
			<c:if test="${alert !=null}">
				<h3 class="alert alertdanger">${alert}</h3>
			</c:if>
			<!-- Email -->
			<div class="mb-3">
				<label for="email" class="form-label">Địa chỉ Email</label> <input
					type="email" class="form-control" name="email" id="email"
					placeholder="Enter your email" required>
			</div>

			<!-- Username -->
			<div class="mb-3">
				<label for="username" class="form-label">Tên đăng nhập</label> <input
					type="text" class="form-control" name="username" id="username"
					placeholder="Choose a username" required>
			</div>

			<!-- Fullname -->
			<div class="mb-3">
				<label for="fullname" class="form-label">Họ và tên</label> <input
					type="text" class="form-control" name="fullname" id="fullname"
					placeholder="Enter your full name" required>
			</div>

			<!-- Phone -->
			<div class="mb-3">
				<label for="phone" class="form-label">Số điện thoại</label> <input
					type="tel" class="form-control" name="phone" id="phone"
					placeholder="Enter your phone number" required>
			</div>

			<!-- Password -->
			<div class="mb-3">
				<label for="password" class="form-label">Mật khẩu</label> <input
					type="password" class="form-control" name="password" id="password"
					placeholder="Enter your password" required>
			</div>

			<!-- Confirm Password -->
			<div class="mb-3">
				<label for="confirmPassword" class="form-label">Xác nhận mật khẩu</label> <input type="password" class="form-control"
					name="confirmPassword" id="confirmPassword"
					placeholder="Re-enter your password" required>
			</div>

			<!-- Submit button -->
			<div class="d-grid">
				<button type="submit" class="btn btn-custom">Đăng kí</button>
			</div>

			<!-- Login link -->
			<p class="text-center mt-3 text-small">
				Đã có tài khoản ? <a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
			</p>
		</form>
	</div>
</body>
</html>
