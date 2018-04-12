package com.harium.propan.sensor.filter;

public class LowPassFilter {

    public static float[] filter(final float alpha, float[] input, float[] output) {
        if (output == null)
            return input;

        for (int i = 0; i < input.length; i++) {
            output[i] = output[i] + alpha * (input[i] - output[i]);
        }
        return output;
    }
}
