package chpc.prediction;

import chpc.UI.controllers.PopUpWindow;
import chpc.dataLoader.NHPIRecord;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.Pair;

import weka.core.Instances;

/**
 * Class containing prediction package classes meant to be used as a pipeline
 */
public class WekaModule {

  /**
   * Take a List of NHPI records and produce an ArrayList of Pairs: (String date, Double nhpi value)
   * @param inputList List of NHPI records meant to be used for prediction
   * @param n Positive integer representing the number of values the user wants to predict
   * @return ArrayList of Pairs of String and Doubles, where the String is the date in the future of the list, and the double is the predicted value for that date.
   */
  public ArrayList<Pair<String, Double>> MLFunc(List<NHPIRecord> inputList, int n) {
    InstancesFactory instancesFactory = new InstancesFactory();
    Instances data = instancesFactory.createInstances(inputList);
    ModelTrainer mTrainer = new LinearRegressionModel();
    mTrainer.buildModel(data);
    ModelEvaluator mEvaluator = new ModelEvaluator();
    int nextN = n;
    String result = mEvaluator.evaluate(mTrainer.getModel(), data);
    PopUpWindow.showResult(result);
    result += "\nPredictedValues: \n";
    ArrayList<Pair<String, Double>> predictedValues = mTrainer.getNext(data, nextN);

    return predictedValues;
  }

}