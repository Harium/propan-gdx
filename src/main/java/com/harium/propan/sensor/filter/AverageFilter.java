package com.harium.propan.sensor.filter;

import java.util.List;

public class AverageFilter {

    public static double[] average(List<float[]> values) {
        double[] result = new double[9];
        for (float[] value : values) {
            for (int i = 0; i < 9; i++) {
                result[i] += value[i];
            }
        }

        for (int i = 0; i < 9; i++) {
            result[i] = result[i] / values.size();
        }

        return result;
    }
}
