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
public class SearchThread extends Thread {

    public SearchThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        search();
    }

    private void search() {
        try {
            InsertionBinaryTree.textPane.setPreferredSize(new Dimension(InsertionBinaryTree.TEXT_AREA_COLUMNS, InsertionBinaryTree.TEXT_AREA_ROWS));
            InsertionBinaryTree.textPane.setText(InsertionBinaryTree.SEARCH_CODE);
            String numString = JOptionPane.showInputDialog(InsertionBinaryTree.textPane, "Qual elemento deseja buscar?");
            int num = Integer.parseInt(numString);

            Node3D result = InsertionBinaryTree.search(num);

            String message = (result != null) ? "Valor encontrado" : "Valor não encontrado";
            JOptionPane.showMessageDialog(InsertionBinaryTree.textPane, message);
            InsertionBinaryTree.clearHighlight(InsertionBinaryTree.textPane, InsertionBinaryTree.SEARCH_CODE);
        } finally {
            InsertionBinaryTree.isRunning = false;
            //InsertionBinaryTree.moveView();
        }
    }
}
