package WarstwaAplikacji;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Start {
    public JPanel Start;
    private JLabel startLabel1;
    private JButton logowanie;
    private JButton zalozKonto;
    public static JFrame frameLogowanie;
    public static JFrame framezalozKonto;


    public Start() {
        $$$setupUI$$$();
        logowanie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frameLogowanie = new JFrame("Logowanie");
                frameLogowanie.setContentPane(new Logowanie().loginFrame);
                frameLogowanie.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frameLogowanie.pack();
                frameLogowanie.setVisible(true);
            }
        });
        zalozKonto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                framezalozKonto = new JFrame("Zakładanie Konta");
                framezalozKonto.setContentPane(new ZakladanieKonta().zakladanieKonta);
                framezalozKonto.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                framezalozKonto.pack();
                framezalozKonto.setVisible(true);
            }
        });
    }


    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        Start = new JPanel();
        Start.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        startLabel1 = new JLabel();
        startLabel1.setText("Start");
        Start.add(startLabel1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        logowanie = new JButton();
        logowanie.setText("Logowanie");
        Start.add(logowanie, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        zalozKonto = new JButton();
        zalozKonto.setText("Załóż Konto");
        Start.add(zalozKonto, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return Start;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
