import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuTie extends JDialog implements OtherInterface {
        @Override
        public void showInterface(String id) {
            setStudent_id(id);
            setLayout(new FlowLayout());
            setBounds(100, 100, 400, 400);
            set();
    setVisible(true);

}


    // 学号
    String student_id;
    // 父框架
    JFrame father;


    Box baseBox = Box.createVerticalBox();

    // 两个输入框和一个提交按钮一个单选按钮
    JTextField inputDormnumber = new JTextField();
    JTextField inputMonth = new JTextField(10);
    JTextField inputMoney = new JTextField(10);
    JButton submit = new JButton("提交");
    JRadioButton jr1=new JRadioButton("特殊补贴");
    JRadioButton jr2= new JRadioButton("全体补贴",true);
    ButtonGroup group=new ButtonGroup();

    BuTie(JFrame father){
        super(father,"空调补贴",true);
        this.father = father;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    // 设置布局
    private void set(){
        initLable();
        initInput();
        JRadioButton();
        initButton();
        addBox();
    }

    private void initLable(){
        JLabel welcome = new JLabel("空调补贴");
        welcome.setFont(new Font("宋体",Font.BOLD,20));
        MyLayout.setOne(baseBox,40,20,2,welcome);
    }

    private void initInput(){
        JLabel w1 = new JLabel("月份//寝室号");
        JLabel w2 = new JLabel("补贴金额");

        MyLayout.setTwo(baseBox,20,20,0,2,w1,inputMonth);
        MyLayout.setTwo(baseBox,20,20,2,10,w2,inputMoney);
        inputMonth.getDocument().addDocumentListener(new RechargeDocumentListener(inputMonth,submit));
        inputMoney.getDocument().addDocumentListener(new RechargeDocumentListener(inputMoney,submit));
    }

    private void JRadioButton(){
        group.add(jr1);
        group.add(jr2);
        MyLayout.setTwo(baseBox,10,20,10,10,jr1,jr2);
    }

    private void initButton(){
        MyLayout.setOne(baseBox,10,20,2,submit);
        submit.setEnabled(false);
        submit.addActionListener(new BuTieAction(jr1,father,inputMonth,inputMoney));
    }


    private void addBox(){
        this.add(baseBox);
    }
}

class BuTieAction implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        DefaultButtonModel model = (DefaultButtonModel) type.getModel();
        if(model.getGroup().isSelected(model)){
            float money = Float.parseFloat(inputMoney.getText());
            String dorm_id = inputMonth.getText();
            DormitoryObject dormitoryObject = DormitoryObject.getById(dorm_id).get(0);
            dormitoryObject.setMoney(dormitoryObject.money + money);
            JOptionPane.showMessageDialog(father,"特殊补贴设置成功","特殊补贴",JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(father,"定时补贴设置成功","定时补贴",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    JRadioButton type;
    JFrame father;
    JTextField inputMonth;
    JTextField inputMoney;

    public BuTieAction(JRadioButton type, JFrame father, JTextField inputMonth, JTextField inputMoney) {
        this.type = type;
        this.father = father;
        this.inputMonth = inputMonth;
        this.inputMoney = inputMoney;
    }
}

