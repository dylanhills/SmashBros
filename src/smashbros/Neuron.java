package smashbros;

public class Neuron {
    Neuron[] inputs;
    double threashold;
    boolean activated;
    double weights[];
    public Neuron(int num,double threash){
        threashold = threash;
        inputs = new Neuron[num];
        weights = new double[num];
        activated = false;
        
        for(int i = 0; i<num; i++){
            weights[i] = (double)((int)(Math.random()*1000))/1000;
        }
    }
    //input nodes use this constructor
    public Neuron(){
        activated = true;
    }
}
