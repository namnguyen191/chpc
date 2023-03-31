package chpc.database;

import chpc.UI.MainUI;

public abstract class Command {
  public MainUI mainUI;

  Command(MainUI mainUI) {
    this.mainUI = mainUI;
  }

  public abstract void execute();
}
