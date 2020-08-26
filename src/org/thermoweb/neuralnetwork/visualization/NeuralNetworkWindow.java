package org.thermoweb.neuralnetwork.visualization;

import org.thermoweb.neuralnetwork.NeuralNetworkApplication;

import javax.swing.JFrame;

public class NeuralNetworkWindow extends JFrame {

    private final NeuralNetworkPanel neuralNetworkPanel;

    public NeuralNetworkWindow(NeuralNetworkApplication neuralNetworks) {
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
