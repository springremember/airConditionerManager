import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLtemple<E> {
    DatabaseManger databaseManger = DatabaseManger.getInstance();
    private String tableName;
    private String primaryKeyName;
    private DataManager unique;

    // 设置表的参数
    SQLtemple(String tableName,String primaryKeyName,DataManager unique){
        this.tableName = tableName;
        this.primaryKeyName = primaryKeyName;
        this.unique = unique;
    }

    //插入函数
    public void ADD(String sql){
        databaseManger.change(sql);
    }

    // 删除函数，以参数划分
    public void DELETE(String value){
        String sql = "DELETE FROM " + tableName + " WHERE " + primaryKeyName + "='" + value + "';";
        databaseManger.change(sql);
    }

    public void DELETE(int value){
        String sql = "DELETE FROM " + tableName + " WHERE " + primaryKeyName + "=" + value + ";";
        databaseManger.change(sql);
    }

    public void DELETE(float value){
        String sql = "DELETE FROM " + tableName + " WHERE " + primaryKeyName + "=" + value + ";";
        databaseManger.change(sql);
    }

    // 修改函数
    public void SET(String primaryKeyValue, String property, String value){
        String sql = "UPDATE " + tableName + " SET " + property + "='" + value + "' WHERE " + primaryKeyName + "='" + primaryKeyValue + "';";
        databaseManger.change(sql);
    }

    public void SET(String primaryKeyValue, String property, int value){
        String sql = "UPDATE " + tableName + " SET " + property + "=" + value + " WHERE " + primaryKeyName + "='" + primaryKeyValue + "';";
        databaseManger.change(sql);
    }

    public void SET(String primaryKeyValue, String property, float value){
        String sql = "UPDATE " + tableName + " SET " + property + "=" + value + " WHERE " + primaryKeyName + "='" + primaryKeyValue + "';";
        databaseManger.change(sql);
    }

    public void SET(String primaryKeyValue, String property, boolean value){
        String sql = "UPDATE " + tableName + " SET " + property + "=" + value + " WHERE " + primaryKeyName + "='" + primaryKeyValue + "';";
        databaseManger.change(sql);
    }

    public void SET(int primaryKeyValue, String property, String value){
        String sql = "UPDATE " + tableName + " SET " + property + "='" + value + "' WHERE " + primaryKeyName + "=" + primaryKeyValue + ";";
        databaseManger.change(sql);
    }

    public void SET(int primaryKeyValue, String property, int value){
        String sql = "UPDATE " + tableName + " SET " + property + "=" + value + " WHERE " + primaryKeyName + "=" + primaryKeyValue + ";";
        databaseManger.change(sql);
    }

    public void SET(int primaryKeyValue, String property, float value){
        String sql = "UPDATE " + tableName + " SET " + property + "=" + value + " WHERE " + primaryKeyName + "=" + primaryKeyValue + ";";
        databaseManger.change(sql);
    }

    public void SET(int primaryKeyValue, String property, boolean value){
        String sql = "UPDATE " + tableName + " SET " + property + "=" + value + " WHERE " + primaryKeyName + "=" + primaryKeyValue + ";";
        databaseManger.change(sql);
    }

    public void SET(float primaryKeyValue, String property, String value){
        String sql = "UPDATE " + tableName + " SET " + property + "='" + value + "' WHERE " + primaryKeyName + "=" + primaryKeyValue + ";";
        databaseManger.change(sql);
    }

    public void SET(float primaryKeyValue, String property, int value){
        String sql = "UPDATE " + tableName + " SET " + property + "=" + value + " WHERE " + primaryKeyName + "=" + primaryKeyValue + ";";
        databaseManger.change(sql);
    }

    public void SET(float primaryKeyValue, String property, float value){
        String sql = "UPDATE " + tableName + " SET " + property + "=" + value + " WHERE " + primaryKeyName + "=" + primaryKeyValue + ";";
        databaseManger.change(sql);
    }

    public void SET(float primaryKeyValue, String property, boolean value){
        String sql = "UPDATE " + tableName + " SET " + property + "=" + value + " WHERE " + primaryKeyName + "=" + primaryKeyValue + ";";
        databaseManger.change(sql);
    }

    // 获取全部
    public ArrayList<E> queryAll(){
        ArrayList<E> resultList = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName + ";";
        ResultSet rs = databaseManger.query(sql);
        try {
            while (rs.next()){
                resultList.add((E)unique.getFromRS(rs));
            }
        }
        catch (SQLException SE){
            System.out.println("查询" + tableName + "出错");
        }
        return resultList;
    }

    // 查询函数
    public ArrayList<E> queryByOne(String property, String value) {
        ArrayList<E> resultList = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName + " WHERE " + property + "='" + value + "';";
        ResultSet rs = databaseManger.query(sql);
        try {
            while (rs.next()){
                resultList.add((E)unique.getFromRS(rs));
            }
        }
        catch (SQLException SE){
            System.out.println("查询" + tableName + "出错");
        }
        return resultList;
    }

    public ArrayList<E> queryByOne(String property, int value) {
        ArrayList<E> resultList = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName + " WHERE " + property + "=" + value + ";";
        ResultSet rs = databaseManger.query(sql);
        try {
            while (rs.next()){
                resultList.add((E)unique.getFromRS(rs));
            }
        }
        catch (SQLException SE){
            System.out.println("查询" + tableName + "出错");
        }
        return resultList;
    }

    public ArrayList<E> queryByOne(String property, float value) {
        ArrayList<E> resultList = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName + " WHERE " + property + "=" + value + ";";
        ResultSet rs = databaseManger.query(sql);
        try {
            while (rs.next()){
                resultList.add((E)unique.getFromRS(rs));
            }
        }
        catch (SQLException SE){
            System.out.println("查询" + tableName + "出错");
        }
        return resultList;
    }

    public ArrayList<E> queryByOne(String property, boolean value) {
        ArrayList<E> resultList = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName + " WHERE " + property + "=" + value + ";";
        ResultSet rs = databaseManger.query(sql);
        try {
            while (rs.next()){
                resultList.add((E)unique.getFromRS(rs));
            }
        }
        catch (SQLException SE){
            System.out.println("查询" + tableName + "出错");
        }
        return resultList;
    }

    // 大于查询
    public ArrayList<E> queryOverOne(String property, float value) {
        ArrayList<E> resultList = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName + " WHERE " + property + ">" + value + ";";
        ResultSet rs = databaseManger.query(sql);
        try {
            while (rs.next()){
                resultList.add((E)unique.getFromRS(rs));
            }
        }
        catch (SQLException SE){
            System.out.println("查询" + tableName + "出错");
        }
        return resultList;
    }
}
