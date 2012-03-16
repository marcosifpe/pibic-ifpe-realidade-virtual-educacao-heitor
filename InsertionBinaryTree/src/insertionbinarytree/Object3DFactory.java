/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package insertionbinarytree;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import java.awt.Color;
import java.awt.Font;
import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Font3D;
import javax.media.j3d.FontExtrusion;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.Shape3D;
import javax.media.j3d.TexCoordGeneration;
import javax.media.j3d.Text3D;
import javax.media.j3d.Texture;
import javax.media.j3d.Texture2D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

/**
 *
 * @author Heitor
 */
public class Object3DFactory {

    private static Object3DFactory instance;
    private static float leafSize = 0.03f;
    public static float yInitial = 0.5f, xInitial = InsertionBinaryTree.r*-7, zInitial = 0.0f;
    private float highlighterH = 0.1f;
    private float highlighterR = InsertionBinaryTree.r+0.02f;

    private Object3DFactory() {
    }

    public static synchronized Object3DFactory getInstance() {
        if (instance == null) {
            instance = new Object3DFactory();
        }
        return instance;
    }

    public TransformGroup getHighlighter(Color color){
        Transform3D tfHighlighter = new Transform3D();
        //Roda o eixo X para que o cilindro fique na posicao certa
        tfHighlighter.rotX(Math.toRadians(90));
        //Localizacao no nó root
        //tfHighlighter.setTranslation(new Vector3f(0.0f, Object3DFactory.yInitial, 0.0f));

        TransformGroup tgHighlighter = new TransformGroup(tfHighlighter);
        Cylinder cy = new Cylinder(highlighterR, highlighterH, createAppearance(color, true));

        tgHighlighter.addChild(cy);

        tgHighlighter.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        tgHighlighter.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        //------------
        Transform3D tf = new Transform3D();
        tf.setTranslation(new Vector3f(999, 999, 999));

        TransformGroup tg = new TransformGroup(tf);

        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        tg.addChild(tgHighlighter);
        //------------
        //return tgHighlighter;
        return tg;
    }

    public Node3D getNode3D() {
        Sphere sphere = new Sphere(InsertionBinaryTree.r, Sphere.GENERATE_NORMALS, 100, createAppearance(Color.BLACK, false));
        sphere.setCapability(Sphere.ENABLE_APPEARANCE_MODIFY);
        Transform3D tfNode = new Transform3D();
        tfNode.setTranslation(new Vector3f(0.0f, yInitial, 0.0f));
        TransformGroup tgNode = new TransformGroup(tfNode);
        tgNode.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
        tgNode.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
        tgNode.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
        tgNode.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tgNode.addChild(sphere);

        //-----------------

        Font3D font3d = new Font3D(new Font(Font.SANS_SERIF, Font.BOLD, 1), new FontExtrusion());
        Text3D text3d = new Text3D(font3d, "", new Point3f(0.0f, 0.0f, 0.0f));

        text3d.setCapability(Text3D.ALLOW_STRING_WRITE);
        text3d.setCapability(Text3D.ALLOW_STRING_READ);

        Shape3D textShape = new Shape3D(text3d);
        textShape.setAppearance(createAppearance(Color.WHITE, true));

        Transform3D tfText = new Transform3D();
        tfText.setScale(InsertionBinaryTree.r * 2 - 0.1f);
        tfText.setTranslation(new Vector3f(-InsertionBinaryTree.r / 2, -InsertionBinaryTree.r / 2, InsertionBinaryTree.r));
        TransformGroup tgText = new TransformGroup(tfText);

        tgText.addChild(textShape);
        tgNode.addChild(tgText);

        Node3D node = new Node3D();

        node.setText3D(text3d);
        node.setTgNode(tgNode);

        Transform3D tfLeftLeaf = new Transform3D();
        //tfLeftLeaf.setTranslation(new Vector3f(-InsertionBinaryTree.r*2, -InsertionBinaryTree.r*2, InsertionBinaryTree.r*2));
        //esconde a folha
        tfLeftLeaf.setTranslation(new Vector3f(999, 999, 999));
        TransformGroup tgLeftLeaf = new TransformGroup(tfLeftLeaf);
        tgLeftLeaf.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tgLeftLeaf.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        tgLeftLeaf.setTransform(tfLeftLeaf);
        Box leftLeaf = new Box(leafSize, leafSize, leafSize, createAppearance(Color.green, false));
        tgLeftLeaf.addChild(leftLeaf);

        
        Transform3D tfRightLeaf = new Transform3D();
        //tfRightLeaf.setTranslation(new Vector3f(InsertionBinaryTree.r*2, -InsertionBinaryTree.r*2, InsertionBinaryTree.r*2));
        tfRightLeaf.setTranslation(new Vector3f(999, 999, 999));
        TransformGroup tgRightLeaf = new TransformGroup(tfRightLeaf);
        tgRightLeaf.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        tgRightLeaf.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tgRightLeaf.setTransform(tfRightLeaf);
        Box rightLeaf = new Box(leafSize, leafSize, leafSize, createAppearance(Color.green, false));
        tgRightLeaf.addChild(rightLeaf);

        node.setLeftLeaf(tgLeftLeaf);
        node.setRightLeaf(tgRightLeaf);
        //node.getTgNode().addChild(tgLeftLeaf);
        //node.getTgNode().addChild(tgRightLeaf);

        node.hideNode(node);
        return node;
    }

    Appearance createAppearance(Color color, boolean isNumber) {
        Appearance app = new Appearance();
        app.setColoringAttributes(new ColoringAttributes(new Color3f(color), 1));

        if (!isNumber) {
            app.setTexture(createTexture("folha.png"));

        TexCoordGeneration tcg = new TexCoordGeneration(TexCoordGeneration.OBJECT_LINEAR,
                TexCoordGeneration.TEXTURE_COORDINATE_2);

        app.setTexCoordGeneration(tcg);
        }

        return app;
    }
    final int TAM = 1024;

    private Texture createTexture(String texture) {
        //Carrega o jpeg da textura
        TextureLoader loader = new TextureLoader(texture, null);
        //Armazena a imagem da textura, os parametros devem ser 2^x
        ImageComponent2D imgComp = loader.getScaledImage(TAM, TAM);
        //Cria a textura
        Texture2D tex2D = new Texture2D(Texture2D.BASE_LEVEL, Texture2D.RGB, imgComp.getWidth(), imgComp.getHeight());
        //Define a imagem usada pela textura
        tex2D.setImage(0, imgComp);
        return tex2D;
    }
}
