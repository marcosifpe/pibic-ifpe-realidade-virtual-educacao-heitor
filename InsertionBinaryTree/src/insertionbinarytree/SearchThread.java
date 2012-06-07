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
public class SearchThread extends Thread {
    private final InsertionBinaryTree tree;

    public SearchThread(String name, InsertionBinaryTree tree) {
        super(name);
        this.tree = tree;
    }

    @Override
    public void run() {
        search();
    }

    private void search() {
        Score score = null;
        try {
            tree.textPane.setPreferredSize(new Dimension(InsertionBinaryTree.TEXT_AREA_COLUMNS, InsertionBinaryTree.TEXT_AREA_ROWS));
            tree.textPane.setText(InsertionBinaryTree.SEARCH_CODE);
            String numString = JOptionPane.showInputDialog(tree.textPane, "Qual elemento deseja buscar?");
            if(numString == null){
                return;
            }
            int num = Integer.parseInt(numString);
            tree.moveView(tree.viewX());
            score = tree.search(num);

            
        } finally {
            tree.clearHighlight(tree.textPane, InsertionBinaryTree.SEARCH_CODE);
            tree.moveView(tree.viewX());
            if(score != null){
                score.show(tree.textPane);
            }
            tree.isRunning = false;
        }
    }
}
