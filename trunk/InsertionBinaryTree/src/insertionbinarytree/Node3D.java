/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package insertionbinarytree;

import java.util.ArrayList;
import javax.media.j3d.Text3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

/**
 *
 * @author Heitor
 */
public class Node3D {

    private int value;
    private TransformGroup tgNode;
    private Node3D left, right;
    private Text3D text3d;
    private boolean autoUpdateText = false;
    private TransformGroup LEFT_LEAF, RIGHT_LEAF;
    private TransformGroup R_CON[], L_CON[];
    private Transform3D R_CON_TF[], L_CON_TF[];
    private Node3D parent;
    private Transform3D tfHide;

    public Node3D() {
        R_CON = new TransformGroup[InsertionBinaryTree.H_MAX];
        L_CON = new TransformGroup[InsertionBinaryTree.H_MAX];
        L_CON_TF = new Transform3D[InsertionBinaryTree.H_MAX];
        R_CON_TF = new Transform3D[InsertionBinaryTree.H_MAX];
        tfHide = new Transform3D();
        tfHide.setTranslation(new Vector3f(999, 999, 999));
    }

    public void addRCon(int index, TransformGroup tg) {
        R_CON[index] = tg;
    }

    public void addLCon(int index, TransformGroup tg) {
        L_CON[index] = tg;
    }

    public void addRConTf(int index, Transform3D tf) {
        R_CON_TF[index] = tf;
    }

    public void addLConTf(int index, Transform3D tf) {
        L_CON_TF[index] = tf;
    }

    public TransformGroup[] getL_CON() {
        return L_CON;
    }

    public TransformGroup[] getR_CON() {
        return R_CON;
    }

    public void showRConnection(int i) {
        if (i >= 0 && i < R_CON.length) {
            R_CON[i].setTransform(R_CON_TF[i]);
        }
    }
    
    public void showLConnection(int i) {
        if (i >= 0 && i < L_CON.length) {
            L_CON[i].setTransform(L_CON_TF[i]);
        }
    }
    
    public void hideLConnection(){
        for(TransformGroup t : L_CON){
            t.setTransform(tfHide);
        }
    }
    
    public void hideRConnection(){
        for(TransformGroup t : R_CON){
            t.setTransform(tfHide);
        }
    }

    private Vector3d getCoordinate() {
        Vector3d v = new Vector3d();
        getTfNode().get(v);
        return v;
    }

    public Node3D getParent() {
        return parent;
    }

    public void setParent(Node3D parent) {
        this.parent = parent;
    }

    public void hideLeftLeaf() {
        Transform3D tfLeftLeaf = new Transform3D();
        tfLeftLeaf.setTranslation(new Vector3f(999, 999, 999));
        LEFT_LEAF.setTransform(tfLeftLeaf);
    }

    public void hideRightLeaf() {
        Transform3D tfRightLeaf = new Transform3D();
        tfRightLeaf.setTranslation(new Vector3f(999, 999, 999));
        RIGHT_LEAF.setTransform(tfRightLeaf);
    }

    public void showLeftLeaf() {
        if (LEFT_LEAF != null) {
            Transform3D tfLeftLeaf = new Transform3D();
            tfLeftLeaf.setTranslation(new Vector3f(-InsertionBinaryTree.r * 2, -InsertionBinaryTree.r * 2, InsertionBinaryTree.r * 2));
            LEFT_LEAF.setTransform(tfLeftLeaf);
        }
    }

    void showRightLeaf() {
        if (RIGHT_LEAF != null) {
            Transform3D tfRightLeaf = new Transform3D();
            tfRightLeaf.setTranslation(new Vector3f(InsertionBinaryTree.r * 2, -InsertionBinaryTree.r * 2, InsertionBinaryTree.r * 2));
            RIGHT_LEAF.setTransform(tfRightLeaf);
        }
    }

    public void setLeftLeaf(TransformGroup left) {
        tgNode.removeChild(LEFT_LEAF);
        LEFT_LEAF = left;
        tgNode.addChild(LEFT_LEAF);
    }

    public void setRightLeaf(TransformGroup right) {
        tgNode.removeChild(RIGHT_LEAF);
        RIGHT_LEAF = right;
        tgNode.addChild(RIGHT_LEAF);
    }

    public TransformGroup getLeftLeaf() {
        return LEFT_LEAF;
    }

    public TransformGroup getRightLeaf() {
        return RIGHT_LEAF;
    }

    public Transform3D getTfNode() {
        Transform3D tfNode = new Transform3D();
        tgNode.getTransform(tfNode);
        return tfNode;
    }

