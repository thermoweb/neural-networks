package org.thermoweb.neuralnetwork;

public class Point {
    private final float x;
    private final float y;
    private boolean guessedRight;

    public Point(float x, float y, boolean guessedRight) {
        this(x, y);
        this.guessedRight = guessedRight;
    }

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
        this.guessedRight = false;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean isGuessedRight() {
        return guessedRight;
    }

    public void setGuessedRight(boolean guessedRight) {
        this.guessedRight = guessedRight;
    }
}
