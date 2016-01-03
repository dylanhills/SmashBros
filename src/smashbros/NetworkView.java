package smashbros;

import javax.swing.JFrame;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

public class NetworkView extends JFrame{
    NeuralNetwork network;
    public NetworkView(NeuralNetwork n){
        network = n;
        
        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        Object[] inputs = new Object[network.inputs.length];
        Object[][] hiddenNeurons = new Object[network.hiddenLayers.length][network.neuronsPerHiddenLayer];
        Object[] outputs = new Object[network.outputs.length];
        
        graph.getModel().beginUpdate();
        try
        {
            int xDisplacement = 20;
            final int firstYDisplacement = 30;
            int yDisplacement = firstYDisplacement;
            final int xIncrement = 250;
            final int yIncrementBig = 100;
            final int yIncrementSmall = 50;
            boolean smallIncrement = true;
            for(int i = 0; i<inputs.length; i++){
                inputs[i] = graph.insertVertex(parent, null, "Input: "+i,xDisplacement,yDisplacement,80,30);
                if(smallIncrement){
                    yDisplacement += yIncrementSmall;
                }else{
                    yDisplacement += yIncrementBig;
                }
            }
            smallIncrement = !smallIncrement;
            yDisplacement = firstYDisplacement;
            xDisplacement+=xIncrement;
            String string = "";
            for(int i = 0; i<hiddenNeurons.length; i++){
                yDisplacement = firstYDisplacement;
                for(int j = 0; j<hiddenNeurons[0].length; j++){
                    string = "";
                    if(network.hiddenLayers[i].neurons[j].activated){
                        string = "activated";
                    }
                    hiddenNeurons[i][j] = graph.insertVertex(parent, null, string,xDisplacement,yDisplacement,80,30);
                    if(smallIncrement){
                        yDisplacement += yIncrementSmall;
                    }else{
                        yDisplacement += yIncrementBig;
                    }
                    if(i == 0){
                        for(int k = 0; k<network.hiddenLayers[i].neurons[j].weights.length; k++){
                            graph.insertEdge(parent, null, network.hiddenLayers[i].neurons[j].weights[k], inputs[k],hiddenNeurons[i][j]);
                        }
                    }
                    else{
                        for(int k = 0; k<network.hiddenLayers[i].neurons[j].weights.length; k++){
                            graph.insertEdge(parent, null, network.hiddenLayers[i].neurons[j].weights[k], hiddenNeurons[i-1][k],hiddenNeurons[i][j]);
                        }
                    }

                }
                xDisplacement+=xIncrement;
                smallIncrement = !smallIncrement;

            }
            yDisplacement = firstYDisplacement;
            for(int i = 0; i<outputs.length; i++){
                string = "";
                if(network.outputs[i].activated){
                    string = "activated";
                }
                outputs[i] = graph.insertVertex(parent, null, string,xDisplacement,yDisplacement,80,30);
                if(smallIncrement){
                    yDisplacement += yIncrementSmall;
                }else{
                    yDisplacement += yIncrementBig;
                }
                for(int k = 0; k<network.hiddenLayers[network.hiddenLayers.length-1].neurons[i].weights.length; k++){
                    graph.insertEdge(parent, null, network.outputs[i].weights[k], hiddenNeurons[hiddenNeurons.length-1][k],outputs[i]);
                }
            }
        }
        finally
        {
            graph.getModel().endUpdate();
        }
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        getContentPane().add(graphComponent);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(100,100,900,900);
    }
}
