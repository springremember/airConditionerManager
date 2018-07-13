import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BaoXiu extends JDialog implements OtherInterface {
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
    // 一个下拉框和一个提交按钮
    String[] content = {"运行故障", "不制冷", "不制热"};
    JComboBox baoxiu11 = new JComboBox(content);
    JButton submit = new JButton("报修");
    Box baseBox = Box.createVerticalBox();

    BaoXiu(JFrame father) {
        super(father, "空调报修", true);
        this.father = father;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    // 设置布局
    private void set() {
        initLable();
        baoxiu11();
        initButton();
        addBox();
    }

    private void initLable() {
        JLabel welcome = new JLabel("请选择报修原因");
        welcome.setFont(new Font("宋体", Font.BOLD, 20));
        MyLayout.setOne(baseBox, 60, 20, 20, welcome);
    }

    private void baoxiu11() {
        MyLayout.setOne(baseBox, 30, 20, 20, baoxiu11);
    }

    private void initButton(){
        MyLayout.setOne(baseBox,50,20,20,submit);
        submit.addActionListener(new BaoXiuAction(student_id,baoxiu11,father));
    }

    private void addBox () {
        this.add(baseBox);
    }
}

class BaoXiuAction implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        String dorm_id = StudentObject.getById(student_id).get(0).dorm_id;
        // 设置宿舍表
        DormitoryObject.getById(dorm_id).get(0).setRun_status(false);
        int repair_id = RepairObject.getAll().size() + 1;
        String reasonStr = reason.getSelectedItem().toString();
        RepairObject repairObject = new RepairObject(repair_id,dorm_id,reasonStr,false,2);
        // 新增报修表
        repairObject.add();
    }

    String student_id;
    JComboBox reason;
    JFrame father;

    public BaoXiuAction(String student_id, JComboBox reason, JFrame father) {
        this.student_id = student_id;
        this.reason = reason;
        this.father = father;
    }
}