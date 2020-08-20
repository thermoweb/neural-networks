package org.thermoweb.neuralnetwork.shapes;

import org.thermoweb.neuralnetwork.Point;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import static org.thermoweb.neuralnetwork.NeuralNetworks.HEIGHT;
import static org.thermoweb.neuralnetwork.NeuralNetworks.WIDTH;
import static org.thermoweb.neuralnetwork.NeuralNetworks.getPixelX;
import static org.thermoweb.neuralnetwork.NeuralNetworks.getPixelY;

public class Line {
    Point pointA;
    Point pointB;

    public Line(Point pointA, Point pointB) {
        this.pointA = pointA;
        this.pointB = pointB;
    }

    public void draw(Graphics g) {
        Line2D.Float line = new Line2D.Float(
                getPixelX(this.pointA.getX(), WIDTH),
                getPixelY(this.pointA.getY(), HEIGHT),
                getPixelX(this.pointB.getX(), WIDTH),
                getPixelY(this.pointB.getY(), HEIGHT));
        ((Graphics2D) g).draw((Line2D) line);
    }
}
