import javax.swing.*;

public class MyLayout {
    public static void setOne(Box baseBox,int top,int buttom,int left,JComponent one){
        baseBox.add(Box.createVerticalStrut(top));
        Box hBox = Box.createHorizontalBox();
        hBox.add(Box.createHorizontalStrut(left));
        hBox.add(one);
        baseBox.add(hBox);
        baseBox.add(Box.createVerticalStrut(buttom));
    }

    public static void setTwo(Box baseBox,int top,int buttom,int left,int space,JComponent one,JComponent two){
        baseBox.add(Box.createVerticalStrut(top));
        Box vBox = Box.createHorizontalBox();
        vBox.add(Box.createHorizontalStrut(left));
        vBox.add(one);
        vBox.add(Box.createHorizontalStrut(space));
        vBox.add(two);
        baseBox.add(vBox);
        baseBox.add(Box.createVerticalStrut(buttom));
    }
}
