package controller;

import model.User;
import network.NetworkServiceUser;
import view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FunctionController extends Thread implements ActionListener {
    private NetworkServiceUser nService;
    private boolean login;
    private boolean signin;
    private MainView view;
    private Integer actualLayout = 1;
    private User userAux;



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
            if(login){
                actualLayout = 5;
                view.changePanel(actualLayout.toString());
            }else{
                JOptionPane.showMessageDialog(null, "CAN'T LOGIN","Inane error", JOptionPane.ERROR_MESSAGE);

            }
        }else if(e.getActionCommand().equals("Signin")){
            nService.sendParameter("sign");
            signin = nService.checkIfIsOkay(view.getUserSignIn());
            if(signin){
                actualLayout = 5;
                view.changePanel(actualLayout.toString());
            }else{
                JOptionPane.showMessageDialog(null, "CANT SIGNIN","Inane error", JOptionPane.ERROR_MESSAGE);

            }

        }

    }
}
