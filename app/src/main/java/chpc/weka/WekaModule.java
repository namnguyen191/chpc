package chpc.weka;

import chpc.UI.controllers.PopUpWindow;
import chpc.dataLoader.NHPIRecord;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.Pair;

import weka.core.Instances;

public class WekaModule {

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