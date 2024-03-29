package insertionbinarytree;

import java.awt.Dimension;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JOptionPane;

/**
 *
 * @author Heitor Paceli Maranhao email: heitorpaceli@gmail.com
 */
public class InsertThread extends Thread {

    private final InsertionBinaryTree tree;

    public InsertThread(String name, InsertionBinaryTree tree) {
        super(name);
        this.tree = tree;
    }

    @Override
    public void run() {
        insert();
    }

    private void insert() {
        Score score = null;
        int num = 0;
        try {
            //InsertionBinaryTree.moveView(InsertionBinaryTree.viewX() + 1);

            tree.textPane.setPreferredSize(new Dimension(InsertionBinaryTree.TEXT_AREA_COLUMNS, InsertionBinaryTree.TEXT_AREA_ROWS));
            tree.textPane.setText(InsertionBinaryTree.INSERT_CODE);
            String numString = JOptionPane.showInputDialog(tree.textPane, "Qual elemento deseja inserir?");
            if(numString == null){
                return;
            }
            
            num = Integer.parseInt(numString);


            int h = tree.prevNodeHeight(num);

            int hMax = InsertionBinaryTree.H_MAX + 1;
            if (h > hMax) {
                JOptionPane.showMessageDialog(tree.textPane, "Por questões de visualização, "
                        + "não é permitido\n que a altura seja maior que " + hMax, "Altura máxima", JOptionPane.INFORMATION_MESSAGE);
            } else {
                tree.moveView(tree.prevViewX(num));
                score = tree.insertValue(num);

            }
            
            
            
            
            tree.updateConnections(tree.root, 0);
            tree.moveView(tree.viewX());
            if (tree.isAVL()) {
                tree.textPane.setText(InsertionBinaryTree.AVL_CODE);
                int height = tree.getHBinaryTree();
                final int H = 2;
                Node3D n = null;
                int direction = tree.LEFT;
                if (height == H) {
//                    JOptionPane.showMessageDialog(null, "1");
//                    tree.root = tree.balance(tree.root);
//                    tree.root.setParent(null);
                } else if (num < tree.root.getValue() && height > H) {
                    n = tree.root.getRight();
                    Node3D left = tree.balance(tree.root.getLeft(), score);
                    left.setParent(tree.root);
                    tree.root.setLeft(left);
                    direction = tree.RIGTH;
                } else if (height > H) {
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
        } finally {
            
            tree.moveView(tree.viewX());
            tree.clearHighlight(tree.textPane, InsertionBinaryTree.INSERT_CODE);
            tree.updateInsertButton();

            tree.updateConnections(tree.root, 0);
            if (score != null) {
                score.show(tree.textPane);
            }
            tree.getVars().setText("");
            tree.isRunning = false;
        }
    }
}
