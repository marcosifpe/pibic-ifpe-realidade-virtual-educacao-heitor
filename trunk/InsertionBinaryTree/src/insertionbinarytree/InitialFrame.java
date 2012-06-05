/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package insertionbinarytree;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author heitor
 */
public class InitialFrame extends JFrame implements Runnable {

    Thread t;
    boolean isBalanced = false;

    public InitialFrame(String title) {
        super(title);

        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton balanced = new JButton("Árvore AVL");

        balanced.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                enviroment = Enviroment.BALANCED;
                isBalanced = true;
                t.start();
            }
        });

        JButton notBalanced = new JButton("Árvore Binária");

        notBalanced.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                enviroment = Enviroment.NOT_BALANCED;
                isBalanced = false;
                t.start();
            }
        });

        JButton sort = new JButton("Algoritmos de Ordenação");
        sort.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                enviroment = Enviroment.SORT;
                t.start();
            }
        });

        this.getContentPane().add(balanced);
        this.getContentPane().add(notBalanced);
        this.getContentPane().add(sort);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        t = new Thread(this);
        this.setVisible(true);
    }
    Enviroment enviroment;

    private void startTree(boolean balanced) {
        InsertionBinaryTree tree = new InsertionBinaryTree(balanced);
        tree.init();
    }

    public enum Enviroment {
        NOT_BALANCED,
        BALANCED,
        SORT
    }

    public void run() {
        System.out.println("running");

        Component[] components = this.getContentPane().getComponents();
        for (Component c : components) {
            c.setVisible(false);
        }
        this.getContentPane().add(new JLabel("Carregando..."));

        switch (enviroment) {
            case NOT_BALANCED:
                startTree(false);
                break;
            case BALANCED:
                startTree(true);
                break;
            case SORT:
                //Sorting.Main sort = new Sorting.Main();
                JOptionPane.showMessageDialog(null, "Não disponível");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida", "ERRO", JOptionPane.ERROR_MESSAGE);
                System.exit(-1);
        }
        this.dispose();
    }
}