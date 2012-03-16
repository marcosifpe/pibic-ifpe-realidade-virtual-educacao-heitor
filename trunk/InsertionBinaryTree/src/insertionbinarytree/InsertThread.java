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
            InsertionBinaryTree.moveView(InsertionBinaryTree.viewX()+1);
            InsertionBinaryTree.textPane.setPreferredSize(new Dimension(InsertionBinaryTree.TEXT_AREA_COLUMNS, InsertionBinaryTree.TEXT_AREA_ROWS));
            InsertionBinaryTree.textPane.setText(InsertionBinaryTree.INSERT_CODE);
            String numString = JOptionPane.showInputDialog(InsertionBinaryTree.textPane, "Qual elemento deseja inserir?");
            int num = Integer.parseInt(numString);
            InsertionBinaryTree.insertValue(num);
            InsertionBinaryTree.clearHighlight(InsertionBinaryTree.textPane, InsertionBinaryTree.INSERT_CODE);
            InsertionBinaryTree.updateInsertButton();

        } finally {
            InsertionBinaryTree.isRunning = false;
            InsertionBinaryTree.moveView(InsertionBinaryTree.viewX());
        }

    }
}
