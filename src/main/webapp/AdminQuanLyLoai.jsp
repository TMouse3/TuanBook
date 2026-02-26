<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản Lý Loại Sách</title>
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
            <li><a href="AdminQuanLyLoaiController" class="nav-link active">Quản lý loại sách</a></li>
            <li><a href="AdminQuanLySachController" class="nav-link link-dark hover-effect">Quản lý sách</a></li>
            <li><a href="AdminXacNhanDonHangController" class="nav-link link-dark hover-effect">Xác nhận đơn hàng</a></li>
            <li><a href="AdminLichSuDonHangController" class="nav-link link-dark hover-effect">Lịch sử đơn hàng</a></li>
            <li class="mt-4 border-top pt-3"><a href="AdminDangXuatController" class="nav-link text-danger fw-bold danger-hover-effect">Đăng xuất</a></li>
        </ul>
    </div>

    <div class="flex-grow-1 p-4" style="margin-left: 260px;">
        <h3 class="mb-4 text-primary fw-bold">QUẢN LÝ LOẠI SÁCH</h3>
        
        <div class="row">
            <div class="col-md-4">
                <div class="card shadow-sm mb-4">
                    <div class="card-header bg-primary text-white fw-bold">Thông tin loại</div>
                    <div class="card-body">
                        <form action="AdminQuanLyLoaiController" method="post">
                            <div class="mb-3">
                                <label class="form-label fw-bold">Mã loại</label>
                                <input type="text" class="form-control" name="txtmaloai" 
                                       value="${loaiSua.maloai}" ${not empty loaiSua ? 'readonly' : ''} required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label fw-bold">Tên loại</label>
                                <input type="text" class="form-control" name="txttenloai" value="${loaiSua.tenloai}" required>
                            </div>
                            
                            <div class="d-grid gap-2">
                                <c:if test="${empty loaiEdit}">
                                    <button type="submit" name="btnThem" class="btn btn-primary">Thêm mới</button>
                                </c:if>
                                <c:if test="${not empty loaiEdit}">
                                    <button type="submit" name="btnSua" class="btn btn-warning text-white">Cập nhật</button>
                                    <a href="AdminQuanLyLoaiController" class="btn btn-secondary">Hủy</a>
                                </c:if>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <div class="col-md-8">
                <div class="card shadow-sm">
                    <div class="card-body p-0">
                        <table class="table table-striped table-hover mb-0 align-middle">
                            <thead class="table-dark">
                                <tr>
                                    <th>Mã loại</th>
                                    <th>Tên loại</th>
                                    <th class="text-center" width="150">Thao tác</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${dsloai}" var="l">
                                    <tr>
                                        <td>${l.maloai}</td>
                                        <td>${l.tenloai}</td>
                                        <td class="text-center">
                                            <a href="AdminQuanLyLoaiController?action=sua&ml=${l.maloai}" class="btn btn-sm btn-outline-primary me-1">Sửa</a>
                                            <a href="AdminQuanLyLoaiController?action=xoa&ml=${l.maloai}" class="btn btn-sm btn-outline-danger" onclick="return confirm('Xóa loại này sẽ ảnh hưởng đến các sách thuộc loại này. Bạn chắc chắn?');">Xóa</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>