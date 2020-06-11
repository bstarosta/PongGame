package com.main;

import java.awt.*;

//Klasa modelująca piłkę
public class Ball extends Thread {

    public static final int SIZE = 16;

    private int x;
    private int y;
    private int xDirection;
    private int yDirection;
    private int velocity = 3;

    public Ball() {
        reset();
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void changeXDirectionToLeft(){
        xDirection=-1;
    }

    //
    public void changeXDirectionToRight(){
        xDirection=1;
    }

    //Zmienia kierunek ruchu piłki wzdłuż osi Y na przeciwny
    public void changeYDirection(){
        yDirection*=-1;
    }

    //Zadania wątku
    public void run() {
        while (Pong.isRunning) {
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            move();
            checkScore();
        }
    }

    //Poruszanie się piłki
    public void move() {
        x += xDirection * velocity;
        y += yDirection * velocity;

        if (y <= 0 || y >= Pong.HEIGHT - SIZE)
            changeYDirection();
    }

    //Sprawdza, czy któraś ze stron zdobyła punkt
    private void checkScore() {
        if (x <= 0 ) {
            Pong.aiScore++;
            reset();
        }

        if (x >= Pong.WIDTH - SIZE){
            Pong.playerScore++;
            reset();
        }
    }

    //Przywraca piłkę do punktu startowego oraz nadaje jej losowy kierunek ruchu
    private void reset() {
        x = Pong.WIDTH / 2 - SIZE / 2;
        y = Pong.HEIGHT / 2 - SIZE / 2;
        xDirection = getRandomDirection();
        yDirection = getRandomDirection();
    }

    //Losowo zwraca 1 lub -1.
    private int getRandomDirection() {
        double randomNumber = Math.random();
        if (randomNumber > 0.5)
            return 1;
        return -1;
    }

    //Rysuje piłkę na planszy
    public void draw(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillOval(x,y, SIZE, SIZE);
    }
}
