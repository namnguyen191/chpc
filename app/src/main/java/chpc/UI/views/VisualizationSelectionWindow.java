package chpc.UI.views;

import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Class for creating a new dialog window for selecting a group from loaded
 * data to be displayed in
 * visualization
 */
public class VisualizationSelectionWindow {

  /**
   * static method to create a window with drop down selection box of loaded
   * group data
   * 
   * @param loadedGeos Set of group names that have been loaded in
   * @return user choice of group as a string
   */
  public static String getLoadedGeoChoice(Set<String> loadedGroups) {
    JFrame choiceWindow = new JFrame();
    choiceWindow.setAlwaysOnTop(true);

    Object[] options = new Object[loadedGroups.size()];
    int i = 0;
    for (String geo : loadedGroups) {
      options[i] = geo;
      i++;
    }
    Object message = "Please choose from among the group you have loaded: ";
    Object selectObj = JOptionPane.showInputDialog(choiceWindow, message, "Visualization Selection",
        JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    String selectObjResult = selectObj.toString();

    return selectObjResult;
  }
}