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
public class RemoveThread extends Thread {

    public RemoveThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        remove();
    }

    private void remove() {
        try {
            InsertionBinaryTree.textPane.setPreferredSize(new Dimension(InsertionBinaryTree.TEXT_AREA_COLUMNS, InsertionBinaryTree.TEXT_AREA_ROWS));
            InsertionBinaryTree.textPane.setText(InsertionBinaryTree.DELETE_CODE);
            String numString = JOptionPane.showInputDialog(InsertionBinaryTree.textPane, "Qual elemento deseja Remover?");
            int num = Integer.parseInt(numString);
            //InsertionBinaryTree.remove(num);
            InsertionBinaryTree.delete(num, InsertionBinaryTree.root);
            InsertionBinaryTree.clearHighlight(InsertionBinaryTree.textPane, InsertionBinaryTree.DELETE_CODE);
            InsertionBinaryTree.updateInsertButton();
            System.out.println("Vai chamar");
            InsertionBinaryTree.updateConnections(InsertionBinaryTree.root);
            System.out.println("chamou");
        } finally {
            InsertionBinaryTree.isRunning = false;
            InsertionBinaryTree.moveView(InsertionBinaryTree.viewX());
        }
    }
}
