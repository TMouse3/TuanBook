<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">

	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-6">

				<div class="card shadow-lg">
					<div class="card-header bg-primary text-white text-center">
						<h4>Đăng ký tài khoản khách hàng</h4>
					</div>

					<div class="card-body">
						<form action="DangKyController" method="post">
							<input type="hidden" name="action" value="dangky">
							<div class="mb-3">
								<label class="form-label">Họ tên</label> <input type="text"
									name="hoten" class="form-control" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Địa chỉ</label> <input type="text"
									name="diachi" class="form-control">
							</div>
							<div class="mb-3">
								<label class="form-label">Số điện thoại</label> <input
									type="text" name="sodt" class="form-control">
							</div>
							<div class="mb-3">
								<label class="form-label">Email</label> <input type="email"
									name="email" class="form-control">
							</div>
							<div class="mb-3">
								<label class="form-label">Tên đăng nhập</label> <input
									type="text" name="tendn" class="form-control" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Mật khẩu</label> <input
									type="password" name="pass" class="form-control" required>
							</div>

							<div class="d-grid">
								<button type="submit" class="btn btn-success">Đăng ký</button>
							</div>
							<div class="text-center mt-3">
								<a href="TrangChuController" class="text-decoration-none">
									&larr; Trở về trang chủ
								</a>
							</div>
						</form>
					</div>

					<c:if test="${not empty msg}">
						<div class="card-footer text-center text-danger fw-bold">
							${msg}</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>

</body>
</html>