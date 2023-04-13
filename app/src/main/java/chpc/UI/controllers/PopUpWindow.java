package chpc.UI.controllers;

import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.math3.util.Pair;

import chpc.visualizations.controllers.ViewFactory;

/**
 * Class for creating a new dialog window for selecting from loaded regions
 */
public class PopUpWindow extends Window {

  public static Pair<String, Integer> getPredictionChoice(String[] stringOptions) {
    JFrame choiceWindow = makeFrame();

    Object[] options = makeOptions(stringOptions);
    String selectObjResult = selectString(choiceWindow, "Please choose from among the regions you have loaded: ", "Prediction Selection", options);
    int nextN = selectInt(choiceWindow, "Please input a positive integer for the number of predictions you want to generate", "Prediction Number");

    System.out.println(selectObjResult + " " + nextN);
    Pair<String, Integer> result = Pair.create(selectObjResult, nextN);
    return result;
  }

  /**
   * static method to create a window with drop down selection box of loaded
   * regions
   * 
   * @param loadedGeos Set of region names that have been loaded in
   * @return user choice of region as a string
   */
  public static String getChoiceWindow(Set<String> loadedGeos) {
    JFrame choiceWindow = makeFrame();

    Object[] options = makeOptions(loadedGeos);
    String selectObjResult = selectString(choiceWindow, "Please choose from among the regions you have loaded: ", "Prediction Selection", options);

    return selectObjResult;
  }

  public static String getTitleWindow() {
    String title = JOptionPane.showInputDialog("What do you want to call this prediction?");
    return title;
  }

  public static String getViewChoiceWindow() {
    JFrame choiceWindow = makeFrame();

    Object[] options = ViewFactory.AVAILABLE_CHARTS.toArray();
    String selectObjResult = selectString(choiceWindow, "Please choose from among the following chart options: ", "Chart Selection", options);

    return selectObjResult;
  }
}