<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Giỏ hàng</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<!-- Thanh navbar -->
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="TrangChuController">Tuan Shop</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="collapsibleNavbar">
				<ul class="navbar-nav">

					<li class="nav-item">
						<a class="nav-link" href="TrangChuController">Trang chủ</a>
					</li>

					<!-- Nếu chưa đăng nhập -->
					<c:if test="${empty sessionScope.user}">
						<li class="nav-item"><a class="nav-link" href="DangNhapController">Đăng nhập</a></li>
						<li class="nav-item"><a class="nav-link" href="DangKyController">Đăng ký</a></li>
					</c:if>

					<!-- Nếu đã đăng nhập -->
					<c:if test="${not empty sessionScope.user}">
						<li class="nav-item"><a class="nav-link" href="GioHangController">Giỏ hàng</a></li>
						<li class="nav-item"><a class="nav-link" href="XacNhanDatMuaController">Xác nhận đặt mua</a></li>
						<li class="nav-item"><a class="nav-link" href="LichSuDatMuaController">Lịch sử đặt mua</a></li>
						<li class="nav-item"><a class="nav-link" href="DangXuatController">Đăng xuất</a></li>
						<li class="nav-item"><a class="nav-link">Xin chào ${sessionScope.user.hoTen}!</a></li>
					</c:if>

				</ul>
			</div>
		</div>
	</nav>

<div class="container mt-3">

    <!-- Nếu giỏ hàng trống -->
    <c:if test="${empty sessionScope.giohangbo or empty sessionScope.giohangbo.dsGioHang}">
        <h3>Giỏ hàng trống</h3>
    </c:if>

    <!-- Nếu giỏ hàng có sản phẩm -->
    <c:if test="${not empty sessionScope.giohangbo and not empty sessionScope.giohangbo.dsGioHang}">
        <h2>Giỏ hàng của bạn</h2>

        <form action="GioHangController" method="post">
            <table class="table table-bordered">
                <tr>
                    <th>Ảnh</th>
                    <th>Tên sách</th>
                    <th>Giá</th>
                    <th>Số lượng</th>
                    <th>Thành tiền</th>
                    <th colspan="2">Thao tác</th>
                </tr>

                <c:forEach var="g" items="${sessionScope.giohangbo.dsGioHang}">
                    <tr>
                        <td><img src="${g.anh}" alt="ảnh" width="80"></td>
                        <td>${g.tensach}</td>
                        <td><fmt:formatNumber value="${g.gia}" pattern="#,###" /> ₫</td>
                        <td>
                            <input type="number" name="${g.masach}" value="${g.soluong}" min="1" class="form-control" style="width: 100px; display: inline-block;">
                            <button type="submit" name="butsua" value="${g.masach}" class="btn btn-outline-success">Sửa</button>
                        </td>
                        <td><fmt:formatNumber value="${g.thanhtien}" pattern="#,###" /> ₫</td>
                        <td><a href="GioHangController?action=xoa&ms=${g.masach}" class="btn btn-outline-danger btn-sm">Xóa</a></td>
                        <td><input type="checkbox" name="cxoa" value="${g.masach}"></td>
                    </tr>
                </c:forEach>

                <tr>
                    <td colspan="4" class="text-end fw-bold">Tổng cộng:</td>
                    <td colspan="2" class="fw-bold text-danger">
                    	<fmt:formatNumber value="${sessionScope.giohangbo.tongTien()}" pattern="#,###" /> ₫</td>
                    <td>
                        <button type="submit" name="butxoa" value="xoaChon" class="btn btn-outline-danger">
                            Xóa các mục đã chọn
                        </button>
                    </td>
                </tr>
            </table>
        </form>

        <form action="XacNhanDatMuaController" method="post" class="text-end mt-2">
            <button type="submit" class="btn btn-success">Xác nhận đặt mua</button>
        </form>
    </c:if>
</div>

</body>
</html>
