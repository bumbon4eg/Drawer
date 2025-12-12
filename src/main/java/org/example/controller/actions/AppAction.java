package org.example.controller.actions;

import java.awt.*;

public interface AppAction {
    void mousePressed(Point point);
    void mouseDragged(Point point);

    void mouseReleased(Point point);

    void execute();
    void unexecute();
    AppAction cloneAction();

}