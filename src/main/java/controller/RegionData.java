package controller;

import model.Region;
import model.RegionDAO;
import model.RegionDAOImpl;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.ArrayList;
import java.util.List;

public class RegionData   {
    private List<View> views;
    List<Region> regions;
    //DefaultCategoryDataset dataset;

    public RegionData(RegionDAO dao) {
        this.views =  new ArrayList<View>();
        //this.dataset = new DefaultCategoryDataset();
        this.regions= dao.getRegions();
    }

    //add one observer
    public void attach(View v) {
        views.add(v);
    }

    //remove an observer
    public void detach(View v){
        views.remove(v);
    }

    //to update all observe
//    public void notifyViews(){
//        for(View v : views){
//            v.update(this);
//        }
//    }

    public void setDataSet(){
        RegionDAO dao = new RegionDAOImpl();
        List<Region> regions= dao.getRegions();
//        for (Region r :regions) {
//            dataset.setValue(r.getNHPI(),r.getRegionName(),r.getPeriod());
//        }
       // updateDataSet();

    }
//    public void updateDataSet(){
//        notifyViews();
//    }

    public List<Region> getData() {


        return regions;
    }
}
