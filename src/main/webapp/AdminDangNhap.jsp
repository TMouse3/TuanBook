<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập Quản Trị</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="bg-light">

    <div class="container d-flex justify-content-center align-items-center vh-100">
        <div class="card shadow-lg p-4" style="max-width: 400px; width: 100%;">
            <div class="text-center mb-4">
                <h3 class="text-primary fw-bold">ADMIN LOGIN</h3>
            </div>

            <form action="AdminDangNhapController" method="post">
                <div class="mb-3">
                    <label for="username" class="form-label fw-semibold">Tên đăng nhập</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>
                
                <div class="mb-3">
                    <label for="password" class="form-label fw-semibold">Mật khẩu</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>

                <c:if test="${sessionScope.demAdmin >= 3}">
                    <div class="mb-3">
                        <label class="form-label fw-semibold">Mã xác nhận</label>
                        <div class="input-group mb-2">
                            <img src="simpleCaptcha.jpg" alt="Captcha" class="border rounded me-2" style="height: 38px;">
                            <input type="text" class="form-control" name="captcha" placeholder="Nhập mã" required>
                        </div>
                    </div>
                </c:if>

                <button type="submit" class="btn btn-primary w-100 py-2 fw-bold">Đăng nhập</button>
            </form>

            <c:if test="${not empty thongbao}">
                <div class="alert alert-danger mt-3 text-center py-2 mb-0" role="alert">
                    <small><strong>${thongbao}</strong></small>
                </div>
            </c:if>
        </div>
    </div>

</body>
</html>