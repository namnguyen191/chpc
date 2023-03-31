package chpc.weka;

import chpc.models.NHPIRecord;
import java.util.List;
import weka.core.Instances;

public class WekaModule {

    public void MLFunc(List<NHPIRecord> inputList){
        InstancesFactory instancesFactory = new InstancesFactory();
        Instances data = instancesFactory.createInstances(inputList);
        ModelTrainer mTrainer = new ModelTrainer();
        mTrainer.buildModel(data);
        ModelEvaluator mEvaluator = new ModelEvaluator();
        mEvaluator.evaluate(mTrainer.getModel(), data);
        mTrainer.getNext(data);
    }
    
}
