package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.AudioRecorder;

public class CommandRecorder extends Thread {
    private AudioRecorder recorder;
    private Cloud cloud;
    private int samplingRate = 44100;
    private short[] pCM;
    private boolean running;

    public CommandRecorder(Cloud cloud) {
        this.cloud = cloud;
        running = true;
        try {
            recorder = Gdx.audio.newAudioRecorder(samplingRate, true);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            terminate();
        }

    }

    //records sound
    public void run() {
        while (running) {
            pCM = new short[samplingRate * 1 / 2]; // 1024 samples
            if (recorder != null) {
                recorder.read(pCM, 0, pCM.length);
            } else {
                terminate();
            }

            int counter = 0;
            for (int i = 0; i < pCM.length; i++) {
                short sample = pCM[i];
                if (sample > 10000) {
                    counter++;
                }
            }
            if (counter > 1200) {
                cloud.removeClouds();
            }
        }
        if (recorder != null) {
            recorder.dispose();
        }
    }

    public void terminate() {
        running = false;
    }
}