    public TransformGroup getTgNode() {
        return tgNode;
    }

    public void setTgNode(TransformGroup tgNode) {
        this.tgNode = tgNode;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        if (autoUpdateText) {
            this.setText("" + value);
        }
    }

    public void setAutoUpdateText(boolean autoUpdateText) {
        this.autoUpdateText = autoUpdateText;
    }

    public boolean isAutoUpdateText() {
        return autoUpdateText;
    }

    public Node3D getLeft() {
        return left;
    }

    public void setLeft(Node3D node) {
        this.left = node;
    }

    public Node3D getRight() {
        return right;
    }

    public void setRight(Node3D node) {
        this.right = node;
    }

    protected Text3D getText3D() {
        return text3d;
    }

    protected void setText3D(Text3D text3D) {
        this.text3d = text3D;
    }

    public void setText(String text) {
        text3d.setString(text);
    }

    //
    public boolean remove(int value, Node3D parent) {

        InsertionBinaryTree.highlightsText(InsertionBinaryTree.REMOVE2, InsertionBinaryTree.COMPLETE_REMOVE);
        InsertionBinaryTree.highlightMov(this, InsertionBinaryTree.searchHighlighter);
        InsertionBinaryTree.highlightsText(InsertionBinaryTree.REMOVE2_LOWER, InsertionBinaryTree.COMPLETE_REMOVE);

        if (value < this.value) {

            InsertionBinaryTree.highlightsText(InsertionBinaryTree.REMOVE2_L_LEAF, InsertionBinaryTree.COMPLETE_REMOVE);

            //testa se o proximo no não é folha
            if (left != null) {

                InsertionBinaryTree.highlightsText(InsertionBinaryTree.REMOVE2_RETURN_REMOVE, InsertionBinaryTree.COMPLETE_REMOVE);

                return left.remove(value, this);
            } else {

                InsertionBinaryTree.highlightsText(InsertionBinaryTree.REMOVE2_ELSE, InsertionBinaryTree.COMPLETE_REMOVE);
                InsertionBinaryTree.highlightsText(InsertionBinaryTree.REMOVE2_FALSE, InsertionBinaryTree.COMPLETE_REMOVE);

                return false;
            }
        } else if (value > this.value) {

            InsertionBinaryTree.highlightsText(InsertionBinaryTree.REMOVE2_GREATER, InsertionBinaryTree.COMPLETE_REMOVE);
            InsertionBinaryTree.highlightsText(InsertionBinaryTree.REMOVE2_R_LEAF, InsertionBinaryTree.COMPLETE_REMOVE);

            //testa se o proximo no não é folha
            if (right != null) {

                InsertionBinaryTree.highlightsText(InsertionBinaryTree.REMOVE2_RETURN_REMOVE2, InsertionBinaryTree.COMPLETE_REMOVE);

                return right.remove(value, this);
            } else {

                InsertionBinaryTree.highlightsText(InsertionBinaryTree.REMOVE2_ELSE2, InsertionBinaryTree.COMPLETE_REMOVE);
                InsertionBinaryTree.highlightsText(InsertionBinaryTree.REMOVE2_FALSE2, InsertionBinaryTree.COMPLETE_REMOVE);

                return false;
            }
        }//Se encontrou o valor
        else {

            InsertionBinaryTree.highlightsText(InsertionBinaryTree.REMOVE2_GREATER, InsertionBinaryTree.COMPLETE_REMOVE);
            InsertionBinaryTree.highlightsText(InsertionBinaryTree.REMOVE2_ELSE3, InsertionBinaryTree.COMPLETE_REMOVE);
            InsertionBinaryTree.highlightsText(InsertionBinaryTree.REMOVE2_NO_LEAF, InsertionBinaryTree.COMPLETE_REMOVE);

            //Testa se os 2 nós filhos não são folhas
            if (left != null && right != null) {

                InsertionBinaryTree.highlightsText(InsertionBinaryTree.REMOVE2_MIN, InsertionBinaryTree.COMPLETE_REMOVE);
                //Usa menor dos maiores
                moveSubstitute();
                InsertionBinaryTree.highlightsText(InsertionBinaryTree.REMOVE2_REMOVE_MIN, InsertionBinaryTree.COMPLETE_REMOVE);
            } else if (parent.left == this) {  //Um ou 2 dos filhos sempre vai ser nulo

                InsertionBinaryTree.highlightsText(InsertionBinaryTree.REMOVE2_R_CHILD, InsertionBinaryTree.COMPLETE_REMOVE);
                InsertionBinaryTree.highlightsText(InsertionBinaryTree.REMOVE2_P_LEFT, InsertionBinaryTree.COMPLETE_REMOVE);

                //Pega todos os nós para inserir de novo para ficar na posição certa
                ArrayList<Node3D> nodes = getAllNodes(this);

                parent.left = (left != null) ? left : right;
                //Destaca o no
                InsertionBinaryTree.highlightMov(parent.left, InsertionBinaryTree.removeHighlighter);

                hideNode(this);
                InsertionBinaryTree.nodes.add(this);

                //Chama o método para inserir
                InsertionBinaryTree.reinsert(nodes);
            } else if (parent.right == this) {

                InsertionBinaryTree.highlightsText(InsertionBinaryTree.REMOVE2_R_CHILD, InsertionBinaryTree.COMPLETE_REMOVE);
                InsertionBinaryTree.highlightsText(InsertionBinaryTree.REMOVE2_L_CHILD, InsertionBinaryTree.COMPLETE_REMOVE);
                InsertionBinaryTree.highlightsText(InsertionBinaryTree.REMOVE2_P_RIGHT, InsertionBinaryTree.COMPLETE_REMOVE);

                //Pega todos os nós para inserir de novo para ficar na posição certa
                ArrayList<Node3D> nodes = getAllNodes(this);

                parent.right = (left != null) ? left : right;
                InsertionBinaryTree.highlightMov(parent.right, InsertionBinaryTree.removeHighlighter);

                hideNode(this);
                InsertionBinaryTree.nodes.add(this);

                //Chama o método para inserir
                InsertionBinaryTree.reinsert(nodes);
            }
            InsertionBinaryTree.highlightMov(null, InsertionBinaryTree.removeHighlighter);

            //Mostra as folhas
            if (parent.left == null) {
                InsertionBinaryTree.sleep(InsertionBinaryTree.SLEEP_TIME * 3);
                parent.showLeftLeaf();
            }
            if (parent.right == null) {
                InsertionBinaryTree.sleep(InsertionBinaryTree.SLEEP_TIME * 3);
                parent.showRightLeaf();
            }
            InsertionBinaryTree.highlightsText(InsertionBinaryTree.REMOVE2_TRUE, InsertionBinaryTree.COMPLETE_REMOVE);
            return true;
        }
    }

