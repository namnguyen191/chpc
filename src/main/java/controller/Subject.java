package controller;

import java.util.ArrayList;
import java.util.List;

public interface Subject {
    public void attach(View v);
    public void detach(View v);
    public void notifyViews();

}
