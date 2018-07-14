package SpringDatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AirConditionerObject implements DataManager<AirConditionerObject> {
    private static SQLtemple<AirConditionerObject> sqLtemple = new SQLtemple<>("airConditioner","air_id",new AirConditionerObject());
    public int air_id;
    public String time;
    public String address;
    public String brand;

    AirConditionerObject(){}

    public AirConditionerObject(int air_id,String time,String address,String brand){
        this.air_id = air_id;
        this.time = time;
        this.address = address;
        this.brand = brand;
    }

    @Override
    public void add() {
        String sql = "INSERT INTO airConditioner VALUES(";
        sql += air_id + ",'";
        sql += time + "','";
        sql += address + "','";
        sql += brand + "');";
        sqLtemple.ADD(sql);
    }

    @Override
    public void delete() {
        sqLtemple.DELETE(air_id);
    }

    @Override
    public AirConditionerObject getFromRS(ResultSet rs) throws SQLException{
        AirConditionerObject temp = new AirConditionerObject();
        temp.air_id = rs.getInt("air_id");
        temp.time = rs.getString("time");
        temp.address = rs.getString("address");
        temp.brand = rs.getString("brand");
        return temp;
    }

    public static ArrayList<AirConditionerObject> getAll(){
        return sqLtemple.queryAll();
    }

    public static ArrayList<AirConditionerObject> getById(int value){
        return sqLtemple.queryByOne("air_id",value);
    }

    public static ArrayList<AirConditionerObject> getByAddress(String address){
        return sqLtemple.queryByOne("address",address);
    }

    public void setTime(String time) {
        this.time = time;
        sqLtemple.SET(air_id,"time",time);
    }

    public void setAddress(String address) {
        this.address = address;
        sqLtemple.SET(air_id,"address",address);
    }

    @Override
    public String toString() {
        return "AirConditionerObject{" +
                "air_id=" + air_id +
                ", time='" + time + '\'' +
                ", address='" + address + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
