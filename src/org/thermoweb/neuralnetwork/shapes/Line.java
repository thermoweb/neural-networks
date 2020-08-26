package org.thermoweb.neuralnetwork.shapes;

import org.thermoweb.neuralnetwork.Point;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import static org.thermoweb.neuralnetwork.NeuralNetworkApplication.getPixelX;
import static org.thermoweb.neuralnetwork.NeuralNetworkApplication.getPixelY;
import static org.thermoweb.neuralnetwork.visualization.NeuralNetworkPanel.WIDTH;
import static org.thermoweb.neuralnetwork.visualization.NeuralNetworkPanel.HEIGHT;

public class Line implements Shape {
    Point pointA;
    Point pointB;

    public Line(Point pointA, Point pointB) {
        this.pointA = pointA;
        this.pointB = pointB;
    }

    @Override
    public void draw(Graphics g) {
        Line2D.Float line = new Line2D.Float(
                getPixelX(this.pointA.getX(), WIDTH),
                getPixelY(this.pointA.getY(), HEIGHT),
                getPixelX(this.pointB.getX(), WIDTH),
                getPixelY(this.pointB.getY(), HEIGHT));
        ((Graphics2D) g).draw(line);
    }
}
