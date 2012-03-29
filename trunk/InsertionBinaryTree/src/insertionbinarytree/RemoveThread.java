/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package insertionbinarytree;

import java.awt.Dimension;
import javax.swing.JOptionPane;

/**
 *
 * @author Heitor Paceli Maranhao
 * email: heitorpaceli@gmail.com
 */
public class RemoveThread extends Thread {
    private final InsertionBinaryTree tree;

    public RemoveThread(String name, InsertionBinaryTree tree) {
        super(name);
        this.tree = tree;
    }

    @Override
    public void run() {
        remove();
    }

    private void remove() {
        try {
            tree.textPane.setPreferredSize(new Dimension(InsertionBinaryTree.TEXT_AREA_COLUMNS, InsertionBinaryTree.TEXT_AREA_ROWS));
            tree.textPane.setText(InsertionBinaryTree.DELETE_CODE);
            String numString = JOptionPane.showInputDialog(tree.textPane, "Qual elemento deseja Remover?");
            int num = Integer.parseInt(numString);
            //Insertiontree.remove(num);
            //tree.delete(num, tree.root);
            tree.delete(num);
            tree.clearHighlight(tree.textPane, InsertionBinaryTree.DELETE_CODE);
            tree.updateInsertButton();
        } finally {
            tree.updateConnections(tree.root, 0);
            tree.isRunning = false;
            tree.moveView(tree.viewX());
        }
    }
}
