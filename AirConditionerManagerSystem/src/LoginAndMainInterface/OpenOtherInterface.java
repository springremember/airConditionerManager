package LoginAndMainInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 调用显示功能界面
public class OpenOtherInterface implements ActionListener {
    String id;
    OtherInterface doThings;

    OpenOtherInterface(String id, OtherInterface doThings){
        this.id = id;
        this.doThings = doThings;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doThings.showInterface(id);
    }
}
