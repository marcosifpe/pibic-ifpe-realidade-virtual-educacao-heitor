package insertionbinarytree;

import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

/**
 *
 * @author Heitor Paceli Maranhao email: heitorpaceli@gmail.com
 */
public class InsertionBinaryTree {

    public static final String INSERTION_INFO = "Algoritmo de Inserção em Árvore Binária\n\n\n"
            + "   O algoritmo antes iniciar a inserção, verifica se o valor já inserido antes. "
            + "Caso o valor já tenha sido inserido na árvore, é gerado um erro e o algoritmo termina. "
            + "Caso o valor ainda não esteja presente na árvore, é iniciada a inserção.\n\n"
            + "   O algoritmo verifica se o nó a ser inserido é maior ou menor que determinado nó da ávore, iniciando com o nó raiz. "
            + "Caso o nó a ser inserido seja menor, o algoritmo compara com o próximo nó a esquerda. "
            + "Caso o nó a ser inserido seja maior, o algoritmo compara com o próximo nó a direita. "
            + "O algoritmo continua realizando as comparações recursivamente até que encontre um nó folha.\n\n"
            + "   Ao encontrar o nó folha, o algoritmo insere o novo nó no lugar do nó folha.",
            SEARCH_INFO = "Algoritmo de Busca em Árvore Binária\n\n\n"
            + "   O algoritmo compara o valor procurado, com o valor de determinado nó da árvore, iniciando com o nó raiz. "
            + "Se o valor buscado for menor que o valor do nó, compara o próximo nó a esquerda, "
            + "senão se o valor buscado for maior que o valor do nó, compara o próximo nó a direita.\n\n"
            + "   As comparações continuam sendo realizadas recursivamente até que o valor seja encontrado, ou o nó comparado seja um nó folha.",
            REMOVE_INFO = "Algoritmo de Remoção em Árvore Binária\n\n\n"
            + "   O algoritmo compara o valor procurado, com o valor de determinado nó da árvore, iniciando com o nó raiz. "
            + "Se o valor buscado for menor que o valor do nó, compara o próximo nó a esquerda, "
            + "senão se o valor buscado for maior que o valor do nó, compara o próximo nó a direita.\n\n"
            + "   As comparações continuam sendo realizadas recursivamente até que o valor seja encontrado, ou o nó comparado seja um nó folha.\n\n"
            + "   Caso o nó comparado seja um nó folha, é gerado um erro e o algoritmo termina. "
            + "Caso o nó tenha sido encontrado, o algoritmo verifica se o próximo nó a direita é um nó folha, se for troca o nó pelo próximo nó a esquerda, "
            + "senão, verifica se o próximo nó a esquerda é um nó folha, se for troca o nó pelo próximo nó a direita, "
            + "senão, troca o valor do nó pelo valor do menor nó dos maiores (nó mais a esquerda, dentre os nós da direita), e remove o menor nó dos maiores.";
    public static final String LEFT_NEXT_MOV = "ESQUERDA", RIGHT_NEXT_MOV = "DIREITA";
    private SimpleUniverse universe;
    public final static int TEXT_AREA_ROWS = 500,
            TEXT_AREA_COLUMNS = 353;
    private static final String newRow = "\n";
    public static final String INSERT1 = "inserir(valor){" + newRow,
            INSERT1_IF_SEARCH = " se(busca(raiz, valor)){" + newRow,
            INSERT1_ERROR = "     ERRO: VALOR JÁ INSERIDO" + newRow,
            INSERT1_ELSE = " }senão{" + newRow,
            INSERT1_INSERT = "     raiz := inserir(valor, raiz);" + newRow;
    public static final String INSERT1_CODE = INSERT1 + INSERT1_IF_SEARCH + INSERT1_ERROR + INSERT1_ELSE + INSERT1_INSERT + "   }" + newRow + "}";
    public static final String INSERTION_TITLE = "INSERÇÃO EM ÁRVORE BINÁRIA" + newRow + newRow,
            INSERT = "inserir(valor, no){" + newRow,
            INSERT_IF_NULL = "  se (no = null) então{" + newRow,
            INSERT_NEW_NODE = "      no := novo No" + newRow,
            INSERT_NEW_VALUE = "      no.valor := valor" + newRow,
            INSERT_END = "  }" + newRow,
            INSERT_EQUALS = "  senão se (valor == no.valor) então{" + newRow,
            INSERT_ERROR = "      ERRO: VALOR JÁ EXISTENTE" + newRow,
            INSERT_LOWER = "  senão se (valor < no.valor) então{" + newRow,
            INSERT_LEFT = "      no.esquerda := inserir(valor, no.esquerda)" + newRow,
            INSERT_GREATER = "  senão se (valor > no.valor) então{" + newRow,
            INSERT_RIGHT = "      no.direita := inserir(valor, no.direita)" + newRow,
            INSERT_RETURN = "   retorne no" + newRow;
    public static final String INSERT_CODE = INSERTION_TITLE + INSERT1_CODE + newRow + newRow + INSERT + INSERT_IF_NULL + INSERT_NEW_NODE
            + INSERT_NEW_VALUE + INSERT_END + INSERT_EQUALS + INSERT_ERROR + INSERT_END + INSERT_LOWER + INSERT_LEFT + INSERT_END
            + INSERT_GREATER + INSERT_RIGHT + INSERT_END + INSERT_RETURN + "}";
    private static final String SEARCH_TITLE = "BUSCA EM ÁRVORE BINÁRIA" + newRow + newRow,
            SEARCH = "boolean busca(no, num) {" + newRow,
            SEARCH_IF_NULL = "   se (no == folha) {" + newRow,
            SEARCH_FALSE = "       retorne falso;" + newRow,
            SEARCH_EQUALS = "   se (no.valor == num) {" + newRow,
            SEARCH_TRUE = "       retorne verdadeiro;" + newRow,
            SEARCH_LOWER = "   se (num < no.valor) {" + newRow,
            SEARCH_LEFT = "       retorne busca(no.esquerda, num);" + newRow,
            SEARCH_ELSE = "   } senão {" + newRow,
            SEARCH_RIGHT = "       retorne busca(no.direita, num);" + newRow;
    public static final String SEARCH_CODE = SEARCH_TITLE + SEARCH + SEARCH_IF_NULL + SEARCH_FALSE + "   }" + newRow + SEARCH_EQUALS + SEARCH_TRUE + "   }" + newRow
            + SEARCH_LOWER + SEARCH_LEFT + SEARCH_ELSE + SEARCH_RIGHT + "   }" + newRow + "}";
    public static final String REMOVE_TITLE = "REMOÇÃO EM ÁRVORE BINÁRIA" + newRow + newRow,
            REMOVE = "boolean remove(valor) {" + newRow,
            REMOVE_IF_NULL = " se (raiz == folha)" + newRow,
            REMOVE_FALSE = "   retorne falso;" + newRow,
            REMOVE_ELSE = " senão{" + newRow,
            REMOVE_ROOT = "   se (raiz.valor == valor) {" + newRow,
            REMOVE_TEMP = "     No temp = novo No(raiz.valor + 1);" + newRow,
            REMOVE_TEMP_L = "     temp.esquerda := raiz;" + newRow,
            REMOVE_RESULT = "     boolean r := remove(valor, temp, raiz);" + newRow,
            REMOVE_ROOT_TEMP = "     raiz := temp.esquerda;" + newRow,
            REMOVE_RETURN_RESULT = "     retorne r;" + newRow,
            REMOVE_ELSE2 = "   } senão {" + newRow,
            REMOVE_RETURN_REMOVE = "     retorne remove(valor, folha, raiz);" + newRow;
    public static final String REMOVE_CODE = REMOVE_TITLE + REMOVE + REMOVE_IF_NULL + REMOVE_FALSE + REMOVE_ELSE + REMOVE_ROOT + REMOVE_TEMP + REMOVE_TEMP_L + REMOVE_RESULT
            + REMOVE_ROOT_TEMP + REMOVE_RETURN_RESULT + REMOVE_ELSE2 + REMOVE_RETURN_REMOVE + "   }\n" + " }\n" + "}\n";
    public static final String REMOVE2_TITLE = REMOVE_TITLE,
            REMOVE2 = "boolean remove(valor, pai, filho) {" + newRow,
            REMOVE2_LOWER = " se (valor < filho.valor) {" + newRow,
            REMOVE2_L_LEAF = "   se (filho.esquerda != folha)" + newRow,
            REMOVE2_RETURN_REMOVE = "     retorne remove(valor, filho, filho.esquerda);" + newRow,
            REMOVE2_ELSE = "   senão" + newRow,
            REMOVE2_FALSE = "     retorne falso;" + newRow,
            REMOVE2_GREATER = " } senão se (valor > filho.valor) {" + newRow,
            REMOVE2_R_LEAF = "   se (filho.direita != folha)" + newRow,
            REMOVE2_RETURN_REMOVE2 = "     retorne remove(valor, filho, filho.direita);" + newRow,
            REMOVE2_ELSE2 = "   senão " + newRow,
            REMOVE2_FALSE2 = "     retorne  falso;" + newRow,
            REMOVE2_ELSE3 = "   } senão  {" + newRow,
            REMOVE2_NO_LEAF = "     se (filho.esquerda != folha E \n\tfilho.direita != folha) {" + newRow,
            REMOVE2_MIN = "       filho.valor := minvalor(filho.direita);" + newRow,
            REMOVE2_REMOVE_MIN = "       remove(filho.valor, filho, filho.direita);" + newRow,
            REMOVE2_R_CHILD = "     } senão se (pai.esquerda == filho) {" + newRow,
            REMOVE2_P_LEFT = "       pai.esquerda = (esquerda != folha) ? \n\tesquerda : direita;" + newRow,
            REMOVE2_L_CHILD = "     } senão se (pai.direita == filho) {" + newRow,
            REMOVE2_P_RIGHT = "       pai.direita = (esquerda != folha) ? \n\tesquerda : direita;" + newRow + "     }" + newRow,
            REMOVE2_TRUE = "     retorne verdadeiro;" + newRow;
    public static final String REMOVE2_CODE = REMOVE2 + REMOVE2_LOWER + REMOVE2_L_LEAF + REMOVE2_RETURN_REMOVE + REMOVE2_ELSE + REMOVE2_FALSE
            + REMOVE2_GREATER + REMOVE2_R_LEAF + REMOVE2_RETURN_REMOVE2 + REMOVE2_ELSE2 + REMOVE2_FALSE2 + REMOVE2_ELSE3 + REMOVE2_NO_LEAF + REMOVE2_MIN
            + REMOVE2_REMOVE_MIN + REMOVE2_R_CHILD + REMOVE2_P_LEFT + REMOVE2_L_CHILD + REMOVE2_P_RIGHT + REMOVE2_TRUE + "   }" + newRow + "}";
    public static final String MIN = "int minvalor(no) {" + newRow,
            MIN_L_NULL = "     if (no.esquerda == folha) {" + newRow,
            MIN_RETURN_NODE = "         retorne no.valor;" + newRow,
            MIN_ELSE = "     } else {" + newRow,
            MIN_RETURN_LEFT = "         return minvalor(no.esquerda);" + newRow;
    public static final String MIN_CODE = MIN + MIN_L_NULL + MIN_RETURN_NODE + MIN_ELSE + MIN_RETURN_LEFT + "     }" + newRow + "}";
    public static final String COMPLETE_REMOVE = REMOVE_CODE + newRow + newRow + REMOVE2_CODE + newRow + newRow + MIN_CODE;
    public static final String DELETE_TITLE = "REMOÇÃO EM ÁRVORE BINÁRIA" + newRow + newRow,
            DELETE = "remover(valor, no) {" + newRow,
            DELETE_IF_NULL = " se (no == folha) {" + newRow,
            DELETE_ERROR = "   ERRO: VALOR NÃO ENCONTRADO" + newRow,
            DELETE_LOWER = " } senão se (valor < no.valor)) {" + newRow,
            DELETE_LEFT = "   no.esquerda := remover(valor, no.esquerda);" + newRow,
            DELETE_GREATER = " } senão se (valor > no.valor) {" + newRow,
            DELETE_RIGHT = "   no.direita := remover(valor, no.direita);" + newRow,
            DELETE_ELSE = " } senão {" + newRow,
            DELETE_EQUALS = "   se (no.direita == folha) {" + newRow,
            DELETE_TEMP1 = "     temp := no;" + newRow,
            DELETE_NODE_L = "     no := no.esquerda;" + newRow,
            DELETE_DELETE1 = "     delete temp;" + newRow,
            DELETE_L_LEAF = "   } senão se (no.esquerda == folha) {" + newRow,
            DELETE_TEMP2 = "     temp := no; " + newRow,
            DELETE_NODE_R = "     no := no.direita;" + newRow,
            DELETE_DELETE2 = "     delete temp; " + newRow,
            DELETE_ELSE2 = "   } senão { " + newRow,
            DELETE_FIND_SUBSTITUTE = "     min:=buscaSubstituto(no.direita);" + newRow,
            DELETE_NODE_MIN = "     no.valor := min.valor;" + newRow,
            DELETE_TEMP_MIN = "     temp := min;" + newRow,
            DELETE_MIN_RIGHT = "     min := min.direita;" + newRow,
            DELETE_DEL_TEMP = "     delete temp;" + newRow,
            //    }
            //  }
            DELETE_RETURN = "retorne no;" + newRow;
    private final static String FIND = "buscaSubstituto(no){" + newRow,
            FIND_IF = "  se(no.esquerda == folha){" + newRow,
            FIND_RETURN = "      retorne no;" + newRow,
            FIND_ELSE = "  } senão {" + newRow,
            FIND_RETURN2 = "      retorne buscaSubstituto(no.esquerda);" + newRow;
    private static final String FIND_CODE = FIND + FIND_IF + FIND_RETURN + FIND_ELSE + FIND_RETURN2 + "   }" + newRow + "}";
    public static final String DELETE_CODE = DELETE_TITLE + DELETE + DELETE_IF_NULL + DELETE_ERROR + DELETE_LOWER + DELETE_LEFT + DELETE_GREATER + DELETE_RIGHT
            + DELETE_ELSE + DELETE_EQUALS + DELETE_TEMP1 + DELETE_NODE_L + DELETE_DELETE1 + DELETE_L_LEAF + DELETE_TEMP2 + DELETE_NODE_R + DELETE_DELETE2
            + DELETE_ELSE2 + DELETE_FIND_SUBSTITUTE + DELETE_NODE_MIN + DELETE_TEMP_MIN + DELETE_MIN_RIGHT + DELETE_DEL_TEMP + "   }" + newRow + " }" + newRow + DELETE_RETURN + "}" + newRow + newRow + FIND_CODE;
    public static final String AVL_TITLE = "Balanceamento de Árvore AVL" + newRow +newRow,
            BALANCE             = "balancear(No no) {" + newRow,
            BALANCE_IF_NULL     = " se (no == null) {" + newRow,
            BALANCE_NULL        = "     retorne null;" + newRow,
            //                      }
            BALANCE_GREATER     = " se (altura(no.direita) - altura(no.esquerda) > 1) {" + newRow,
            BALANCE_LOWER       = "     se (altura(no.direita.direita) - altura(no.direita.esquerda) < -1) {" + newRow,
            BALANCE_DOUBLE_L    = "         no = rotaçãoEsquerdaDupla(no);" + newRow,
            BALANCE_ELSE        = "     } senão {" + newRow,
            BALANCE_SINGLE_L    = "         no = rotaçãoEsquerdaSimples(no);" + newRow,
            //                          }
            BALANCE_GREATER2    = " } senão se (altura(no.esquerda) - altura(no.direita) > 1) {" + newRow,
            BALANCE_GREATER3    = "     se (altura(no.esquerda.direita) - altura(no.esquerda.esquerda) > 1) {" + newRow,
            BALANCE_DOUBLE_R    = "         no = rotaçãoDireitaDupla(no);" + newRow,
            BALANCE_ELSE2       = "     }senão {" + newRow,
            BALANCE_SINGLE_R    = "         no = rotaçãoDireitaSimples(no);" + newRow,
            //                          }
            //                      }
            BALANCE_RETURN      = " retorne no;" + newRow;
//                                 }
    public final static String AVL_CODE = AVL_TITLE + BALANCE + BALANCE_IF_NULL + BALANCE_NULL + "  }" +newRow+
            BALANCE_GREATER + BALANCE_LOWER+BALANCE_DOUBLE_L + BALANCE_ELSE + BALANCE_SINGLE_L + "      }"+newRow+BALANCE_GREATER2+
            BALANCE_GREATER3+BALANCE_DOUBLE_R+BALANCE_ELSE2+BALANCE_SINGLE_R+"      }"+newRow+" }"+newRow+BALANCE_RETURN+"}";
    private static final int NUMBER_OF_VALUES = 15;
    public static final int LEFT = -1, RIGTH = 1;
    public Node3D root;
    final static float r = 0.1f;
    public ArrayList<Node3D> nodes = new ArrayList<Node3D>();
    public static final int SLEEP_TIME = 40;
    public JTextPane textPane;
    public TransformGroup searchHighlighter, removeHighlighter;
    private SimpleAttributeSet deafaultText = new SimpleAttributeSet();
    private SimpleAttributeSet highlightedText = new SimpleAttributeSet();
    public JButton insertButton;
    private JTextArea vars = new JTextArea();
    private JButton removeButton;
    private JButton searchButton;
    public boolean isRunning = false;
    private Vector3f[] viewPositions;
    public static final float DISTANCE = r * 2;
    public static final int H_MAX = 3;
    private boolean balanced;

