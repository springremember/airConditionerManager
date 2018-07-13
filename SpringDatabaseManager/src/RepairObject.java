import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepairObject implements DataManager<RepairObject> {
    static SQLtemple<RepairObject> sqLtemple = new SQLtemple<>("repair","repair_id",new RepairObject());

    RepairObject(int repair_id,String dorm_id,String reason,boolean repair_status,int infor_status){
        this.repair_id = repair_id;
        this.dorm_id = dorm_id;
        this.reason = reason;
        this.repair_status = repair_status;
        this.infor_status = infor_status;
    }

    RepairObject(){}

    int repair_id;
    String dorm_id;
    String reason;
    boolean repair_status;
    int infor_status;

    @Override
    public void add() {
        String sql = "INSERT INTO repair VALUES(";
        sql = sql + repair_id + ",";
        sql = sql + "'" + dorm_id + "',";
        sql = sql + "'" + reason + "',";
        sql = sql + repair_status + ",";
        sql = sql + infor_status + ");";
        sqLtemple.ADD(sql);
    }

    @Override
    public void delete() {
        sqLtemple.DELETE(repair_id);
    }

    @Override
    public RepairObject getFromRS(ResultSet rs) throws SQLException{
        RepairObject temp = new RepairObject();
        temp.repair_id = rs.getInt("repair_id");
        temp.dorm_id = rs.getString("dorm_id");
        temp.reason = rs.getString("reason");
        temp.repair_status = rs.getBoolean("repair_status");
        temp.infor_status = rs.getInt("infor_status");
        return temp;
    }

    public static ArrayList<RepairObject> getAll(){
        return sqLtemple.queryAll();
    }

    public static ArrayList<RepairObject> getById(int repair_id){
        return sqLtemple.queryByOne("repair_id",repair_id);
    }

    public static ArrayList<RepairObject> getByRepairStatus(boolean repair_status){
        return sqLtemple.queryByOne("repair_status",repair_status);
    }

    public static ArrayList<RepairObject> getByInforStatus(int infor_status){
        return sqLtemple.queryByOne("infor_status",infor_status);
    }

    public void setRepair_status(boolean repair_status) {
        this.repair_status = repair_status;
        sqLtemple.SET(repair_id,"repair_status",repair_status);
    }

    public void setInfor_status(int infor_status){
        this.infor_status = infor_status;
        sqLtemple.SET(repair_id,"infor_status",infor_status);
    }

    @Override
    public String toString() {
        return "RepairObject{" +
                "repair_id=" + repair_id +
                ", dorm_id='" + dorm_id + '\'' +
                ", reason='" + reason + '\'' +
                ", repair_status=" + repair_status +
                ", infor_status=" + infor_status +
                '}';
    }
}
