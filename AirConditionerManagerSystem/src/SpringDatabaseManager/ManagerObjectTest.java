package SpringDatabaseManager;

import org.junit.Test;

import java.util.ArrayList;

public class ManagerObjectTest {

    @Test
    public void getAll(){
        ArrayList<ManagerObject> hmc = ManagerObject.getAll();
        for (ManagerObject one :
                hmc) {
            System.out.println(one);
        }
    }

    @Test
    public void getById() {
        ArrayList<ManagerObject> hmc = ManagerObject.getById("MA00000001");
        if (hmc.size() == 1){
            ManagerObject one = hmc.get(0);
            System.out.println(one);
        }
        else {
            System.out.println("出错了");
        }
    }
}
