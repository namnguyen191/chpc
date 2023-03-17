package chpc.weka;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import chpc.models.NHPIRecord;

public class InstancesFactory{

    List<NHPIRecord> dataSet;

    public Instances createInstances(List<NHPIRecord> dataInput){
        dataSet = RecordSorter.sortRecords(dataInput);

        System.out.println("Sorted records: " + dataSet);

        ArrayList<Attribute> attributes = new ArrayList<Attribute>();
        attributes.add(new Attribute("date", "yyyy-MM"));
        attributes.add(new Attribute("NHPI value"));

        Instances data = new Instances("Dataset", attributes, 0);

        double[] row;

        for(NHPIRecord nhpiRecord : dataSet){
            row = new double[data.numAttributes()];
            String date = nhpiRecord.getRefDate();
            try{
                row[0] = data.attribute(0).parseDate(date);
            } catch (ParseException pe1){
                System.out.println("Error! ParseException: " + date + " at " + pe1.getErrorOffset());
            }

            row[1] = nhpiRecord.getValue();
            data.add(new DenseInstance(1.0, row));
        }
        if(data.classIndex() == -1){
            data.setClassIndex(data.numAttributes() - 1);
        }
        System.out.println(data);
        return data;
    }
}