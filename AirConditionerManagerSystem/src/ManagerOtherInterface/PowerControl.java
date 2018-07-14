package ManagerOtherInterface;
import SpringDatabaseManager.*;
import LoginAndMainInterface.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PowerControl extends JDialog implements OtherInterface {
    @Override
    public void showInterface(String id) {
        setVisible(true);
    }


    // 父框架
    private JFrame father;
    // 一个输入框和一个提交按钮
    private JTextField inputNumber = new JTextField(10);
    private JButton submit = new JButton("查询");
    private Box baseBox = Box.createVerticalBox();

    public PowerControl(JFrame father){
        super(father,"空调用电控制",true);
        this.father = father;
        setLayout(new FlowLayout());
        setBounds(100,100,400,400);
        set();
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }


    // 设置布局
    private void set(){
        initInput();
        initButton();
        addBox();
    }

    private void initInput(){
        MyLayout.setOne(baseBox,30,20,20,inputNumber);
        MyLayout.setTwo(baseBox,30,20,20,20,new JLabel("请输入限电阈值:"),inputNumber);
        inputNumber.getDocument().addDocumentListener(new ControlDocumentListener(inputNumber,submit));
    }

    private void initButton(){
        MyLayout.setOne(baseBox,20,20,20,submit);
        submit.setEnabled(false);
        submit.addActionListener(new ControlActionListener(this,inputNumber,new PowerControlShow(father)));
    }

    private void addBox(){
        this.add(baseBox);
    }
}

// 输入框不为空时可提交
class ControlActionListener implements ActionListener{
    private JDialog father;
    private JTextField inputNumber;
    private OtherInterface oneStep;


    public ControlActionListener(JDialog father, JTextField inputNumber, OtherInterface oneStep) {
        this.father = father;
        this.inputNumber = inputNumber;
        this.oneStep = oneStep;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String number = inputNumber.getText();
        oneStep.showInterface(number);
    }
}

// 提交按钮事件
class ControlDocumentListener implements DocumentListener{
    private JTextField inputNumber;
    private JButton submit;

    public ControlDocumentListener(JTextField inputNumber, JButton submit) {
        this.inputNumber = inputNumber;
        this.submit = submit;
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        if (!inputNumber.getText().isEmpty()){
            submit.setEnabled(true);
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
