package Model.DAO;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class KetNoiDB {
    public Connection cn;

    public void KetNoi() {
        try {
            Properties props = new Properties();
            // Đọc file config.properties từ classpath
            InputStream in = getClass().getClassLoader().getResourceAsStream("config.properties");
            if (in != null) {
                props.load(in);
            } else {
                System.out.println("Không tìm thấy file config.properties!");
                return;
            }

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"));
            System.out.println("Kết nối thành công SQL Server!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Kết nối thất bại!");
        }
    }

    public void DongKetNoi() {
        try {
            if (cn != null && !cn.isClosed())
                cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
