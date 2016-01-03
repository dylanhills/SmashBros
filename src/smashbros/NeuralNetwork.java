package smashbros;

public class NeuralNetwork {
    int numHiddenLayers;
    int numInputs;
    int numOutputs;
    int neuronsPerHiddenLayer;
    Neuron[] inputs;
    Neuron[] outputs;
    Neuron[] currentNeuronLayer;
    NeuronLayer prevLayer;
    NeuronLayer[] hiddenLayers;
    public NeuralNetwork(){
        numHiddenLayers = 3;
        hiddenLayers = new NeuronLayer[numHiddenLayers];
        neuronsPerHiddenLayer = 3;
        numInputs = 3;
        numOutputs = 2;
        inputs = new Neuron[numInputs];
        outputs = new Neuron[numOutputs];
        for(int i = 0; i<inputs.length; i++){
            inputs[i] = new Neuron();
        }
        prevLayer = new NeuronLayer(inputs);
        for(int i = 0; i<hiddenLayers.length; i++){
            currentNeuronLayer = new Neuron[neuronsPerHiddenLayer];
            for(int j = 0; j<neuronsPerHiddenLayer;j++){
                currentNeuronLayer[j] = new Neuron(prevLayer.numNeurons,1);//change the threashold here
                currentNeuronLayer[j].inputs = prevLayer.neurons;
            }
            hiddenLayers[i] = new NeuronLayer(currentNeuronLayer);
            prevLayer = hiddenLayers[i];
        }
        for(int i = 0; i<outputs.length; i++){
            outputs[i] = new Neuron(prevLayer.numNeurons,1);//change threashold here
            outputs[i].inputs = prevLayer.neurons;
        }
        
    }
    public void computeOutputs(){
        double sum;
        for(int i = 0; i<hiddenLayers.length; i++){
            for(int j = 0; j<neuronsPerHiddenLayer;j++){
                sum = 0;
                for(int l = 0; l<hiddenLayers[i].neurons[j].inputs.length;l++){
                    if(hiddenLayers[i].neurons[j].inputs[l].activated){
                        sum += hiddenLayers[i].neurons[j].weights[l];
                    }
                }
                if(hiddenLayers[i].neurons[j].threashold<sum){
                    hiddenLayers[i].neurons[j].activated = true;
                }
            }
        }
        for(int i = 0; i<outputs.length; i++){
            sum = 0;
            for(int l = 0; l<outputs[i].inputs.length;l++){
                if(outputs[i].inputs[l].activated){
                    sum+=outputs[i].weights[l];
                }
            }
            System.out.println("Output "+i+" = "+sum);
            if(sum>outputs[i].threashold){
                outputs[i].activated = true;
            }
        }
    }

}
