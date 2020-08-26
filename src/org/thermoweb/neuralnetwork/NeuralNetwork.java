package org.thermoweb.neuralnetwork;

public class NeuralNetwork {

    private final int inputNodesNumber;
    private final int hiddenNodesNumber;
    private final int outputNodesNumber;

    private final Matrix weightsInputHiddenLayer;
    private final Matrix weightsHiddenOutputLayer;
    private final Matrix biasHidden;
    private final Matrix biasOutput;

    public NeuralNetwork(int inputNodesNumber, int hiddenNodesNumber, int outputNodesNumber) {
        this.inputNodesNumber = inputNodesNumber;
        this.hiddenNodesNumber = hiddenNodesNumber;
        this.outputNodesNumber = outputNodesNumber;

        this.weightsInputHiddenLayer = new Matrix(hiddenNodesNumber, inputNodesNumber);
        this.weightsHiddenOutputLayer = new Matrix(outputNodesNumber, hiddenNodesNumber);
        this.weightsInputHiddenLayer.randomize();
        this.weightsHiddenOutputLayer.randomize();

        this.biasHidden = new Matrix(hiddenNodesNumber, 1);
        this.biasOutput = new Matrix(outputNodesNumber, 1);
        this.biasHidden.randomize();
        this.biasOutput.randomize();
    }

    private Matrix feedforward(Matrix inputs) {
        Matrix hiddenMatrix = Matrix.product(this.weightsInputHiddenLayer, inputs);
        hiddenMatrix.add(this.biasHidden);

        hiddenMatrix.map(NeuralNetwork::sigmoid);
        Matrix output = Matrix.product(this.weightsHiddenOutputLayer, hiddenMatrix);
        output.add(this.biasOutput);
        output.map(NeuralNetwork::sigmoid);

        return output;
    }

    public void train(Matrix inputs, Matrix answer) {
        Matrix output = this.feedforward(inputs);
        Matrix outputErrors = Matrix.subtract(answer, output);

        Matrix weightsHiddenOutputTransposed = Matrix.transpose(this.weightsHiddenOutputLayer);
        Matrix hiddenErrors = Matrix.product(weightsHiddenOutputTransposed, outputErrors);
    }

    private static double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    public static void main(String[] args) {
        NeuralNetwork neuralNetwork = new NeuralNetwork(2, 2, 1);
        Matrix inputs = new Matrix(2, 1);
        inputs.randomize();

        Matrix output = neuralNetwork.feedforward(inputs);

        System.out.println("output = " + output);
    }
}
