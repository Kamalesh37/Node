
import java.util.Scanner;

public class NeuralNetworkApplication {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);

        System.out.print("Enter the number of layers in the neural network: ");
        int layersCount = inputScanner.nextInt();

        int[] nodesInEachLayer = new int[layersCount];
        for (int i = 0; i < layersCount; i++) {
            System.out.print("Enter the number of nodes in layer " + (i + 1) + ": ");
            nodesInEachLayer[i] = inputScanner.nextInt();
        }

        NeuralNetworkModel neuralNetworkModel = new NeuralNetworkModel(layersCount, nodesInEachLayer);

        // Set edge weights
        for (int i = 1; i < layersCount; i++) {
            for (int j = 0; j < nodesInEachLayer[i - 1]; j++) {
                for (int k = 0; k < nodesInEachLayer[i]; k++) {
                    System.out.print("Enter weight for edge from node " + (j + 1) +
                                     " in layer " + i + " to node " + (k + 1) + " in layer " + (i + 1) + ": ");
                    double edgeWeight = inputScanner.nextDouble();
                    neuralNetworkModel.setEdgeWeight(i, j + 1, k + 1, edgeWeight);
                }
            }
        }

        // Query edge weights
        System.out.print("Enter the layer of the edge: ");
        int edgeLayer = inputScanner.nextInt();
        System.out.print("Enter the source node in the layer: ");
        int fromNode = inputScanner.nextInt();
        System.out.print("Enter the destination node in the next layer: ");
        int toNode = inputScanner.nextInt();

        double queriedEdgeWeight = neuralNetworkModel.getEdgeWeight(edgeLayer, fromNode, toNode);
        System.out.println("Weight of the edge: " + queriedEdgeWeight);

        inputScanner.close();
    }
}

class NeuralNetworkModel {
    private int layersCount;
    private int[] nodesInEachLayer;
    private double[][][] edgeWeights;

    // Constructor
    public NeuralNetworkModel(int layersCount, int[] nodesInEachLayer) {
        this.layersCount = layersCount;
        this.nodesInEachLayer = nodesInEachLayer;
        this.edgeWeights = new double[layersCount - 1][][];
        
        for (int i = 0; i < layersCount - 1; i++) {
            int currentLayerNodes = nodesInEachLayer[i];
            int nextLayerNodes = nodesInEachLayer[i + 1];
            
            edgeWeights[i] = new double[currentLayerNodes][nextLayerNodes];
        }
    }

    // Method to set edge weight
    public void setEdgeWeight(int layer, int fromNode, int toNode, double weight) {
        edgeWeights[layer - 1][fromNode - 1][toNode - 1] = weight;
    }

    // Method to get edge weight
    public double getEdgeWeight(int layer, int fromNode, int toNode) {
        return edgeWeights[layer - 1][fromNode - 1][toNode - 1];
    }
}

    // Method to get edge weight
    public double getEdgeWeight(int layer, int fromNode, int toNode) {
        return weights[layer - 1][fromNode - 1][toNode - 1];
    }
}
