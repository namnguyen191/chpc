package controller;

import model.Region;
import model.RegionDAO;
import model.RegionDAOImpl;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.ArrayList;
import java.util.List;

public class RegionData implements Subject {
    private List<View> views;
    //DefaultCategoryDataset dataset;

    public RegionData() {
        this.views =  new ArrayList<View>();
        //this.dataset = new DefaultCategoryDataset();
    }

    public void attach(View v) {
        views.add(v);
    }

    public void detach(View v){
        views.remove(v);
    }

    public void notifyViews(){
        for(View v : views){
            v.update(this);
        }
    }

    public void setDataSet(){
        RegionDAO dao = new RegionDAOImpl();
        List<Region> regions= dao.getRegions();
//        for (Region r :regions) {
//            dataset.setValue(r.getNHPI(),r.getRegionName(),r.getPeriod());
//        }
       // updateDataSet();

    }
    public void updateDataSet(){
        notifyViews();
    }

    public List<Region> getData() {
        RegionDAO dao = new RegionDAOImpl();
        List<Region> regions= dao.getRegions();

        return regions;
    }
}
