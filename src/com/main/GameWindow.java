package com.main;

import javax.swing.*;

//Główne okno aplikacji
public class GameWindow {

    //Konstruktor głównego okna. Ustawia parametry okna oraz rozpoczyna rozgrywkę
    public GameWindow(Pong pongGame) {
        JFrame frame = new JFrame("PongGame");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(pongGame);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        pongGame.start();
    }
}
