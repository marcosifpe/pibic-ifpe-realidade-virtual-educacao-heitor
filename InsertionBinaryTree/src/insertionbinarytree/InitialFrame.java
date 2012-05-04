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

/**
 *
 * @author heitor
 */
public class InitialFrame extends JFrame implements Runnable{
    
    Thread t;
    boolean isBalanced = false;

    public InitialFrame(String title) {
        super(title);
        
        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton balanced = new JButton("Árvore Binária Balanceada");

        balanced.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                isBalanced = true;
                t.start();
            }

            
        });

        JButton notBalanced = new JButton("Árvore Binária não Balanceada");

        notBalanced.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                isBalanced = false;
                t.start();
            }
        });

        this.getContentPane().add(balanced);
        this.getContentPane().add(notBalanced);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        t = new Thread(this);
        this.setVisible(true);
    }
    
    
    public void run() {
        System.out.println("running");
        
        Component[] components = this.getContentPane().getComponents();
        for(Component c : components){
            c.setVisible(false);
        }
        this.getContentPane().add(new JLabel("Carregando..."));
        InsertionBinaryTree tree = new InsertionBinaryTree(isBalanced);
        tree.init();
        this.dispose();
    }
    
}