    public InsertionBinaryTree(boolean balanced) {
        this.balanced = balanced;
    }

    public boolean isAVL() {
        return balanced;
    }

    public int viewLeft() {
        int contLeft = 0;
        Node3D node = root;
        //Calcula quantos nos estao a esquerda
        while (node != null) {
            node = node.getLeft();
            if (node != null) {
                contLeft++;
            }
        }
        return contLeft;
    }

    public int viewRight() {
        Node3D node = root;
        int contRight = 0;
        //Calcula quantos nos estao a direita
        while (node != null) {
            node = node.getRight();
            if (node != null) {
                contRight++;
            }
        }
        return contRight;
    }

    public int viewX() {
        Node3D node = root;
        int contLeft = viewLeft();
        int contRight = viewRight();

        if (contLeft > contRight) {
            return contLeft;
        } else {
            return contRight;
        }
    }

    public int prevViewX(int n) {
        int lView = viewLeft();
        int rView = viewRight();
        int view = 0;

        if (root != null) {
            if (n < root.minNode().getValue()) {
                if (lView + 1 > rView) {
                    view = lView + 1;
                } else {
                    view = rView;
                }
                return view;
            } else if (n > root.maxNode().getValue()) {
                if (rView + 1 > lView) {
                    view = rView + 1;
                } else {
                    view = lView;
                }
                return view;
            } else {
                return viewX();
            }
        } else {
            return 0;
        }
    }

