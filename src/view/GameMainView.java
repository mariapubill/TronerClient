package view;




import controller.GameController;
import model.ServerGrid;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;


public class GameMainView extends JFrame{

    private final int files = 120;
    private final int columnes = 120;



    private JPanel[][] jTauler; //matriu (caselles)
    private JPanel jpMainView;
    private JPanel jpGrid;


    public GameMainView(){
        setSize(700,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        jpMainView = new JPanel();
        jpMainView.setLayout(new BorderLayout());

        jTauler = new JPanel[files][columnes];
        getContentPane().setLayout(new GridLayout(files, columnes));

        for(int i = 0; i<files; i++){
            for(int j=0; j<columnes; j++){
                jTauler[i][j]=new JPanel();
                jTauler[i][j].setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
                jTauler[i][j].setOpaque(false);
                getContentPane().add(jTauler[i][j]);
            }
        }
        //super.setSize(120*3, 120*3);
    }

    public void actionPerformed(GameController c) {

        this.addKeyListener(c);
    }

    /**
     * procediment que si es necessàri actualitza el color de les caselles, segons si hi ha client o no (amb el seu respectiu color)
     * @param matrix rebem un char (0: transparent, 1,2,3,4:indiquen quin client i per tant quin color)
     */
    public void canviaGrid(ServerGrid matrix) {
        for (int i=0; i<files; i++) {
            for (int j=0; j<columnes; j++) {
                if (matrix.getServerGrid()[i][j]==0) {
                    jTauler[i][j].setOpaque(false);
                }
                if (matrix.getServerGrid()[i][j]==1) {
                    jTauler[i][j].setBackground(Color.CYAN);
                }
                if (matrix.getServerGrid()[i][j]==2) {
                    jTauler[i][j].setBackground(Color.black);
                }
                if (matrix.getServerGrid()[i][j]==3) {
                    jTauler[i][j].setBackground(Color.blue);
                }
                if (matrix.getServerGrid()[i][j]==4) {
                    jTauler[i][j].setBackground(Color.red);
                }

            }
        }

    }
    public long getFiles() {
        return files;
    }

    public long getColumnes() {
        return columnes;
    }
}

