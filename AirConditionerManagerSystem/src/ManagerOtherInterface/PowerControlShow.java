package ManagerOtherInterface;
import SpringDatabaseManager.*;
import LoginAndMainInterface.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class PowerControlShow extends JDialog implements OtherInterface {
    @Override
    public void showInterface(String id) {
        setCost(Integer.parseInt(id));
        setLayout(new FlowLayout());
        setBounds(100,100,400,400);
        set();
        setVisible(true);
    }


    // 父框架
    JFrame father;
    int cost;
    // 一个输入框和一个提交按钮
    JTextArea SrollArea = new JTextArea();
    JButton submit1 = new JButton("一键断电");
    JButton submit2 = new JButton("一键供电");
    Box baseBox = Box.createVerticalBox();
    ArrayList<DormitoryObject> over;

    PowerControlShow(JFrame father){
        super(father,"用户信息修改",true);
        this.father = father;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    // 设置布局
    private void set(){
        initSroll();
        initButton();
        addBox();
    }

    private void initSroll(){
        SrollArea.setEnabled(false);
        String content = "";
        over = DormitoryObject.getOverCost(cost);
        for (DormitoryObject one :
                over) {
            content += "寝室号:" + one.dorm_id + "\t" + "用电量" + one.cost6;
            content += "\n";
        }
        SrollArea.setText(content);
        MyLayout.setOne(baseBox,20,20,20,SrollArea);

    }


    private void initButton(){
        MyLayout.setTwo(baseBox,30,20,20,20,submit1,submit2);
        submit1.addActionListener(new PowerCut(over,father));
        submit2.addActionListener(new PowerOn(over,father));
    }

    private void addBox(){
        this.add(baseBox);
    }
}

class PowerCut implements ActionListener{
    ArrayList<DormitoryObject> over;
    JFrame father;

    public PowerCut(ArrayList<DormitoryObject> over, JFrame father) {
        this.over = over;
        this.father = father;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (DormitoryObject one :
                over) {
            one.setPower_status(false);
        }
        JOptionPane.showMessageDialog(father,"断电成功","操作完成",JOptionPane.INFORMATION_MESSAGE);
    }
}

class PowerOn implements ActionListener{
    ArrayList<DormitoryObject> over;
    JFrame father;

    public PowerOn(ArrayList<DormitoryObject> over, JFrame father) {
        this.over = over;
        this.father = father;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (DormitoryObject one :
                over) {
            one.setPower_status(true);
        }
        JOptionPane.showMessageDialog(father,"供电成功成功","操作完成",JOptionPane.INFORMATION_MESSAGE);
    }
}






