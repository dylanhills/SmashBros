package smashbros;

public class NeuronLayer {
    Neuron[] neurons;
    int numNeurons;
    public NeuronLayer(Neuron[] n){
        neurons = n;
        numNeurons = n.length;
    }
}
