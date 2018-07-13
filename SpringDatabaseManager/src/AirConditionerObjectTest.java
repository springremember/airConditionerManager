import org.junit.Test;

import java.util.ArrayList;

public class AirConditionerObjectTest {
    @Test
    public void add() {
        AirConditionerObject newOne = new AirConditionerObject(777777,"20180711","桃园","格力");
        newOne.add();
    }

    @Test
    public void delete() {
        ArrayList<AirConditionerObject> hmc = AirConditionerObject.getById(777777);
        if (hmc.size() == 1){
            AirConditionerObject one = hmc.get(0);
            one.delete();
        }
        else {
            System.out.println("出错了");
        }
    }

    @Test
    public void getAll(){
        ArrayList<AirConditionerObject> hmc = AirConditionerObject.getAll();
        for (AirConditionerObject one :
                hmc) {
            System.out.println(one);
        }
    }

    @Test
    public void getById() {
        ArrayList<AirConditionerObject> hmc = AirConditionerObject.getById(777777);
        if (hmc.size() == 1){
            AirConditionerObject one = hmc.get(0);
            System.out.println(one);
        }
        else {
            System.out.println("出错了");
        }
    }

    @Test
    public void getByAddress(){
        ArrayList<AirConditionerObject> hmc = AirConditionerObject.getByAddress("0000");
        for (AirConditionerObject one :
                hmc) {
            System.out.println(one);
        }
    }

    @Test
    public void set(){
        ArrayList<AirConditionerObject> hmc = AirConditionerObject.getById(777777);
        if (hmc.size() == 1){
            AirConditionerObject one = hmc.get(0);
            one.setTime("20490101");
            one.setAddress("0000");
        }
        else {
            System.out.println("出错了");
        }
    }
}
