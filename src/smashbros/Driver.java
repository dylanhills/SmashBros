package smashbros;

public class Driver {
    public static void main(String[] args){
        System.out.println("Smash Bros");
        
        NeuralNetwork n = new NeuralNetwork();
        n.computeOutputs();
        NetworkView nw = new NetworkView(n);
    }
}
