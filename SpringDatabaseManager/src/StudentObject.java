import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentObject implements DataManager<StudentObject>{
    static SQLtemple<StudentObject> sqLtemple = new SQLtemple<>("student","student_id",new StudentObject());

    String student_id;
    String dorm_id;
    String password;
    String name;
    boolean sex;
    String phonenumber;

    StudentObject(String student_id, String dorm_id, String password, String name, boolean sex, String phonenumber){
        this.student_id = student_id;
        this.dorm_id = dorm_id;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.phonenumber = phonenumber;
    }

    StudentObject(){

    }

    public String getStudent_id() {
        return student_id;
    }

    public String getDorm_id() {
        return dorm_id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPassword(String password) {
        this.password = password;
        sqLtemple.SET(getStudent_id(),"password",password);
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
        sqLtemple.SET(getStudent_id(),"phonenumber",phonenumber);
    }

    @Override
    public void add() {
        String sql = "INSERT INTO student VALUES('" + getStudent_id() + "','" + getDorm_id() + "','" + getPassword() + "','" + getName() + "'," + sex + ",'" + getPhonenumber() + "');";
        sqLtemple.ADD(sql);
    }

    @Override
    public void delete() {
        sqLtemple.DELETE(student_id);
    }

    @Override
    public StudentObject getFromRS(ResultSet rs) throws SQLException{
        return new StudentObject(rs.getString("student_id"),rs.getString("dorm_id"),rs.getString("password"),rs.getString("name"),rs.getBoolean("sex"),rs.getString("phonenumber"));
    }

    public static ArrayList<StudentObject> getAll(){
        return sqLtemple.queryAll();
    }

    public static ArrayList<StudentObject> getById(String student_id){
        return sqLtemple.queryByOne("student_id",student_id);
    }

    @Override
    public String toString() {
        return "StudentObject{" +
                "student_id='" + student_id + '\'' +
                ", dorm_id='" + dorm_id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", phonenumber='" + phonenumber + '\'' +
                '}';
    }
}


