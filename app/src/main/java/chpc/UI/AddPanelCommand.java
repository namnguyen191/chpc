package chpc.UI;

import javax.swing.JComponent;

public class AddPanelCommand extends Command {
  private JComponent panel;

  public AddPanelCommand(MainUI mainUI, JComponent panel) {
    super(mainUI);
    this.panel = panel;
  }

  @Override
  public void execute() {
    this.mainUI.mainContentPanel.add(this.panel);
    this.mainUI.revalidate();
  }
}
