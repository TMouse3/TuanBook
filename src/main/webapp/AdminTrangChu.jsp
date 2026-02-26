<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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
	border-left: 4px solid #dc3545 !important;
	/* Viền trái màu đỏ danger */
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

		<div class="d-flex flex-column flex-shrink-0 p-3 bg-light border-end"
			style="width: 260px; height: 100vh; position: fixed; top: 0; left: 0;">
			<a href="AdminTrangChuController"
				class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-primary text-decoration-none border-bottom w-100 pb-2 justify-content-center">
				<span class="fs-4 fw-bold text-uppercase">Admin Panel</span>
			</a>

			<div class="text-center mb-3 text-muted fst-italic">
				Xin chào, <strong>${sessionScope.admin}</strong>
			</div>

			<ul class="nav nav-pills flex-column mb-auto">
				<li class="nav-item"><a href="AdminTrangChuController"
					class="nav-link active">Trang chủ</a></li>
				<li><a href="AdminQuanLyLoaiController"
					class="nav-link link-dark hover-effect">Quản lý loại sách</a></li>
				<li><a href="AdminQuanLySachController"
					class="nav-link link-dark hover-effect">Quản lý sách</a></li>
				<li><a href="AdminXacNhanDonHangController"
					class="nav-link link-dark hover-effect">Xác nhận đơn hàng</a></li>
				<li><a href="AdminLichSuDonHangController"
					class="nav-link link-dark hover-effect">Lịch sử đơn hàng</a></li>
				<li class="mt-4 border-top pt-3"><a
					href="AdminDangXuatController"
					class="nav-link text-danger fw-bold danger-hover-effect">Đăng
						xuất</a></li>
			</ul>
		</div>

		<div class="flex-grow-1 p-4" style="margin-left: 260px;">
			<div class="container-fluid">
				<h2 class="mb-4 pb-2 border-bottom text-primary fw-bold">Tổng
					Quan Hệ Thống</h2>

				<div class="alert alert-success border-0 shadow-sm" role="alert">
					<h4 class="alert-heading">Chào mừng quay trở lại!</h4>
					<p>Hệ thống quản lý bán sách đang hoạt động bình thường. Bạn có
						thể chọn các chức năng bên menu trái để bắt đầu làm việc.</p>
				</div>

				<div class="row g-4 mb-4">
					<div class="col-md-4">
						<div class="card text-white bg-info h-100 shadow-sm">
							<div class="card-body">
								<h5 class="card-title">Doanh thu hôm nay</h5>
								<p class="card-text fs-3 fw-bold">
									<fmt:formatNumber value="${dtNgay}" pattern="#,###" />
									₫
								</p>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="card text-white bg-primary h-100 shadow-sm">
							<div class="card-body">
								<h5 class="card-title">Doanh thu tháng này</h5>
								<p class="card-text fs-3 fw-bold">
									<fmt:formatNumber value="${dtThang}" pattern="#,###" />
									₫
								</p>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="card text-white bg-success h-100 shadow-sm">
							<div class="card-body">
								<h5 class="card-title">Tổng doanh thu</h5>
								<p class="card-text fs-3 fw-bold">
									<fmt:formatNumber value="${dtTong}" pattern="#,###" />
									₫
								</p>
							</div>
						</div>
					</div>
				</div>

				<div class="row mb-4">
					<div class="col-12">
						<div class="card shadow-sm">
							<div
								class="card-header bg-white d-flex justify-content-between align-items-center">
								<h5 class="mb-0 text-primary fw-bold">
									<i class="bi bi-bar-chart-line-fill"></i> Biểu đồ doanh thu năm
									${selectedYear}
								</h5>

								<form action="AdminTrangChuController" method="post"
									class="d-flex align-items-center">
									<label for="selectYear" class="me-2 fw-bold text-muted">Chọn
										năm:</label> <select name="year" id="selectYear"
										class="form-select form-select-sm" style="width: 100px;"
										onchange="this.form.submit()">
										<c:forEach items="${listYears}" var="y">
											<option value="${y}" ${y == selectedYear ? 'selected' : ''}>
												${y}</option>
										</c:forEach>
									</select>
								</form>
							</div>

							<div class="card-body">
								<div style="height: 350px;">
									<canvas id="revenueChart"></canvas>
								</div>
							</div>
						</div>
					</div>
				</div>

				<jsp:useBean id="now" class="java.util.Date" />
				<div class="row mt-4">
					<div class="col-md-12 mb-4">
						<div class="card shadow-sm">
							<div class="card-header bg-warning text-dark fw-bold">
								<i class="bi bi-hourglass-split"></i> Đơn hàng mới hôm nay (
								<fmt:formatDate value="${now}" pattern="dd/MM/yyyy" />
								)
							</div>
							<div class="card-body p-0">
								<table class="table table-striped table-hover mb-0">
									<thead class="table-light">
										<tr>
											<th>Mã HĐ</th>
											<th>Khách Hàng</th>
											<th>SĐT</th>
											<th>Địa Chỉ</th>
											<th>Tổng Tiền</th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${not empty listChuaXacNhan}">
												<c:forEach items="${listChuaXacNhan}" var="o">
													<tr>
														<td><strong>#${o.maHoaDon}</strong></td>
														<td>${o.hoTen}</td>
														<td>${o.soDT}</td>
														<td>${o.diaChi}</td>
														<td class="text-danger fw-bold"><fmt:formatNumber
																value="${o.tongTien}" pattern="#,###" /> ₫</td>
													</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<tr>
													<td colspan="5" class="text-center text-muted py-3">Không
														có đơn mới.</td>
												</tr>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="card shadow-sm">
							<div class="card-header bg-success text-white fw-bold">
								<i class="bi bi-check-circle"></i> Đơn hàng đã bán hôm nay (
								<fmt:formatDate value="${now}" pattern="dd/MM/yyyy" />
								)
							</div>
							<div class="card-body p-0">
								<table class="table table-striped table-hover mb-0">
									<thead class="table-light">
										<tr>
											<th>Mã HĐ</th>
											<th>Khách Hàng</th>
											<th>SĐT</th>
											<th>Địa Chỉ</th>
											<th>Tổng Tiền</th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${not empty listDaMua}">
												<c:forEach items="${listDaMua}" var="o">
													<tr>
														<td>#${o.maHoaDon}</td>
														<td>${o.hoTen}</td>
														<td>${o.soDT}</td>
														<td>${o.diaChi}</td>
														<td class="text-success fw-bold"><fmt:formatNumber
																value="${o.tongTien}" pattern="#,###" /> ₫</td>
													</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<tr>
													<td colspan="5" class="text-center text-muted py-3">Chưa
														bán được đơn nào.</td>
												</tr>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>

				<div class="row g-4 mt-2">
					<div class="col-md-4">
						<div
							class="card shadow-sm border-0 h-100 bg-primary bg-opacity-10">
							<div class="card-body text-center">
								<h3 class="card-title text-primary">
									<i class="bi bi-cart-check"></i> Đơn Hàng
								</h3>
								<p class="card-text text-muted">Kiểm tra và xác nhận các đơn
									hàng mới từ khách hàng.</p>
								<a href="AdminXacNhanDonHangController"
									class="btn btn-primary w-100">Xử lý ngay</a>
							</div>
						</div>
					</div>

					<div class="col-md-4">
						<div
							class="card shadow-sm border-0 h-100 bg-success bg-opacity-10">
							<div class="card-body text-center">
								<h3 class="card-title text-success">
									<i class="bi bi-book"></i> Kho Sách
								</h3>
								<p class="card-text text-muted">Quản lý số lượng, cập nhật
									thông tin và nhập sách mới.</p>
								<a href="AdminQuanLySachController"
									class="btn btn-success w-100">Quản lý</a>
							</div>
						</div>
					</div>

					<div class="col-md-4">
						<div
							class="card shadow-sm border-0 h-100 bg-warning bg-opacity-10">
							<div class="card-body text-center">
								<h3 class="card-title text-warning">
									<i class="bi bi-clock-history"></i> Lịch Sử
								</h3>
								<p class="card-text text-muted">
									Xem lại các đơn hàng và chi tiết các đơn hàng đã hoàn tất giao
									dịch.<br>
								</p>
								<a href="AdminLichSuDonHangController"
									class="btn btn-warning text-white w-100">Xem lịch sử</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js">
	</script>
	<script>
    // 1. Nhận dữ liệu
    const dataDoanhThu = [
        <c:forEach items="${listDoanhThuThang}" var="tien">
            ${tien}, 
        </c:forEach>
    ];

    // 2. Vẽ biểu đồ
    const ctx = document.getElementById('revenueChart').getContext('2d');
    const myChart = new Chart(ctx, {
        type: 'bar', // Dạng biểu đồ: bar (cột), line (đường), pie (tròn)
        data: {
            labels: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 
                     'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'],
            datasets: [{
                label: 'Doanh thu (VNĐ)',
                data: dataDoanhThu,
                backgroundColor: 'rgba(13, 110, 253, 0.7)', // Màu xanh Bootstrap Primary
                borderColor: 'rgba(13, 110, 253, 1)',
                borderWidth: 1,
                borderRadius: 4, // Bo tròn góc cột
                barPercentage: 0.6, // Độ rộng cột
                
                // Xử lý khi cột có giá trị quá nhỏ không hiện:
             	// Bắt buộc mọi cột đều cao ít nhất 5px (giúp tháng 11 hiện ra)
                minBarLength: 5,

                // Xử lý màu nền: Có tiền -> Xanh, 0 đồng -> Trong suốt
                backgroundColor: function(context) {
                    return context.raw > 0 ? 'rgba(13, 110, 253, 0.7)' : 'rgba(0, 0, 0, 0)';
                },
                
                // Xử lý viền: Tương tự, 0 đồng thì ẩn viền
                borderColor: function(context) {
                    return context.raw > 0 ? 'rgba(13, 110, 253, 1)' : 'rgba(0, 0, 0, 0)';
                },
                
                // Ẩn màu khi rê chuột vào cột 0 đồng
                hoverBackgroundColor: function(context) {
                    return context.raw > 0 ? 'rgba(13, 110, 253, 0.9)' : 'rgba(0, 0, 0, 0)';
                },
                hoverBorderColor: function(context) {
                    return context.raw > 0 ? 'rgba(13, 110, 253, 1)' : 'rgba(0, 0, 0, 0)';
                }
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            scales: {
            	x: {
                    stacked: true,
                },
                y: {
                    beginAtZero: true,
                    ticks: {
                        callback: function(value) {
                            // Format tiền Việt (ví dụ: 1.000.000 ₫)
                            return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(value);
                        }
                    }
                }
            },
            plugins: {
                tooltip: {
                	// Chỉ hiện Tooltip nếu giá trị > 0
                    filter: function(tooltipItem) {
                        return tooltipItem.raw > 0;
                    },
                    callbacks: {
                        label: function(context) {
                            let label = context.dataset.label || '';
                            if (label) {
                                label += ': ';
                            }
                            if (context.parsed.y !== null) {
                                label += new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(context.parsed.y);
                            }
                            return label;
                        }
                    }
                },
                legend: {
                    position: 'bottom'
                }
            }
        }
    });
</script>
</body>
</html>