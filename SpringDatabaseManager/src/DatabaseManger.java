import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseManger {
    private static DatabaseManger single = null;
    private String driver;
    private String url;
    private String user;
    private String pass;

    private DatabaseManger(){
        driver = "org.mariadb.jdbc.Driver";
        url = "jdbc:mariadb://115.159.85.157:3306/test";
        user = "root";
        pass = "71190303";
    }

    public static DatabaseManger getInstance(){
        if (single == null){
            single = new DatabaseManger();
        }
        return single;
    }

    public ResultSet query(String sql){
        try {
            Class.forName(driver);
            //获取数据库连接
            Connection conn = DriverManager.getConnection(url, user, pass);
            //创建Statement对象
            Statement stmt = conn.createStatement();
            //执行查询
            ResultSet rs = stmt.executeQuery(sql);
            conn.close();
            return rs;
        }
        catch (Exception exp){

        }
        return null;
    }

    public void change(String sql){
        try {
            Class.forName(driver);
            //获取数据库连接
            Connection conn = DriverManager.getConnection(url, user, pass);
            //创建Statement对象
            Statement stmt = conn.createStatement();
            //执行查询
            stmt.executeQuery(sql);
            conn.close();
        }
        catch (Exception exp){

        }
    }
}
