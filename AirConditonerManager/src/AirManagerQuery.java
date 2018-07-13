import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class AirManagerQuery extends JDialog implements OtherInterface {
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
    JTextField inputDormitory = new JTextField(10);
    JPanel jPanel = new JPanel();
    String str1[]= {"寝室查询","闲置查询","报废查询"};
    JComboBox jComboBox = new JComboBox(str1);
    JTextField inputDormId = new JTextField(10);
    JButton submit = new JButton("提交");
//    RepairDocumentListener repairDocumentListener = new RepairDocumentListener(inputRepairId,inputNewAirId,normalFix,changeFix);

    AirManagerQuery(JFrame father){
        super(father,"空调查询",true);
        this.father = father;
    }

    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
    }

    // 布局
    private void set(){
        initLable();
        initComboBox();
        initButton();
        addBox();
    }

    private void initLable(){
        JLabel welcome = new JLabel("空调查询");
        welcome.setFont(new Font("宋体",Font.BOLD,20));
        MyLayout.setOne(baseBox,50,20,20,welcome);
    }

    private void initComboBox(){
        jPanel.setLayout(new FlowLayout());
        JLabel lable=new JLabel("选项：");
        jPanel.add(lable);

        jPanel.add(jComboBox);
        MyLayout.setTwo(baseBox,20,20,10,10,jPanel,inputDormId);
        jPanel.setVisible(true);
    }

    private void initButton(){
        MyLayout.setOne(baseBox,20,20,10,submit);
        submit.addActionListener(new AirManagerQueryListener(jComboBox,inputDormId,father));
    }

    private void addBox(){
        this.add(baseBox);
    }
}

class AirManagerQueryListener implements ActionListener{
    JComboBox jComboBox;
    JTextField inputDormId;
    JFrame father;

    public AirManagerQueryListener(JComboBox jComboBox, JTextField inputDormId, JFrame father) {
        this.jComboBox = jComboBox;
        this.inputDormId = inputDormId;
        this.father = father;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String type = jComboBox.getSelectedItem().toString();
        if (type.equals("寝室查询")){
            String dorm_id = inputDormId.getText();
            ArrayList<AirConditionerObject> airList = AirConditionerObject.getByAddress(dorm_id);
            OtherInterface show = new AirManagerShow(father,airList);
            show.showInterface("无用");
        }
        else if (type.equals("闲置查询")){
            ArrayList<AirConditionerObject> airList = AirConditionerObject.getByAddress("闲置");
            OtherInterface show = new AirManagerShow(father,airList);
            show.showInterface("无用");
        }
        else {
            ArrayList<AirConditionerObject> airList = AirConditionerObject.getByAddress("报废");
            OtherInterface show = new AirManagerShow(father,airList);
            show.showInterface("无用");
        }
    }
}

class AirManagerShow extends JDialog implements OtherInterface {
    @Override
    public void showInterface(String s) {
        setLayout(new FlowLayout());
        setBounds(100,100,400,400);
        set();
        setVisible(true);
    }

    JFrame father;
    Box baseBox = Box.createVerticalBox();
    JTextArea jTextArea = new JTextArea();
    ArrayList<AirConditionerObject> airList;

    AirManagerShow(JFrame father,ArrayList<AirConditionerObject> airList){
        super(father,"空调查询结果",true);
        this.father = father;
        this.airList = airList;
    }

    private void set(){
        initShow();
        add(baseBox);
    }

    private void initShow(){
        jTextArea.setFont(new Font("宋体",Font.BOLD,15));
        String showStr = "";
        for (AirConditionerObject one :
                airList) {
            showStr += "维修号:" + one.air_id + "\t" + "所在地:" + one.address;
            showStr += "\n";
        }
        jTextArea.setText(showStr);
        jTextArea.setEnabled(false);
        MyLayout.setOne(baseBox,40,20,20,jTextArea);
    }
}