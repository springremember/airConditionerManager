import LoginAndMainInterface.*;
import StudentOtherInterface.*;
import ManagerOtherInterface.*;

public class Demo {
    public static void main(String[] args){
        Student student = new Student();
        OtherInterface addInterface = new Recharge(student);
        OtherInterface repairInterface = new BaoXiu(student);
        OtherInterface modifyInterface = new Modify(student);
        student.setAll(addInterface,repairInterface,modifyInterface);

        Manager manager = new Manager();
        OtherInterface airConditionerManager = new AirManager(manager);
        OtherInterface airConditionerFix = new Repair(manager);
        OtherInterface userManager = new UserManagerBatch(manager);
        OtherInterface powerControl = new PowerControl(manager);
        OtherInterface allowances = new BuTie(manager);
        manager.setOtherInterface(airConditionerManager,airConditionerFix,userManager,powerControl,allowances);

        LoginInterface loginInterface = new LoginInterface(student,manager);
    }
}
