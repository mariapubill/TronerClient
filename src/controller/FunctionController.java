package controller;

import model.User;
import network.NetworkServiceUser;
import view.MainView;

import javax.swing.*;
import java.awt.event.*;

import static controller.Controller.ranking;

public class FunctionController extends Thread implements ActionListener,WindowListener,ComponentListener {
    private NetworkServiceUser nService;
    private boolean login;
    private boolean signin;
    private MainView view;
    private Integer actualLayout = 1;
    private User userActual;




    public  FunctionController(NetworkServiceUser nService, MainView view){
       this.nService = nService;
       this.nService.startServerComunication();
       this.view = view;
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Login")){
            nService.sendParameter("login");
            login = nService.checkIfIsOkay(view.getUserLogin());
            System.out.println(login+"      123145");
            if(login){
                userActual  = nService.getUser();
                System.out.println("entra");
                System.out.println(userActual.getNickname());
                view.getMenuView().setNamed((userActual.getNickname()));
                view.getMenuView().setGame2(Integer.toString(userActual.recountType(1)));

                view.getMenuView().setGame4(Integer.toString(userActual.recountType(2)));
                view.getMenuView().setTournament(Integer.toString(userActual.recountType(3)));
                ranking = nService.getRanking();
                System.out.println("recibe ranking");
                view.getMenuView().changeTable(ranking);
                System.out.println("TENDRIA KE CAMB");
              //  user.getScore()
                actualLayout = 5;
                view.changePanel(actualLayout.toString());
                System.out.println("faiòjdsfàsdijpfasi`dfjas`d");
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
        System.out.println(p1+" "+p2);
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
}
