package chpc.weka;

import chpc.models.NHPIRecord;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RecordSorter {
    
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

        return sortedRecords;
    }
}
