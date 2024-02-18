
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of layers in the neural network: ");
        int numLayers = scanner.nextInt();

        int[] numNodesInLayer = new int[numLayers];
        for (int i = 0; i < numLayers; i++) {
            System.out.print("Enter the number of nodes in layer " + (i + 1) + ": ");
            numNodesInLayer[i] = scanner.nextInt();
        }

        NeuralNetwork neuralNetwork = new NeuralNetwork(numLayers, numNodesInLayer);

        // Set edge weights
        for (int i = 1; i < numLayers; i++) {
            for (int j = 0; j < numNodesInLayer[i - 1]; j++) {
                for (int k = 0; k < numNodesInLayer[i]; k++) {
                    System.out.print("Enter weight for edge from node " + (j + 1) +
                                     " in layer " + i + " to node " + (k + 1) + " in layer " + (i + 1) + ": ");
                    double weight = scanner.nextDouble();
                    neuralNetwork.setEdgeWeight(i, j + 1, k + 1, weight);
                }
            }
        }

        // Query edge weights
        System.out.print("Enter the layer of the edge: ");
        int layer = scanner.nextInt();
        System.out.print("Enter the source node in the layer: ");
        int fromNode = scanner.nextInt();
        System.out.print("Enter the destination node in the next layer: ");
        int toNode = scanner.nextInt();

        double edgeWeight = neuralNetwork.getEdgeWeight(layer, fromNode, toNode);
        System.out.println("Weight of the edge: " + edgeWeight);

        scanner.close();
    }
}

class NeuralNetwork {
    private int numLayers;
    private int[] numNodesInLayer;
    private double[][][] weights;

    // Constructor
    public NeuralNetwork(int numLayers, int[] numNodesInLayer) {
        this.numLayers = numLayers;
        this.numNodesInLayer = numNodesInLayer;
        this.weights = new double[numLayers - 1][][];
        
        for (int i = 0; i < numLayers - 1; i++) {
            int nodesInCurrentLayer = numNodesInLayer[i];
            int nodesInNextLayer = numNodesInLayer[i + 1];
            
            weights[i] = new double[nodesInCurrentLayer][nodesInNextLayer];
        }
    }

    // Method to set edge weight
    public void setEdgeWeight(int layer, int fromNode, int toNode, double weight) {
        weights[layer - 1][fromNode - 1][toNode - 1] = weight;
    }

    // Method to get edge weight
    public double getEdgeWeight(int layer, int fromNode, int toNode) {
        return weights[layer - 1][fromNode - 1][toNode - 1];
    }
}
