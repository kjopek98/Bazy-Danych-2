package WarstwaAplikacji;

import WarstwaBiznesowa.MenedzerBilety;
import WarstwaBiznesowa.MenedzerPolaczenia;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ZakupioneBilety {
    private JLabel zakupioneLabel;
    private JTable zakupioneBiletyTable;
    public JPanel zakupioneBiletyFrame;
    private JScrollPane scroll1;

    ZakupioneBilety() {
        DefaultTableModel model = new DefaultTableModel(new String[]{
                "ID", "Data_wylotu", "Miejsce_wylotu", "Miejsce_przylotu", "Numer_Siedzenia", "Bagaż", "Anulowanie"
        }, 0);
        zakupioneBiletyTable.setModel(model);

        scroll1.setViewportView(zakupioneBiletyTable);

        String[] dane = MenedzerPolaczenia.podajDaneZakupione();

        TableColumn bagazColumn = zakupioneBiletyTable.getColumnModel().getColumn(5);
        TableColumn zakupColumn = zakupioneBiletyTable.getColumnModel().getColumn(6);


        Action zakup = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                JTable listaPolaczenTable = (JTable) actionEvent.getSource();
                int modelRow = Integer.valueOf(actionEvent.getActionCommand());
                String[] info = new String[2];
                Object nrLotu = ((DefaultTableModel) listaPolaczenTable.getModel()).getValueAt(modelRow, 0);
                Object bagaz = ((DefaultTableModel) listaPolaczenTable.getModel()).getValueAt(modelRow, 5);
                info[0] = nrLotu.toString();
                info[1] = bagaz.toString();
                MenedzerBilety.anulujBilet(info);
                ((DefaultTableModel) listaPolaczenTable.getModel()).removeRow(modelRow);
            }

        };

        ButtonColumn buttonColumn = new ButtonColumn(zakupioneBiletyTable, zakup, 6);
        buttonColumn.setMnemonic(KeyEvent.VK_D);

        for (int i = 0; i < dane.length; i = i + 6) {
            model.addRow(new Object[]{
                    dane[i], dane[i + 1], dane[i + 2], dane[i + 3], dane[i + 4], dane[i + 5], "ANULUJ"
            });
        }

    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        zakupioneBiletyFrame = new JPanel();
        zakupioneBiletyFrame.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        zakupioneLabel = new JLabel();
        zakupioneLabel.setText("Zakupione Bilety");
        zakupioneBiletyFrame.add(zakupioneLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        zakupioneBiletyTable = new JTable();
        zakupioneBiletyTable.setAutoCreateRowSorter(false);
        zakupioneBiletyTable.setAutoResizeMode(4);
        zakupioneBiletyFrame.add(zakupioneBiletyTable, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        scroll1 = new JScrollPane();
        zakupioneBiletyFrame.add(scroll1, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return zakupioneBiletyFrame;
    }

}
