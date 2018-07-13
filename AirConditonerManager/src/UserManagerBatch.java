import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserManagerBatch extends JDialog implements OtherInterface {
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
    JTextField inputStartId = new JTextField(10);
    JTextField inputOverId = new JTextField(10);
    JRadioButton batchAdd = new JRadioButton("添加",true);
    JRadioButton batchDelete = new JRadioButton("删除");
    ButtonGroup group = new ButtonGroup();
    JButton submit = new JButton("提交");

    UserManagerBatch(JFrame father){
        super(father,"批量操作",true);
        this.father = father;
    }

    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
    }

    // 布局
    private void set(){
        initLable();
        initTextFieldStartId();
        initTextFieldOverId();
        initBatchAddOrDelete();
        initButton();
        addBox();
    }

    private void initLable(){
        JLabel welcome = new JLabel("批量操作");
        welcome.setFont(new Font("宋体",Font.BOLD,20));
        MyLayout.setOne(baseBox,50,20,20,welcome);
    }

    private void initTextFieldStartId(){
        JLabel AirId =new JLabel("起始学号");
        MyLayout.setTwo(baseBox,20,20,10,10,AirId,inputStartId);
    }
    private void initTextFieldOverId(){
        JLabel DormId =new JLabel("终止学号");
        MyLayout.setTwo(baseBox,20,20,10,10,DormId,inputOverId);
    }

    private void initBatchAddOrDelete(){
        group.add(batchAdd);
        group.add(batchDelete);
        //MyLayout.setOne(baseBox,20,20,20,10,group);
        MyLayout.setTwo(baseBox,20,20,20,10,batchAdd,batchDelete);
    }

    private void initButton(){
        MyLayout.setOne(baseBox,20,20,20,submit);
        submit.addActionListener(new UserManagerAction(father,batchAdd,inputStartId,inputOverId));
    }

    private void addBox(){
        this.add(baseBox);
    }
}

class UserManagerAction implements  ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        String startStr = start.getText();
        String endStr = end.getText();
        String common = startStr.substring(0,startStr.length()-2);
        int startInt = Integer.parseInt(startStr.substring(startStr.length()-2));
        int endInt = Integer.parseInt(endStr.substring(endStr.length()-2));
        DefaultButtonModel model = (DefaultButtonModel)type.getModel();
        String student_id = "";
        StudentObject temp;
        if (model.getGroup().isSelected(model)){
            for (int i=startInt;i<=endInt;i++){
                if (i < 10){
                    student_id = common + "0" + i;
                }
                else {
                    student_id = common + i;
                }
                temp = new StudentObject(student_id,"桃2119","123456","无名氏",true,"123456789");
                temp.add();
            }
            JOptionPane.showMessageDialog(father,"添加成功","添加提示",JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            for (int i=startInt;i<=endInt;i++){
                if (i < 10){
                    student_id = common + "0" + i;
                }
                else {
                    student_id = common + i;
                }
                temp = StudentObject.getById(student_id).get(0);
                temp.delete();
            }
            JOptionPane.showMessageDialog(father,"删除成功","删除提示",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    JFrame father;
    JRadioButton type;
    JTextField start;
    JTextField end;

    public UserManagerAction(JFrame father, JRadioButton type, JTextField start, JTextField end) {
        this.father = father;
        this.type = type;
        this.start = start;
        this.end = end;
    }
}
