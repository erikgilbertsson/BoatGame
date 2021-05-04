package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.AudioRecorder;

public class CloudThread extends Thread {
    //records sound
    public void run() {
        int samplingRate = 44100;
        AudioRecorder recorder = Gdx.audio.newAudioRecorder(samplingRate, true);
        short[] pCM = new short[samplingRate*5]; // 1024 samples
        recorder.read(pCM, 0, pCM.length);
    }
}
