package com.devenes.SnakeEs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                if (!GameFrame.isGamePaused) {
                    if (GameFrame.direction != 'R') {
                        GameFrame.direction = 'L';
                    }
                }
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                if (!GameFrame.isGamePaused) {
                    if (GameFrame.direction != 'U') {
                        GameFrame.direction = 'D';
                    }
                }
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                if (!GameFrame.isGamePaused) {
                    if (GameFrame.direction != 'L') {
                        GameFrame.direction = 'R';
                    }
                }
                break;
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                if (!GameFrame.isGamePaused) {
                    if (GameFrame.direction != 'D') {
                        GameFrame.direction = 'U';
                    }
                }
                break;
            case KeyEvent.VK_SPACE:
                GameFrame.snakeSize++;
                break;
            case KeyEvent.VK_P:
                GameFrame.isGamePaused = !GameFrame.isGamePaused;
                break;
            case KeyEvent.VK_G:
                GameFrame.spawnItems();
                break;

        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
