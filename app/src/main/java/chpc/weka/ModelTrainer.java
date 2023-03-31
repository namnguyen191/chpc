package chpc.weka;
import weka.classifiers.functions.LinearRegression;
import weka.core.Instance;
import weka.core.Instances;

/**
 * A class for training the model and using it.
 */
public class ModelTrainer {

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
    public double getNext(Instances data){
        Instance lastRow = data.lastInstance();
        double nhpi = 0;
        try{
            nhpi = model.classifyInstance(lastRow);
            System.out.println("Next NHPI: " + nhpi);
        } catch (Exception e1){
            System.out.println("Exception: Failed to classify last instance.");
        }
        return nhpi;
    }
    //https://medium.com/@rahulvaish/linear-regression-prediction-weka-way-3fdc1643e1b6
}
