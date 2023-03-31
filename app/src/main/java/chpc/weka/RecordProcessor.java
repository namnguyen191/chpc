package chpc.weka;

import chpc.models.NHPIRecord;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
                String aGeo = a.getGeo();
                String bGeo = b.getGeo();
                String aDate = a.getRefDate();
                String bDate = b.getRefDate();
                Double aValue = a.getValue();
                Double bValue = b.getValue();

                if(!aGeo.equals(bGeo)){
                    return aGeo.compareTo(bGeo);
                }
                else if(!aDate.equals(bDate)){
                    return aDate.compareTo(bDate);
                }
                else if(!(aValue==bValue)){
                    return (aValue < bValue) ? -1 : 1;
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
}
