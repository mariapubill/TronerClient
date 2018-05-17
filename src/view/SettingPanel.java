package view;



import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class SettingPanel extends JPanel{
    private JButton jbLeft;
    private JButton jbRight;
    private JButton jbTop;
    private JButton jbDown;
    private MainView mainView;
    private JLabel jlLeft;
    private JLabel jlRight;
    private JLabel jlTop;
    private JLabel jlDown;
    private Font font;
    private JButton jbBack;

    private Icon iconTop;
    private Icon iconDown;
    private Icon iconLeft;
    private Icon iconRight;

    private KeyEvent keyEvent;

    public SettingPanel(){



        jbDown = new JButton();
        jbLeft = new JButton();
        jbRight = new JButton();
        jbTop = new JButton();
        jbBack = new JButton();

        jlDown = new JLabel("DOWN");
        jlLeft = new JLabel("  LEFT");
        jlRight = new JLabel("RIGHT  ");
        jlTop = new JLabel("TOP");

        iconTop = new ImageIcon("data/settingsTop.png");
        iconDown = new ImageIcon("data/settingsDown.png");
        iconLeft = new ImageIcon("data/settingsLeft.png");
        iconRight = new ImageIcon("data/settings.png");

        GridBagConstraints gbc = new GridBagConstraints();
        int i = 5;
        int j = 5;
        this.setLayout(new GridLayout(i,j));

        JPanel[][] panelHolder = new JPanel[i][j];
        for(int m = 0; m < i; m++) {
            for(int n = 0; n < j; n++) {
                panelHolder[m][n] = new JPanel();
                panelHolder[m][n].setOpaque(false);
                add(panelHolder[m][n]);
            }
        }
        setOpaque(false);

        createButtons();
        createLabels();

       /* gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(jbBack,gbc);


        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 2;
        gbc.gridy = 1;
        this.add(jbTop,gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 1;
        gbc.gridy = 2;
        this.add(jbLeft,gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 3;
        gbc.gridy = 2;
        this.add(jbRight,gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 2;
        gbc.gridy = 3;
        this.add(jbDown,gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = gbc.CENTER;
        this.add(jlTop,gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.anchor = gbc.CENTER;
        this.add(jlDown,gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.anchor = gbc.CENTER;
        this.add(jlRight,gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = gbc.CENTER;
        this.add(jlLeft,gbc);*/

        panelHolder[2][1].add(jbLeft);
        panelHolder[3][2].add(jbDown);
        panelHolder[2][3].add(jbRight);
        panelHolder[1][2].add(jbTop);

        panelHolder[0][2].setLayout(new GridBagLayout());
        panelHolder[0][2].add(jlTop);

        panelHolder[4][2].setLayout(new GridBagLayout());
        panelHolder[4][2].add(jlDown);

        panelHolder[2][0].setLayout(new GridBagLayout());
        panelHolder[2][0].add(jlLeft);

        panelHolder[2][4].setLayout(new GridBagLayout());
        panelHolder[2][4].add(jlRight);

        panelHolder[0][0].setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = c.FIRST_LINE_START;
        panelHolder[0][0].add(jbBack,c);




    }


    public void createButtons(){
        Image top = new ImageIcon("data/settingsTop.png").getImage();
        top = top.getScaledInstance(100,100,Image.SCALE_DEFAULT);
        jbTop.setIcon(new ImageIcon(top));
        jbTop.setOpaque(false);
        jbTop.setContentAreaFilled(false);
        jbTop.setBorderPainted(false);
        jbTop.setFocusPainted(false);
        jbTop.setFocusPainted(false);
        jbTop.setBorder(new LineBorder(Color.cyan));

        Image right = new ImageIcon("data/settings.png").getImage();
        right = right.getScaledInstance(100,100,Image.SCALE_DEFAULT);
        jbRight.setIcon(new ImageIcon(right));
        jbRight.setOpaque(false);
        jbRight.setContentAreaFilled(false);
        jbRight.setBorderPainted(false);
        jbRight.setFocusPainted(false);
        jbRight.setFocusPainted(false);
        jbRight.setBorder(new LineBorder(Color.cyan));

        Image left = new ImageIcon("data/settingsLeft.png").getImage();
        left = left.getScaledInstance(100,100,Image.SCALE_DEFAULT);
        jbLeft.setIcon(new ImageIcon(left));
        jbLeft.setOpaque(false);
        jbLeft.setContentAreaFilled(false);
        jbLeft.setBorderPainted(false);
        jbLeft.setFocusPainted(false);
        jbLeft.setFocusPainted(false);
        jbLeft.setBorder(new LineBorder(Color.cyan));

        Image down = new ImageIcon("data/settingsDown.png").getImage();
        down = down.getScaledInstance(100,100,Image.SCALE_DEFAULT);
        jbDown.setIcon(new ImageIcon(down));
        jbDown.setOpaque(false);
        jbDown.setContentAreaFilled(false);
        jbDown.setBorderPainted(false);
        jbDown.setFocusPainted(false);
        jbDown.setFocusPainted(false);
        jbDown.setBorder(new LineBorder(Color.cyan));


        Image img = new ImageIcon("data/display/neon.png").getImage();
        img = img.getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        //img.getScaledInstance(2,2,Image.SCALE_FAST);
        jbBack.setIcon(new ImageIcon(img));
        jbBack.setOpaque(false);
        jbBack.setContentAreaFilled(false);
        jbBack.setBorderPainted(false);
        jbBack.setFocusPainted(false);
        jbBack.setFocusPainted(false);
        jbBack.setSize(new Dimension(40, 40));

    }


    public void createLabels(){
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("data/font2.ttf"));
            GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            genv.registerFont(font);
            font = font.deriveFont(30f);

            jlDown.setForeground(new Color(157, 207, 222));
            jlDown.setFont(font);

            jlTop.setForeground(new Color(157, 207, 222));
            jlTop.setFont(font);

            jlRight.setForeground(new Color(157, 207, 222));
            jlRight.setFont(font);

            jlLeft.setForeground(new Color(157, 207, 222));
            jlLeft.setFont(font);

        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void registerController(ActionListener c){
        jbBack.setActionCommand("backSettings");
        jbDown.setActionCommand("down");
        jbLeft.setActionCommand("left");
        jbTop.setActionCommand("top");
        jbRight.setActionCommand("right");

        jbTop.addActionListener(c);
        jbBack.addActionListener(c);
        jbLeft.addActionListener(c);
        jbDown.addActionListener(c);
        jbRight.addActionListener(c);
    }



    public void showDialogTop(){
        font = font.deriveFont(20f);
        jlTop.setFont(font);
        jlTop.setText("<html>Press the <br/>key for up<br/>movement</html>");
    }

    public void writeKeyTop(String c){
        font = font.deriveFont(30f);
        jlTop.setFont(font);
        jlTop.setText(c);
    }

    public void showDialogDown(){
        font = font.deriveFont(20f);
        jlDown.setFont(font);
        jlDown.setText("<html>Press the <br/>key for down<br/>movement</html>");
    }

    public void writeKeyDown(String c){
        font = font.deriveFont(30f);
        jlDown.setFont(font);
        jlDown.setText(c);

    }

    public void showDialogLeft(){
        font = font.deriveFont(20f);
        jlLeft.setFont(font);
        jlLeft.setText("<html>Press the <br/>key for left<br/>movement</html>");
    }
    public void writeKeyLeft(String c){
        font = font.deriveFont(30f);
        jlLeft.setFont(font);
        jlLeft.setText(c);

    }

    public void showDialogRight(){
        font = font.deriveFont(20f);
        jlRight.setFont(font);
        jlRight.setText("<html>Press the <br/>key for right<br/>movement</html>");
    }

    public void writeKeyRight(String c){
        font = font.deriveFont(30f);
        jlRight.setFont(font);
        jlRight.setText(c);

    }

    public void showErrorMessage(String s){
        JOptionPane.showMessageDialog(this,
                s,
                "Inane error",
                JOptionPane.ERROR_MESSAGE);

    }
}
