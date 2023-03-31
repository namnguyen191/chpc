package chpc.views;

import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Class for creating a new dialog window for selecting from loaded regions
 */
public class PredictionSelectionWindow {

    /**
     * static method to create a window with drop down selection box of loaded regions
     * @param loadedGeos Set of region names that have been loaded in
     * @return user choice of region as a string
     */
    public static String getLoadedGeoChoice(Set<String> loadedGeos){
        JFrame choiceWindow = new JFrame();
        choiceWindow.setAlwaysOnTop(true);

        Object[] options = new Object[loadedGeos.size()];
        int i = 0;
        for(String geo : loadedGeos){
        options[i] = geo;
        i++;
        }
        Object message = "Please choose from among the regions you have loaded: ";
        Object selectObj = JOptionPane.showInputDialog(choiceWindow, message, "Prediction Selection", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        String selectObjResult = selectObj.toString();

        return selectObjResult;
    }
}
