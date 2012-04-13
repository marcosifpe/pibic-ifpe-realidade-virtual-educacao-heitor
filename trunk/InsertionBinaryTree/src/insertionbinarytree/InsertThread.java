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
public class InsertThread extends Thread {
    private final InsertionBinaryTree tree;

    public InsertThread(String name,InsertionBinaryTree tree) {
        super(name);
        this.tree = tree;
    }

    @Override
    public void run() {
        insert();
    }

    private void insert() {
        Score score = null;
        try {
            //InsertionBinaryTree.moveView(InsertionBinaryTree.viewX() + 1);
            
            tree.textPane.setPreferredSize(new Dimension(InsertionBinaryTree.TEXT_AREA_COLUMNS, InsertionBinaryTree.TEXT_AREA_ROWS));
            tree.textPane.setText(InsertionBinaryTree.INSERT_CODE);
            String numString = JOptionPane.showInputDialog(tree.textPane, "Qual elemento deseja inserir?");
            int num = Integer.parseInt(numString);
            
            
            int h = tree.prevNodeHeight(num);
            
            int hMax = InsertionBinaryTree.H_MAX+1;
            if (h > hMax) {
                JOptionPane.showMessageDialog(tree.textPane, "Por questões de visualização, "
                        + "não é permitido\n que a altura seja maior que " + hMax, "Altura máxima", JOptionPane.INFORMATION_MESSAGE);
            } else {
                tree.moveView(tree.prevViewX(num));
                score = tree.insertValue(num);
                tree.root = tree.balance(tree.root);
                tree.clearHighlight(tree.textPane, InsertionBinaryTree.INSERT_CODE);
                tree.updateInsertButton();
            }
        } finally {
            ArrayList<Node3D> allNodes = tree.root.getAllNodes();
            allNodes.add(0, tree.root);
            tree.root = null;
            tree.reinsert(allNodes);
            
            
            tree.updateConnections(tree.root, 0);
            tree.moveView(tree.viewX());
            if(score!=null){
                score.show(tree.textPane);
            }
            tree.isRunning = false;
        }
    }
}
