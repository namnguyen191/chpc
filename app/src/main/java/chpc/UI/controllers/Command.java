package chpc.UI.controllers;

import chpc.UI.views.MainUI;

/**
 * A class inherited to help add component to MainUI window
 */
public abstract class Command {
  public MainUI mainUI;

  /**
   *
   * @param mainUI the MainUI window that the component will add to
   */
  Command(MainUI mainUI) {
    this.mainUI = mainUI;
  }
  /**
   * add a component to a window
   */
  public abstract void execute();
}
