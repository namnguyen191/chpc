package chpc.prediction;

import java.util.ArrayList;

import org.apache.commons.math3.util.Pair;

import weka.classifiers.Classifier;
import weka.core.Instances;

public interface ModelTrainer {
    public void buildModel(Instances data);

    public Classifier getModel();
    
    public ArrayList<Pair<String, Double>> getNext(Instances data, int nextN);
}
