package ManagerOtherInterface;
import SpringDatabaseManager.*;
import LoginAndMainInterface.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class Repair extends JDialog implements OtherInterface {
    @Override
    public void showInterface(String s) {
        setManager_id(s);
        setVisible(true);
    }

    private String manager_id;
    private JFrame father;
    private Box baseBox = Box.createVerticalBox();
    private JTextField inputRepairId = new JTextField(10);
    private JTextField inputNewAirId = new JTextField("正常维修请勿填写",10);
    private JButton normalFix = new JButton("正常维修");
    private JButton changeFix = new JButton("更换维修");
    private RepairDocumentListener repairDocumentListener = new RepairDocumentListener(inputRepairId,inputNewAirId,normalFix,changeFix);

    public Repair(JFrame father){
        super(father,"空调维修",true);
        this.father   = father;
        setLayout(new FlowLayout());
        setBounds(100,100,400,400);
        set();
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
    }

    // 布局
    private void set(){
        initLable();
        initRepairId();
        initChangeId();
        initButton();
        addBox();
    }

    private void initLable(){
        JLabel welcome = new JLabel("空调维修");
        welcome.setFont(new Font("宋体",Font.BOLD,20));
        MyLayout.setOne(baseBox,50,20,20,welcome);
    }

    private void initRepairId(){
        JLabel welcome = new JLabel("请输入报修号");
        welcome.setFont(new Font("宋体",Font.BOLD,16));
        MyLayout.setTwo(baseBox,40,20,10,10,welcome,inputRepairId);
        inputRepairId.getDocument().addDocumentListener(repairDocumentListener);
    }

    private void initChangeId(){
        JLabel welcome = new JLabel("请输入更换的空调号");
        welcome.setFont(new Font("宋体",Font.BOLD,16));
        MyLayout.setTwo(baseBox,10,20,10,10,welcome,inputNewAirId);
        inputNewAirId.getDocument().addDocumentListener(repairDocumentListener);
    }

    private void initButton(){
        MyLayout.setTwo(baseBox,20,10,10,20,normalFix,changeFix);
        normalFix.setEnabled(false);
        changeFix.setEnabled(false);
        normalFix.addActionListener(new RepairNormalAction(father,inputRepairId));
        changeFix.addActionListener(new RepairChangeAction(father,inputRepairId,inputNewAirId));
    }

    private void addBox(){
        this.add(baseBox);
    }
}

class RepairDocumentListener implements DocumentListener{
    private JTextField inputRepairId;
    private JTextField inputNewAirId;
    private JButton normalFix;
    private JButton changeFix;

    public RepairDocumentListener(JTextField inputRepairId, JTextField inputNewAirId, JButton normalFix, JButton changeFix) {
        this.inputRepairId = inputRepairId;
        this.inputNewAirId = inputNewAirId;
        this.normalFix = normalFix;
        this.changeFix = changeFix;
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        if (!inputRepairId.getText().isEmpty()){
            normalFix.setEnabled(true);
            if (!inputNewAirId.getText().isEmpty() || !inputNewAirId.getText().equals("正常维修请勿填写")){
                changeFix.setEnabled(true);
            }
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        changedUpdate(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        changedUpdate(e);
    }
}

class RepairNormalAction implements ActionListener{
    private JFrame father;
    private JTextField inputRepairId;

    public RepairNormalAction(JFrame father,JTextField inputRepairId) {
        this.father = father;
        this.inputRepairId = inputRepairId;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RepairObject repairObject = RepairObject.getById(Integer.parseInt(inputRepairId.getText())).get(0);
        if (repairObject.repair_status){
            JOptionPane.showMessageDialog(father,"已经提交过维修报告,请勿重复提交","重复提交提示",JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            // 设置维修成功
            repairObject.setRepair_status(true);
            // 设置通知用户
            repairObject.setInfor_status(1);
            DormitoryObject dormitoryObject = DormitoryObject.getByRepairId(repairObject.repair_id).get(0);
            dormitoryObject.setRun_status(true);
            JOptionPane.showMessageDialog(father, "维修提交成功", "维修提交通知", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

class RepairChangeAction implements ActionListener{
    private JFrame father;
    private JTextField inputRepairId;
    private JTextField inputNewAirId;

    public RepairChangeAction(JFrame father,JTextField inputRepairId, JTextField inputNewAirId) {
        this.father = father;
        this.inputRepairId = inputRepairId;
        this.inputNewAirId = inputNewAirId;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        RepairObject repairObject = RepairObject.getById(Integer.parseInt(inputRepairId.getText())).get(0);
        if (repairObject.repair_status){
            JOptionPane.showMessageDialog(father,"已经提交维修成功报告","请勿重复提交",JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            // 设置维修成功
            repairObject.setRepair_status(true);
            // 设置通知用户
            repairObject.setInfor_status(1);
            DormitoryObject dormitoryObject = DormitoryObject.getByRepairId(repairObject.repair_id).get(0);
            dormitoryObject.setRun_status(true);
            AirConditionerObject airConditionerObject = AirConditionerObject.getById(Integer.parseInt(inputNewAirId.getText())).get(0);
            airConditionerObject.setAddress(dormitoryObject.dorm_id);
            airConditionerObject = AirConditionerObject.getByAddress(dormitoryObject.dorm_id).get(0);
            airConditionerObject.setAddress("报废");

            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            String yearStr = Integer.toString(year);
            int month = calendar.get(Calendar.MONTH) + 1;
            String monthStr = "";
            if (month < 10) {
                monthStr = monthStr + "0" + Integer.toString(month);
            } else {
                monthStr = Integer.toString(month);
            }
            int day = calendar.get(Calendar.DATE);
            String dayStr = "";
            if (day < 10) {
                dayStr = dayStr + "0" + Integer.toString(day);
            } else {
                dayStr = Integer.toString(day);
            }
            String time = yearStr + monthStr + dayStr;

            airConditionerObject.setTime(airConditionerObject.address + time);
            JOptionPane.showMessageDialog(father, "更换维修提交成功", "更换维修通知", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}