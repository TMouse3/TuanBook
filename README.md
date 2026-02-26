\# Dự án bán sách



Dự án website bán sách được xây dựng bằng Java Servlet/JSP theo mô hình MVC.



\## Yêu cầu hệ thống

\* \*\*Java\*\*: JDK 11 hoặc mới hơn (trong dự án này dùng JDK 21).

\* \*\*Server\*\*: Apache Tomcat (Khuyên dùng phiên bản 9.0).

\* \*\*Database\*\*: SQL Server.



\## Hướng dẫn cài đặt \& Khởi chạy



\### 1. Cấu hình Database

\* Sử dụng script SQL trong thư mục `database/QlSach\_Database.sql` để tạo cấu trúc bảng và dữ liệu.



\### 2. Cấu hình thông tin kết nối

\* Copy file `src/main/java/config.example.properties` và đổi tên thành `config.properties`.

\* Cập nhật thông tin `db.url`, `db.user`, và `db.password` cho phù hợp với máy của bạn.



\### 3. Cách chạy dự án

Dự án sử dụng các Controller chính để điều hướng:



\* \*\*Giao diện Người dùng (Client)\*\*: Chạy qua `TrangChuController`.

\* \*\*Giao diện Quản trị (Admin)\*\*: Chạy qua `AdminTrangChuController`.



\## Thư viện sử dụng

Các file `.jar` hỗ trợ nằm trong thư mục `src/main/webapp/WEB-INF/lib/`, bao gồm:

\* Kết nối SQL Server: `mssql-jdbc`.

\* Upload file: `commons-fileupload`.

\* JSTL: `jstl-1.2.jar`.

