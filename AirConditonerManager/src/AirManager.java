import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AirManager extends JDialog implements OtherInterface {
    @Override
    public void showInterface(String s) {
        setManager_id(s);
        setLayout(new FlowLayout());
        setBounds(100,100,300,300);
        set();
        setVisible(true);
    }

    String manager_id;
    JFrame father;
    Box baseBox = Box.createVerticalBox();
    JButton buttonQuery = new JButton("查询");
    JButton buttonAdd =new JButton("添加");
    JButton buttonChange =new JButton("修改");
//    RepairDocumentListener repairDocumentListener = new RepairDocumentListener(inputRepairId,inputNewAirId,normalFix,changeFix);

    AirManager(JFrame father){
        super(father,"空调管理",true);
        this.father = father;
    }

    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
    }

    // 布局
    private void set(){
        initLable();
        initButtonQuery();
        initButtonAdd();
        initButtonChange();
        addBox();
        initAction();
    }

    private void initLable(){
        JLabel welcome = new JLabel("空调管理");
        welcome.setFont(new Font("宋体",Font.BOLD,20));
        MyLayout.setOne(baseBox,20,10,20,welcome);
    }

    private void initButtonQuery(){
        MyLayout.setOne(baseBox,20,10,20,buttonQuery);
    }

    private  void initButtonAdd(){
        MyLayout.setOne(baseBox,10,10,20,buttonAdd);
    }

    private void initButtonChange(){
        MyLayout.setOne(baseBox,10,10,20,buttonChange);
    }

    private void addBox(){
        this.add(baseBox);
    }

    private void initAction(){
        QueryAction queryAction = new QueryAction(this.father);
        buttonQuery.addActionListener(queryAction);
        AddAction addAction = new AddAction(this.father);
        buttonAdd.addActionListener(addAction);
        ChangeAction changeAction = new ChangeAction(this.father);
        buttonChange.addActionListener(changeAction);
    }
}

class QueryAction implements ActionListener{
    JFrame frame =new JFrame();
    QueryAction(JFrame frame1){
        this.frame=frame1;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        OtherInterface test = new AirManagerQuery(frame);
        test.showInterface("dddd");
    }
}

class AddAction implements ActionListener{
    JFrame frame =new JFrame();
    AddAction(JFrame frame1){
        this.frame=frame1;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        OtherInterface test = new AirManagerAdd(frame);
        test.showInterface("dddd");
    }
}

class ChangeAction implements ActionListener{
    JFrame frame =new JFrame();
    ChangeAction(JFrame frame1){
        this.frame=frame1;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        OtherInterface test = new AirManagerChange(frame);
        test.showInterface("dddd");
    }
}