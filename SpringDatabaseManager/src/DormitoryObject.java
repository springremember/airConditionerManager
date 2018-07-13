import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DormitoryObject implements DataManager<DormitoryObject>{
    public static SQLtemple<DormitoryObject> sqLtemple = new SQLtemple<>("dormitory","dorm_id",new DormitoryObject());

    public String dorm_id;
    int number;
    float money;
    boolean run_status;
    boolean power_status;
    float cost1;
    float cost2;
    float cost3;
    float cost4;
    float cost5;
    float cost6;
    int repair_id;

    DormitoryObject(){}

    public DormitoryObject(String dorm_id, int number, float money, boolean run_status, boolean power_status, float cost1, float cost2, float cost3, float cost4, float cost5, float cost6, int repair_id) {
        this.dorm_id = dorm_id;
        this.number = number;
        this.money = money;
        this.run_status = run_status;
        this.power_status = power_status;
        this.cost1 = cost1;
        this.cost2 = cost2;
        this.cost3 = cost3;
        this.cost4 = cost4;
        this.cost5 = cost5;
        this.cost6 = cost6;
        this.repair_id = repair_id;
    }

    @Override
    public void add() {
        String sql = "INSERT INTO dormitory VALUES('";
        sql += dorm_id + "',";
        sql += number + ",";
        sql += money + ",";
        sql += run_status + ",";
        sql += power_status + ",";
        sql += cost1 + ",";
        sql += cost2 + ",";
        sql += cost3 + ",";
        sql += cost4 + ",";
        sql += cost5 + ",";
        sql += cost6 + ",";
        sql += repair_id + ");";
        sqLtemple.ADD(sql);
    }

    @Override
    public void delete() {
        sqLtemple.DELETE(dorm_id);
    }

    @Override
    public DormitoryObject getFromRS(ResultSet rs) throws SQLException{
        DormitoryObject temp = new DormitoryObject();
        temp.dorm_id = rs.getString("dorm_id");
        temp.number = rs.getInt("number");
        temp.money = rs.getFloat("money");
        temp.run_status = rs.getBoolean("run_status");
        temp.power_status = rs.getBoolean("power_status");
        temp.cost1 = rs.getFloat("cost1");
        temp.cost2 = rs.getFloat("cost2");
        temp.cost3 = rs.getFloat("cost3");
        temp.cost4 = rs.getFloat("cost4");
        temp.cost5 = rs.getFloat("cost5");
        temp.cost6 = rs.getFloat("cost6");
        temp.repair_id = rs.getInt("repair_id");
        return temp;
    }

    public static ArrayList<DormitoryObject> getAll(){
        return sqLtemple.queryAll();
    }

    public static ArrayList<DormitoryObject> getById(String dorm_id){
        return sqLtemple.queryByOne("dorm_id",dorm_id);
    }

    public static ArrayList<DormitoryObject> getByRepairId(int repair_id){
        return sqLtemple.queryByOne("repair_id",repair_id);
    }

    public static ArrayList<DormitoryObject> getOverCost(float cost){
        return sqLtemple.queryOverOne("cost6",cost);
    }

    public void setMoney(float money) {
        this.money = money;
        sqLtemple.SET(dorm_id,"money",money);
    }

    public void setPower_status(boolean power_status) {
        this.power_status = power_status;
        sqLtemple.SET(dorm_id,"power_status",power_status);
    }

    public void setRun_status(boolean run_status) {
        this.run_status = run_status;
        sqLtemple.SET(dorm_id,"run_status",run_status);
    }

    public void setRepair_id(int repair_id) {
        this.repair_id = repair_id;
        sqLtemple.SET(dorm_id,"repair_id",repair_id);
    }

    @Override
    public String toString() {
        return "DormitoryObject{" +
                "dorm_id='" + dorm_id + '\'' +
                ", number=" + number +
                ", money=" + money +
                ", run_status=" + run_status +
                ", power_status=" + power_status +
                ", cost1=" + cost1 +
                ", cost2=" + cost2 +
                ", cost3=" + cost3 +
                ", cost4=" + cost4 +
                ", cost5=" + cost5 +
                ", cost6=" + cost6 +
                ", repair_id=" + repair_id +
                '}';
    }
}
