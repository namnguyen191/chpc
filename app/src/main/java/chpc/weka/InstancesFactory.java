package chpc.weka;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import chpc.dataLoader.NHPIRecord;

/**
 * Class for producing Instances object from a List of nhpi records
 */
public class InstancesFactory{

    /**
     * List of nhpi records to create Instances object
     */
    List<NHPIRecord> dataSet;

    /**
     * Create Instances object from list of nhpi records, containing dates and nhpi values
     * @param dataInput List of nhpi records
     * @return Instances object of dates and nhpi values, sorted by date in increasing order
     */
    public Instances createInstances(List<NHPIRecord> dataInput) {
        dataSet = RecordProcessor.sortRecords(dataInput);
        System.out.println("Sorted records: " + dataSet);
        ArrayList<Attribute> attributes = createAttributes();
        Instances data = new Instances("Dataset", attributes, 0);
        populateInstancesWithData(data, dataSet);
        if (data.classIndex() == -1) {
            data.setClassIndex(data.numAttributes() - 1);
        }
        return data;
    }

    private ArrayList<Attribute> createAttributes() {
        ArrayList<Attribute> attributes = new ArrayList<Attribute>();
        attributes.add(new Attribute("date", "yyyy-MM"));
        attributes.add(new Attribute("NHPI value"));
        return attributes;
    }

    private void populateInstancesWithData(Instances data, List<NHPIRecord> dataSet) {
        double[] row;
        for (NHPIRecord nhpiRecord : dataSet) {
            row = new double[data.numAttributes()];
            addRecordToInstances(data, nhpiRecord, row);
        }
    }

    private void addRecordToInstances(Instances data, NHPIRecord nhpiRecord, double[] row) {
        String date = nhpiRecord.getRefDate();
        try {
            row[0] = data.attribute(0).parseDate(date);
        } catch (ParseException pe1) {
            System.out.println("Error! ParseException: " + date + " at " + pe1.getErrorOffset());
        }

        row[1] = nhpiRecord.getValue();
        data.add(new DenseInstance(1.0, row));
    }



    public String[] addInstance(Instances dataSet, int numToAdd){
        double[] row;

        final int START_YEAR = 2023;

        String[] yearsAdded = new String[numToAdd];

        for(int i = 0; i < numToAdd; i++){
            row = new double[dataSet.numAttributes()];
            int year = START_YEAR + Math.floorDiv(i, 12);
            int month = (i % 12) + 1;
            String date = year + "-" + month;
            yearsAdded[i] = date;
            //System.out.println(date);

            try{
                row[0] = dataSet.attribute(0).parseDate(date);
            } catch (ParseException pe1){
                System.out.println("Error! ParseException: " + date + " at " + pe1.getErrorOffset());
            }

            row[1] = 0;
            dataSet.add(new DenseInstance(1.0, row));
        }

        return yearsAdded;
        //System.out.println(dataSet);
    }
}