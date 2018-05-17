package controller;


import network.NetworkServiceUser;
import view.MainView;

import java.io.IOException;

public class Server extends Thread {
    private NetworkServiceUser networkService;
    private FunctionController functionController;
    private Controller controller;
    private MainView mainView;
  //  private NetworkServiceUser networkService;

    public Server(MainView mainView,Controller controller){
        this.mainView = mainView;
        this.controller =controller;

    }



    public void run(){
        System.out.println("run");
        networkService = new NetworkServiceUser(mainView);
        if(networkService.isOn()) {
            controller.setOn(true);
            functionController = new FunctionController(networkService, mainView);
            mainView.registerFunctionController(functionController);
            System.out.println(controller.isOn()+"tred");
            controller.setEnd(true);
        }
    }

    public NetworkServiceUser getNetworkService() {
        return networkService;
    }

    public void setNetworkService(NetworkServiceUser networkService) {
        this.networkService = networkService;
    }


    public void closeSession() {

    }
    public void disconnectUser(){
        try {
            networkService.getDoStreamO().writeObject(functionController.getUserActual());//;functionController.getUserActual())
        }catch (IOException e){

        }
    }

}
