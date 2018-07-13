import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AirManagerChange extends JDialog implements OtherInterface {
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
    JTextField inputAirId=new JTextField(10);
    JTextField inputDormId = new JTextField(10);
    JButton submit = new JButton("提交");

    AirManagerChange(JFrame father){
        super(father,"空调修改",true);
        this.father = father;
    }

    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
    }

    // 布局
    private void set(){
        initLable();
        initTextFieldAirId();
        initTextFieldDormId();
        initButton();
        addBox();
    }

    private void initLable(){
        JLabel welcome = new JLabel("空调修改");
        welcome.setFont(new Font("宋体",Font.BOLD,20));
        MyLayout.setOne(baseBox,50,20,20,welcome);
    }

    private void initTextFieldAirId(){
        JLabel AirId =new JLabel("空调号");
        MyLayout.setTwo(baseBox,20,20,10,10,AirId,inputAirId);
    }
    private void initTextFieldDormId(){
        JLabel DormId =new JLabel("寝室号");
        MyLayout.setTwo(baseBox,20,20,10,10,DormId,inputDormId);
    }
    private void initButton(){
        MyLayout.setOne(baseBox,20,20,20,submit);
        submit.addActionListener(new AirChangerAction(inputAirId,inputDormId,father));
    }

    private void addBox(){
        this.add(baseBox);
    }
}

class AirChangerAction implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        int air_id = Integer.parseInt(inputAirId.getText());
        String dorm_id = inputDormId.getText();
        AirConditionerObject.getById(air_id).get(0).setAddress(dorm_id);
        JOptionPane.showMessageDialog(father,"修改成功","空调修改提示",JOptionPane.INFORMATION_MESSAGE);
    }

    JTextField inputAirId;
    JTextField inputDormId;
    JFrame father;

    public AirChangerAction(JTextField inputAirId, JTextField inputDormId, JFrame father) {
        this.inputAirId = inputAirId;
        this.inputDormId = inputDormId;
        this.father = father;
    }
}