    public Node3D insertValue(Node3D child, Node3D node, float distance, Score score) {

        updateInsertVars(child, node);

        highlightsText(INSERT, INSERT_CODE);
        highlightsText(INSERT_IF_NULL, INSERT_CODE);

        if (node == null) {
            node = child;
            //Move para a posicao correta
            Transform3D tf = new Transform3D();
            searchHighlighter.getTransform(tf);
            highlightsText(INSERT_NEW_NODE, INSERT_CODE);
            highlightsText(INSERT_NEW_VALUE, INSERT_CODE);
            child.getTgNode().setTransform(tf);

            updateInsertVars(child, node);
            sleep(SLEEP_TIME * 5);
        } else if (child.getValue() == node.getValue()) {
            highlightsText(INSERT_EQUALS, INSERT_CODE);
            highlightsText(INSERT_ERROR, INSERT_CODE);
        } else if (child.getValue() < node.getValue()) {
            highlightsText(INSERT_EQUALS, INSERT_CODE);

            nextMovement(LEFT_NEXT_MOV, score);

            highlightsText(INSERT_LOWER, INSERT_CODE);
            highlightsText(INSERT_LEFT, INSERT_CODE);
            //Seta o no pai, para depois remover o no folha da animacao
            if (node.getLeft() == null) {
                //System.out.println("setado");
                child.setParent(node);
            }

            //mostra a animacao
            insert3D(child, LEFT, distance / 5, false, searchHighlighter);
            node.setLeft(insertValue(child, node.getLeft(), distance / 2, score));

        } else if (child.getValue() > node.getValue()) {
            highlightsText(INSERT_EQUALS, INSERT_CODE);

            //textPane.getStyledDocument().setCharacterAttributes(0, INSERT_CODE.length(), deafaultText, true);

            nextMovement(RIGHT_NEXT_MOV, score);

            highlightsText(INSERT_LOWER, INSERT_CODE);
            highlightsText(INSERT_GREATER, INSERT_CODE);
            highlightsText(INSERT_RIGHT, INSERT_CODE);
            //Seta o no pai, para depois remover o no folha da animacao
            if (node.getRight() == null) {
                //System.out.println("setado");
                child.setParent(node);
            }

            //mostra a animacao
            insert3D(child, RIGTH, distance / 5, false, searchHighlighter);
            node.setRight(insertValue(child, node.getRight(), distance / 2, score));
        }
        highlightsText(INSERT_RETURN, INSERT_CODE);
        return node;
    }

    public float getDistance(int h) {
        if (h <= 0) {
            return DISTANCE / 5;
        } else {
            return (DISTANCE / 5) / (h * 2);
        }
    }

    private void createPositionsVector() {
        final int MAX_VIEW_POSITIONS = 5;
        viewPositions = new Vector3f[MAX_VIEW_POSITIONS];

        Transform3D tfTemp = new Transform3D();
        universe.getViewingPlatform().getViewPlatformTransform().getTransform(tfTemp);

        Vector3f view = new Vector3f();
        tfTemp.get(view);

        float Z_CHANGE = 2.4f, Y_CHANGE = 0.2f;

        view.z += Z_CHANGE;
        view.y -= Y_CHANGE;
        Z_CHANGE -= 0.1f;


        for (int i = 0; i < viewPositions.length; i++) {
            viewPositions[i] = new Vector3f(view);
            view.z += Z_CHANGE;
            view.y -= Y_CHANGE;
            Z_CHANGE -= 0.1f;
            if (Z_CHANGE > 1.5f) {
                Z_CHANGE = 1.5f;
            }
        }
    }

    public int getHBinaryTree() {
        return getHBinaryTree(root);
    }

