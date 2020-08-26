package org.thermoweb.neuralnetwork;

import org.thermoweb.neuralnetwork.shapes.Line;
import org.thermoweb.neuralnetwork.visualization.NeuralNetworkWindow;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.DoubleUnaryOperator;

public class NeuralNetworkApplication {

    private final Map<Point, Integer> points = new HashMap<>();
    private static final Random random = new Random();

    private final Perceptron perceptron;
    private final DoubleUnaryOperator functionToIdentify;

    private Line lineToGuess;
    private Line guessedLine;

    public NeuralNetworkApplication() {
        perceptron = new Perceptron();
        float randomSlope = random.nextFloat() * 2 - 1;
        float randomIntercept = random.nextFloat() * 2 - 1;
        functionToIdentify = (x) -> (float) (randomSlope * x + randomIntercept);
    }

    public static void main(String[] args) {
        NeuralNetworkApplication neuralNetwork = new NeuralNetworkApplication();
        NeuralNetworkWindow neuralNetworkWindow = new NeuralNetworkWindow(neuralNetwork);
        DoubleUnaryOperator function = neuralNetwork.getFunctionToIdentify();

        neuralNetwork.setLineToGuess(getLineToGuess(function));

        int numberOfPoints = 200;
        Map<Point, Integer> data = createRandomData(function, numberOfPoints);
        neuralNetwork.getPoints().putAll(data);

        neuralNetwork.setGuessedLine(getLineFromWeights(neuralNetwork.getPerceptron().getWeights()));
        neuralNetworkWindow.getNeuralNetworkPanel().repaint();

        Trainer trainer = new Trainer(neuralNetwork.getPerceptron(), neuralNetwork.getPoints(), (float) 0.001);

        System.out.println("start training");
        boolean isPerceptronTrainedWell;
        do {
            trainer.train();
            isPerceptronTrainedWell = isPerceptronTrainedWell(neuralNetwork);
            neuralNetworkWindow.getNeuralNetworkPanel().repaint();
        } while(!isPerceptronTrainedWell);

        System.out.println("Training ends");
    }

    private static Map<Point, Integer> createRandomData(DoubleUnaryOperator function, int numberOfPoints) {
        Map<Point, Integer> data = new HashMap<>();
        for(int i = 0; i < numberOfPoints; i++) {
            Point point = new RandomPoint();
            data.put(point, getTarget(point, function));
        }
        return data;
    }

    private static boolean isPerceptronTrainedWell(NeuralNetworkApplication neuralNetwork) {
        boolean alwaysRight = true;
        for (Map.Entry<Point, Integer> entry : neuralNetwork.getPoints().entrySet()) {
            Point point = entry.getKey();
            Integer target = entry.getValue();
            int guess = neuralNetwork.getPerceptron().guess(point);
            boolean isRight = guess == target;
            point.setGuessedRight(isRight);
            neuralNetwork.setGuessedLine(getLineFromWeights(neuralNetwork.getPerceptron().getWeights()));
            if (!isRight) {
                alwaysRight = false;
            }
            waitWithoutErrors();
        }
        return alwaysRight;
    }

    private static Line getLineToGuess(DoubleUnaryOperator function) {
        return new Line(
                new Point(-1, (float) function.applyAsDouble(-1)),
                new Point(1, (float) function.applyAsDouble(1)));
    }

    private static void waitWithoutErrors() {
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int getTarget(Point point, DoubleUnaryOperator function) {
        float minY = (float) function.applyAsDouble(point.getX());
        if (minY < point.getY()) {
            return 1;
        }
        return - 1;
    }

    public static Line getLineFromWeights(float[] weights) {
        Point pointA = new Point(-1, getYFromWeights(-1, weights));
        Point pointB = new Point(1, getYFromWeights(1, weights));
        return new Line(pointA, pointB);
    }

    public static float getYFromWeights(float x, float[] weights) {
        return ((- weights[0] * x) - weights[2]) / weights[1];
    }

    public static int getPixelX(float x, int max) {
        return (int) (x * max + max)/2;
    }

    public static int getPixelY(float x, int max) {
        return (int) (-x * max + max)/2;
    }

    public DoubleUnaryOperator getFunctionToIdentify() {
        return functionToIdentify;
    }

    public Perceptron getPerceptron() {
        return perceptron;
    }

    public Map<Point, Integer> getPoints() {
        return points;
    }

    public Line getLineToGuess() {
        return lineToGuess;
    }

    public Line getGuessedLine() {
        return guessedLine;
    }

    public void setLineToGuess(Line lineToGuess) {
        this.lineToGuess = lineToGuess;
    }

    public void setGuessedLine(Line guessedLine) {
        this.guessedLine = guessedLine;
    }
}
