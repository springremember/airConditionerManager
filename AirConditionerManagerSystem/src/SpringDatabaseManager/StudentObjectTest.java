package SpringDatabaseManager;

import org.junit.Test;

import java.util.ArrayList;

public class StudentObjectTest {

    @org.junit.Test
    public void add() {
        StudentObject newOne = new StudentObject("test","桃2119","123456","test",true,"123456");
        newOne.add();
    }

    @org.junit.Test
    public void delete() {
        ArrayList<StudentObject> hmc = StudentObject.getById("test");
        if (hmc.size() == 1){
            StudentObject one = hmc.get(0);
            one.delete();
        }
        else {
            System.out.println("出错了");
        }
    }

    @Test
    public void getAll(){
        ArrayList<StudentObject> hmc = StudentObject.getAll();
        for (StudentObject one :
                hmc) {
            System.out.println(one);
        }
    }

    @org.junit.Test
    public void getById() {
        ArrayList<StudentObject> hmc = StudentObject.getById("test");
        if (hmc.size() == 1){
            StudentObject one = hmc.get(0);
            System.out.println(one);
        }
        else {
            System.out.println("出错了");
        }
    }

    @org.junit.Test
    public void set(){
        ArrayList<StudentObject> hmc = StudentObject.getById("test");
        if (hmc.size() == 1){
            StudentObject one = hmc.get(0);
            one.setPassword("test");
            one.setPhonenumber("test");
        }
        else {
            System.out.println("出错了");
        }
    }
}
