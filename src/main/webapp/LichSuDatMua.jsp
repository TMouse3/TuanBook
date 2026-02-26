<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lịch sử đặt mua</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body style="background-color: #f8f9fa;">
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

					<li class="nav-item"><a class="nav-link"
						href="TrangChuController">Trang chủ</a></li>

					<!-- Nếu chưa đăng nhập -->
					<c:if test="${empty sessionScope.user}">
						<li class="nav-item"><a class="nav-link"
							href="DangNhapController">Đăng nhập</a></li>
						<li class="nav-item"><a class="nav-link"
							href="DangKyController">Đăng ký</a></li>
					</c:if>

					<!-- Nếu đã đăng nhập -->
					<c:if test="${not empty sessionScope.user}">
						<li class="nav-item"><a class="nav-link"
							href="GioHangController">Giỏ hàng</a></li>
						<li class="nav-item"><a class="nav-link"
							href="XacNhanDatMuaController">Xác nhận đặt mua</a></li>
						<li class="nav-item"><a class="nav-link"
							href="LichSuDatMuaController">Lịch sử đặt mua</a></li>
						<li class="nav-item"><a class="nav-link"
							href="DangXuatController">Đăng xuất</a></li>
						<li class="nav-item"><a class="nav-link">Xin chào
								${sessionScope.user.hoTen}!</a></li>
					</c:if>

				</ul>
			</div>
		</div>
	</nav>
	<div class="container mt-5">
		<h2 class="text-center mb-4">LỊCH SỬ ĐẶT MUA</h2>

		<c:if test="${empty lsdm}">
			<div class="alert alert-info text-center">Bạn chưa có lịch sử
				đặt mua nào!</div>
		</c:if>
		<!-- 
		<c:if test="${not empty lsdm}">
			<div class="row g-3">
				<div class="col-12">
					<div class="p-3">
						<div class="row text-center fw-bold border-bottom pb-2">
							<div class="col-1">STT</div>
							<div class="col-3">Tên sách</div>
							<div class="col-2">Số lượng</div>
							<div class="col-2">Giá</div>
							<div class="col-2">Thành tiền</div>
							<div class="col-2">Đã mua</div>
						</div>
					</div>
				</div>
				<c:forEach items="${lsdm}" var="item" varStatus="s">
					<div class="col-12">
						<div class="p-3 bg-white shadow-sm rounded">
							<div class="row text-center pt-2">
								<div class="col-1">${s.index + 1}</div>
								<div class="col-3 text-start">${item.tenSach}</div>
								<div class="col-2">${item.soLuongMua}</div>
								<div class="col-2">${item.gia}đ</div>
								<div class="col-2">${item.thanhTien}đ</div>
								<div class="col-2">
									<c:choose>
										<c:when test="${item.daMua == 1}">
											<span class="badge bg-success">Đã mua</span>
										</c:when>
										<c:otherwise>
											<span class="badge bg-secondary">Chưa</span>
										</c:otherwise>
									</c:choose>
								</div>
							</div>

						</div>
					</div>
				</c:forEach>
			</div>
		</c:if>
 -->
		<c:if test="${not empty lsdm}">
			<table class="table table-bordered table-hover shadow-sm bg-white">
				<thead class="table-primary text-center">
					<tr>
						<th>STT</th>
						<th>Tên sách</th>
						<th>Số lượng</th>
						<th>Giá</th>
						<th>Thành tiền</th>
						<th>Đã mua</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${lsdm}" var="item" varStatus="s">
						<tr>
							<td class="text-center">${s.index + 1}</td>
							<td>${item.tenSach}</td>
							<td class="text-center">${item.soLuongMua}</td>
							<td><fmt:formatNumber value="${item.gia}" pattern="#,###" /> ₫</td>
							<td><fmt:formatNumber value="${item.thanhTien}" pattern="#,###" /> ₫</td>
							<td class="text-center"><c:choose>
									<c:when test="${item.daMua == 1}">
										<span class="badge bg-success">Đã mua</span>
									</c:when>
									<c:otherwise>
										<span class="badge bg-secondary">Chưa</span>
									</c:otherwise>
								</c:choose></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>


		<div class="text-center mt-4">
			<a href="TrangChuController" class="btn btn-outline-primary">←
				Quay lại trang chủ</a>
		</div>
	</div>
</body>
</html>