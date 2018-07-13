import java.sql.ResultSet;
import java.sql.SQLException;

public interface DataManager<E> {
    void add();         //获取插入SQL语句
    void delete();
    E getFromRS(ResultSet rs) throws SQLException;  //从数据库读取对象
}
