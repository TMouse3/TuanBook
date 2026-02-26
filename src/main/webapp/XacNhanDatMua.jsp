<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Xác nhận đặt mua</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">

    <h2 class="mb-3 text-success">Đặt mua thành công!</h2>

    <!-- Kiểm tra xem có thuộc tính tongtien không -->
    <c:if test="${not empty requestScope.tongtien}">
        <p>Cảm ơn bạn đã đặt mua hàng. Tổng tiền là: 
            <strong>${tongtien} VNĐ</strong>
        </p>
    </c:if>

    <c:if test="${empty requestScope.tongtien}">
        <p class="text-danger">Không có thông tin đặt mua!</p>
    </c:if>

    <a href="TrangChuController" class="btn btn-outline-primary mt-3">Về trang chủ</a>

</body>
</html>
