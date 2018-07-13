import org.junit.Test;

import java.util.ArrayList;

public class RepairObjectTest {
    @Test
    public void add() {
        RepairObject newOne = new RepairObject(777777,"桃2119","test",false,2);
        newOne.add();
    }

    @Test
    public void delete() {
        ArrayList<RepairObject> hmc = RepairObject.getById(777777);
        if (hmc.size() == 1){
            RepairObject one = hmc.get(0);
            one.delete();
        }
        else {
            System.out.println("出错了");
        }
    }

    @Test
    public void getAll(){
        ArrayList<RepairObject> hmc = RepairObject.getAll();
        for (RepairObject one :
                hmc) {
            System.out.println(one);
        }
    }

    @Test
    public void getById() {
        ArrayList<RepairObject> hmc = RepairObject.getById(777777);
        if (hmc.size() == 1){
            RepairObject one = hmc.get(0);
            System.out.println(one);
        }
        else {
            System.out.println("出错了");
        }
    }

    @Test
    public void getByRepairStatus(){
        ArrayList<RepairObject> hmc = RepairObject.getByRepairStatus(true);
        for (RepairObject one :
                hmc) {
            System.out.println(one);
        }
    }

    @Test
    public void getByInforStatus(){
        ArrayList<RepairObject> hmc = RepairObject.getByInforStatus(1);
        for (RepairObject one :
                hmc) {
            System.out.println(one);
        }
    }

    @Test
    public void set(){
        ArrayList<RepairObject> hmc = RepairObject.getById(777777);
        if (hmc.size() == 1){
            RepairObject one = hmc.get(0);
            one.setRepair_status(true);
            one.setInfor_status(1);
        }
        else {
            System.out.println("出错了");
        }
    }
}
