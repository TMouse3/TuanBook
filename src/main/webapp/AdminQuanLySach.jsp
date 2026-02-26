<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản Lý Sách</title>
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
        <div class="text-center mb-3 text-muted fst-italic">
            Xin chào, <strong>${sessionScope.admin}</strong>
        </div>
        <ul class="nav nav-pills flex-column mb-auto">
            <li><a href="AdminTrangChuController" class="nav-link nav-link-action link-dark hover-effect">Trang chủ</a></li>
            <li><a href="AdminQuanLyLoaiController" class="nav-link link-dark hover-effect">Quản lý loại sách</a></li>
            <li class="nav-item"><a href="AdminQuanLySachController" class="nav-link active">Quản lý sách</a></li>
            <li><a href="AdminXacNhanDonHangController" class="nav-link link-dark hover-effect">Xác nhận đơn hàng</a></li>
            <li><a href="AdminLichSuDonHangController" class="nav-link link-dark hover-effect">Lịch sử đơn hàng</a></li>
            <li class="mt-4 border-top pt-3"><a href="AdminDangXuatController" class="nav-link text-danger fw-bold danger-hover-effect">Đăng xuất</a></li>
        </ul>
    </div>

    <div class="flex-grow-1 p-4" style="margin-left: 260px;">
        <div class="d-flex justify-content-between align-items-center mb-4 pb-2 border-bottom">
            <h3 class="text-primary fw-bold">QUẢN LÝ SÁCH</h3>
            <a href="AdminThemSachController" class="btn btn-primary shadow-sm">
                &plus; Thêm sách mới
            </a>
        </div>
        
        <div class="mb-3">
		    <form action="AdminQuanLySachController" method="get" class="d-flex" role="search">
		        <input class="form-control me-2" type="search" name="tukhoa" 
		               placeholder="Tìm kiếm theo tên sách hoặc tác giả:" 
		               value="${tukhoa}" aria-label="Search">
		        <button class="btn btn-outline-success" type="submit">Tìm</button>
		        <c:if test="${not empty tukhoa}">
		             <a href="AdminQuanLySachController" class="btn btn-outline-secondary ms-2">Hủy</a>
		        </c:if>
		    </form>
		</div>

        <div class="card shadow-sm border-0">
            <div class="card-body p-0">
                <table class="table table-hover table-striped align-middle mb-0">
                    <thead class="table-dark">
                        <tr>
                            <th>Mã</th>
                            <th style="width: 30%;">Tên sách</th>
                            <th>Tác giả</th>
                            <th>Giá</th>
                            <th>SL</th>
                            <th>Ảnh</th>
                            <th class="text-center">Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${dssach}" var="s">
                            <tr>
                                <td class="fw-bold">${s.masach}</td>
                                <td>${s.tensach}</td>
                                <td>${s.tacgia}</td>
                                <td class="text-success fw-bold"><fmt:formatNumber value="${s.gia}" pattern="#,###" /> ₫</td>
                                <td>${s.soluong}</td>
                                <td>
                                    <img src="${s.anh}" width="50" height="70" style="object-fit: cover; border: 1px solid #ddd; border-radius: 4px;">
                                </td>
                                <td class="text-center">
                                    <a href="AdminQuanLySachController?action=sua&ms=${s.masach}" class="btn btn-sm btn-outline-info me-1">
                                        Sửa
                                    </a>
                                    <a href="AdminQuanLySachController?action=xoa&ms=${s.masach}" class="btn btn-sm btn-outline-danger" onclick="return confirm('Bạn chắc chắn muốn xóa sách này?');">
                                        Xóa
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>