    private int getHBinaryTree(Node3D node) {
        if (node == null) {
            return -1; // altura de árvore vazia é -1
        } else {
            int hl = getHBinaryTree(node.getLeft());
            int hr = getHBinaryTree(node.getRight());
            if (hl < hr) {
                return hr + 1;
            } else {
                return hl + 1;
            }
        }
    }

    private JTextPane createTextPane(String code) {
        StyledDocument style = new DefaultStyledDocument();
        JTextPane text = new JTextPane(style);
        text.setPreferredSize(new Dimension(TEXT_AREA_COLUMNS, TEXT_AREA_ROWS));
        text.setText(code);
        text.setEditable(false);
        return text;
    }

    public void clearHighlight(JTextPane pane, String code) {
        pane.getStyledDocument().setCharacterAttributes(0, code.length(), deafaultText, true);
    }

    private void searchAction() {
        if (!isRunning) {
            isRunning = true;
            SearchThread thread = new SearchThread("search", this);
            thread.start();
        }
    }

    private void removeAction() {
        if (!isRunning) {
            isRunning = true;
            RemoveThread thread = new RemoveThread("remove", this);
            thread.start();
        }
    }

    private void insertAction() {
        if (!isRunning) {
            isRunning = true;
            InsertThread thread = new InsertThread("insert", this);
            thread.start();
        }
    }

    private void balanceAction() {
        Transform3D tfTemp = root.getTfNode();
        root = balance(root);
        root.getTgNode().setTransform(tfTemp);
        moveNodes(root);
        updateConnections(root, 0);
    }

