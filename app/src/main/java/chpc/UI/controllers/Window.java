package chpc.UI.controllers;

import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Window {
    public static JFrame makeFrame(){
        JFrame newWindow = new JFrame();
        newWindow.setAlwaysOnTop(true);

        return newWindow;
    }

    public static Object[] makeOptions(String[] inputOptions){
        Object[] options = new Object[inputOptions.length];
        int i = 0;
        for (String entry : inputOptions) {
        options[i] = entry;
        i++;
        }
        return options;
    }

    public static Object[] makeOptions(Set<String> geoOptions){
        Object[] options = new Object[geoOptions.size()];
        int i = 0;
        for (String geo : geoOptions) {
        options[i] = geo;
        i++;
        }
        return options;
    }

    public static String selectString(JFrame component, String message, String title, Object[] options){
        Object messageParam = message;
        Object selectObj = JOptionPane.showInputDialog(component, messageParam, title, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        String result = selectObj.toString();
        return result;
    }

    public static int selectInt(JFrame component, String message, String title){
        Object messageParam = message;
        Object selectObj = JOptionPane.showInputDialog(component, messageParam, title, JOptionPane.PLAIN_MESSAGE, null, null, 4);
        int nextN = Integer.parseInt(selectObj.toString());
        return nextN;
    }

    public static void showResult(String result) {
        JFrame resultWindow = makeFrame();
        JOptionPane.showMessageDialog(resultWindow, result);
    }
}
