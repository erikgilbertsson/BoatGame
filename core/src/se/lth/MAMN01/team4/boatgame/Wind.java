package se.lth.MAMN01.team4.boatgame;

import com.badlogic.gdx.utils.TimeUtils;

import java.util.Random;

public class Wind {

    private static float MAX_FORCE = 0;
    private static final float MAX_FORCE_PRIME = (float) 0.005;

    private long windChangeTime;
    private long timer;

    private float xForcePrime;
    private float xForce;
    private Random r;

    public Wind() {
        r = new Random();
        xForce = 0;
        windChangeTime = 0;
        timer = 5000;
    }

    public void setMaxForce(float force) {
        MAX_FORCE = force;
    }

    private void setXForcePrime() {
        xForcePrime = -MAX_FORCE_PRIME + r.nextFloat() * MAX_FORCE_PRIME * 2;
    }

    private void updateXForce() {
        xForce += xForcePrime;
        if (xForce > MAX_FORCE) {
            xForce = MAX_FORCE;
        } else if (xForce < -MAX_FORCE) {
            xForce = -MAX_FORCE;
        }
    }

    public float getXForce() {
        if (TimeUtils.timeSinceMillis(windChangeTime) > timer) {
            setXForcePrime();
            windChangeTime = TimeUtils.millis();
        }
        updateXForce();
        return xForce;
    }
}