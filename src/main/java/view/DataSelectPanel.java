package view;

import controller.View;
import controller.ViewFactory;
import model.RegionDAO;
import model.RegionDAOImplement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;

public class DataSelectPanel {
    static Box selectData;
    private View parent;



    public DataSelectPanel(){
        selectData = Box.createVerticalBox();
        setBox();
        //selectData.setLayout(new FlowLayout());

    }
    public DataSelectPanel(View parent){
        selectData = Box.createVerticalBox();
        this.parent = parent;
        setBox();
    }

    public void setBox(){
        JPanel selectRegion = new JPanel();
        JTextField region = new JTextField(10);
        //textField.getText()
        // Set top bar
        JLabel chooseCountryLabel = new JLabel("Choose a region: ");
        selectRegion.add(chooseCountryLabel);
        selectRegion.add(region);

        JPanel selectPeriod = new JPanel();
        JLabel fromYear = new JLabel("From");
        JLabel toYear = new JLabel("To");
        Vector<String> years = new Vector<>();
        for (int i = 2022; i >= 1981; i--) {
            years.add("" + i);
        }
        JComboBox<String> fromList = new JComboBox<>(years);
        JComboBox<String> toList = new JComboBox<>(years);
        //add month
        JLabel fromMonth = new JLabel();
        JLabel toMonth = new JLabel();
        Vector<String> monthes = new Vector<>();
        for (int i = 12; i >= 1; i--) {
            monthes.add("" + i);
        }
        JComboBox<String> fromListMonthes = new JComboBox<>(monthes);
        JComboBox<String> toListMonthes = new JComboBox<>(monthes);
        selectPeriod.add(fromYear);
        selectPeriod.add(fromList);
        selectPeriod.add(fromMonth);
        selectPeriod.add(fromListMonthes);
        selectPeriod.add(toYear);
        selectPeriod.add(toList);
        selectPeriod.add(toMonth);
        selectPeriod.add(toListMonthes);

        JButton loadData = new JButton("Load Data");
        loadData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String area = region.getText();
                String from_month = (String) fromListMonthes.getSelectedItem();
                String to_month = (String) toListMonthes.getSelectedItem();
                String from_year = (String) fromList.getSelectedItem();
                String to_year = (String) toList.getSelectedItem();
                String from = "";
                if(from_month.length() == 1){
                    from = from_year +"-0"+from_month;
                }else {
                    from = from_year +"-"+from_month;
                }
                String end;
                if(to_month.length() == 1){
                    end = to_year +"-0"+to_month;
                }else {
                    end = to_year +"-"+to_month;
                }
                System.out.printf("region: %s + from: %s to %s",area,from,end);

                RegionDAO dao = RegionDAOImplement.getInstance();
                dao.loadData(area,from,end);

                if(parent != null){
                    ViewFactory.createView(parent.chartType);
                    parent.dispose();
                }

//                if(parent.type.equals("Bar")){
//                    View b = Bar.getInstance();
//                    b.createChart(area,from,end );
//                }else if(parent.type.equals("Line")){
//                    View l = Line.getInstance();
//                    l.createChart(area,from,end );
//                }



            }
        });

        selectData.add(selectRegion);
        selectData.add(selectPeriod);
        selectData.add(loadData);

        selectData.add(loadData);
    }


    public Box getSelectPanel(){
        return selectData;
    }




}
