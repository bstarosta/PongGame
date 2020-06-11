package com.main;

import java.awt.*;

//Klasa modelująca paletkę gracza
public class PlayerPaddle extends Thread {

    private final int WIDTH = 20;
    private final int HEIGHT = 80;
    private final int x;
    private int y;
    private int yDirection; //kierunek poruszania się paletki
    private final int speed;
    private Ball ball;

    public PlayerPaddle(Ball ball) {
        x = 20;
        y = Pong.HEIGHT / 2 - HEIGHT / 2;
        yDirection = 0;
        speed = 5;
        this.ball = ball;
    }

    public void setyDirection(int direction) {
        yDirection = direction;
    }

    //Zadania wątku
    public void run() {
        while (Pong.isRunning) {
            try {
                sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            checkForCollision();
            move();
        }
    }

    //Poruszanie się paletki
    private void move() {
        y = checkMovementBounds(y + yDirection * speed);
    }

    //Uniemożliwia ruch poza granice planszy
    private int checkMovementBounds(int y) {
        return Math.min(Math.max(0, y), Pong.HEIGHT - HEIGHT);
    }

    //Obsługa kolizji z piłką
    private void checkForCollision() {
        if (ball.getX() <= x + WIDTH && (ball.getY() + Ball.SIZE > y && ball.getY() < y + HEIGHT))
            ball.changeXDirectionToRight();
    }

    //Rysowanie paletki gracza na planszy
    public void draw(Graphics graphics) {
        graphics.setColor(Color.GREEN);
        graphics.fillRect(x, y, WIDTH, HEIGHT);
    }
}
