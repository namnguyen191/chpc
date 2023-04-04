package chpc.prediction;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import java.util.Random;
import weka.core.Instances;

/**
 * Evaluate a model with training data using cross validation method
 */
public class ModelEvaluator {

    /**
     * Take a trained model and data used for training and evaluate model's metrics
     * @param model Classifier object that has already been trained
     * @param classifiedData Instances object used to train the classifier
     */
    public String evaluate(Classifier model, Instances classifiedData){
        try{
            Evaluation eval = new Evaluation(classifiedData);
            eval.crossValidateModel(model, classifiedData, 4, new Random(1));
            //System.out.println(eval.toSummaryString("\nResults\n======\n", false));
            String result = eval.toSummaryString("\nResults\n======\n", false);
            return result;
        } catch (Exception e1){
            System.out.println("Exception: Failed to cross validate model.");
        }

        return "Evaluation failed!";
    }
}