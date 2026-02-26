<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js">
</script>
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

	<div class="row">
		<!-- Cột trái: hiển thị loại -->
		<div class="col-sm-3">
			<h5>Hiển thị loại</h5>
			<div class="list-group">
				<c:forEach var="l" items="${dsLoai}">
					<a href="TrangChuController?ml=${l.maloai}" 
					   class="list-group-item list-group-item-action">
						${l.tenloai}
					</a>
				</c:forEach>
			</div>
		</div>

		<!-- Cột giữa: hiển thị sách -->
		<div class="col-sm-6">
			<h5>Hiển thị sách</h5>

			<!-- Thông báo thêm thành công -->
			<c:if test="${param.dathem eq '1'}">
				<div class="alert alert-success text-center" id="alert-added" role="alert">
					Sản phẩm đã được thêm vào giỏ hàng!
				</div>
			</c:if>

			<div class="row">
				<c:forEach var="s" items="${dsSach}">
					<div class="col-sm-4 p-2 d-flex flex-column">
						<img src="${s.anh}" alt="ảnh" style="width:100%">
						<h4>${s.tensach}</h4>
						<h5>${s.tacgia}</h5>
						<p>Số lượng: ${s.soluong}</p>
						<p><fmt:formatNumber value="${s.gia}" pattern="#,###" /> ₫</p>
						<a href="DatHangController?ms=${s.masach}&ts=${s.tensach}&g=${s.gia}&anh=${s.anh}&sl=1" 
						   class="mt-auto">
						   <img alt="Đặt hàng" src="buynow.jpg">
						</a>
					</div>
				</c:forEach>
			</div>
		</div>

		<!-- Cột phải: tìm kiếm -->
		<div class="col-sm-3">
			<h5>Tìm kiếm</h5>
			<form action="TrangChuController" method="get" class="form-control">
				<input type="text" name="tukhoa" value="${tukhoa}" placeholder="Nhập từ khóa" class="form-control">
				<input type="submit" value="Tìm kiếm" class="btn btn-primary mt-2">
				<c:if test="${not empty tukhoa}">
		             <a href="TrangChuController" class="btn btn-secondary ms-2">Hủy</a>
		        </c:if>
			</form>
		</div>
	</div>

	<script>
		// Ẩn thông báo sau 1.5s
		setTimeout(() => {
			const alert = document.getElementById("alert-added");
			if (alert) alert.style.display = "none";
			
			// Xóa ?dathem=1 khỏi URL mà không reload lại
			const url = new URL(window.location);
			url.searchParams.delete("dathem");
			window.history.replaceState({}, document.title, url);
		}, 1500);
	</script>
</body>
</html>
