package chpc.UI.controllers;

import chpc.UI.views.MainUI;

import javax.swing.JComponent;

/**
 * A class used to help add component to MainUI window
 */
public class AddPanelCommand extends Command {
  private JComponent panel;

  /**
   *
   * @param mainUI the MainUI window that the component will add to
   * @param panel the panel component that will be added to the window
   */
  public AddPanelCommand(MainUI mainUI, JComponent panel) {
    super(mainUI);
    this.panel = panel;
  }

  /**
   * add a panel to the MainUI
   */
  @Override
  public void execute() {
    this.mainUI.mainContentPanel.add(this.panel);
    this.mainUI.revalidate();
  }
}
