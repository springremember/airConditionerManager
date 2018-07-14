package ManagerOtherInterface;
import SpringDatabaseManager.*;
import LoginAndMainInterface.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class AirManagerAdd extends JDialog implements OtherInterface {
    @Override
    public void showInterface(String s) {
        setManager_id(s);
        setLayout(new FlowLayout());
        setBounds(100,100,400,400);
        set();
        setVisible(true);
    }

    String manager_id;
    JFrame father;
    Box baseBox = Box.createVerticalBox();
    JPanel jPanel = new JPanel();
    String str1[]= {"格力","美的","海尔","其它"};
    JComboBox jComboBox = new JComboBox(str1);
    JButton submit = new JButton("提交");

    AirManagerAdd(JFrame father){
        super(father,"空调添加",true);
        this.father = father;
    }

    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
    }

    // 布局
    private void set(){
        initLable();
        initComboBox();
        initButton();
        addBox();
    }

    private void initLable(){
        JLabel welcome = new JLabel("空调添加");
        welcome.setFont(new Font("宋体",Font.BOLD,20));
        MyLayout.setOne(baseBox,50,20,20,welcome);
    }

    private void initComboBox(){
        jPanel.setLayout(new FlowLayout());
        JLabel lable=new JLabel("品牌：");
        jPanel.add(lable);

        jPanel.add(jComboBox);
        MyLayout.setOne(baseBox,20,20,10,jPanel);
        jPanel.setVisible(true);
    }

    private void initButton(){
        MyLayout.setOne(baseBox,20,20,10,submit);
        submit.addActionListener(new AirAddAction(jComboBox,father));
    }

    private void addBox(){
        this.add(baseBox);
    }
}

class AirAddAction implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        int air_id = AirConditionerObject.getAll().size() + 1;

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        String yearStr = Integer.toString(year);
        int month = calendar.get(Calendar.MONTH) + 1;
        String monthStr = "";
        if (month < 10){
            monthStr = monthStr + "0" + Integer.toString(month);
        }
        else {
            monthStr = Integer.toString(month);
        }
        int day = calendar.get(Calendar.DATE);
        String dayStr = "";
        if (day < 10){
            dayStr = dayStr + "0" + Integer.toString(day);
        }
        else {
            dayStr = Integer.toString(day);
        }
        String time = yearStr + monthStr + dayStr;

        String address = "闲置";
        String brand = jComboBox.getSelectedItem().toString();

        AirConditionerObject airConditionerObject = new AirConditionerObject(air_id,time,address,brand);
        airConditionerObject.add();
        JOptionPane.showMessageDialog(father,"添加成功","添加提示",JOptionPane.INFORMATION_MESSAGE);
    }

    JComboBox jComboBox;
    JFrame father;

    public AirAddAction(JComboBox jComboBox, JFrame father) {
        this.jComboBox = jComboBox;
        this.father = father;
    }
}


