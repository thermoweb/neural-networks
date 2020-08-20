package org.thermoweb.neuralnetwork.shapes;

import org.thermoweb.neuralnetwork.Point;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import static org.thermoweb.neuralnetwork.NeuralNetworks.getPixelX;
import static org.thermoweb.neuralnetwork.NeuralNetworks.getPixelY;

public class Circle {
    int x;
    int y;

    public Circle(Point point, int maxWidth, int maxHeight) {
        this.x = getPixelX(point.getX(), maxWidth);
        this.y = getPixelY(point.getY(), maxHeight);
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, 10, 10);

        g2d.setColor(Color.GRAY);
        g2d.fill(circle);
    }
}