    public Node3D minNode() {
        if (left == null) {
            return this;
        } else {
            return left.minNode();
        }
    }

    private void moveSubstitute() {
        this.setAutoUpdateText(true);
        Node3D min = right.minNode();
        this.setValue(min.value);

        //Pega todos os nós para inserir de novo para ficar na posição certa
        ArrayList<Node3D> nodes = getAllNodes(min);

        hideNode(min);
        //Remove o nó da árvore
        if (min.parent.left == min) {
            min.parent.left = null;
            min.parent.showLeftLeaf();
        }
        if (min.parent.right == min) {
            min.parent.right = null;
            min.parent.showRightLeaf();
        }

        InsertionBinaryTree.nodes.add(min);

        //InsertionBinaryTree.resetNode(min);
        //Chama o método para inserir
        InsertionBinaryTree.reinsert(nodes);
    }

    public ArrayList<Node3D> getAllNodes(Node3D node) {
        ArrayList<Node3D> nodes = new ArrayList<Node3D>();
        getAllNodes(node, nodes);

        //Posiciona os nós na posição inicial, para serem inseridos novamente
        for (Node3D temp : nodes) {
            Transform3D tf = new Transform3D();
            tf.setTranslation(new Vector3f(0.0f, Object3DFactory.yInitial, 0.0f));
            temp.getTgNode().setTransform(tf);
        }

        return nodes;
    }

    public void getAllNodes(Node3D node, ArrayList<Node3D> nodes) {
        if (node != null) {
            if (node.left != null) {
                nodes.add(node.left);
            }
            if (node.right != null) {
                nodes.add(node.right);
            }
            getAllNodes(node.left, nodes);
            getAllNodes(node.right, nodes);
            //node.left = null;
            //node.right = null;
        }
    }

    public void hideNode(Node3D node) {
        Transform3D tfHide = new Transform3D();
        tfHide.setTranslation(new Vector3f(999, 999, 999));

        node.getTgNode().setTransform(tfHide);
    }

    void moveToPosition(double x, double y, double z) {
        Transform3D tf = new Transform3D();
        tf.setTranslation(new Vector3d(x, y, z));

        this.getTgNode().setTransform(tf);
    }
}
