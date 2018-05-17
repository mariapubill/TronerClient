package controller;

import model.User;
import network.NetworkServiceUser;
import view.MainView;

import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

import static controller.Controller.ranking;

public class FunctionController extends Thread implements ActionListener,WindowListener,ComponentListener, KeyListener {
    private NetworkServiceUser nService;
    private boolean login;
    private boolean signin;
    private MainView view;
    private Integer actualLayout = 1;
    private User userActual;
    private boolean topMov;
    private boolean downMov;
    private boolean leftMov;
    private boolean rightMov;
    private Music music;
    private boolean soundNow;
    private boolean closeSound;
    private boolean stopMusic;
    private boolean duringThread;

    private int keyDown = KeyEvent.VK_DOWN;
    private int keyTop = KeyEvent.VK_UP;
    private int keyLeft = KeyEvent.VK_LEFT;
    private int keyRight = KeyEvent.VK_RIGHT;




    public  FunctionController(NetworkServiceUser nService, MainView view){
       this.nService = nService;
       this.nService.startServerComunication();
        music = new Music(this);
        closeSound = false;
        soundNow = false;

        this.view = view;
       topMov =false;
       downMov=false;
       leftMov=false;
       rightMov = false;
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Login")){
            nService.sendParameter("login");
            login = nService.checkIfIsOkay(view.getUserLogin());
            if(login){
                actualLayout = 5;
                view.changePanel(actualLayout.toString());
                userActual  = nService.getUser();
                view.getMenuView().setNamed((userActual.getNickname()));
                view.getMenuView().setGame2(Integer.toString(userActual.recountType(1)));
                view.getMenuView().setGame4(Integer.toString(userActual.recountType(2)));
                view.getMenuView().setTournament(Integer.toString(userActual.recountType(3)));
                //ranking = nService.getRanking();
               // view.getMenuView().changeTable(ranking);
              //  user.getScore()

            }else{
                JOptionPane.showMessageDialog(null, "CAN'T LOGIN","Inane error", JOptionPane.ERROR_MESSAGE);

            }
        }else if(e.getActionCommand().equals("Signin")){
            if(checkSignIn() != 4){
                setErrorMessage();
            }else {
                nService.sendParameter("sign");
                signin = nService.checkIfIsOkay(view.getUserSignIn());
                if (signin) {
                    actualLayout = 5;
                    view.changePanel(actualLayout.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "CANT SIGNIN", "Inane error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else if (e.getActionCommand().equals("2game")){
            nService.sendParameter("2game");

        }else if (e.getActionCommand().equals("4game")){
            ((JButton)e.getSource()).getTopLevelAncestor().requestFocus();
        }else if (e.getActionCommand().equals("Tournament")){
            ((JButton)e.getSource()).getTopLevelAncestor().requestFocus();
        }
        else if(e.getActionCommand().equals("top")) {
            ((JButton) e.getSource()).getTopLevelAncestor().requestFocus();
            view.showDialog("t");
            topMov = true;
        }else if(e.getActionCommand().equals("down")){
            ((JButton)e.getSource()).getTopLevelAncestor().requestFocus();
            view.showDialog("d");
            downMov=true;
        }
        else if(e.getActionCommand().equals("left")) {
            ((JButton) e.getSource()).getTopLevelAncestor().requestFocus();
            view.showDialog("l");
            leftMov = true;

        }else if(e.getActionCommand().equals("right")){
            ((JButton)e.getSource()).getTopLevelAncestor().requestFocus();
            view.showDialog("r");
            rightMov=true;

        } else if(e.getActionCommand().equals("Settings")){
            ((JButton)e.getSource()).getTopLevelAncestor().requestFocus();
            actualLayout=6;
            view.changePanel(actualLayout.toString());

        }
        else if(e.getActionCommand().equals("backSettings")){
            ((JButton)e.getSource()).getTopLevelAncestor().requestFocus();
            System.out.println("entra al back settings");
            actualLayout = 5;
            view.changePanel(actualLayout.toString());
        }

    }


    public void setErrorMessage(){
        switch (checkSignIn()){
            case 1:
                JOptionPane.showMessageDialog(null, "Invalid Email","Inane error", JOptionPane.ERROR_MESSAGE);
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "Passwords don't match ","Inane error", JOptionPane.ERROR_MESSAGE);
                break;
            case 3:
                JOptionPane.showMessageDialog(null, "Invalid passowrd","Inane error", JOptionPane.ERROR_MESSAGE);
                break;
            case 5:
                JOptionPane.showMessageDialog(null, "Empty Fields","Inane error", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    public int checkSignIn(){
        if(!checkEmail(view.getEmail())){
            return 1;
        }else if(!checkBothPasswords(view.getPasswordS(), view.getRepeatPasswordS())){
            return 2;
        }else if(!checkPassword(view.getPasswordS())){
            return 3;
        }else if(checkFields(view.getNicknameS(),view.getEmail(),view.getPasswordS(),view.getRepeatPasswordS())) {
            return 5;
        }else{
            return 4;
        }

    }

    public boolean checkEmail(String email){
        boolean ok = false;
        for(int i = 0; i < email.length(); i++){
            if(email.charAt(i) == '@') {
                ok = true;
            }
        }
        return ok;
    }
    public boolean checkBothPasswords(String p1, String p2){
        return p1.equals(p2);
    }
    public boolean checkPassword(String p){
        boolean ok = false;
        boolean isUpperCase=false;
        boolean isLowerCase = false;
        boolean isDigit=false;

        if(p.length() > 5 ){
            for(int i = 0; i < p.length(); i++){
                if(Character.isUpperCase(p.charAt(i))){
                    isUpperCase=true;
                }
                if(Character.isLowerCase(p.charAt(i))){
                    isLowerCase=true;
                }
                if(Character.isDigit(p.charAt(i))){
                    isDigit=true;
                }
            }
            ok = isDigit&isLowerCase&isUpperCase;
        }
        return ok;
    }

    public boolean checkFields(String p1, String p2, String p3, String p4){
        boolean ok = false;
        if(p1.equals("Please input User Name")|| p2.equals("example@gmail.com")|| p3.equals("Minimum 8 carachters")|| p4.equals("Minimum 8 carachters")||p1.equals("")|| p2.equals("")|| p3.equals("")|| p4.equals("")){
            ok=true;
        }
        return ok;
    }


    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {
            nService.writeUser(userActual);
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void componentResized(ComponentEvent e) {

        view.getMenuView().changeSize(view,ranking);
        view.getBgPanel().revalidate();
        view.getBgPanel().repaint();
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
       // System.out.println("typed " + e.getKeyChar() +" with code "+e.getExtendedKeyCode());
        if(topMov&!downMov&&!leftMov&!rightMov&& e.getExtendedKeyCode()!=keyDown && e.getExtendedKeyCode()!=keyRight && e.getExtendedKeyCode()!=keyLeft && actualLayout==6 ){
            if(e.getExtendedKeyCode() == KeyEvent.VK_UP){
                view.setCharTop("arrow");
                keyTop = KeyEvent.VK_UP;
            }else if(e.getExtendedKeyCode()!=keyDown && e.getExtendedKeyCode()!=keyLeft && e.getExtendedKeyCode()!=keyRight){
                view.setCharTop(Character.toString(e.getKeyChar()));
                keyTop = e.getExtendedKeyCode();

            }
            topMov=false;
        }
        else if(!topMov&&downMov&&!leftMov&!rightMov && e.getExtendedKeyCode()!=keyTop && e.getExtendedKeyCode()!=keyRight && e.getExtendedKeyCode()!=keyLeft && actualLayout==6  ){
            System.out.println("key top "+keyTop+" el q reb "+e.getExtendedKeyCode());
            if(e.getExtendedKeyCode()==KeyEvent.VK_DOWN){
                view.setCharDown("arrow");
                keyDown = KeyEvent.VK_DOWN;
            }else if(e.getExtendedKeyCode()!=keyTop && e.getExtendedKeyCode()!=keyLeft && e.getExtendedKeyCode()!=keyRight){
                keyDown = e.getExtendedKeyCode();
                view.setCharDown(Character.toString(e.getKeyChar()));
            }
            downMov=false;
        }
        else if(!topMov&!downMov&leftMov&!rightMov & e.getExtendedKeyCode()!=keyDown & e.getExtendedKeyCode()!=keyRight & e.getExtendedKeyCode()!=keyTop && actualLayout==6 ){
            if(e.getExtendedKeyCode() == KeyEvent.VK_LEFT){
                view.setCharLeft("arrow");
                keyLeft = KeyEvent.VK_LEFT;
            }else if(e.getExtendedKeyCode()!=keyTop && e.getExtendedKeyCode()!=keyDown && e.getExtendedKeyCode()!=keyRight){
                keyLeft = e.getExtendedKeyCode();
                view.setCharLeft(Character.toString(e.getKeyChar()));
            }

            leftMov=false;
        }else if(!topMov&!downMov&!leftMov&rightMov & e.getExtendedKeyCode()!=keyDown & e.getExtendedKeyCode()!=keyTop & e.getExtendedKeyCode()!=keyLeft && actualLayout==6 ){
            if(e.getExtendedKeyCode() == KeyEvent.VK_RIGHT){
                keyRight = KeyEvent.VK_RIGHT;
                view.setCharRight("arrow");
            }else if(e.getExtendedKeyCode()!=keyTop && e.getExtendedKeyCode()!=keyLeft && e.getExtendedKeyCode()!=keyDown){
                keyRight = e.getExtendedKeyCode();
                view.setCharRight(Character.toString(e.getKeyChar()));
            }
            rightMov=false;
        }

        if (e.getKeyCode() == 27) {
            switchAndChange();
        }
        if (e.getKeyCode() == 10 && actualLayout == 1) {
            //actualLayout = 2;
            //music.stopMusic();
            //music.runMusic(new File("data/Laser.wav"));

            stopMusic();
            // isBack = true;
            //t.start();
            soundNow = true;
            Thread t = new Thread(new Runnable() {
                public void run() {
                    try {

                        TimeUnit.MILLISECONDS.sleep(1600);
                        //music.stopMusic();
                        TimeUnit.MILLISECONDS.sleep(500);
                        //actualLayout++;
                        actualLayout++;
                        view.changePanel(actualLayout.toString());
                        soundNow = false;
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            t.start();

            //closeSound = false;
            //startMusic();
            //music.runMusic(new File("data/LogInTheme.wav"));

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    private void switchAndChange() {
        switch (actualLayout) {
            case 1:
                if (view.showDialog("¿Desea salir del juego?", actualLayout)) {
                    System.exit(0);
                }
                break;
            case 3:
                actualLayout = 2;
                view.changePanel(actualLayout.toString());
                break;
            case 4:
                actualLayout = 2;
                view.changePanel(actualLayout.toString());
                break;
            case 5:
                if (view.showDialog("¿Desea volver a la pantalla de inicio?", actualLayout)) {
                    view.clearFieldsLogin();
                    actualLayout = 1;
                    view.changePanel(actualLayout.toString());
                }
                break;
            case 7:
                if (view.showDialog("Si abandona la partida sera penalizado.\n¿esta usted seguro de abandonar?", actualLayout)) {
                    actualLayout = 5;
                    view.changePanel(actualLayout.toString());
                }
            default:
                actualLayout--;
                view.changePanel(actualLayout.toString());
                break;
        }


    }

    public Integer getActualLayout() {
        return actualLayout;
    }

    public void setActualLayout(Integer actualLayout) {
        this.actualLayout = actualLayout;
    }

    private void stopMusic() {
        this.stopMusic = false;
    }

    public void startMusic() {
        this.stopMusic = true;
    }
    public boolean getMusicOn(){
        return stopMusic;
    }

    public boolean getCloseSound() {
        return closeSound;
    }

    public boolean getSoundNow() {
        return soundNow;
    }

    public void setSoundNow(boolean soundNow) {
        this.soundNow = soundNow;
    }

    public void setMusicOn(boolean musicOn) {
        this.stopMusic = musicOn;
    }

    public void setCloseSound(boolean closeSound) {
        this.closeSound = closeSound;
    }
    public boolean duringThread() {
        return duringThread;
    }
}
