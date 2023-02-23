package chpc.controllers;

import chpc.views.MainUI;

public abstract class Command {
  public MainUI mainUI;

  Command(MainUI mainUI) {
    this.mainUI = mainUI;
  }

  public abstract void execute();
}
