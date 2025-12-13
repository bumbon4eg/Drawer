package org.example.controller.actions;

import java.awt.*;

public interface AppAction {
    void mousePressed(Point point);
    void mouseDragged(Point point);
    void mouseReleased();
    void execute();
    void unexecute();
    AppAction cloneAction();
    boolean hasShape();

}