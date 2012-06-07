package insertionbinarytree;

import java.awt.Dimension;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
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
            if(numString == null){
                return;
            }
            int num = Integer.parseInt(numString);
            tree.moveView(tree.viewX());
            score = tree.delete(num);
//            
//            if (tree.isAVL()) {
//                int h = tree.getHBinaryTree();
//                final int H = 2;
//                if(h==H){
//                    tree.root = tree.balance(tree.root);
//                    tree.root.setParent(null);
//                }else if(h>H){
//                    Node3D right = tree.balance(tree.root.getRight());
//                    if(right != null) {
//                        right.setParent(tree.root);
//                    }
//                    tree.root.setRight(right);
//                    
//                    Node3D left = tree.balance(tree.root.getLeft());
//                    if(left != null){
//                        left.setParent(tree.root);
//                    }
//                    tree.root.setLeft(left);
//                }
//            }
            
            if (tree.isAVL()) {
                tree.textPane.setText(InsertionBinaryTree.AVL_CODE);
                int h = tree.getHBinaryTree();
                final int H = 2;
                Node3D n = null;
                int direction = tree.LEFT;
                if (h == H) {
//                    JOptionPane.showMessageDialog(null, "1");
//                    tree.root = tree.balance(tree.root);
//                    tree.root.setParent(null);
                } else if (h > H) {
                    n = tree.root.getRight();
                    Node3D left = tree.balance(tree.root.getLeft(), score);
                    left.setParent(tree.root);
                    tree.root.setLeft(left);
                    direction = tree.RIGTH;
                
                    n = tree.root.getLeft();
                    Node3D right = tree.balance(tree.root.getRight(), score);
                    right.setParent(tree.root);
                    tree.root.setRight(right);
                    direction = tree.LEFT;
                }
                tree.root = tree.balance(tree.root, score);
                tree.root.setParent(null);
                if (n != null && tree.getHBinaryTree() == 3) {
                    TransformGroup tgTemp = new TransformGroup(n.getTfNode());
                    float d = (n == tree.root) ? tree.DISTANCE / 5 : (tree.DISTANCE / 2) / 5;
                    Transform3D tf = tree.insert3D(null, direction, d, true, tgTemp);
                    n.getTgNode().setTransform(tf);
                }
                tree.textPane.setText(InsertionBinaryTree.INSERT_CODE);
                tree.moveView(tree.viewX());
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
