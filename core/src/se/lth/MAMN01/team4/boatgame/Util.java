package se.lth.MAMN01.team4.boatgame;

import java.util.ArrayList;

public class Util {

    private static final float ALPHA = 0.25f;

    // Copied from tutorial https://www.built.io/blog/applying-low-pass-filter-to-android-sensor-s-readings
    protected static float lowPass(float input, float output) {
        if (output == 0) return input;
        output = output + ALPHA * (input - output);
        return output;
    }

    protected static ArrayList<Integer> bubbleSort (ArrayList<Integer> list) {
        boolean swap = true;
        int temp;
        while(swap){
            swap = false;
            for(int i = 0;i < list.size()-1; i++){
                if(list.get(i) < list.get(i+1)){
                    temp = list.get(i);
                    list.set(i, list.get(i+1));
                    list.set(i+1, temp);
                    swap = true;
                }
            }
        }
        return list;
    }
}
