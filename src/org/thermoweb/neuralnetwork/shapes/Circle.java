package org.thermoweb.neuralnetwork.shapes;

import org.thermoweb.neuralnetwork.Point;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import static org.thermoweb.neuralnetwork.NeuralNetworkApplication.getPixelX;
import static org.thermoweb.neuralnetwork.NeuralNetworkApplication.getPixelY;

public class Circle implements Shape {
    int x;
    int y;
    Point point;

    public Circle(Point point, int maxWidth, int maxHeight) {
        this.point = point;
        this.x = getPixelX(point.getX(), maxWidth);
        this.y = getPixelY(point.getY(), maxHeight);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, 10, 10);
        Color color = Color.RED;
        if (point.isGuessedRight()) {
            color = Color.GREEN;
        }
        g2d.setColor(color);
        g2d.fill(circle);
        g2d.setColor(Color.GRAY);
    }
}
