package chpc.prediction;

import chpc.dataLoader.NHPIRecord;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.math3.util.Pair;

/**
 * A class for processing NHPI records. Sorts and removes missing values.
 */

public class RecordProcessor {
    
    /**
     * Sort list of methods by increasing date. Returns a new sorted list of NHPI records.
     * 
     * @param inputList the list of nhpi records meant to be sorted  
     */
    public static List<NHPIRecord> sortRecords(List<NHPIRecord> inputList){
        List<NHPIRecord> sortedRecords = new ArrayList<NHPIRecord>(inputList);

        Comparator<NHPIRecord> comparator = new Comparator<NHPIRecord>(){
            public int compare(NHPIRecord a, NHPIRecord b){
                if(!(a.getGeo()).equals(b.getGeo())){
                    return a.getGeo().compareTo(b.getGeo());
                }
                else if(!(a.getRefDate()).equals(b.getRefDate())){
                    return a.getRefDate().compareTo(b.getRefDate());
                }
                else if(!(a.getValue()==b.getValue())){
                    return (a.getValue() < b.getValue()) ? -1 : 1;
                }
                else {
                    return 0;
                }
            }
        };
        Collections.sort(sortedRecords, comparator);
        removeMissingValues(sortedRecords);
        return sortedRecords;
    }

    /**
     * Remove all nhpi records from list that have missing values
     * 
     * @param inputList
     */
    public static void removeMissingValues(List<NHPIRecord> inputList){
        inputList.removeIf(p -> p.getValue() == -1);
    }

    public static void addPredictedValues(List<NHPIRecord> inputList, String geo, List<Pair<String, Double>> predictedValues){
        for(Pair<String, Double> pair : predictedValues){
            NHPIRecord entry = new NHPIRecord(geo, pair.getFirst(), pair.getSecond());
            inputList.add(entry);
        }
    }
}