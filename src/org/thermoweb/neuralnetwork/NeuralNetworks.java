package org.thermoweb.neuralnetwork;

import org.thermoweb.neuralnetwork.shapes.Circle;
import org.thermoweb.neuralnetwork.shapes.Line;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class NeuralNetworks extends JPanel {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

    private final List<Object> shapes = new ArrayList<>();
    private final List<Point> points = new ArrayList<>();

    private final Perceptron perceptron;

    public NeuralNetworks() {
        this(200);
    }

    public NeuralNetworks(int amount) {
        perceptron = new Perceptron();

        shapes.add(new Line(new Point(-1, function(-1)), new Point(1, function(1))));

        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        for(int i=0; i < amount;i++) {
            Point point = new RandomPoint();
            points.add(point);
            shapes.add(new Circle(point, WIDTH, HEIGHT));
            int guess = perceptron.guess(point);
            System.out.printf("guess point[%f,%f] = %d%n", point.getX(), point.getY(), guess);
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Object s : shapes) {
            if (s instanceof Circle) {
                ((Circle) s).draw(g);
            } else if (s instanceof Line) {
                ((Line) s).draw(g);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new NeuralNetworks());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public float function(float x) {
        return (float) (-0.2 * x + 0.3);
    }



    public static int getPixelX(float x, int max) {
        return (int) (x * max + max)/2;
    }

    public static int getPixelY(float x, int max) {
        return (int) (-x * max + max)/2;
    }
}
