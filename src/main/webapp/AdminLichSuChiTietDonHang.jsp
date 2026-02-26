<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi Tiết Lịch Sử</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
	.nav-link {
        padding-left: 1rem; 
    }
    .nav-link.hover-effect:hover {
        background-color: #e9ecef;
        border-left: 4px solid #0d6efd; /* Viền trái màu xanh primary */
        padding-left: 0.75rem !important;
        transition: all 0.1s ease-in-out;
        color: #0d6efd !important;
    }
    .nav-link.danger-hover-effect:hover {
        background-color: #f8d7da !important;
        border-left: 4px solid #dc3545 !important; /* Viền trái màu đỏ danger */
        padding-left: 0.75rem !important;
        transition: all 0.1s ease-in-out;
        color: #dc3545 !important;
    }
    .nav-link.active.hover-effect:hover {
        background-color: #0d6efd; /* Giữ nguyên màu nền của active */
        border-left: none;
        color: white !important;
    }
</style>
</head>
<body>

<div class="d-flex">
    <div class="d-flex flex-column flex-shrink-0 p-3 bg-light border-end" style="width: 260px; height: 100vh; position: fixed; top: 0; left: 0;">
        <a href="AdminTrangChuController" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-primary text-decoration-none border-bottom w-100 pb-2 justify-content-center">
            <span class="fs-4 fw-bold text-uppercase">Admin Panel</span>
        </a>
        <div class="text-center mb-3 text-muted fst-italic">Xin chào, <strong>${sessionScope.admin}</strong></div>
        <ul class="nav nav-pills flex-column mb-auto">
            <li><a href="AdminTrangChuController" class="nav-link link-dark hover-effect">Trang chủ</a></li>
            <li><a href="AdminQuanLyLoaiController" class="nav-link link-dark hover-effect">Quản lý loại sách</a></li>
            <li><a href="AdminQuanLySachController" class="nav-link link-dark hover-effect">Quản lý sách</a></li>
            <li><a href="AdminXacNhanDonHangController" class="nav-link link-dark hover-effect">Xác nhận đơn hàng</a></li>
            <li><a href="AdminLichSuDonHangController" class="nav-link active">Lịch sử đơn hàng</a></li>
            <li class="mt-4 border-top pt-3"><a href="AdminDangXuatController" class="nav-link text-danger fw-bold danger-hover-effect">Đăng xuất</a></li>
        </ul>
    </div>

    <div class="flex-grow-1 p-4" style="margin-left: 260px;">
        <div class="d-flex justify-content-between align-items-center mb-4 pb-2 border-bottom">
            <h3 class="text-primary fw-bold">CHI TIẾT LỊCH SỬ ĐƠN HÀNG #${mhd}</h3>
            <a href="AdminLichSuDonHangController" class="btn btn-outline-secondary">
                &larr; Quay lại danh sách
            </a>
        </div>

		<div class="card mb-4 border-secondary">
			<div class="card-header bg-secondary text-white fw-bold">Thông tin đơn hàng</div>
			<div class="card-body">
				<div class="row">
					<div class="col-md-4">
						<strong>Khách hàng:</strong> ${hoaDon.hoTen}
					</div>
					<div class="col-md-4">
						<strong>Ngày mua:</strong> <fmt:formatDate value="${hoaDon.ngayMua}" pattern="dd/MM/yyyy"/>
					</div>
					<div class="col-md-4 text-end">
						<strong>Tổng thanh toán:</strong> <span class="text-danger fw-bold fs-5">
						<fmt:formatNumber value="${hoaDon.tongThanhTien}" pattern="#,###" /> ₫</span>
					</div>
				</div>
			</div>
		</div>

        <div class="card shadow-sm border-0">
            <div class="card-body p-0">
                <table class="table table-bordered table-hover mb-0 align-middle">
                    <thead class="table-secondary">
                        <tr>
                            <th>Mã chi tiết</th>
                            <th>Tên sách</th>
                            <th>Giá bán</th>
                            <th>Số lượng</th>
                            <th>Thành tiền</th>
                            <th>Ngày mua</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${dsChiTietLichSu}" var="ct">
                            <tr>
                                <td class="fw-bold text-danger">#${ct.maChiTietHD}</td>
                                <td>${ct.tenSach}</td>
                                <td><fmt:formatNumber value="${ct.gia}" pattern="#,###" /> ₫</td>
                                <td>${ct.soLuongMua}</td>
                                <td class="fw-bold text-success">
                                	<fmt:formatNumber value="${ct.thanhTien}" pattern="#,###" /> ₫</td>
                                <td><fmt:formatDate value="${ct.ngayMua}" pattern="dd/MM/yyyy" /></td>
                            </tr>
                        </c:forEach>
                        <tr class="table-info">
                        	<td colspan="4" class="text-end fw-bold">TỔNG CỘNG:</td>
                        	<td colspan="2" class="fw-bold text-danger fs-5">
                        		<fmt:formatNumber value="${hoaDon.tongThanhTien}" pattern="#,###" /> ₫</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>