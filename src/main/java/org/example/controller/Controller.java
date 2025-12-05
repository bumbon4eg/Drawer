package org.example.controller;

import lombok.Getter;
import org.example.controller.action.AppAction;
import org.example.model.Model;
import org.example.view.MyFrame;
import org.example.view.MyPanel;

import java.awt.*;

public class Controller {
    private static Controller instance;
    private final Model model;
    private final MyFrame frame;
    private final MyPanel panel;
    @Getter
    private AppAction action;
    private final MenuController menu;
    private Controller() {
        model = new Model();
        frame = new MyFrame();

        menu = MenuController.getInstance(model);
        action = menu.getAction();
        frame.setJMenuBar(menu.getMenuBar());

        updateShapeFromMenu();

        panel = new MyPanel(this);

        model.addObserver(panel);
        frame.setPanel(panel);

        frame.revalidate();

    }

    public void updateShapeFromMenu() {
        model.setMyShape(menu.getSelectedShape());
    }

    public synchronized static Controller getInstance() {
        if (instance==null) {
            instance = new Controller();
        }
        return instance;
    }

    public void draw(Graphics2D g2) {
        action = menu.getAction();
        model.draw(g2);
    }
}
