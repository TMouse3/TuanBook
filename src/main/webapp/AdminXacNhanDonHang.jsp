<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Xác Nhận Đơn Hàng</title>
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
            <li><a href="AdminXacNhanDonHangController" class="nav-link active">Xác nhận đơn hàng</a></li>
            <li><a href="AdminLichSuDonHangController" class="nav-link link-dark hover-effect">Lịch sử đơn hàng</a></li>
            <li class="mt-4 border-top pt-3"><a href="AdminDangXuatController" class="nav-link text-danger fw-bold danger-hover-effect">Đăng xuất</a></li>
        </ul>
    </div>

    <div class="flex-grow-1 p-4" style="margin-left: 260px;">
        <h3 class="mb-4 text-primary fw-bold">ĐƠN HÀNG CHỜ XÁC NHẬN</h3>
        
        <form action="AdminXacNhanDonHangController" method="post">
            <div class="mb-3 sticky-top bg-white py-2" style="top: 0; z-index: 10;">
                <button type="submit" name="btnXacNhan" value="xacnhan" class="btn btn-outline-success fw-bold shadow-sm" onclick="return confirm('Xác nhận các đơn hàng đã chọn?');">
                    Xác nhận các đơn đã chọn
                </button>
            </div>
            
            <div class="card shadow-sm border-0">
                <div class="card-body p-0">
                    <table class="table table-hover mb-0 align-middle">
                        <thead class="table-dark">
                            <tr>
                                <th class="text-center" width="50">#</th>
                                <th>Mã hóa đơn</th>
                                <th>Khách hàng</th>
                                <th>Ngày mua</th>
                                <th>Tổng tiền</th>
                                <th class="text-center">Thao tác</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${empty dsDonHang}">
                                <tr><td colspan="6" class="text-center text-muted fst-italic py-4">Hiện không có đơn hàng nào chờ xác nhận.</td></tr>
                            </c:if>
                            <c:forEach items="${dsDonHang}" var="h">
                                <tr>
                                    <td class="text-center">
                                        <input type="checkbox" name="ckbMHD" value="${h.maHoaDon}" class="form-check-input border-secondary" style="transform: scale(1.3);">
                                    </td>
                                    <td class="fw-bold text-danger">#${h.maHoaDon}</td>
                                    <td>${h.hoTen}</td>
                                    <td><fmt:formatDate value="${h.ngayMua}" pattern="dd/MM/yyyy" /></td>
                                    <td class="fw-bold text-success"><fmt:formatNumber
																value="${h.tongThanhTien}" pattern="#,###" /> ₫</td>
                                    <td class="text-center">
                                    	<a href="AdminXacNhanChiTietHoaDonController?mhd=${h.maHoaDon}" class="btn btn-sm btn-outline-primary me-2">Chi tiết</a>
                                    	<a href="AdminXacNhanDonHangController?action=xacnhan1&id=${h.maHoaDon}" class="btn btn-sm btn-outline-success fw-bold" onclick="return confirm('Xác nhận đơn hàng #${h.maHoaDon}?');">Xác nhận</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </form>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>