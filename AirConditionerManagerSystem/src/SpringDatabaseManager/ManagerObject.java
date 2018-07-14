package SpringDatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManagerObject implements DataManager<ManagerObject>{
    static SQLtemple<ManagerObject> sqLtemple = new SQLtemple<>("manager", "manager_id", new ManagerObject());

    public String manager_id;
    public String name;
    public boolean sex;
    public String phonenumber;
    public String password;

    @Override
    public void add() {
    }

    @Override
    public void delete() {
        sqLtemple.DELETE(manager_id);
    }

    @Override
    public ManagerObject getFromRS(ResultSet rs) throws SQLException{
        ManagerObject temp = new ManagerObject();
        temp.manager_id = rs.getString("manager_id");
        temp.name = rs.getString("name");
        temp.sex = rs.getBoolean("sex");
        temp.phonenumber = rs.getString("phonenumber");
        temp.password = rs.getString("password");
        return temp;
    }

    public static ArrayList<ManagerObject> getAll(){
        return sqLtemple.queryAll();
    }

    public static ArrayList<ManagerObject> getById(String value){
        return sqLtemple.queryByOne("manager_id",value);
    }

    @Override
    public String toString() {
        return "ManagerObject{" +
                "manager_id='" + manager_id + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", phonenumber='" + phonenumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
