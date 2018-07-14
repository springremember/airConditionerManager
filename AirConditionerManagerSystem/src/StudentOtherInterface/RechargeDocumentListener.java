package StudentOtherInterface;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class RechargeDocumentListener implements DocumentListener {
    private JTextField inputMoney;
    private JButton submit;

    public RechargeDocumentListener(JTextField inputMoney, JButton submit) {
        this.inputMoney = inputMoney;
        this.submit = submit;
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        if (!inputMoney.getText().isEmpty()){
            submit.setEnabled(true);
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        changedUpdate(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        changedUpdate(e);
    }
}
