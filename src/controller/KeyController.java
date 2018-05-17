package controller;

import view.MainView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class KeyController {
    private MainView view;
    private Integer actualLayout;
    private Music music;
    private boolean soundNow;
    private boolean closeSound;
    private boolean stopMusic = true;
    private boolean duringThread=false;

    private Timer t;


    public KeyController(MainView view) {
        closeSound = false;
        soundNow = false;
        this.view = view;
        actualLayout =1;


    }




}
