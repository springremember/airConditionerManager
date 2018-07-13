import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Manager extends JFrame implements MainInterface{
    String manager_id;
    Box baseBox = Box.createVerticalBox();
    JButton number = new JButton("待处理的维修数");
    JButton airConditionerManager = new JButton("空调管理");
    OtherInterface interfaceAirConditionManager;
    JButton airConditionerFix = new JButton("空调维修");
    OtherInterface interfaceAirConditionerFix;
    JButton userManager = new JButton("用户管理");
    OtherInterface interfaceUserManager;
    JButton powerControl = new JButton("用电管理");
    OtherInterface interfacePowerControl;
    JButton allowances = new JButton("空调补贴");
    OtherInterface interFaceAllowances;

    public void setOtherInterface(OtherInterface interfaceAirConditionManager,OtherInterface interfaceAirConditionerFix,OtherInterface interfaceUserManager,OtherInterface interfacePowerControl,OtherInterface interFaceAllowances){
        this.interfaceAirConditionManager = interfaceAirConditionManager;
        this.interfaceAirConditionerFix = interfaceAirConditionerFix;
        this.interfaceUserManager = interfaceUserManager;
        this.interfacePowerControl = interfacePowerControl;
        this.interFaceAllowances = interFaceAllowances;
    }

    Manager(String manager_id){
        this.manager_id = manager_id;
    }

    Manager(){}

    // 布局函数
    private void set(){
        initButton();
        setAirConditionerManager();
        setAirConditionerFix();
        setUserManager();
        setPowerControl();
        setAllowances();
        setBox();
    }

    private void initButton(){
        MyLayout.setOne(baseBox,40,40,20,number);
        number.setFont(new Font("宋体",Font.BOLD,15));
        number.addActionListener(new ShowRepairDetail(this));
    }

    private void setAirConditionerManager(){
        MyLayout.setOne(baseBox,10,20,20,airConditionerManager);
        airConditionerManager.setFont(new Font("宋体",Font.BOLD,15));
        airConditionerManager.addActionListener(new OpenOtherInterface(manager_id,interfaceAirConditionManager));
    }

    private void setAirConditionerFix() {
        MyLayout.setOne(baseBox,0,20,20,airConditionerFix);
        airConditionerFix.setFont(new Font("宋体",Font.BOLD,15));
        airConditionerFix.addActionListener(new OpenOtherInterface(manager_id,interfaceAirConditionerFix));
    }

    private void setUserManager() {
        MyLayout.setOne(baseBox,0,20,20,userManager);
        userManager.setFont(new Font("宋体",Font.BOLD,15));
        userManager.addActionListener(new OpenOtherInterface(manager_id,interfaceUserManager));
    }

    private void setPowerControl(){
        MyLayout.setOne(baseBox,0,20,20,powerControl);
        powerControl.setFont(new Font("宋体",Font.BOLD,15));
        powerControl.addActionListener(new OpenOtherInterface(manager_id,interfacePowerControl));
    }

    private void setAllowances(){
        MyLayout.setOne(baseBox,0,20,20,allowances);
        allowances.setFont(new Font("宋体",Font.BOLD,15));
        allowances.addActionListener(new OpenOtherInterface(manager_id,interFaceAllowances));
    }

    private void setBox(){
        this.add(baseBox);
    }

    // 定时器函数
    public void changeBytime(){
        number.setText("待处理的维修数：" + RepairObject.getByRepairStatus(false).size());
        ArrayList<RepairObject> infoList = RepairObject.getByInforStatus(2);
        if (infoList.size() != 0){
            JDialog info = new JDialog(this,"维修通知",true);
            info.setBounds(100,100,300,300);
            info.setLayout(new FlowLayout());
            Box infoBox = Box.createVerticalBox();
            JLabel infoWelcome = new JLabel("新的维修通知");
            infoWelcome.setFont(new Font("宋体",Font.BOLD,20));
            MyLayout.setOne(infoBox,50,30,20,infoWelcome);
            JTextArea showInfo = new JTextArea();
            String content = "";
            for (RepairObject one :
                    infoList) {
                content += one.dorm_id + "\n";
                one.setInfor_status(3);
            }
            showInfo.setText(content);
            showInfo.setEnabled(false);
            MyLayout.setOne(infoBox,0,0,20,new JScrollPane(showInfo));
            info.add(infoBox);
            info.setVisible(true);
        }
    }

    private void setTimeChanger(){
        changeBytime();
        Timer t = new Timer(10000,new ChangeByTime(this));
        t.start();
    }

    //实现接口
    @Override
    public void setId(String manager_id) {
        this.manager_id = manager_id;
    }

    @Override
    public void showInterface() {
        // JFrame函数
        setTitle("主界面");
        setBounds(100,100,400,450);
        setLayout(new FlowLayout());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 设置布局
        set();
        // 设置定时器
        setTimeChanger();
        setVisible(true);
    }




}

class ChangeByTime implements ActionListener{
    private Manager father;

    ChangeByTime(Manager father){
        this.father = father;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        father.changeBytime();
    }
}

class ShowRepairDetail implements ActionListener{
    JFrame father;

    ShowRepairDetail(JFrame father){
        this.father = father;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        OtherInterface showRepair = new ShowRepair(father);
        showRepair.showInterface("无意义");
    }
}

class ShowRepair extends JDialog implements OtherInterface{
    @Override
    public void showInterface(String id) {
        setLayout(new FlowLayout());
        setBounds(100, 100, 400, 400);
        set();
        setVisible(true);
    }

    JFrame father;
    Box baseBox = Box.createVerticalBox();
    JTextArea showArea = new JTextArea();

    ShowRepair(JFrame father){
        super(father,"待维修详情",true);
        this.father = father;
    }

    private void set(){
        initLable();
        initArea();
        add(baseBox);
    }

    private void initLable(){
        JLabel welcome = new JLabel("维修详情");
        welcome.setFont(new Font("宋体",Font.BOLD,20));
        MyLayout.setOne(baseBox,50,20,20,welcome);
    }

    private void initArea(){
        ArrayList<RepairObject> repairList = RepairObject.getByRepairStatus(false);
        String showStr = "";
        for (RepairObject one :
                repairList) {
            showStr += "维修号:" + one.repair_id + "\t" + "维修原因:" + one.reason;
            showStr += "\n";
        }
        showArea.setText(showStr);
        showArea.setEnabled(false);
        showArea.setFont(new Font("宋体",Font.BOLD,15));
        MyLayout.setOne(baseBox,30,0,20,showArea);
    }
}