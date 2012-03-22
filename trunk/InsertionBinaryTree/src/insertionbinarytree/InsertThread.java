/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package insertionbinarytree;

import java.awt.Dimension;
import javax.swing.JOptionPane;

/**
 *
 * @author heitor
 */
public class InsertThread extends Thread {

    public InsertThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        insert();
    }

    private void insert() {
        try {
            InsertionBinaryTree.moveView(InsertionBinaryTree.viewX() + 1);
            
            InsertionBinaryTree.textPane.setPreferredSize(new Dimension(InsertionBinaryTree.TEXT_AREA_COLUMNS, InsertionBinaryTree.TEXT_AREA_ROWS));
            InsertionBinaryTree.textPane.setText(InsertionBinaryTree.INSERT_CODE);
            String numString = JOptionPane.showInputDialog(InsertionBinaryTree.textPane, "Qual elemento deseja inserir?");
            int num = Integer.parseInt(numString);


            int h = InsertionBinaryTree.prevNodeHeight(num);
            
            int hMax = InsertionBinaryTree.H_MAX+1;
            if (h > hMax) {
                JOptionPane.showMessageDialog(InsertionBinaryTree.textPane, "Por questões de visualização, "
                        + "não é permitido\n que a altura seja maior que " + hMax, "Altura máxima", JOptionPane.INFORMATION_MESSAGE);
            } else {

                InsertionBinaryTree.insertValue(num);
                InsertionBinaryTree.clearHighlight(InsertionBinaryTree.textPane, InsertionBinaryTree.INSERT_CODE);
                InsertionBinaryTree.updateInsertButton();
            }
        } finally {
            InsertionBinaryTree.isRunning = false;
            InsertionBinaryTree.moveView(InsertionBinaryTree.viewX());
        }

    }
}
