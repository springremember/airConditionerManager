import org.junit.Test;

import java.util.ArrayList;

public class DormitoryObjectTest {
    @Test
    public void add() {
        DormitoryObject newOne = new DormitoryObject("桃园test",2,7,false,false,12,14,11,0,0,0,777777);
        newOne.add();
    }

    @Test
    public void delete() {
        ArrayList<DormitoryObject> hmc = DormitoryObject.getById("桃园test");
        if (hmc.size() == 1){
            DormitoryObject one = hmc.get(0);
            one.delete();
        }
        else {
            System.out.println("出错了");
        }
    }

    @Test
    public void getAll(){
        ArrayList<DormitoryObject> hmc = DormitoryObject.getAll();
        for (DormitoryObject one :
                hmc) {
            System.out.println(one);
        }
    }

    @Test
    public void getById() {
        ArrayList<DormitoryObject> hmc = DormitoryObject.getById("桃园test");
        if (hmc.size() == 1){
            DormitoryObject one = hmc.get(0);
            System.out.println(one);
        }
        else {
            System.out.println("出错了");
        }
    }
    @Test
    public void getByRepairId(){
        ArrayList<DormitoryObject> hmc = DormitoryObject.getByRepairId(33);
        if (hmc.size() == 1){
            DormitoryObject one = hmc.get(0);
            System.out.println(one);
        }
        else {
            System.out.println("出错了");
        }
    }

    @Test
    public void getOverCost(){
        ArrayList<DormitoryObject> hmc = DormitoryObject.getOverCost(10);
        for (DormitoryObject one :
                hmc) {
            System.out.println(one);
        }
    }

    @Test
    public void set() {
        ArrayList<DormitoryObject> hmc = DormitoryObject.getById("桃园test");
        if (hmc.size() == 1){
            DormitoryObject one = hmc.get(0);
            one.setMoney(9);
            one.setPower_status(true);
            one.setRun_status(true);
            one.setRepair_id(999999);
        }
        else {
            System.out.println("出错了");
        }
    }
}
