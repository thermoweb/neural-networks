package org.thermoweb.neuralnetwork;

import java.util.Random;

public class Perceptron {
    private static final Random random = new Random();

    private final float[] weights = new float[3];

    public Perceptron() {
        for (int i = 0; i < weights.length; i++) {
            weights[i] = random.nextFloat() * 2 - 1;
        }
    }

    public int guess(Point point) {
        float[] inputs = getInputsFromPoint(point);
        float sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += inputs[i] * weights[i];
        }
        if (sum < 0) {
            return -1;
        }

        return 1;
    }

    public static float[] getInputsFromPoint(Point point) {
        float[] inputs = new float[3];
        inputs[0] = point.getX();
        inputs[1] = point.getY();
        inputs[2] = (float) 1;

        return inputs;
    }

    public float[] getWeights() {
        return weights;
    }
}
