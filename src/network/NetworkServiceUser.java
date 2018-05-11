package network;

import model.Petition;
import model.ServerGrid;
import model.User;
import view.GameMainView;

import javax.swing.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


/**
 * Permet: (1) rebre les actualitzacions de lestat del model que
 emmagatzema el servidor i (2) enviar de les dades (fnom,producte)
 quan lusuari selecciona la opcio send
 */
public class NetworkServiceUser extends Thread {
    private ObjectOutputStream doStreamO;

    private static final String IP = "127.0.0.1";
    private static final int PORT_USER = 12345;

    private Socket socketToServer;
    private GameMainView finestra;
    private ObjectInputStream objectIn;
    private boolean isOn;



    /**
     * constructor amb paramentres
     * @param finestra ens permetra actualitzar la finestra al rebre una dada
     */
    public NetworkServiceUser(GameMainView finestra) {
        try {
            this.isOn = false;
            this.finestra = finestra;
            this.socketToServer = new Socket(IP, PORT_USER);
            this.doStreamO = new ObjectOutputStream(socketToServer.getOutputStream());
            this.objectIn = new ObjectInputStream(socketToServer.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();

            JOptionPane.showMessageDialog(null, "No esta conectat al servidor, tanqui la finestra", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    /**
     * comença la comunicació amb el servidor
     */
    public void startServerComunication() {
        isOn = true;
        this.start();
    }

    /**
     * Finalitza la comunicacio amb el servidor
     */
    public void stopServerComunication() {
        this.isOn = false;
        this.interrupt();
    }

    public void run() {

        try {
            String aux1 =(String)objectIn.readObject();
            System.out.println("ha llegit el client el seguent objecte "+ aux1);


            while (isOn){

                User hey = new User("client", "guai","email","data register", "data acces");
                // User aux =(User)objectIn.readObject();
               // System.out.println("el client ha llegit desde el while "+aux.getNickname());
                //escolta les actualizacions de lestat del model
                //que envia el servidor quan algun client fa clic
                //a al botor de send7

            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error ha saltat el catch de q n ha pogut llegir be", JOptionPane.ERROR_MESSAGE);
            stopServerComunication();
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            stopServerComunication();
            e.printStackTrace();
        }
        stopServerComunication();
    }

    public void sendParameter(String selection) {
        try {
            doStreamO.writeObject(selection);
            System.out.println("ha escrit el que toca");


        } catch (IOException e) {
            e.printStackTrace();
            stopServerComunication();
            System.out.println("*** ESTA EL SERVIDOR EN EXECUCIO? ***");
        } /*catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }

    public boolean checkIfIsOkay(User userToSend) {
        boolean isOkay = false;
        try {
            doStreamO.writeObject(userToSend);
            isOkay =(boolean)objectIn.readObject();
            System.out.println("mirem si is okay "+ isOkay);


        } catch (IOException e) {
            e.printStackTrace();
            stopServerComunication();
            System.out.println("*** ESTA EL SERVIDOR EN EXECUCIO? ***");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return isOkay;
    }


    public boolean checkReady() {
        boolean ready = false;
        try {
            ready =(boolean)objectIn.readObject();
            System.out.println(ready);


        } catch (IOException e) {
            e.printStackTrace();
            stopServerComunication();
            System.out.println("*** ha saltat el catch del ready ***");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ready;
    }
}
