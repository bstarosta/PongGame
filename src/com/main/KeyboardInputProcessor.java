package com.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//Klasa umożliwiająca sterowanie paletką gracza za pomocą klawiatury
public class KeyboardInputProcessor extends KeyAdapter {

    PlayerPaddle playerPaddle;

    public KeyboardInputProcessor(PlayerPaddle paddle) {
        playerPaddle = paddle;
    }

    //Instrukcje wykonywane po wciśnięciu strzałek w górę lub w dół
    public void keyPressed(KeyEvent e) {
        //Ruch w górę
        if(e.getKeyCode()==KeyEvent.VK_UP)
            playerPaddle.setyDirection(-1);
        //Ruch w dół
        if(e.getKeyCode()==KeyEvent.VK_DOWN)
            playerPaddle.setyDirection(1);
    }

    //Zatrzymanie paletki po zwolnieniu strzałki w górę lub w dół
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_UP || e.getKeyCode()==KeyEvent.VK_DOWN )
            playerPaddle.setyDirection(0);
    }
}

