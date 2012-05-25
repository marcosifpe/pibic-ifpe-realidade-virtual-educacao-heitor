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
        Score score = null;
        try {
            tree.textPane.setPreferredSize(new Dimension(InsertionBinaryTree.TEXT_AREA_COLUMNS, InsertionBinaryTree.TEXT_AREA_ROWS));
            tree.textPane.setText(InsertionBinaryTree.DELETE_CODE);
            String numString = JOptionPane.showInputDialog(tree.textPane, "Qual elemento deseja Remover?");
            int num = Integer.parseInt(numString);
            tree.moveView(tree.viewX());
            score = tree.delete(num);
            
            
            if (tree.isAVL()) {
                int h = tree.getHBinaryTree();
                final int H = 2;
                if(h==H){
                    tree.root = tree.balance(tree.root);
                    tree.root.setParent(null);
                }else if(h>H){
                    Node3D right = tree.balance(tree.root.getRight());
                    if(right != null) {
                        right.setParent(tree.root);
                    }
                    tree.root.setRight(right);
                    
                    Node3D left = tree.balance(tree.root.getLeft());
                    if(left != null){
                        left.setParent(tree.root);
                    }
                    tree.root.setLeft(left);
                }
            }
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
