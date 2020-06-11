package com.main;

import java.awt.*;
import java.awt.image.BufferStrategy;

//Klasa odpowiedzialna za rysowanie planszy.
public class Pong extends Canvas implements Runnable {

    public static final int WIDTH = 1000;
    public static final float ASPECT_RATIO = (float) 9 / 16; //stosunek wysokości do szerokości
    public static final int HEIGHT = (int) (WIDTH * ASPECT_RATIO);
    public static int playerScore = 0;
    public static int aiScore = 0;
    public static boolean isRunning = false;

    private Ball ball;
    private PlayerPaddle playerPaddle;
    private AIPaddle aiPaddle;
    private Thread boardThread;

    //Konstruktor planszy
    public Pong() {
        setup();
        initialize();
        GameWindow gameWindow = new GameWindow(this);
        addKeyListener(new KeyboardInputProcessor(playerPaddle));
        this.setFocusable(true);
    }

    //Inicjalizacja elementów na planszy
    private void initialize() {
        ball = new Ball();
        playerPaddle = new PlayerPaddle(ball);
        aiPaddle = new AIPaddle(ball);
    }

    //Ustawienia parametrów okna
    private void setup() {
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
    }

    //Zmiana koloru tła
    private void drawBackground(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
    }

    //Wyświetla wynika AI i gracza
    private void drawScore(Graphics graphics) {
        Font font = new Font("Roboto", Font.PLAIN, 72);
        graphics.setFont(font);
        int padding = 60;

        //Wyświetlanie wyników gracza
        String scoreString = Integer.toString(playerScore);
        int stringWidth = graphics.getFontMetrics(font).stringWidth(scoreString);
        int scoreX = Pong.WIDTH / 2 - padding - stringWidth;
        graphics.setColor(Color.GREEN);
        graphics.drawString(scoreString, scoreX, 60);

        //Wyświetlanie wyników AI
        scoreString = Integer.toString(aiScore);
        scoreX = Pong.WIDTH / 2 + padding;
        graphics.setColor(Color.RED);
        graphics.drawString(scoreString, scoreX, 60);
    }

    @Override
    //Pętla gry
    public void run() {
        this.requestFocus();
        while (isRunning) {
            draw();
        }
        stop();
    }

    //Rysowanie elementów na planszy
    private void draw() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if (bufferStrategy == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics graphics = bufferStrategy.getDrawGraphics();
        drawBackground(graphics);
        ball.draw(graphics);
        playerPaddle.draw(graphics);
        aiPaddle.draw(graphics);
        drawScore(graphics);
        graphics.dispose();
        bufferStrategy.show();
    }

    //Start gry
    public synchronized void start() {
        boardThread = new Thread(this);
        boardThread.start();
        ball.start();
        aiPaddle.start();
        playerPaddle.start();
        isRunning = true;
    }

    //Koniec gry
    public synchronized void stop() {
        try {
            boardThread.join();
            ball.join();
            aiPaddle.join();
            playerPaddle.join();
            isRunning = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
