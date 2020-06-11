package com.main;

import java.awt.*;

//Klasa modelująca paletkę AI
public class AIPaddle extends Thread {

    private final int WIDTH = 20;
    private final int HEIGHT = 80;
    private final int x;
    private int y;
    private final int speed = 6;
    private final Ball ball; //piłka, którą będzie śledzić AI

    public AIPaddle(Ball ball) {
        x = Pong.WIDTH - WIDTH - 20;
        y = Pong.HEIGHT / 2 - HEIGHT / 2;
        this.ball = ball;
    }

    //Zadania wątku
    public void run() {
        while (Pong.isRunning) {
            try {
                this.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            checkForCollision();
            trackBall();
        }

    }

    //Obsługa kolizji z piłką
    private void checkForCollision(){
        if(ball.getX() + Ball.SIZE >= x && (ball.getY()+Ball.SIZE>y&& ball.getY()<y+HEIGHT))
            ball.changeXDirectionToLeft();
    }

    //Algorytm odpowiedzialny za poruszanie się paletki AI
    private void trackBall() {
        if (y + HEIGHT / 2 > ball.getY())
            if (y > 0)
                y -= speed;
        if (y + HEIGHT / 2 < ball.getY())
            if (y < Pong.HEIGHT - HEIGHT)
                y += speed;
    }

    //Rysowanie palekti AI na planszy
    public void draw(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fillRect(x, y, WIDTH, HEIGHT);
    }
}
