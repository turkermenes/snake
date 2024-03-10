package com.devenes.SnakeEs;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class GameSoundManager {
    Clip clip;
    public void setFile(String filePath) {

        try {
            File file = new File(filePath);
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void play(String filePath) {
        setFile(filePath);
        clip.start();
    }

}
