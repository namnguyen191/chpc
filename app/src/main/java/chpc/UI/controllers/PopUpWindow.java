package chpc.UI.controllers;

import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.commons.math3.util.Pair;

import chpc.visualizations.controllers.ViewFactory;

/**
 * Class for creating a new dialog window for selecting from loaded regions
 */
public class PopUpWindow {

  public static Pair<String, Integer> getPredictionChoice(String[] stringOptions) {
    JFrame choiceWindow = new JFrame();
    choiceWindow.setAlwaysOnTop(true);

    Object[] options = new Object[stringOptions.length];
    int i = 0;
    for (String entry : stringOptions) {
      options[i] = entry;
      i++;
    }
    Object message = "Please choose from among the regions you have loaded: ";
    Object selectObj = JOptionPane.showInputDialog(choiceWindow, message, "Prediction Selection",
        JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    String selectObjResult = selectObj.toString();

    Object message2 = "Please input a positive integer for the number of predictions you want to generate";
    int nextN = Integer.parseInt((JOptionPane.showInputDialog(choiceWindow, message2, "Prediction Number",
        JOptionPane.PLAIN_MESSAGE, null, null, 4)).toString());

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
    JFrame choiceWindow = new JFrame();
    choiceWindow.setAlwaysOnTop(true);

    Object[] options = new Object[loadedGeos.size()];
    int i = 0;
    for (String geo : loadedGeos) {
      options[i] = geo;
      i++;
    }
    Object message = "Please choose from among the regions you have loaded: ";
    Object selectObj = JOptionPane.showInputDialog(choiceWindow, message, "Prediction Selection",
        JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    String selectObjResult = selectObj.toString();

    return selectObjResult;
  }

  public static String getTitleWindow() {
    JFrame choiceWindow = new JFrame();
    choiceWindow.setAlwaysOnTop(true);

    String title = JOptionPane.showInputDialog("What do you want to call this prediction?");
    System.out.println(title);
    return title;
  }

  public static String getViewChoiceWindow() {
    JFrame choiceWindow = new JFrame();
    choiceWindow.setAlwaysOnTop(true);

    Object[] options = ViewFactory.AVAILABLE_CHARTS.toArray();
    Object message = "Please choose from among the following chart options: ";
    Object selectObj = JOptionPane.showInputDialog(choiceWindow, message, "Chart Selection", JOptionPane.PLAIN_MESSAGE,
        null, options, options[0]);
    String selectObjResult = selectObj.toString();

    return selectObjResult;
  }

  public static void showResult(String result) {
    JPanel container = new JPanel();
    JFrame resultWindow = new JFrame();
    resultWindow.add(container);
    resultWindow.setAlwaysOnTop(true);
    JOptionPane.showMessageDialog(container, result);
  }

  public static void main(String[] args) {
    String[] options = { "Linear Regression" };
    Pair<String, Integer> test = getPredictionChoice(options);

    System.out.println(test.getFirst() + ", " + test.getSecond());

    String title = getTitleWindow();
    System.out.println(title);
  }
}