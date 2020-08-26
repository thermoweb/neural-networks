package org.thermoweb.neuralnetwork;

import java.util.Arrays;
import java.util.Random;
import java.util.function.DoubleUnaryOperator;

public class Matrix {

    private static final Random random = new Random();

    private final int cols;
    private final int rows;
    private final double[][] values;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.values = new double[rows][cols];

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.values[i][j] = 0;
            }
        }
    }

    public static Matrix subtract(Matrix a, Matrix b) {
        Matrix minusB = Matrix.multiply(b, -1);
        return Matrix.add(a, minusB);
    }

    public static Matrix add(Matrix a, Matrix b) {
        Matrix result = new Matrix(a.getRows(), a.getCols());
        for (int i = 0; i < a.getRows(); i++) {
            for (int j = 0; j < a.getCols(); j++) {
                result.getValues()[i][j] = a.getValues()[i][j] + b.getValues()[i][j];
            }
        }
        return result;
    }

    public static Matrix multiply(Matrix a, int b) {
        Matrix result = new Matrix(a.getRows(), a.getCols());

        for (int i = 0; i < result.getRows(); i++) {
            for (int j = 0; j < result.getCols(); j++) {
                result.getValues()[i][j] *= b;
            }
        }
        return result;
    }

    public void multiply(double n) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.values[i][j] *= n;
            }
        }
    }

    public void add(double n) {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.values[i][j] += n;
            }
        }
    }

    public void add(Matrix matrix) {
        if (matrix.getCols() != this.cols
            || matrix.getRows() != this.rows) {
            throw new IllegalArgumentException();
        }
        for (int i=0; i < this.rows; i++) {
            for (int j=0; j < this.cols; j++) {
                this.values[i][j] += matrix.getValues()[i][j];
            }
        }
    }

    public void randomize() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                this.values[i][j] = random.nextFloat() * 2 - 1;
            }
        }
    }

    public void map(DoubleUnaryOperator function) {
        for (int i=0; i < this.rows; i++) {
            for (int j=0; j < this.cols; j++) {
                this.values[i][j] = function.applyAsDouble(values[i][j]);
            }
        }
    }

    public static Matrix transpose(Matrix matrix) {
        Matrix result = new Matrix(matrix.getCols(), matrix.getRows());
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getCols(); j++) {
                result.getValues()[j][i] = matrix.getValues()[i][j];
            }
        }
        return result;
    }

    public static Matrix product(Matrix a, Matrix b) {
        if (a.getCols() != b.getRows()) {
            throw new IllegalArgumentException();
        }
        Matrix result = new Matrix(a.getRows(), b.getCols());
        for (int i = 0; i < result.getRows(); i++) {
            for (int j = 0; j < result.getCols(); j++) {
                double sum = 0;
                for (int k = 0; k < a.getCols() ; k++) {
                    sum += a.getValues()[i][k] + b.getValues()[k][j];
                }
                result.getValues()[i][j] = sum;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Matrix matrix = new Matrix(3, 2);
        matrix.randomize();
        System.out.println(matrix);

        Matrix secondMatrix = new Matrix(2, 3);
        secondMatrix.randomize();
        System.out.println(secondMatrix);
        Matrix product = Matrix.product(matrix, secondMatrix);
        System.out.println(product);
        Matrix transposed = Matrix.transpose(product);
        System.out.println(transposed);
        transposed.map((x) -> x + 2);
        System.out.println(transposed);
    }

    public String toString() {
        return Arrays.deepToString(this.values);
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public double[][] getValues() {
        return values;
    }
}
