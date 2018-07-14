package StudentOtherInterface;
import SpringDatabaseManager.*;
import LoginAndMainInterface.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Modify extends JDialog implements OtherInterface {
    @Override
    public void showInterface(String id) {
        setStudent_id(id);
        setAction();
        setVisible(true);
    }

    // 学号
    private String student_id;
    // 父框架
    private JFrame father;
    // 一个输入框和一个提交按钮
    private JTextField inputPhonenumber = new JTextField(10);
    private JTextField inputpassword = new JTextField(10);
    private JButton submit = new JButton("修改");
    private Box baseBox = Box.createVerticalBox();

    public Modify(JFrame father){
        super(father,"用户信息修改",true);
        this.father = father;
        setLayout(new FlowLayout());
        setBounds(100,100,400,400);
        set();
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    // 设置布局
    private void set(){
        initinput1();
        initInput2();
        initButton();
        addBox();
    }

    private void initinput1(){
        MyLayout.setTwo(baseBox,100,10,20,10,new JLabel("修改手机号码:"),inputPhonenumber);
        inputPhonenumber.getDocument().addDocumentListener(new ModifyDocumentListener(inputPhonenumber,inputpassword,submit));
    }

    private void initInput2(){
        MyLayout.setTwo(baseBox,20,20,20,35,new JLabel("修改密码:"),inputpassword);
        inputpassword.getDocument().addDocumentListener(new ModifyDocumentListener(inputPhonenumber,inputpassword,submit));
    }

    private void initButton(){
        MyLayout.setOne(baseBox,40,20,20,submit);
        submit.setEnabled(false);
    }

    private void addBox(){
        this.add(baseBox);
    }

    private void setAction(){
        submit.addActionListener(new ModifyActionListener(this,inputPhonenumber,inputpassword,student_id));
    }
}

// 输入框不为空时可提交
class ModifyActionListener implements ActionListener{
    private JDialog father;
    private JTextField inputPhonenumber;
    private JTextField inputpassword;
    private String student_id;

    public ModifyActionListener(JDialog father, JTextField inputPhonenumber, JTextField inputpassword, String student_id) {
        this.father = father;
        this.inputPhonenumber = inputPhonenumber;
        this.inputpassword = inputpassword;
        this.student_id = student_id;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        StudentObject theStudent = StudentObject.getById(student_id).get(0);
        int n=0;
        String a;
        if (!inputPhonenumber.getText().isEmpty()){
            theStudent.setPhonenumber(inputPhonenumber.getText());
            n=n+1;
        }
        if (!inputpassword.getText().isEmpty()){
            theStudent.setPassword(inputpassword.getText());
            n=n+2;
        }
        if(n==1)
            a="电话号码修改成功";
        else if(n==2)
            a="密码修改成功";
        else
            a="电话号码修改成功"+"\n"+"密码修改成功";
        JOptionPane.showMessageDialog(father,a,"修改成功",JOptionPane.INFORMATION_MESSAGE);
    }
}

// 提交按钮事件
class ModifyDocumentListener implements DocumentListener{
    private JTextField inputPhonenumber;
    private JTextField inputpassword;
    private JButton submit;

    public ModifyDocumentListener(JTextField inputPhonenumber,JTextField inputpassword, JButton submit) {
        this.inputPhonenumber = inputPhonenumber;
        this.inputpassword = inputpassword;
        this.submit = submit;
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        if (!inputPhonenumber.getText().isEmpty()||!inputpassword.getText().isEmpty()){
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
