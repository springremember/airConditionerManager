import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Student extends JFrame implements MainInterface{
    // 学号
    String student_id;
    // 盒子
    Box baseBox = Box.createVerticalBox();
    // 三个功能的按钮和对应对象
    JLabel welcome = new JLabel("当前余额：");
    JButton add = new JButton("充值空调费");
    OtherInterface addInterface;
    JButton repair = new JButton("故障报修");
    OtherInterface repairInterface ;
    JButton personal = new JButton("个人信息修改");
    OtherInterface personalInterface;
    boolean info = true;

    // 设置功能函数
    public void setAll(OtherInterface addInterface,OtherInterface repairInterface,OtherInterface personalInterface){
        this.addInterface = addInterface;
        this.repairInterface = repairInterface;
        this.personalInterface = personalInterface;
    }

    public Student(String student_id){
        this.student_id = student_id;
    }

    public Student(){}

    //布局函数
    private void set(){
        initLable();
        initAddCost();
        initRepair();
        initPersonal();
        setBox();
    }
    private void initLable(){
        MyLayout.setOne(baseBox,40,90,20,welcome);
        welcome.setFont(new Font("微软雅黑",Font.BOLD,16));
    }

    private void initAddCost(){
        MyLayout.setOne(baseBox,0,25,20,add);
        add.addActionListener(new OpenOtherInterface(student_id,addInterface));
    }

    private void initRepair(){
        MyLayout.setOne(baseBox,0,20,20,repair);
        repair.addActionListener(new OpenOtherInterface(student_id,repairInterface));
    }

    private void initPersonal(){
        MyLayout.setOne(baseBox,0,50,20,personal);
        personal.addActionListener(new OpenOtherInterface(student_id,personalInterface));
    }

    private void setBox(){
        this.add(baseBox);
    }

    // 事件函数
    private void getMoney(){
        String dorm_id = StudentObject.getById(student_id).get(0).dorm_id;
        float money = DormitoryObject.getById(dorm_id).get(0).money;
        welcome.setText("当前余额："+money);
        if (info && money < 5){
            info = false;
            JOptionPane.showMessageDialog(this,"空调余额不足，请注意充值","提醒",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void getInformation(){
        String dorm_id = StudentObject.getById(student_id).get(0).dorm_id;
        int repair_id = DormitoryObject.getById(dorm_id).get(0).repair_id;
        if (repair_id != 0) {
            RepairObject one = RepairObject.getById(repair_id).get(0);
            one.setInfor_status(0);
            JOptionPane.showMessageDialog(this,"您的空调已经维修成功","维修通知",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void change(){
        getMoney();
        getInformation();
    }

    // 设置定时器
    private void setTimeChanger(){
        change();
        Timer t = new Timer(10000,new TimeChanger(this));
        t.start();
    }

    // 实现接口
    @Override
    public void setId(String student_id) {
        this.student_id = student_id;
    }

    @Override
    public void showInterface() {
        setTitle("主界面");
        setBounds(100,100,400,400);
        setLayout(new FlowLayout());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 设置界面
        set();
        // 设置定时器
        setTimeChanger();
        setVisible(true);
    }
}

// 定时事件实现
class TimeChanger implements ActionListener {
    private Student father;

    TimeChanger(Student father){
        this.father = father;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        father.change();
    }
}
