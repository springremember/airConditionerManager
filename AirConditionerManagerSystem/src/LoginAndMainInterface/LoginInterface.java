package LoginAndMainInterface;
import SpringDatabaseManager.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginInterface extends JFrame{
    Box baseBox = Box.createVerticalBox();
    JTextField inputName = new JTextField(10);
    JPasswordField inputPassword = new JPasswordField(10);
    ButtonGroup type = new ButtonGroup();
    JRadioButton student = new JRadioButton("学生",true);
    JRadioButton manager = new JRadioButton("管理员");
    JButton submit = new JButton("登录");
    TextFillAction textFillAction = new TextFillAction(inputName,inputPassword,submit);
    MainInterface studentInterface;
    MainInterface managerInterface;

    public LoginInterface(MainInterface studentInterface, MainInterface managerInterface){
        this.studentInterface = studentInterface;
        this.managerInterface = managerInterface;
        setTitle("空调管理系统");
        setBounds(100,100,400,400);
        setLayout(new FlowLayout());
        type.add(student);
        type.add(manager);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setInterface();
        setAction();
        setVisible(true);
    }

    private void setInterface(){
        initLabel();
        initName();
        initPassword();
        initType();
        initSubmit();
        addBox();
    }

    // 第一行标题
    private void initLabel(){
        JLabel name = new JLabel("空调管理系统");
        MyLayout.setOne(baseBox,60,60,30,name);
        name.setFont(new Font("宋体",Font.BOLD,20));
    }

    // 第二行学号、管理员号输入框
    private void initName(){
        JLabel userName = new JLabel("学号/管理员号");
        userName.setFont(new Font("宋体",Font.BOLD,15));
        MyLayout.setTwo(baseBox,0,15,20,10,userName,inputName);
    }

    // 第三行密码输入框
    private void initPassword(){
        JLabel userPassword = new JLabel("密码");
        userPassword.setFont(new Font("宋体",Font.BOLD,15));
        MyLayout.setTwo(baseBox,0,15,20,20,userPassword,inputPassword);
    }

    // 第四行单选框
    private void initType(){
        MyLayout.setTwo(baseBox,0,15,20,15,student,manager);
    }

    // 第五行提交按钮
    private void initSubmit(){
        submit.setEnabled(false);
        MyLayout.setOne(baseBox,0,0,30,submit);
    }

    // 设置事件
    private void setAction(){
        inputName.getDocument().addDocumentListener(textFillAction);
        inputPassword.getDocument().addDocumentListener(textFillAction);
        LoginListener loginListener = new LoginListener(this,inputName,inputPassword,student,studentInterface,managerInterface);
        submit.addActionListener(loginListener);
    }

    // 设置盒子布局和显示
    private void addBox(){
        this.add(baseBox);
    }
}

class LoginListener implements ActionListener {
    JFrame father;
    JTextField name;
    JPasswordField password;
    JRadioButton student;
    MainInterface studentInterface;
    MainInterface managerInterface;

    LoginListener(JFrame father, JTextField name, JPasswordField password, JRadioButton student, MainInterface studentInterface, MainInterface managerInterface) {
        this.father = father;
        this.name = name;
        this.password = password;
        this.student = student;
        this.studentInterface = studentInterface;
        this.managerInterface = managerInterface;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nameValue = name.getText();
        String passwordValue = String.valueOf(password.getPassword());
        DefaultButtonModel model = (DefaultButtonModel) student.getModel();
        if (model.getGroup().isSelected(model)) {
            ArrayList<StudentObject> hmc = StudentObject.getById(nameValue);
            if (hmc.size() == 1 && hmc.get(0).getPassword().equals(passwordValue)) {
                studentInterface.setId(nameValue);
                studentInterface.showInterface();
                father.dispose();
            } else {
                JOptionPane.showMessageDialog(father, "用户名或密码错误", "输入", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            ArrayList<ManagerObject> hmc = ManagerObject.getById(nameValue);
            if (hmc.size() == 1 && hmc.get(0).password.equals(passwordValue)){
                managerInterface.setId(nameValue);
                managerInterface.showInterface();
                father.dispose();
            }else {
                JOptionPane.showMessageDialog(father, "用户名或密码错误", "输入", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}


class TextFillAction implements DocumentListener{
    JTextField name;
    JPasswordField password;
    JButton submit;

    TextFillAction(JTextField name,JPasswordField password,JButton submit){
        this.name = name;
        this.password = password;
        this.submit = submit;
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
       if (name.getText().length() != 0){
           String thePassword = String.valueOf(password.getPassword());
           if (thePassword.length() != 0){
               submit.setEnabled(true);
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