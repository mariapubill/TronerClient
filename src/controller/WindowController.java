package controller;

import view.MainView;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import static controller.Controller.ranking;


public class WindowController implements WindowListener, ComponentListener{
    private MainView mainView;
    private Controller controller;


    public WindowController(MainView mainView,Controller controller){
        this.mainView = mainView;
        this.controller = controller;

    }

    public void componentResized(ComponentEvent e) {
     //   Component currentLayout = mainView.getBgPanel().getComponent(controller.getActualLayout());
     //   controller.setActualLayout(controller.getActualLayout());
        //controller.setActualLayout(controller.ge);
       // System.out.println(controller.getActualLayout());
            mainView.getMenuView().changeSize(mainView,ranking);

            //mainView.getBgPanel().remove(controller.getActualLayout() - 1);
           // mainView.getBgPanel().add(controller.getActualLayout().toString(), selectJpanel());
           // mainView.changePanel(controller.getActualLayout().toString());
             mainView.getBgPanel().revalidate();
             mainView.getBgPanel().repaint();

        //.registerController(controller);
    //  e.getComponent().getA
    }
    /*
    private JPanel selectJpanel() {
        JPanel jPanel = new JPanel();
        switch (controller.getActualLayout()){
            case 1:
                LabelClass labelClass = new LabelClass();
                return labelClass;
            case 2:
                LogSignPanel logSignPanel = new LogSignPanel();
                return logSignPanel;
                //A mirar el redimensionamiento si se quiere
            case 3:
                return new LogInPanel();
                //Lo mismo
            case 4:
                return new SignInPanel();
            case 5:
                mainView.setBgPanel(controller.getActualLayout());
                MenuView menuView = new MenuView(mainView);
                return menuView;
            case 6:
            case 7:
        }
        return jPanel;
    }*/

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
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {
        //CASO A = RESTAR PUNTOS


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
}