/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package insertionbinarytree;

import java.awt.Dimension;
import java.util.ArrayList;
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
        Score score = null;
        try {
            tree.textPane.setPreferredSize(new Dimension(InsertionBinaryTree.TEXT_AREA_COLUMNS, InsertionBinaryTree.TEXT_AREA_ROWS));
            tree.textPane.setText(InsertionBinaryTree.DELETE_CODE);
            String numString = JOptionPane.showInputDialog(tree.textPane, "Qual elemento deseja Remover?");
            int num = Integer.parseInt(numString);
            tree.moveView(tree.viewX());
            score = tree.delete(num);
            //tree.root = tree.balance(tree.root);
            
            /*
            ArrayList<Node3D> allNodes = tree.root.getAllNodes();
            allNodes.add(0, tree.root);
            tree.root = null;
            tree.reinsert(allNodes);
            */
            
            tree.clearHighlight(tree.textPane, InsertionBinaryTree.DELETE_CODE);
            tree.updateInsertButton();
        } finally {
            tree.updateConnections(tree.root, 0);
            tree.moveView(tree.viewX());
            if(score != null){
                score.show(tree.textPane);
            }
            tree.isRunning = false;
        }
    }
}
