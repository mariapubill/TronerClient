package controller;


import network.NetworkServiceUser;
import view.MainView;

public class Server extends Thread {
    private NetworkServiceUser networkService;
    private FunctionController functionController;
    private Controller controller;
    private MainView mainView;

    public Server(MainView mainView,Controller controller){
        this.mainView = mainView;
        this.controller =controller;

    }



    public void run(){
        System.out.println("run");
        NetworkServiceUser networkService = new NetworkServiceUser(mainView);
        if(networkService.isOn()) {
            controller.setOn(true);
            System.out.println("Estamos dentro puto");
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


}
