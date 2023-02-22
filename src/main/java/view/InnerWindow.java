package view;

import controller.Bar;
import controller.Line;
import controller.View;
import controller.ViewFactory;
import javafx.scene.Parent;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import static com.sun.java.accessibility.util.SwingEventMonitor.addInternalFrameListener;

public class InnerWindow {

    private static JInternalFrame innerWindow;
//    private JButton button1;
//    private JButton button2;
    private View parent;

    public InnerWindow(View parent) {
        this.parent = parent;
        //set up internal frame
        innerWindow = new JInternalFrame(
                "configure window",  // title
                true,       // resizable
                true,       // closable
                true,       // maximizable
                true        // iconifiable
        );



        JPanel panel = new JPanel();

//        JButton button1 = new JButton("Cancel");
//        JButton button2 = new JButton("Load Data");

        //add  data info panel to main panel
        panel.add(new DataSelectPanel(parent).getSelectPanel(),BorderLayout.CENTER);
       // panel.add(button1);
//        panel.add(button2);



        innerWindow.setContentPane(panel);
        innerWindow.setSize(500, 400);
        innerWindow.setLocation(50, 50);
        innerWindow.setVisible(true);


        innerWindow.addInternalFrameListener(new InternalFrameAdapter() {
            public void internalFrameClosing(InternalFrameEvent e) {
                ViewFactory.createView(parent.chartType);
                //when refresh view frame, the inner window will disappear automatically

            }
        });

    }

    public JInternalFrame getInnerWindow(){
        return innerWindow;
    }


}
