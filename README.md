# 📚 Dự án Website Bán sách

[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Tomcat](https://img.shields.io/badge/Tomcat-9.0-F8DC75?style=for-the-badge&logo=apache-tomcat&logoColor=black)](https://tomcat.apache.org/)
[![SQL Server](https://img.shields.io/badge/SQL%20Server-2022-CC2927?style=for-the-badge&logo=microsoft-sql-server&logoColor=white)](https://www.microsoft.com/en-us/sql-server/)

Dự án website bán sách trực tuyến được xây dựng bằng công nghệ Java Servlet/JSP, tuân thủ mô hình kiến trúc MVC (Model-View-Controller).

## 🛠 Công nghệ & Yêu cầu hệ thống
* **Ngôn ngữ:** Java JDK 11 hoặc mới hơn (Dự án này dùng JDK 21).
* **Web Server:** Apache Tomcat 9.0.
* **Cơ sở dữ liệu:** SQL Server.
* **Kiến trúc:** MVC Pattern.

## ⚙️ Hướng dẫn cài đặt & Khởi chạy

### 1. Thiết lập Database
* Sử dụng file script SQL tại đường dẫn `database/QlSach_Database.sql` để khởi tạo cấu trúc bảng và dữ liệu mẫu.

### 2. Cấu hình kết nối
* Tìm file `src/main/java/config.example.properties` và đổi tên thành `config.properties`.
* Cập nhật các thông số `db.url`, `db.user`, và `db.password` theo cấu hình SQL Server trên máy của bạn.

### 3. Điều hướng khởi chạy
Dự án sử dụng các Controller trung tâm để điều phối giao diện:

* **Giao diện Người dùng (Client):** Truy cập và chạy qua `TrangChuController`.
* **Giao diện Quản trị (Admin):** Truy cập và chạy qua `AdminTrangChuController`.

## 📦 Thư viện hỗ trợ (Libraries)
Toàn bộ các file `.jar` cần thiết đã được tích hợp sẵn trong thư mục `src/main/webapp/WEB-INF/lib/`:
* **Kết nối CSDL:** `mssql-jdbc-13.2.0.jre11.jar`.
* **Xử lý File:** `commons-fileupload-1.3.1.jar`.
* **Hiển thị dữ liệu:** `jstl-1.2.jar` và các thẻ tiêu chuẩn.
