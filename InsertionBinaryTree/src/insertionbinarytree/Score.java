/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package insertionbinarytree;

import java.awt.Component;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author heitor
 */
public class Score {

    private int total = 0;
    private int correct = 0;
    private final static int HIDE_SCORE = -1;

    public int getCorrect() {
        return correct;
    }

    public void addCorrect() {
        correct++;
    }

    public double getScore() {
        if (total != 0) {
            double result = ((double) correct / total)*100;
            return result;
        } else {
            return HIDE_SCORE;
        }
    }

    public void addTotal() {
        total++;
    }

    public int getTotal() {
        return total;
    }

    void show(Component comp) {
        double score = getScore();
        if(score != HIDE_SCORE){
            DecimalFormat decimal = new DecimalFormat("0.00");
            JOptionPane.showMessageDialog(comp, "Você alcançou uma pontuação de " + decimal.format(score)+"%", "Pontuação", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
