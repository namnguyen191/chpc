package chpc.visualizations;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import java.awt.*;

public class ConfigWindow {

  private static JInternalFrame innerWindow;
  // private JButton button1;
  // private JButton button2;
  private View parent;


  /**\
   *
   * @param parent it specifys the parent window of the inner window
   */
  public ConfigWindow(View parent) {
    this.parent = parent;
    // set up internal frame
    innerWindow = new JInternalFrame(
        "configure window", // title
        true, // resizable
        true, // closable
        true, // maximizable
        true // iconifiable
    );

    JPanel panel = new JPanel();

    //create a box

    // add data info panel to main panel
    panel.add(new DataSelectPanel(parent).getSelectPanel(), BorderLayout.CENTER);

    innerWindow.setContentPane(panel);
    innerWindow.setSize(500, 400);
    innerWindow.setLocation(50, 50);
    innerWindow.setVisible(true);

    innerWindow.addInternalFrameListener(new InternalFrameAdapter() {
      public void internalFrameClosing(InternalFrameEvent e) {
        ViewFactory.createView(parent.chartType);
        // when refresh view frame, the inner window will disappear automatically
      }
    });

  }

  public JInternalFrame getInnerWindow() {
    return innerWindow;
  }

}
