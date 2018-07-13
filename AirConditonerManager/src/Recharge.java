import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Recharge extends JDialog implements OtherInterface {
    @Override
    public void showInterface(String id) {
        setStudent_id(id);
        setLayout(new FlowLayout());
        setBounds(100,100,400,400);
        set();
        setVisible(true);
    }

    // 学号
    String student_id;
    // 父框架
    JFrame father;
    // 一个输入框和一个提交按钮
    JTextField inputMoney = new JTextField();
    JButton submit = new JButton("充值");
    Box baseBox = Box.createVerticalBox();

    Recharge(JFrame father){
        super(father,"空调费用充值",true);
        this.father = father;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    // 设置布局
    private void set(){
        initLable();
        initInput();
        initButton();
        addBox();
    }

    private void initLable(){
        JLabel welcome = new JLabel("请输入充值金额");
        welcome.setFont(new Font("宋体",Font.BOLD,20));
        MyLayout.setOne(baseBox,60,20,20,welcome);
    }

    private void initInput(){
        MyLayout.setOne(baseBox,30,20,20,inputMoney);
        inputMoney.getDocument().addDocumentListener(new RechargeDocumentListener(inputMoney,submit));
    }

    private void initButton(){
        MyLayout.setOne(baseBox,10,20,20,submit);
        submit.setEnabled(false);
        submit.addActionListener(new RechargeActionListener(this,inputMoney,student_id));
    }

    private void addBox(){
        this.add(baseBox);
    }
}

// 输入框不为空时可提交
class RechargeActionListener implements ActionListener{
    JDialog father;
    JTextField inputMoney;
    String student_id;

    public RechargeActionListener(JDialog father,JTextField inputMoney, String student_id) {
        this.father = father;
        this.inputMoney = inputMoney;
        this.student_id = student_id;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StudentObject theStudent = StudentObject.getById(student_id).get(0);
        DormitoryObject theDorm = DormitoryObject.getById(theStudent.dorm_id).get(0);
        theDorm.setMoney(Float.parseFloat(inputMoney.getText()) + theDorm.money);
        JOptionPane.showMessageDialog(father,"充值成功","充值成功",JOptionPane.INFORMATION_MESSAGE);
    }
}

// 提交按钮事件
class RechargeDocumentListener implements DocumentListener{
    JTextField inputMoney;
    JButton submit;

    public RechargeDocumentListener(JTextField inputMoney, JButton submit) {
        this.inputMoney = inputMoney;
        this.submit = submit;
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        if (!inputMoney.getText().isEmpty()){
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