    private void createFrame() {
        JFrame frame = new JFrame("Binary Tree");
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = createMenuBar();
        frame.setJMenuBar(menuBar);

        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        //Onde sera mostrada a animacao
        Canvas3D canvas = new Canvas3D(config);
        contentPane.add(BorderLayout.CENTER, canvas);

        BranchGroup scene = createScene();
        universe = new SimpleUniverse(canvas);

        universe.getViewingPlatform().setNominalViewingTransform();
        universe.addBranchGraph(scene);
        //---------
        createPositionsVector();
        //---------
        addLight(universe);

        textPane = createTextPane(INSERT_CODE);

        JPanel panel = new JPanel();
        panel.setSize(TEXT_AREA_COLUMNS, TEXT_AREA_ROWS);


        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JScrollPane scroll = new JScrollPane(textPane);

        panel.add(scroll);
        //frame.getContentPane().add(BorderLayout.EAST, textPane);

        vars.setEditable(false);

        JPanel lowerPanel = new JPanel(new GridLayout(3, 1));
        panel.add(vars);

        insertButton = new JButton("Inserir número");
        insertButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                insertAction();
            }
        });

        //frame.getContentPane().add(BorderLayout.SOUTH, insertButton);
        lowerPanel.add(insertButton);

        searchButton = new JButton("Buscar número");

        searchButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                searchAction();
            }
        });
        //frame.getContentPane().add(BorderLayout.SOUTH, searchButton);
        lowerPanel.add(searchButton);

        removeButton = new JButton("Remover número");
        removeButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(ActionEvent e) {
                removeAction();
            }
        });
        lowerPanel.add(removeButton);
        //
        panel.add(lowerPanel);

        contentPane.add(BorderLayout.WEST, panel);
        //

        frame.setSize(800, 640);
        frame.setVisible(true);

        OrbitBehavior ob = new OrbitBehavior(canvas);
        ob.setSchedulingBounds(new BoundingSphere(new Point3d(0.0, 0.0, 0.0), Double.MAX_VALUE));
        universe.getViewingPlatform().setViewPlatformBehavior(ob);
    }

    public void moveNodes(Node3D node) {
        if (node == null) {
            return;
        }
        Node3D right = node.getRight();
        Node3D left = node.getLeft();
        if (right != null) {
            removeHighlighter.setTransform(node.getTfNode());
            Transform3D tfTemp = insert3D(right, RIGTH, getDistance(getNodeHeight(right)), true, removeHighlighter);
            right.getTgNode().setTransform(tfTemp);
            moveNodes(right);
        }
        if (left != null) {
            removeHighlighter.setTransform(node.getTfNode());
            Transform3D tfTemp = insert3D(left, LEFT, getDistance(getNodeHeight(left)), true, removeHighlighter);
            left.getTgNode().setTransform(tfTemp);
            moveNodes(left);
        }
        highlightMov(null, removeHighlighter);
    }

    public void moveView(int i) {
        Transform3D tfUniverse = new Transform3D();
        universe.getViewingPlatform().getViewPlatformTransform().getTransform(tfUniverse);

        if (i < 0) {
            i = 0;
        } else if (i > viewPositions.length) {
            i = viewPositions.length - 1;
        }
        Vector3f vector = viewPositions[i];

        tfUniverse.set(vector);
        universe.getViewingPlatform().getViewPlatformTransform().setTransform(tfUniverse);
    }

    public Score search(int num) {
        highlightMov(root, searchHighlighter);
        Score score = new Score();
        Node3D result = search(root, num, score);
        highlightMov(null, searchHighlighter);
        vars.setText(" ");
        //score.show(textPane);

        String message = (result != null) ? "Valor encontrado" : "Valor não encontrado";
        JOptionPane.showMessageDialog(textPane, message);
        return score;
    }

    private Node3D search(Node3D node, int num, Score score) {
        String code = SEARCH_CODE;
        //----
        String varsText = "no = ";
        varsText += (node == null) ? "null" : Integer.toString(node.getValue());
        varsText += newRow;
        varsText += "num = " + num;
        vars.setText(varsText);
        //---
        highlightsText(SEARCH_IF_NULL, code);

        if (node == null) {
            //Valor nao encontrado
            highlightsText(SEARCH_FALSE, code);
            return node;
        }

        highlightsText(SEARCH_EQUALS, code);

        if (node.getValue() == num) {
            //Encontrou o valor
            highlightsText(SEARCH_TRUE, SEARCH_CODE);

            return node;
        }
        String mov = (num < node.getValue()) ? LEFT_NEXT_MOV : RIGHT_NEXT_MOV;
        nextMovement(mov, score);

        highlightsText(SEARCH_LOWER, code);

        if (num < node.getValue()) {
            highlightsText(SEARCH_LEFT, code);
            highlightMov(node.getLeft(), searchHighlighter);

            return search(node.getLeft(), num, score);
        } else {
            highlightsText(SEARCH_ELSE, code);
            highlightsText(SEARCH_RIGHT, code);
            highlightMov(node.getRight(), searchHighlighter);

            return search(node.getRight(), num, score);
        }
    }

    public void highlightMov(Node3D node, TransformGroup highlighter) {
        Transform3D tf = new Transform3D();

        sleep(SLEEP_TIME);
        if (node != null) {
            Transform3D tfNode = node.getTfNode();
            Vector3f v3f = new Vector3f();
            tfNode.get(v3f);

            tf.set(v3f);
        } else {
            //Se o valor não foi encontrado
            tf.set(new Vector3f(999, 999, 999));
        }
        highlighter.setTransform(tf);
        //sleep(SLEEP_TIME * 20);
    }

    private BranchGroup createScene() {
        BranchGroup scene = new BranchGroup();

        for (int i = 0; i < NUMBER_OF_VALUES; i++) {
            Node3D node3D = Object3DFactory.getInstance().getNode3D();

            nodes.add(node3D);
            scene.addChild(node3D.getTgNode());
        }
        searchHighlighter = Object3DFactory.getInstance().getHighlighter(Color.WHITE);
        removeHighlighter = Object3DFactory.getInstance().getHighlighter(Color.blue);
        scene.addChild(removeHighlighter);
        scene.addChild(searchHighlighter);

        return scene;
    }

    public void insert(Node3D node, List<Integer> directions) {
        if (node != null) {
            node.moveToPosition(Object3DFactory.xInitial, Object3DFactory.yInitial, Object3DFactory.zInitial);
        }
        root = insert(node, root, directions);
    }

    private Node3D insert(Node3D child, Node3D node, List directions) {
        //armazena o movimento do no, no list directions
        if (node == null) {
            node = child;
        } else if (child.getValue() == node.getValue()) {
//            throw new RuntimeException("Element already exists");
        } else if (child.getValue() < node.getValue()) {
            //Seta o no pai, para depois remover o no folha da animacao
            if (node.getLeft() == null) {
                child.setParent(node);
            }
            node.setLeft(insert(child, node.getLeft(), directions));
            directions.add(LEFT);
        } else if (child.getValue() > node.getValue()) {
            //Seta o no pai, para depois remover o no folha da animacao
            if (node.getRight() == null) {
                child.setParent(node);
            }
            node.setRight(insert(child, node.getRight(), directions));
            directions.add(RIGTH);
        }
        return node;
    }

    public Transform3D insert3D(Node3D node, final int direction, final float distance, boolean animationOff, TransformGroup tg) {

        TransformGroup tgNode = tg;
        Transform3D tfNode = new Transform3D();
        tgNode.getTransform(tfNode);
        Vector3f translation = new Vector3f();
        tfNode.get(translation);

        float x = translation.x;
        float y = translation.y;
        System.out.println("y=" + y + " | x=" + x);
        float cont = 0.0f;

        while (true) {
            //testa se chegou a posicao certa
            if (cont >= (4 * r)) {
                break;
            }
            //define a nova posicao

            if (direction == LEFT) {

                x -= distance;
                y -= 0.01f;
                cont += 0.01f;
            } else if (direction == RIGTH) {

                x += distance;
                y -= 0.01f;
                cont += 0.01f;
            }

            translation.x = x;
            translation.y = y;

            if (!animationOff) {
                //Atualiza a posicao
                tfNode.setTranslation(translation);
                tgNode.setTransform(tfNode);

                sleep(SLEEP_TIME);
            }
        }

        if (animationOff) {
            //Atualiza a posicao
            tfNode.setTranslation(translation);
            tgNode.setTransform(tfNode);
        }
        System.out.println("y=" + y + " | x=" + x);
        return tfNode;
    }

    public Score insertValue(int num) {
        Score score = null;
        String varsText = "valor = " + num + newRow
                + "raiz = " + ((root != null) ? root.getValue() : "null");
        vars.setText(varsText);
        highlightsText(INSERT1, INSERT_CODE);
        highlightsText(INSERT1_IF_SEARCH, INSERT_CODE);
        if (exists(num, root)) {
            highlightsText(INSERT1_ERROR, INSERT_CODE);
            JOptionPane.showMessageDialog(textPane, "O valor já foi inserido antes");
        } else {

            highlightsText(INSERT1_ELSE, INSERT_CODE);
            highlightsText(INSERT1_INSERT, INSERT_CODE);
            //startInsertValue(num);
            Node3D node = nodes.remove(0);

            if (node == null) {
                return null;
            } else {
                resetNode(node, false);
                node.setAutoUpdateText(true);
                node.setValue(num);
                node.getText3D().setString(Integer.toString(num));
                showHighlighter(searchHighlighter);
                sleep(SLEEP_TIME * 3);

                node.moveToPosition(Object3DFactory.xInitial, Object3DFactory.yInitial, Object3DFactory.zInitial);

                score = new Score();
                root = insertValue(node, root, DISTANCE, score);

                int nodeH = getNodeHeight(node);
                Node3D parent = node.getParent();
                if (parent != null) {
                    if (node == parent.getLeft()) {
                        parent.showLConnection(nodeH);
                    } else {
                        parent.showRConnection(nodeH);
                    }
                }

                sleep(SLEEP_TIME * 3);
                highlightMov(null, searchHighlighter);
            }
        }
        vars.setText("");
        return score;
    }

    public boolean exists(int num, Node3D node) {
        if (node == null) {
            return false;
        } else if (node.getValue() == num) {
            return true;
        } else if (num < node.getValue()) {
            return exists(num, node.getLeft());
        } else {
            return exists(num, node.getRight());
        }

    }

    public void addLight(SimpleUniverse su) {

        BranchGroup bgLight = new BranchGroup();

        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), Double.MAX_VALUE);
        Color3f lightColour1 = new Color3f(1.0f, 1.0f, 1.0f);
        Vector3f lightDir1 = new Vector3f(0.2f, -0.1f, -1.0f);
        DirectionalLight light1 = new DirectionalLight(lightColour1, lightDir1);
        light1.setInfluencingBounds(bounds);

        bgLight.addChild(light1);

        su.addBranchGraph(bgLight);

    }

    public void sleep(long sleepTime) {
        //Deixa a animacao mais lenta para que se possar ver
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void highlightsText(String string, String code) {
        //Remove destaques
        textPane.getStyledDocument().setCharacterAttributes(0, code.length(), deafaultText, true);
        int i = code.indexOf(string);
        textPane.getStyledDocument().setCharacterAttributes(i, string.length(), highlightedText, true);
        sleep(SLEEP_TIME * 30);
    }

    public void delete(int element, Node3D node, Score score) {
        sleep(SLEEP_TIME * 3);
        //variaveis que serão mostradas no painel lateral
        String left, right, nodeValue;
        //inicia as variaveis que serão mostradas
        if (node != null) {
            nodeValue = Integer.toString(node.getValue());
            left = (node.getLeft() != null) ? Integer.toString(node.getLeft().getValue()) : "null";
            right = (node.getRight() != null) ? Integer.toString(node.getRight().getValue()) : "null";
        } else {
            nodeValue = left = right = "null";
        }
        setRemoveVars(element, nodeValue, left, right, null);
        highlightMov(node, searchHighlighter);
        highlightsText(DELETE, DELETE_CODE);
        highlightsText(DELETE_IF_NULL, DELETE_CODE);

        if (node == null) {

            highlightsText(DELETE_ERROR, DELETE_CODE);

            JOptionPane.showMessageDialog(textPane, "Valor não encontrado");

        } else {
            //Se ainda não encontrou o elemento pergunta qual é o próximo movimento
            if (element != node.getValue()) {
                String answer = (element < node.getValue()) ? LEFT_NEXT_MOV : RIGHT_NEXT_MOV;
                nextMovement(answer, score);
            }
            highlightsText(DELETE_LOWER, DELETE_CODE);
            if (element < node.getValue()) {

                highlightsText(DELETE_LEFT, DELETE_CODE);

                delete(element, node.getLeft(), score);

            } else if (element > node.getValue()) {

                highlightsText(DELETE_GREATER, DELETE_CODE);
                highlightsText(DELETE_RIGHT, DELETE_CODE);

                delete(element, node.getRight(), score);
            } else {

                highlightsText(DELETE_GREATER, DELETE_CODE);
                highlightsText(DELETE_ELSE, DELETE_CODE);
                highlightsText(DELETE_EQUALS, DELETE_CODE);

                if (node.getRight() == null) {

                    String temp = nodeValue;
                    setRemoveVars(element, nodeValue, left, right, temp);
                    highlightsText(DELETE_TEMP1, DELETE_CODE);
                    nodeValue = left;
                    if (node.getLeft() != null) {
                        Node3D substitute = node.getLeft();
                        left = (substitute.getLeft() != null) ? Integer.toString(substitute.getLeft().getValue()) : "null";
                        right = (substitute.getRight() != null) ? Integer.toString(substitute.getRight().getValue()) : "null";
                    } else {
                        left = "null";
                        right = "null";
                    }
                    setRemoveVars(element, nodeValue, left, right, temp);
                    highlightsText(DELETE_NODE_L, DELETE_CODE);
                    setRemoveVars(element, nodeValue, left, right, null);
                    highlightsText(DELETE_DELETE1, DELETE_CODE);

                    //removeNode(node);
                    //********************
                    doDelete(node);
                    //*******************
                } else if (node.getLeft() == null) {
                    highlightsText(DELETE_L_LEAF, DELETE_CODE);

                    String temp = nodeValue;
                    setRemoveVars(element, nodeValue, left, right, temp);
                    highlightsText(DELETE_TEMP2, DELETE_CODE);
                    nodeValue = right;
                    if (node.getRight() != null) {
                        Node3D substitute = node.getRight();
                        left = (substitute.getLeft() != null) ? Integer.toString(substitute.getLeft().getValue()) : "null";
                        right = (substitute.getRight() != null) ? Integer.toString(substitute.getRight().getValue()) : "null";
                    } else {
                        left = "null";
                        right = "null";
                    }
                    setRemoveVars(element, nodeValue, left, right, temp);
                    highlightsText(DELETE_NODE_R, DELETE_CODE);
                    setRemoveVars(element, nodeValue, left, right, null);
                    highlightsText(DELETE_DELETE2, DELETE_CODE);

                    //removeNode(node);
                    //*************************
                    doDelete(node);
                    //*************************
                } else {

                    highlightsText(DELETE_L_LEAF, DELETE_CODE);
                    highlightsText(DELETE_ELSE2, DELETE_CODE);
                    highlightsText(DELETE_FIND_SUBSTITUTE, DELETE_CODE);

                    //Faz pergunta ao usuário
                    whatIsSubstitute(node);
                    String varsTemp = vars.getText();
                    //Node3D min = findSubstitute(node.getRight(), node/*so passa o no para mostrar o valor no painel*/, true);
                    Node3D min = findSubstitute(node.getRight(), true);
                    highlightsText(DELETE_NODE_MIN, DELETE_CODE);
                    node.setValue(min.getValue());
                    setRemoveVars(element, Integer.toString(node.getValue()), left, right, null);
                    vars.append("\nmin = " + min.getValue());
                    highlightsText(DELETE_TEMP_MIN, DELETE_CODE);
                    setRemoveVars(element, Integer.toString(node.getValue()), left, right, Integer.toString(min.getValue()));
                    highlightsText(DELETE_MIN_RIGHT, DELETE_CODE);
                    setRemoveVars(element, Integer.toString(node.getValue()), left, right, Integer.toString(min.getValue()));
                    String newMin = ((min.getRight() != null)
                            ? Integer.toString(min.getRight().getValue()) : "null");
                    vars.append("\nmin = " + newMin);
                    removeNode(min);
                    highlightsText(DELETE_DEL_TEMP, DELETE_CODE);
                    setRemoveVars(element, Integer.toString(node.getValue()), left, right, null);
                    vars.append("\nmin = " + newMin);
                }
            }
        }
        sleep(SLEEP_TIME * 3);
        highlightMov(null, searchHighlighter);
        highlightMov(null, removeHighlighter);
        highlightsText(DELETE_RETURN, DELETE_CODE);
        vars.setText("");
    }

    public void resetNode(Node3D node, boolean add) {
        if (node != null) {
            node.setValue(0);

            node.hideNode(node);

            node.setLeft(null);
            node.setRight(null);

            node.setText(" ");

            node.setAutoUpdateText(false);

            node.setParent(null);

            if (add) {
                nodes.add(node);

            }
        }
    }

    public void reinsert(ArrayList<Node3D> nodesToInsert) {
        List<Integer> directions = new ArrayList();
        for (int j = 0; j < nodesToInsert.size(); j++) {
            Node3D node = nodesToInsert.get(j);
            node.setLeft(null);
            node.setRight(null);
            node.setAutoUpdateText(true);
            node.getText3D().setString(Integer.toString(node.getValue()));
            node.setParent(null);
            insert(node, directions);
            float distance = DISTANCE;

            TransformGroup tgTemp = new TransformGroup();
            showHighlighter(tgTemp);
            /*
             * showHighlighter(searchHighlighter); highlightMov(node,
             * removeHighlighter);
             */

            Transform3D tfTemp = null;
            for (int i = directions.size() - 1; i >= 0; i--) {
                //tfTemp = insert3D(node, directions.get(i), distance / 5, true, searchHighlighter);
                tfTemp = insert3D(node, directions.get(i), distance / 5, true, tgTemp);
                distance /= 2;
            }
            if (node != null && tfTemp != null) {
                node.getTgNode().setTransform(tfTemp);
            } else {
                if (node != null) {
                    node.moveToPosition(0.0, Object3DFactory.yInitial, 0.0);
                }
            }
            sleep(SLEEP_TIME * 3);
            highlightMov(null, searchHighlighter);
            highlightMov(null, removeHighlighter);
            updateConnections(this.root, 0);
            directions.clear();
        }
    }

    public void updateConnections(Node3D node, int h) {

        if (node != null) {

            Node3D left = node.getLeft();
            Node3D right = node.getRight();

            node.hideLConnection();
            node.showLConnection(h);
            updateConnections(left, h + 1);

            node.hideRConnection();
            node.showRConnection(h);
            updateConnections(right, h + 1);

        }

    }

    private void removeNode(Node3D node) {
        node.hideNode(node);
        ArrayList<Node3D> nodesReinsert = node.getAllChildren(node);

        Node3D parent = node.getParent();
        //Se o no pai for null é porque é o nó raiz
        if (parent != null) {
            if (node == parent.getLeft()) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
        } else {
            root = null;
        }

        resetNode(node, true);
        reinsert(nodesReinsert);
    }

    private Node3D findSubstitute(Node3D node, boolean highlight) {
        if (node != null) {
            if (highlight) {
                String varsText = "no = " + node.getValue() + newRow;
                varsText += "no.esquerda = " + ((node.getLeft() != null) ? node.getLeft().getValue() : "null");
                vars.setText(varsText);
                highlightMov(node, removeHighlighter);
                highlightsText(FIND, DELETE_CODE);
                highlightsText(FIND_IF, DELETE_CODE);
            }
            if (node.getLeft() == null) {
                if (highlight) {
                    highlightsText(FIND_RETURN, DELETE_CODE);
                    highlightMov(null, removeHighlighter);
                }
                return node;
            } else {
                if (highlight) {
                    highlightsText(FIND_ELSE, DELETE_CODE);
                    highlightsText(FIND_RETURN2, DELETE_CODE);
                    highlightMov(node.getLeft(), removeHighlighter);
                }
                return findSubstitute(node.getLeft(), highlight);
            }
        } else {
            return null;
        }
    }

    public void nextMovement(String correct, Score score) {
        int answer;
        String[] options = {LEFT_NEXT_MOV, RIGHT_NEXT_MOV};
        boolean repeatQuestion = false;
        int cont = 0;

        do {
            answer = JOptionPane.showOptionDialog(textPane, "Qual é o próximo movimento?", "Pergunta",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);

            //se a janela for fechada
            if (answer == -1) {
                repeatQuestion = true;
            } else { //se errou
                if (options[answer] == null ? correct != null : !options[answer].equals(correct)) {

                    JOptionPane.showMessageDialog(textPane, "Resposta incorreta!");
                    repeatQuestion = true;
                } else { //se acertou

                    repeatQuestion = false;
                }
            }
            cont++;
        } while (repeatQuestion);
        if (cont == 1) {
            score.addCorrect();
            score.addTotal();
        } else {
            score.addTotal();
        }
    }

    //Pergunta qual é o nó substituto
    private void whatIsSubstitute(Node3D node) {
        //Node3D substitute = findSubstitute(node.getRight(), null, false);
        Node3D substitute = findSubstitute(node.getRight(), false);

        ArrayList<Node3D> optionsNodes = new ArrayList<Node3D>();
        root.getAllChildren(root, optionsNodes);
        optionsNodes.add(root);
        optionsNodes.remove(node);

        Integer[] options = new Integer[optionsNodes.size()];
        for (int i = 0; i < optionsNodes.size(); i++) {
            options[i] = optionsNodes.get(i).getValue();
        }

        swapPositions(options);

        boolean repeatQuestion = false;
        int answer;
        do {
            JOptionPane question = new JOptionPane();
            question.setMaximumSize(new Dimension(TEXT_AREA_COLUMNS, TEXT_AREA_ROWS));

            answer = question.showOptionDialog(textPane, "Utilizando a estratégia \"menor dos \nmaiores\", qual é o nó substituto?", "Pergunta",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
            if (answer == -1) {
                repeatQuestion = true;
            } else {
                if (options[answer] != substitute.getValue()) {
                    JOptionPane.showMessageDialog(textPane, "Resposta incorreta!");
                    repeatQuestion = true;
                } else {
                    repeatQuestion = false;
                }
            }
        } while (repeatQuestion);
    }

    //Esta funcao embaralha um vetor
    private void swapPositions(Integer[] vector) {
        //Quantidade de vezes que vai trocar posições
        int loop = (int) (Math.random() * 20);
        for (int j = 0; j < loop; j++) {

            int i = (int) (Math.random() * vector.length);
            int k = (int) (Math.random() * vector.length);
            //Se o valor aleatorio for igual, não faz a troca
            if (i != k) {
                int temp = vector[i];
                vector[i] = vector[k];
                vector[k] = temp;
            }
        }

    }

    void updateInsertButton() {
        if (nodes.size() > 0 && insertButton != null) {
            insertButton.setEnabled(true);
        } else {
            if (insertButton != null) {
                insertButton.setEnabled(false);
            }
        }
    }

    private void setRemoveVars(int element, String node, String left, String right, String temp) {
        String varsText = "valor = " + element + newRow
                + "no = " + node + newRow
                + "no.esquerda = " + left + newRow
                + "no.direita = " + right;
        if (temp != null) {
            varsText += newRow + "temp = " + temp;
        }

        vars.setText(varsText);
        sleep(SLEEP_TIME);
    }

    private void showHighlighter(TransformGroup highlighter) {
        Transform3D tfInitial = new Transform3D();
        tfInitial.setTranslation(new Vector3f(0.0f, Object3DFactory.yInitial, 0.0f));
        highlighter.setTransform(tfInitial);
    }

    private void updateInsertVars(Node3D child, Node3D node) {
        String varsText = "valor = " + ((child != null) ? child.getValue() : "null") + newRow;
        if (node == null) {
            varsText += "no = null\nno.esquerda = null\nno.direita = null";
        } else {
            varsText += "no = " + node.getValue() + newRow
                    + "no.esquerda = " + ((node.getLeft() != null) ? node.getLeft().getValue() : "null") + newRow
                    + "no.direita = " + ((node.getRight() != null) ? node.getRight().getValue() : "null");
        }
        vars.setText(varsText);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Explicações");
        menu.setMnemonic(KeyEvent.VK_F1);

        menuBar.add(menu);

        JMenuItem menuItem = new JMenuItem("Inserção", KeyEvent.VK_I);
        menuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                showInfo("Inserção", INSERTION_INFO);
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Busca", KeyEvent.VK_B);
        menuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                showInfo("Busca", SEARCH_INFO);
            }
        });
        menu.add(menuItem);

        menuItem = new JMenuItem("Remoção", KeyEvent.VK_R);
        menuItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                showInfo("Remoção", REMOVE_INFO);
            }
        });
        menu.add(menuItem);

        return menuBar;
    }

    public void init() {
        //Cor e background do texto não destacado
        StyleConstants.setForeground(deafaultText, Color.black);
        StyleConstants.setBackground(deafaultText, Color.white);
        //Cor e background do texto destacado
        StyleConstants.setForeground(highlightedText, Color.blue);
        StyleConstants.setBackground(highlightedText, Color.yellow);

        createFrame();
    }

    private void showInfo(String title, String info) {
        JFrame frame = new JFrame(title);
        frame.setSize(TEXT_AREA_COLUMNS, TEXT_AREA_ROWS);
        frame.setResizable(false);
        JTextPane text = new JTextPane();
        text.setText(info);
        text.setEditable(false);

        JScrollPane scroll = new JScrollPane(text);

        frame.add(scroll);
        frame.setVisible(true);
        frame.setLocationRelativeTo(textPane);
        frame.setAlwaysOnTop(true);
    }

    public int getNodeHeight(Node3D node) {
        int h = -1;
        if (node != null) {
            //Enquanto node nao for o root
            while (node.getParent() != null) {
                node = node.getParent();
                h++;
            }
        }
        return h;
    }

    public int prevNodeHeight(int n) {
        int cont = 1;
        Node3D node = root;

        while (node != null) {
            cont++;
            if (node.getValue() == n) {
                break;
            } else if (n < node.getValue()) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
        return cont;
    }

    public Score delete(int num) {
        Score score = new Score();
        delete(num, root, score);
        return score;
    }

    public Node3D balance(Node3D node) {
        //highlightsText(AVL_TITLE, AVL_CODE);
        highlightsText(BALANCE, AVL_CODE);
        highlightsText(BALANCE_IF_NULL, AVL_CODE);
        if (node == null) {
            highlightsText(BALANCE_NULL, AVL_CODE);
            return null;
        }
        boolean greater2Highlighted = false;
        highlightsText(BALANCE_GREATER, AVL_CODE);
        if (this.getHBinaryTree(node.getRight()) - this.getHBinaryTree(node.getLeft()) > 1) {
            highlightsText(BALANCE_LOWER, AVL_CODE);
            if (this.getHBinaryTree(node.getRight().getRight()) - this.getHBinaryTree(node.getRight().getLeft()) < -1) {
                highlightsText(BALANCE_DOUBLE_L, AVL_CODE);
                node = doubleLeftRotate(node);
            } else {
                highlightsText(BALANCE_ELSE, AVL_CODE);
                highlightsText(BALANCE_SINGLE_L, AVL_CODE);
                node = singleLeftRotate(node);
            }
        } else if (this.getHBinaryTree(node.getLeft()) - this.getHBinaryTree(node.getRight()) > 1) {
            highlightsText(BALANCE_GREATER2, AVL_CODE);
            greater2Highlighted=true;
            highlightsText(BALANCE_GREATER3, AVL_CODE);
            if (this.getHBinaryTree(node.getLeft().getRight()) - this.getHBinaryTree(node.getLeft().getLeft()) > 1) {
                highlightsText(BALANCE_DOUBLE_R, AVL_CODE);
                node = doubleRightRotate(node);
            } else {
                highlightsText(BALANCE_ELSE2, AVL_CODE);
                highlightsText(BALANCE_SINGLE_R, AVL_CODE);
                node = singleRightRotate(node);
            }
        }
        if(!greater2Highlighted){
            highlightsText(BALANCE_GREATER2, AVL_CODE);
        }
        highlightsText(BALANCE_RETURN, AVL_CODE);
        return node;
    }

    Node3D singleLeftRotate(Node3D node) {
        if (node == null) {
            return null;
        }

        try {
//            Node3D right = node.getRight();
//            while (right != null) {
//                right = right.getLeft();
//            }
//            if (right != null) {
////                Transform3D tf3d = right.getTfNode();
////                Vector3f v3f = new Vector3f();
////                tf3d.get(v3f);
////                v3f.y += 0.40999982;
////                v3f.x += 1.6399995 / (getNodeHeight(right) - 1);
////                tf3d.set(v3f);
////                right.getTgNode().setTransform(tf3d);
////
////                moveBalance(right.getLeft(), 1, 1, -1);
//
//                //*********
//                right.getTgNode().setTransform(node.getTfNode());
//                //*********
//
//                Node3D rl;
//                int aux;
//                if (right.getRight() != null) {
//                    rl = right.getRight();
//                    aux = 1;
//                } else {
//                    rl = right.getLeft();
//                    aux = -1;
//                }
//                if (rl != null) {
//                    Transform3D tfRl = rl.getTfNode();
//                    Vector3f vRL = new Vector3f();
//                    tfRl.get(vRL);
//                    vRL.y += 0.40999982;
//                    vRL.x -= (1.6399995 / (getNodeHeight(rl) + 1)) * aux;
//                    tfRl.set(vRL);
//                    rl.getTgNode().setTransform(tfRl);
//
//                    moveBalance(rl.getLeft(), 1, -1, 1);
//                    moveBalance(rl.getRight(), 1, -1, 1);
//                }
//            }
//            Transform3D tfNode = node.getTfNode();
//            Vector3f vNode = new Vector3f();
//            tfNode.get(vNode);
//            vNode.y -= 0.40999982;
//            vNode.x += 1.6399995 / (getNodeHeight(node));
//            tfNode.set(vNode);
//            node.getTgNode().setTransform(tfNode);
//
//            moveBalance(node.getLeft(), -1, 1, 0);
//            moveBalance(node.getRight(), -1, 1, 0);


////*********************************************************************
//            Node3D temp = node.getRight();
//            if(temp != null){
//                temp.getTgNode().setTransform(node.getTfNode());
//            }
//*********************************************************************
//
            Node3D p, oldRight;
            oldRight = node.getRight();
            p = node.getRight().getLeft();
            node.getRight().setLeft(null);
            if (p == null) {
                p = node.getRight();
            } else {
                p.setRight(node.getRight());
            }
            node.setRight(null);
            //node.setRight(p.getLeft());
            p.setLeft(node);

            if (p.getRight() != null && p.getRight() != oldRight) {
                p.getRight().getTgNode().setTransform(p.getTfNode());
            }
            p.getTgNode().setTransform(node.getTfNode());

            TransformGroup tgTemp = new TransformGroup(node.getTfNode());
            float d = (node == root) ? DISTANCE / 5 : (DISTANCE / 2) / 5;
            Transform3D tf = insert3D(null, LEFT, d, true, tgTemp);

            node.getTgNode().setTransform(tf);
//            
//            Transform3D tfNode = node.getTfNode();
//            Vector3f vNode = new Vector3f();
//            tfNode.get(vNode);
//            vNode.y -= 0.40999982;
//            vNode.x += 1.6399995 / (getNodeHeight(node));
//            tfNode.set(vNode);
//            node.getTgNode().setTransform(tfNode);

//
//            Node3D nNode;
//            if (node.getRight() != null) {
//                nNode = node.getRight();
//                if (nNode.getLeft() != null) {
//                    nNode = node.getRight().getLeft();
//                }
//            }else{
//                return root;
//            }
//            nNode.setLeft(node);
//            return nNode;
            updateConnections(root, 0);
            return p;

        } catch (NullPointerException ex) {
            ex.printStackTrace();
            updateConnections(root, 0);
        }
        return root;
    }

    Node3D singleRightRotate(Node3D node) {
        if (node == null) {
            return null;
        }

        try {

            Node3D p, oldLeft;
            oldLeft = node.getLeft();
            p = node.getLeft().getRight();
            node.getLeft().setRight(null);
            if (p == null) {
                p = node.getLeft();
            } else {
                p.setLeft(node.getLeft());
            }
            node.setLeft(null);
            //node.setRight(p.getLeft());
            p.setRight(node);

            if (p.getLeft() != null && p.getLeft() != oldLeft) {
                p.getLeft().getTgNode().setTransform(p.getTfNode());
            }
            p.getTgNode().setTransform(node.getTfNode());

            TransformGroup tgTemp = new TransformGroup(node.getTfNode());
            float d = (node == root) ? DISTANCE / 5 : (DISTANCE / 2) / 5;
            Transform3D tf = insert3D(null, RIGTH, d, true, tgTemp);

            node.getTgNode().setTransform(tf);
            updateConnections(root, 0);
            return p;

        } catch (NullPointerException ex) {
            ex.printStackTrace();
            updateConnections(root, 0);
        }
        return root;

        /*
         * try { Node3D p; p = node.getLeft(); Transform3D pTf = null; if (p !=
         * null) { pTf = p.getTfNode(); }
         *
         *
         * node.setLeft(p.getRight()); p.setRight(node); return p; } catch
         * (NullPointerException ex) { ex.printStackTrace(); } return root;
         *
         */
    }

    Node3D doubleLeftRotate(Node3D node) {
        Node3D parent = node.getParent();
        Node3D temp = node;
        node = singleRightRotate(node.getRight());
        if (parent != null) {
            if (parent.getLeft() == node) {
                parent.setLeft(node);
            } else {
                parent.setRight(node);
            }
        } else if (temp == root) {
            root = node;
        }
        node = singleLeftRotate(node);
        return node;
    }

    Node3D doubleRightRotate(Node3D node) {
        Node3D parent = node.getParent();
        Node3D temp = node;
        node = singleLeftRotate(node.getLeft());
        if (parent != null) {
            if (parent.getLeft() == node) {
                parent.setLeft(node);
            } else {
                parent.setRight(node);
            }
        } else if (temp == root) {
            root = node;
        }
        node = singleRightRotate(node);
        return node;
    }

    private void doDelete(Node3D node) {
        Node3D parent = searchParent(node.getValue());
        if (parent != null) {
            //Node3D parent = node.getParent();
            if (parent.getLeft() == node) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
        } else {
            root = null;
        }
        ArrayList re = node.getAllChildren(node);
        node.hideNode(node);
        reinsert(re);
    }

    private Node3D searchParent(int num) {
        return searchParent(num, root);
    }

    private Node3D searchParent(int num, Node3D node) {
        Node3D r, l;
        if (node == null) {
            return null;
        } else {
            r = node.getRight();
            l = node.getLeft();
        }
        if (node.getValue() == num) {
            return node.getParent();
        }
        if (r != null) {
            if (r.getValue() == num) {
                return node;
            }
        }
        if (l != null) {
            if (l.getValue() == num) {
                return node;
            }
        }
        if (num < node.getValue()) {
            return searchParent(num, node.getLeft());
        } else {
            return searchParent(num, node.getRight());
        }
    }

    private void moveBalance(Node3D temp, int ySign, int xSign, int hSum) {
        if (temp == null) {
            return;
        }
        try {
            Transform3D tfTemp = temp.getTfNode();
            Vector3f vTemp = new Vector3f();
            tfTemp.get(vTemp);
            vTemp.y += (0.40999982 * ySign);
            vTemp.x += (1.6399995 / (getNodeHeight(temp) + hSum)) * xSign;
            tfTemp.setTranslation(vTemp);
            temp.getTgNode().setTransform(tfTemp);
            moveBalance(temp.getLeft(), ySign, xSign, hSum);
            moveBalance(temp.getRight(), ySign, xSign, hSum);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
