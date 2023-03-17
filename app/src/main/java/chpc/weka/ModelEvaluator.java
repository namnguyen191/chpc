package chpc.weka;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import java.util.Random;
import weka.core.Instances;

public class ModelEvaluator {
    public void evaluate(Classifier model, Instances classifiedData){
        try{
            Evaluation eval = new Evaluation(classifiedData);
            eval.crossValidateModel(model, classifiedData, 10, new Random(1));
            System.out.println(eval.toSummaryString("\nResults\n======\n", false));
        } catch (Exception e1){
            System.out.println("Exception: Failed to cross validate model.");
        }
    }
}
