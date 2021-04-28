package se.lth.MAMN01.team4.boatgame;

public class Util {

    private static final float ALPHA = 0.25f;

    // Copied from tutorial https://www.built.io/blog/applying-low-pass-filter-to-android-sensor-s-readings
    protected static float lowPass(float input, float output) {
        if (output == 0) return input;
        output = output + ALPHA * (input - output);
        return output;
    }
}
