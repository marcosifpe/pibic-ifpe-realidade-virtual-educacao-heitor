package insertionbinarytree;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.ImageException;
import com.sun.j3d.utils.image.TextureLoader;
import java.awt.Color;
import java.awt.Font;
import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

/**
 *
 * @author Heitor Paceli Maranhao
 * email: heitorpaceli@gmail.com
 */
public class Object3DFactory {

    private static Object3DFactory instance;
    public static float leafSize = InsertionBinaryTree.r/3;
    public static float yInitial = 0.5f, xInitial = InsertionBinaryTree.r * -7, zInitial = 0.0f;
    private float highlighterH = 0.1f;
    private float highlighterR = InsertionBinaryTree.r + 0.02f;

    private Object3DFactory() {
    }

    public static synchronized Object3DFactory getInstance() {
        if (instance == null) {
            instance = new Object3DFactory();
        }
        return instance;
    }

    public TransformGroup getHighlighter(Color color) {
        Transform3D tfHighlighter = new Transform3D();
        //Roda o eixo X para que o cilindro fique na posicao certa
        tfHighlighter.rotX(Math.toRadians(90));

        TransformGroup tgHighlighter = new TransformGroup(tfHighlighter);
        Cylinder cy = new Cylinder(highlighterR, highlighterH, createAppearance(color, true, null));

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
        return tg;
    }

    public Node3D getNode3D() {
        Sphere sphere = new Sphere(InsertionBinaryTree.r, Sphere.GENERATE_NORMALS, 100, createAppearance(Color.BLACK, false,"folha.png"));
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
        textShape.setAppearance(createAppearance(Color.WHITE, true, null));

        Transform3D tfText = new Transform3D();
        tfText.setScale(InsertionBinaryTree.r * 2 - 0.1f);
        tfText.setTranslation(new Vector3f(-InsertionBinaryTree.r / 2, -InsertionBinaryTree.r / 2, InsertionBinaryTree.r));
        TransformGroup tgText = new TransformGroup(tfText);

        tgText.addChild(textShape);
        tgNode.addChild(tgText);

        Node3D node = new Node3D();

        node.setText3D(text3d);
        node.setTgNode(tgNode);
        node.hideNode(node);
        calculateConnections(node);
        return node;
    }

    public void calculateConnections(Node3D node) {
        //float d = distance/5/dist;

        float distance = InsertionBinaryTree.DISTANCE / 5;
        for (int i = 0; i < node.NUM_CONNECTIONS; i++) {
            float x = 0, y = yInitial;
            float cont = 0.0f;
            float d = InsertionBinaryTree.DISTANCE / 5 / distance;
            while (true) {
                //testa se chegou a posicao certa
                if (cont >= (4 * InsertionBinaryTree.r)) {
                    break;
                }
                //define a nova posicao
                x -= distance;
                y -= 0.01f;
                cont += 0.01f;
            }
            double hypot = Math.hypot(y, x);
            double angle = Math.atan2(y, x);
            if (i == 0) {
                angle += Math.toRadians(260);
            } else if (i == 1) {
                angle += Math.toRadians(250);
            } else {
                angle += Math.toRadians(240);
                hypot += 0.1f;
            }


            //hypot/=2;
            TransformGroup right = createNodeConnection(hypot, angle, false);
            TransformGroup left = createNodeConnection(hypot, angle, true);

            Transform3D tfL = new Transform3D();
            left.getTransform(tfL);
            Transform3D tfR = new Transform3D();
            right.getTransform(tfR);

            node.addLCon(i, left);
            node.addLConTf(i, tfL);
            node.addRCon(i, right);
            node.addRConTf(i, tfR);


            Transform3D tfHide = new Transform3D();
            tfHide.setTranslation(new Vector3f(999, 999, 999));
            left.setTransform(tfHide);
            right.setTransform(tfHide);


            node.getTgNode().addChild(left);
            node.getTgNode().addChild(right);

            distance /= 2;
        }
    }
    public static int HIDE = 999;

    private TransformGroup createNodeConnection(double hypot, double angle, boolean rotY) {
        Color brown = new Color(139, 69, 19);
        //Se usar textura, estoura a memoria (em um pc com 6gb de ram)
        Appearance app = getInstance().createAppearance(brown, true, "wood.png");
        Box box = new Box(0.02f, (float) hypot / 2, 0.02f, app);
        
        
        //-----------------------------
        
        Transform3D tfLeaf = new Transform3D();
        tfLeaf.setTranslation(new Vector3f(0.0f, -(float) hypot - InsertionBinaryTree.r, 0.0f));
        TransformGroup tgLeaf = new TransformGroup(tfLeaf);
        tgLeaf.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tgLeaf.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        tgLeaf.setTransform(tfLeaf);
        Box leaf = new Box(leafSize, leafSize, leafSize, createAppearance(Color.green, false, "folha.png"));
        tgLeaf.addChild(leaf);
        
        //-----------------------------
        

        Transform3D tf = new Transform3D();
        Transform3D tfBox = new Transform3D();
        tfBox.setTranslation(new Vector3f(0.0f, -(float) hypot / 2 -InsertionBinaryTree.r, 0.0f));
        TransformGroup tgBox = new TransformGroup(tfBox);
        tgBox.addChild(box);

        //System.out.println(Math.toDegrees(angle));
        //260 250 
        //angle = Math.toRadians(230) + angle;
        if (rotY) {
            angle *= -1;
        }
        tf.rotZ(angle);

        //tf.setTranslation(new Vector3f(999, 999, 999));

        TransformGroup tg = new TransformGroup(tf);

        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tg.addChild(tgBox);
        tg.addChild(tgLeaf);
        return tg;
    }

    Appearance createAppearance(Color color, boolean noTexture, String texture) {
        Appearance app = new Appearance();
        app.setColoringAttributes(new ColoringAttributes(new Color3f(color), 1));

        if (!noTexture) {
            try{
                //app.setTexture(createTexture("folha.png"));
                app.setTexture(createTexture(texture));
            }catch(ImageException ex){
                app.setColoringAttributes(new ColoringAttributes(new Color3f(0.0f, 0.3f, 0.0f), 1));
            }

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
