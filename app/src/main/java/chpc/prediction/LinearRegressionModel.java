package chpc.prediction;
import java.util.ArrayList;

import org.apache.commons.math3.util.Pair;

import weka.classifiers.functions.LinearRegression;
import weka.core.Instance;
import weka.core.Instances;

/**
 * A class for training the model and using it.
 */
public class LinearRegressionModel implements ModelTrainer {

    /**
     * Model uses Linear Regression algorithm.
     */
    LinearRegression model;

    /**
     * Build model using Instances. 
     * @param data  Instances object, containing dates and nhpi values
     */
    public void buildModel(Instances data){
        model = new LinearRegression();
        try
        {
            model.buildClassifier(data);
        } catch (Exception e1){
            System.out.println("Exception: Failed to build model!");
        }
        //System.out.println(data);
        System.out.println(model.toString());
    }

    /**
     * Return model object
     * @return LinearRegression object
     */
    public LinearRegression getModel(){
        return model;
    }

    /**
     * Get the next predicted value 
     * @param data Instances object, containing date and nhpi value
     * @return Next nhpi value
     */
    public ArrayList<Pair<String, Double>> getNext(Instances data, int nextN){
        ArrayList<Pair<String, Double>> result = new ArrayList<Pair<String, Double>>();
        int oldSize = data.size();
        System.out.println("INSTANCES STARTS AT SIZE " + oldSize);
        InstancesFactory instanceAdder = new InstancesFactory();
        String[] yearsAdded = instanceAdder.addInstance(data, nextN);
        int newSize = data.size();
        for(int i = oldSize; i < newSize; i++){
            Instance currentRow = data.get(i);
            double nhpi = 0;
            try{
                nhpi = model.classifyInstance(currentRow);
                currentRow.setValue(1, nhpi);
                data.remove(i);
                data.add(i, currentRow);
                result.add(Pair.create(yearsAdded[i - oldSize], nhpi));
            } catch (Exception e1){
                System.out.println("Exception: Failed to classify last instance.");
            }
        }
        return result;
    }
    //https://medium.com/@rahulvaish/linear-regression-prediction-weka-way-3fdc1643e1b6
}