package org.thermoweb.neuralnetwork.visualization;

import org.thermoweb.neuralnetwork.NeuralNetworks;
import org.thermoweb.neuralnetwork.Point;

import javax.swing.JFrame;

public class NeuralNetworkWindow extends JFrame {

    private final NeuralNetworkPanel neuralNetworkPanel;

    public NeuralNetworkWindow(NeuralNetworks neuralNetworks) {
        super();
        this.neuralNetworkPanel = new NeuralNetworkPanel(neuralNetworks);
        this.setContentPane(neuralNetworkPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public NeuralNetworkPanel getNeuralNetworkPanel() {
        return neuralNetworkPanel;
    }
}
