package org.thermoweb.neuralnetwork;

import java.util.Arrays;
import java.util.Map;

public class Trainer {

    private final Perceptron perceptron;
    private final Map<Point, Integer> trainingData;

    private final float learningRate;

    public Trainer(Perceptron perceptron, Map<Point, Integer> trainingData, float learningRate) {
        this.perceptron = perceptron;
        this.trainingData = trainingData;
        this.learningRate = learningRate;
    }

    public void train() {
        trainingData.forEach((point, target) -> {
            int guess = perceptron.guess(point);
            int error = target - guess;
            float[] inputs = Perceptron.getInputsFromPoint(point);
            for (int i = 0; i < perceptron.getWeights().length; i++) {
                perceptron.getWeights()[i] += error * inputs[i] * learningRate;
            }
        });
    }
}
