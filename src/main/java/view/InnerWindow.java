package view;

import controller.Bar;
import controller.Line;
import controller.View;
import javafx.scene.Parent;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;
import static com.sun.java.accessibility.util.SwingEventMonitor.addInternalFrameListener;

public class InnerWindow {

    private static JInternalFrame innerWindow;
    private JButton button1;
    private JButton button2;
    private View parent;

    public InnerWindow(View parent) {
        this.parent = parent;
        innerWindow = new JInternalFrame(
                "configure window",  // title
                true,       // resizable
                true,       // closable
                true,       // maximizable
                true        // iconifiable
        );



        innerWindow.setSize(500, 400);
        innerWindow.setLocation(50, 50);

        JPanel panel = new JPanel();

        JButton button1 = new JButton("Cancel");
        JButton button2 = new JButton("Load Data");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // handle button 1 click
                //mainPanel.updateUI();
                cancel();

            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // handle button 2 click
            }
        });
        panel.add(new DataSelectPanel().getSelectPanel(),BorderLayout.CENTER);
        panel.add(button1);
        panel.add(button2);

//        Box vBox = Box.createVerticalBox();
//        vBox.add(panel01);
//        vBox.add(panel02);
//        vBox.add(panel03);

        innerWindow.setContentPane(panel);
        //innerWindow.pack();

        /*
         * another way to set up panel
         *     internalFrame.setLayout(new FlowLayout());
         *     internalFrame.add(new JLabel("Label001"));
         *     internalFrame.add(new JButton("JButton001"));
         */

        innerWindow.setVisible(true);

        addInternalFrameListener(new InternalFrameAdapter() {
            public void internalFrameClosing(InternalFrameEvent e) {
                //parent.repaint();
                //mainPanel.updateUI();
                //View.super.repaint();
                //parent = Bar.getInstance();
                innerWindow.dispose();

            }
        });



    }

    public JInternalFrame getInnerWindow(){
        return innerWindow;
    }

    public void loadData(){

    }

    public void cancel(){
        if(parent.type.equals("Bar")){
            View b = Bar.getInstance();
            b.createChart( );
        }else if(parent.type.equals("Line")){
            View l = Line.getInstance();
            l.createChart();
        }
    }

}
