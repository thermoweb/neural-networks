package org.thermoweb.neuralnetwork.visualization;

import org.thermoweb.neuralnetwork.NeuralNetworkApplication;
import org.thermoweb.neuralnetwork.shapes.Circle;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Optional;

public class NeuralNetworkPanel extends JPanel {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

    private final NeuralNetworkApplication neuralNetworks;

    public NeuralNetworkPanel(NeuralNetworkApplication neuralNetworks) {
        this.neuralNetworks = neuralNetworks;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        neuralNetworks.getPoints().forEach((point, value) -> {
            Circle circle = new Circle(point, WIDTH, HEIGHT);
            circle.draw(g);
        });

        Optional.ofNullable(neuralNetworks.getLineToGuess()).ifPresent(l -> l.draw(g));
        Optional.ofNullable(neuralNetworks.getGuessedLine()).ifPresent(l -> l.draw(g));
    }
}
