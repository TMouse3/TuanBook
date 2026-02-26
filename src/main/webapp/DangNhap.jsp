<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5" style="max-width: 400px;">
    <h3 class="text-center">Đăng nhập</h3>

    <form action="DangNhapController" method="post">
        <div class="mb-3">
            <label for="username" class="form-label">Tên đăng nhập</label>
            <input type="text" class="form-control" id="username" name="username" required>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Mật khẩu</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <c:if test="${sessionScope.dem >= 3}">
            <div class="mb-3">
                <img src="simpleCaptcha.jpg" alt="Captcha Image" class="mt-2 border rounded">
                <input type="text" class="form-control mt-2" id="captcha" name="captcha" placeholder="Nhập mã xác nhận" required>
            </div>
        </c:if>
        <button type="submit" class="btn btn-primary w-100"><strong>Đăng nhập</strong></button>
    </form>

    <!-- Thông báo lỗi -->
    <c:if test="${not empty thongbao}">
        <p class="mt-3 text-danger text-center"><strong>${thongbao}</strong></p>
    </c:if>
    
    <div class="text-center mt-3">
        <a href="TrangChuController" class="text-decoration-none">
            &larr; Trở về trang chủ
        </a>
    </div>
</div>
</body>
</html>
