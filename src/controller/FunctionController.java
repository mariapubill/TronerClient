package controller;

import network.NetworkServiceUser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FunctionController extends Thread implements ActionListener {
    private NetworkServiceUser nService;

   public  FunctionController(NetworkServiceUser nService){
       this.nService = nService;
       this.nService.startServerComunication();

   }




    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Login")){
            nService.sendParameter("login");
        }

    }
}
