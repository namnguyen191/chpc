package chpc.prediction;

import java.util.ArrayList;

import org.apache.commons.math3.util.Pair;

import weka.classifiers.Classifier;
import weka.core.Instances;

/**
 * Interface for models
 * Any class implementing this can be used for model prediction.
 * So different classifiers can be swapped in and out.
 */
public interface ModelTrainer {
    public void buildModel(Instances data);

    public Classifier getModel();
    
    public ArrayList<Pair<String, Double>> getNext(Instances data, int nextN);
}
