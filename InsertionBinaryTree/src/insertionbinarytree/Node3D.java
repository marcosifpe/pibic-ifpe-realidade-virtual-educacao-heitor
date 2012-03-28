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
 * @author Heitor Paceli Maranhao
 * email: heitorpaceli@gmail.com
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
    public final int NUM_CONNECTIONS = InsertionBinaryTree.H_MAX+1;

    public Node3D() {
        
        R_CON = new TransformGroup[NUM_CONNECTIONS];
        L_CON = new TransformGroup[NUM_CONNECTIONS];
        L_CON_TF = new Transform3D[NUM_CONNECTIONS];
        R_CON_TF = new Transform3D[NUM_CONNECTIONS];
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
        /*
        Transform3D tfLeftLeaf = new Transform3D();
        tfLeftLeaf.setTranslation(new Vector3f(999, 999, 999));
        LEFT_LEAF.setTransform(tfLeftLeaf);
        * 
        */
    }

    public void hideRightLeaf() {
        /*
        Transform3D tfRightLeaf = new Transform3D();
        tfRightLeaf.setTranslation(new Vector3f(999, 999, 999));
        RIGHT_LEAF.setTransform(tfRightLeaf);
        * 
        */
    }

    public void showLeftLeaf() {
        if (LEFT_LEAF != null) {
            Transform3D tfLeftLeaf = new Transform3D();
            tfLeftLeaf.setTranslation(new Vector3f(-InsertionBinaryTree.r * 2-Object3DFactory.leafSize/2, -InsertionBinaryTree.r * 2, InsertionBinaryTree.r * 2));
            LEFT_LEAF.setTransform(tfLeftLeaf);
        }
    }

    public void showRightLeaf() {
        if (RIGHT_LEAF != null) {
            Transform3D tfRightLeaf = new Transform3D();
            tfRightLeaf.setTranslation(new Vector3f(InsertionBinaryTree.r * 2+Object3DFactory.leafSize, -InsertionBinaryTree.r * 2, InsertionBinaryTree.r * 2));
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

    public Node3D minNode() {
        if (left == null) {
            return this;
        } else {
            return left.minNode();
        }
    }
    
    public Node3D maxNode() {
        if (right == null) {
            return this;
        } else {
            return right.maxNode();
        }
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

    public void moveToPosition(double x, double y, double z) {
        Transform3D tf = new Transform3D();
        tf.setTranslation(new Vector3d(x, y, z));

        this.getTgNode().setTransform(tf);
    }
}
