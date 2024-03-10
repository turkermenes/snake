package com.devenes.SnakeEs;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameFrame extends JFrame {

    public static int gameStatus = 1; // 0 -> PRE_GAME, 1 -> IN_GAME, 2 -> END_GAME
    private static final int GAME_WIDTH = 600;
    private static final int GAME_HEIGHT = 600;
    private static final int SIZE = 30;
    private final int DELAY = 100;
    public static int snakeSize = 5; // Bir hata olursa bunu 1 yapmayı dene
    public static int snakeHeadX = SIZE;
    public static int snakeHeadY = SIZE;
    public static char direction = 'R';
    static Random random;
    static int appleX, appleY, index, score = 0;
    static ArrayList<Integer> coordsX = new ArrayList<>();
    static ArrayList<Integer> coordsY = new ArrayList<>();
    Timer timer;
    public static boolean isGamePaused = false;
    JLabel scoreLabel;
    String hissingSound = "src/com/devenes/SnakeEs/res/SnakeHissing.wav";
    GameSoundManager soundManager = new GameSoundManager();

    public GameFrame() {
        super("SnakeES");
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setFocusable(true);
        setResizable(false);
        setLayout(null);
        getContentPane().setBackground(Color.decode("#2F2220"));
        addKeyListener(new GameKeyListener());

        initGame();
        spawnItems();
        addGuiComponents();
    }

    private void addGuiComponents() {
        scoreLabel = new JLabel("Skor: " + score);
        scoreLabel.setBounds(GAME_WIDTH - 4 * SIZE, 0, 200, SIZE);
        scoreLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
        scoreLabel.setForeground(Color.white);
        scoreLabel.setVisible(true);
        getContentPane().add(scoreLabel);
    }

    public void initGame() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!isGamePaused) {
                    move();
                }
            }
        }, 0, DELAY);
    }

    public void paint(Graphics g) {
        boolean switchColor = false;
        super.paint(g);
        index = coordsX.size() - 1;
        for (int i = 0; i < coordsX.size(); i++) {
            if (switchColor) {
                g.setColor(Color.decode("#116708"));
                switchColor = false;
            } else {
                g.setColor(Color.decode("#66D928"));
                switchColor = true;
            }
            g.fillRect(coordsX.get(index), coordsY.get(index), SIZE, SIZE);
            index--;
        }
        index = coordsX.size() - 1;
        g.setColor(Color.red);
        g.fillOval(appleX, appleY, SIZE, SIZE);

    }

    public void checkCollisions() {
        if (snakeHeadX > GAME_WIDTH || snakeHeadX < 0 || snakeHeadY > GAME_HEIGHT || snakeHeadY < 0) {
            gameStatus = 2;
            snakeHeadX = SIZE;
            snakeHeadY = SIZE;
            direction = 'R';
        }
        if (coordsX.contains(snakeHeadX)) {
            int ind = coordsX.indexOf(snakeHeadX);
            if (coordsY.get(ind) == snakeHeadY) {
                gameStatus = 2;
            }
        }
        if (snakeHeadX == appleX && snakeHeadY == appleY) {
            score++;
            snakeSize++;
            scoreLabel.setText("Skor: " + score);
            soundManager.play(hissingSound);
            spawnItems();
        }
    }


    public void statusChanger() {
        switch (gameStatus) {
            case 2:
                System.out.println("Game Over!");
                timer.cancel();
                getContentPane().removeAll();
                break;
        }
    }

    public static void spawnItems() {
        random = new Random();
        appleX = SIZE * random.nextInt(GAME_WIDTH / SIZE);
        appleY = SIZE * random.nextInt(1, GAME_HEIGHT / SIZE);
        while (coordsX.contains(appleX) && coordsY.get(coordsX.indexOf(appleX)) == appleY) {
            appleX = SIZE * random.nextInt(GAME_WIDTH / SIZE);
            appleY = SIZE * random.nextInt(1, GAME_HEIGHT / SIZE);
        }
    }

    public void move() {
        switch (direction) {
            case 'U':
                snakeHeadY -= SIZE;
                break;
            case 'D':
                snakeHeadY += SIZE;
                break;
            case 'L':
                snakeHeadX -= SIZE;
                break;
            case 'R':
                snakeHeadX += SIZE;
                break;
        }
        checkCollisions();
        if (coordsX.size() < snakeSize) {
            coordsX.add(snakeHeadX);
            coordsY.add(snakeHeadY);

        } else {
            coordsX.add(snakeHeadX);
            coordsX.remove(0);
            coordsY.add(snakeHeadY);
            coordsY.remove(0);
        }
        repaint();

    }


}
