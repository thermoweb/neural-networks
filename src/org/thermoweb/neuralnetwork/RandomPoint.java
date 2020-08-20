package org.thermoweb.neuralnetwork;

import java.util.Random;

public class RandomPoint extends Point {
    private static final Random random = new Random();

    public RandomPoint() {
        super(random.nextFloat() * 2 - 1, random.nextFloat() * 2 - 1);
    }
}
