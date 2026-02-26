<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cập Nhật Sách</title>
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
                <li><a href="AdminQuanLySachController" class="nav-link active">Quản lý sách</a></li>
                <li><a href="AdminXacNhanDonHangController" class="nav-link link-dark hover-effect">Xác nhận đơn hàng</a></li>
                <li><a href="AdminLichSuDonHangController" class="nav-link link-dark hover-effect">Lịch sử đơn hàng</a></li>
                <li class="mt-4 border-top pt-3"><a href="AdminDangXuatController" class="nav-link text-danger fw-bold danger-hover-effect">Đăng xuất</a></li>
            </ul>
        </div>

        <div class="flex-grow-1 p-4" style="margin-left: 260px;">
            <div class="d-flex justify-content-between align-items-center mb-4 pb-2 border-bottom">
                <h3 class="text-primary fw-bold">CẬP NHẬT SÁCH</h3>
                <a href="AdminQuanLySachController" class="btn btn-outline-secondary">&larr; Quay lại danh sách</a>
            </div>

            <div class="card shadow-sm border-0">
                <div class="card-body p-4">
                    <form action="AdminSuaSachController" method="post" enctype="multipart/form-data">
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="form-label fw-bold">Mã sách (Không được sửa)</label>
                                <input type="text" name="txtmasach" class="form-control" value="${sach.masach}" readonly>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label class="form-label fw-bold">Tên sách</label>
                                <input type="text" name="txttensach" class="form-control" value="${sach.tensach}" required>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="form-label fw-bold">Tác giả</label>
                                <input type="text" name="txttacgia" class="form-control" value="${sach.tacgia}">
                            </div>
                            <div class="col-md-6 mb-3">
                                <label class="form-label fw-bold">Loại sách</label>
                                <select name="cmbmaloai" class="form-select">
                                    <c:forEach items="${dsloai}" var="l">
                                        <option value="${l.maloai}" ${l.maloai == sach.maloai ? 'selected' : ''}>${l.tenloai}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="form-label fw-bold">Số lượng</label>
                                <input type="number" name="txtsoluong" class="form-control" min="0" value="${sach.soluong}">
                            </div>
                            <div class="col-md-6 mb-3">
                                <label class="form-label fw-bold">Giá (VNĐ)</label>
                                <input type="number" name="txtgia" class="form-control" min="0" value="${sach.gia}">
                            </div>
                        </div>

                        <div class="mb-4">
                            <label class="form-label fw-bold">Ảnh bìa</label>
                            <div class="mb-2">
                                <img src="${sach.anh}" height="100" alt="Ảnh cũ">
                            </div>
                            <input type="file" name="fileanh" class="form-control">
                            <input type="hidden" name="anhcu" value="${sach.anh}">
                        </div>

                        <div class="d-flex gap-2">
                            <button type="submit" class="btn btn-warning fw-bold px-4">Lưu thay đổi</button>
                            <a href="AdminQuanLySachController" class="btn btn-outline-secondary px-4">Hủy</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>